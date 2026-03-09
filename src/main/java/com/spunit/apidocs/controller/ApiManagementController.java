package com.spunit.apidocs.controller;

import com.spunit.apidocs.dto.ApiResponse;
import com.spunit.apidocs.dto.DeprecationInfo;
import com.spunit.apidocs.service.ApiDeprecationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API Management Controller
 * Provides endpoints to manage and view API deprecation information
 */
@Slf4j
@RestController
@RequestMapping("/api/management")
@RequiredArgsConstructor
@Tag(name = "API Management", description = "API lifecycle and deprecation management endpoints")
public class ApiManagementController {

    private final ApiDeprecationService apiDeprecationService;

    @GetMapping("/deprecations")
    @Operation(
            summary = "Get all deprecated APIs",
            description = "Returns a list of all deprecated API endpoints with their deprecation details"
    )
    public ResponseEntity<ApiResponse<List<DeprecationInfo>>> getDeprecatedApis() {
        log.info("Fetching all deprecated APIs");
        List<DeprecationInfo> deprecations = apiDeprecationService.getAllDeprecatedApis();
        return ResponseEntity.ok(ApiResponse.success(deprecations,
                "Deprecated APIs retrieved successfully", "v3"));
    }

    @GetMapping("/versions")
    @Operation(
            summary = "Get all API versions",
            description = "Returns information about all supported API versions"
    )
    public ResponseEntity<ApiResponse<List<String>>> getSupportedVersions() {
        log.info("Fetching supported API versions");
        List<String> versions = List.of("v1 (deprecated)", "v2 (stable)", "v3 (latest)");
        return ResponseEntity.ok(ApiResponse.success(versions,
                "API versions retrieved successfully", "v3"));
    }
}

