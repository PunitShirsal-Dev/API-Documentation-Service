package com.spunit.apidocs.controller.v1;

import com.spunit.apidocs.annotation.ApiDeprecated;
import com.spunit.apidocs.dto.ApiResponse;
import com.spunit.apidocs.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * API Version 1 Controller - DEPRECATED
 * This version is deprecated and will be removed in 180 days
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Tag(name = "API v1 (Deprecated)", description = "Version 1 APIs - Deprecated, please migrate to v3")
@Deprecated
@ApiDeprecated(
        since = "2025-01-01",
        sunsetDate = "2026-09-01",
        replacement = "/api/v3",
        migrationGuide = "https://api.example.com/migration/v1-to-v3",
        reason = "Enhanced security and performance improvements in v3"
)
public class UserControllerV1 {

    @GetMapping("/users")
    @Operation(
            summary = "Get all users (v1) - DEPRECATED",
            description = "Returns a list of all users. This endpoint is deprecated, please use /api/v3/users",
            deprecated = true
    )
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        log.info("V1 API called: GET /api/v1/users");

        List<UserDto> users = Arrays.asList(
                UserDto.builder()
                        .id(1L)
                        .username("john_doe")
                        .email("john@example.com")
                        .firstName("John")
                        .lastName("Doe")
                        .build(),
                UserDto.builder()
                        .id(2L)
                        .username("jane_smith")
                        .email("jane@example.com")
                        .firstName("Jane")
                        .lastName("Smith")
                        .build()
        );

        return ResponseEntity.ok(ApiResponse.success(users, "Users retrieved successfully", "v1"));
    }

    @GetMapping("/users/{id}")
    @Operation(
            summary = "Get user by ID (v1) - DEPRECATED",
            description = "Returns a user by ID. This endpoint is deprecated, please use /api/v3/users/{id}",
            deprecated = true
    )
    public ResponseEntity<ApiResponse<UserDto>> getUserById(
            @Parameter(description = "User ID") @PathVariable Long id) {
        log.info("V1 API called: GET /api/v1/users/{}", id);

        UserDto user = UserDto.builder()
                .id(id)
                .username("user_" + id)
                .email("user" + id + "@example.com")
                .firstName("User")
                .lastName("" + id)
                .build();

        return ResponseEntity.ok(ApiResponse.success(user, "User retrieved successfully", "v1"));
    }

    @PostMapping("/users")
    @Operation(
            summary = "Create user (v1) - DEPRECATED",
            description = "Creates a new user. This endpoint is deprecated, please use /api/v3/users",
            deprecated = true
    )
    public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody UserDto userDto) {
        log.info("V1 API called: POST /api/v1/users");
        userDto.setId(System.currentTimeMillis());
        return ResponseEntity.ok(ApiResponse.success(userDto, "User created successfully", "v1"));
    }
}

