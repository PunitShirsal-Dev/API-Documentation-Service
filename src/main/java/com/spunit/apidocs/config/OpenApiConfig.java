package com.spunit.apidocs.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI Configuration for API Documentation
 * Configures Swagger UI and API documentation with versioning support
 */
@Configuration
public class OpenApiConfig {

    @Value("${api.info.title:API Documentation}")
    private String apiTitle;

    @Value("${api.info.description:API Documentation Service}")
    private String apiDescription;

    @Value("${api.info.version:1.0.0}")
    private String apiVersion;

    @Value("${api.info.contact.name:API Support}")
    private String contactName;

    @Value("${api.info.contact.email:support@example.com}")
    private String contactEmail;

    @Value("${api.info.contact.url:https://example.com}")
    private String contactUrl;

    @Value("${api.info.license.name:Apache 2.0}")
    private String licenseName;

    @Value("${api.info.license.url:https://www.apache.org/licenses/LICENSE-2.0.html}")
    private String licenseUrl;

    @Value("${api.info.terms-of-service:https://example.com/terms}")
    private String termsOfService;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(apiTitle)
                        .description(apiDescription)
                        .version(apiVersion)
                        .contact(new Contact()
                                .name(contactName)
                                .email(contactEmail)
                                .url(contactUrl))
                        .license(new License()
                                .name(licenseName)
                                .url(licenseUrl))
                        .termsOfService(termsOfService))
                .externalDocs(new ExternalDocumentation()
                        .description("API Migration Guide")
                        .url("https://api.example.com/migration"))
                .servers(List.of(
                        new Server().url("http://localhost:8095").description("Local Development Server"),
                        new Server().url("http://localhost:8080").description("API Gateway"),
                        new Server().url("https://api.example.com").description("Production Server")
                ));
    }

    @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder()
                .group("v1-apis")
                .pathsToMatch("/api/v1/**")
                .displayName("API Version 1 (Deprecated)")
                .build();
    }

    @Bean
    public GroupedOpenApi apiV2() {
        return GroupedOpenApi.builder()
                .group("v2-apis")
                .pathsToMatch("/api/v2/**")
                .displayName("API Version 2 (Stable)")
                .build();
    }

    @Bean
    public GroupedOpenApi apiV3() {
        return GroupedOpenApi.builder()
                .group("v3-apis")
                .pathsToMatch("/api/v3/**")
                .displayName("API Version 3 (Latest)")
                .build();
    }

    @Bean
    public GroupedOpenApi deprecatedApi() {
        return GroupedOpenApi.builder()
                .group("deprecated-apis")
                .pathsToMatch("/api/deprecated/**")
                .displayName("Deprecated APIs")
                .build();
    }

    @Bean
    public GroupedOpenApi allApis() {
        return GroupedOpenApi.builder()
                .group("all-apis")
                .pathsToMatch("/api/**")
                .displayName("All APIs")
                .build();
    }

    @Bean
    public GroupedOpenApi actuatorApi() {
        return GroupedOpenApi.builder()
                .group("actuator")
                .pathsToMatch("/actuator/**")
                .pathsToExclude("/actuator/health/**")
                .displayName("Actuator Endpoints")
                .build();
    }
}

