package com.spunit.apidocs.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Filter to add deprecation headers to deprecated API endpoints
 * Implements RFC 8594 - The Sunset HTTP Header Field
 */
@Slf4j
public class ApiDeprecationFilter implements Filter {

    private static final String DEPRECATION_HEADER = "Deprecation";
    private static final String SUNSET_HEADER = "Sunset";
    private static final String LINK_HEADER = "Link";
    private static final String WARNING_HEADER = "Warning";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestUri = httpRequest.getRequestURI();

        // Check if the endpoint is deprecated
        if (isDeprecatedEndpoint(requestUri)) {
            addDeprecationHeaders(httpResponse, requestUri);
            log.warn("Deprecated API endpoint accessed: {} from IP: {}",
                    requestUri, httpRequest.getRemoteAddr());
        }

        chain.doFilter(request, response);
    }

    private boolean isDeprecatedEndpoint(String uri) {
        // Check for explicitly deprecated endpoints
        if (uri.contains("/api/deprecated/") || uri.contains("/api/v1/")) {
            return true;
        }
        return false;
    }

    private void addDeprecationHeaders(HttpServletResponse response, String uri) {
        // RFC 8594: Deprecation header (true indicates deprecated)
        response.addHeader(DEPRECATION_HEADER, "true");

        // Calculate sunset date (180 days from now)
        ZonedDateTime sunsetDate = ZonedDateTime.now().plusDays(180);
        String formattedSunsetDate = sunsetDate.format(DateTimeFormatter.RFC_1123_DATE_TIME);
        response.addHeader(SUNSET_HEADER, formattedSunsetDate);

        // Add Link header to migration guide
        String migrationUrl = "https://api.example.com/migration";
        response.addHeader(LINK_HEADER, String.format("<%s>; rel=\"deprecation\"; type=\"text/html\"", migrationUrl));

        // Add Warning header (199 is miscellaneous persistent warning)
        String warningMessage = String.format("199 - \"This API endpoint is deprecated and will be removed on %s. " +
                "Please migrate to the latest version. See %s for details.\"", formattedSunsetDate, migrationUrl);
        response.addHeader(WARNING_HEADER, warningMessage);

        // Custom headers for additional information
        response.addHeader("X-API-Deprecation-Info", "This endpoint is scheduled for removal");
        response.addHeader("X-API-Deprecation-Replacement", getReplacementEndpoint(uri));
    }

    private String getReplacementEndpoint(String deprecatedUri) {
        // Map deprecated endpoints to their replacements
        if (deprecatedUri.contains("/api/v1/")) {
            return deprecatedUri.replace("/api/v1/", "/api/v3/");
        } else if (deprecatedUri.contains("/api/deprecated/")) {
            return deprecatedUri.replace("/api/deprecated/", "/api/v3/");
        }
        return "/api/v3/";
    }
}

