package com.spunit.apidocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * API Documentation Service Application
 * Provides comprehensive API documentation with versioning, contract testing, and deprecation management
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiDocumentationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDocumentationServiceApplication.class, args);
    }
}

