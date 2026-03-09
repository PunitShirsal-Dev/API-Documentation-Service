package com.spunit.apidocs.service;

import com.spunit.apidocs.dto.DeprecationInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for managing API deprecation information
 */
@Slf4j
@Service
public class ApiDeprecationService {

    /**
     * Get all deprecated API endpoints
     */
    public List<DeprecationInfo> getAllDeprecatedApis() {
        List<DeprecationInfo> deprecations = new ArrayList<>();

        // V1 APIs - All deprecated
        deprecations.add(DeprecationInfo.builder()
                .endpoint("/api/v1/users")
                .version("v1")
                .deprecated(true)
                .deprecatedSince("2025-01-01")
                .sunsetDate("2026-09-01")
                .replacement("/api/v3/users")
                .migrationGuide("https://api.example.com/migration/v1-to-v3")
                .reason("Enhanced security and performance improvements in v3")
                .lastChecked(LocalDateTime.now())
                .build());

        deprecations.add(DeprecationInfo.builder()
                .endpoint("/api/v1/users/{id}")
                .version("v1")
                .deprecated(true)
                .deprecatedSince("2025-01-01")
                .sunsetDate("2026-09-01")
                .replacement("/api/v3/users/{id}")
                .migrationGuide("https://api.example.com/migration/v1-to-v3")
                .reason("Enhanced security and performance improvements in v3")
                .lastChecked(LocalDateTime.now())
                .build());

        log.info("Retrieved {} deprecated APIs", deprecations.size());
        return deprecations;
    }

    /**
     * Check if an endpoint is deprecated
     */
    public boolean isDeprecated(String endpoint) {
        return endpoint.contains("/api/v1/") || endpoint.contains("/api/deprecated/");
    }

    /**
     * Get deprecation info for a specific endpoint
     */
    public DeprecationInfo getDeprecationInfo(String endpoint) {
        List<DeprecationInfo> all = getAllDeprecatedApis();
        return all.stream()
                .filter(info -> info.getEndpoint().equals(endpoint))
                .findFirst()
                .orElse(null);
    }
}

