package com.spunit.apidocs.controller.v2;

import com.spunit.apidocs.dto.ApiResponse;
import com.spunit.apidocs.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * API Version 2 Controller - STABLE
 * Current stable version with enhanced features
 */
@Slf4j
@RestController
@RequestMapping("/api/v2")
@Tag(name = "API v2 (Stable)", description = "Version 2 APIs - Current stable version")
public class UserControllerV2 {

    @GetMapping("/users")
    @Operation(
            summary = "Get all users (v2)",
            description = "Returns a paginated list of all users with enhanced filtering"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved users",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers(
            @Parameter(description = "Filter by status") @RequestParam(required = false) String status) {
        log.info("V2 API called: GET /api/v2/users with status={}", status);

        List<UserDto> users = Arrays.asList(
                UserDto.builder()
                        .id(1L)
                        .username("john_doe")
                        .email("john@example.com")
                        .firstName("John")
                        .lastName("Doe")
                        .status("active")
                        .build(),
                UserDto.builder()
                        .id(2L)
                        .username("jane_smith")
                        .email("jane@example.com")
                        .firstName("Jane")
                        .lastName("Smith")
                        .status("active")
                        .build()
        );

        return ResponseEntity.ok(ApiResponse.success(users, "Users retrieved successfully", "v2"));
    }

    @GetMapping("/users/{id}")
    @Operation(
            summary = "Get user by ID (v2)",
            description = "Returns a user by ID with additional metadata"
    )
    public ResponseEntity<ApiResponse<UserDto>> getUserById(
            @Parameter(description = "User ID") @PathVariable Long id) {
        log.info("V2 API called: GET /api/v2/users/{}", id);

        UserDto user = UserDto.builder()
                .id(id)
                .username("user_" + id)
                .email("user" + id + "@example.com")
                .firstName("User")
                .lastName("" + id)
                .status("active")
                .build();

        return ResponseEntity.ok(ApiResponse.success(user, "User retrieved successfully", "v2"));
    }

    @PostMapping("/users")
    @Operation(
            summary = "Create user (v2)",
            description = "Creates a new user with validation"
    )
    public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody UserDto userDto) {
        log.info("V2 API called: POST /api/v2/users");
        userDto.setId(System.currentTimeMillis());
        userDto.setStatus("active");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(userDto, "User created successfully", "v2"));
    }

    @PutMapping("/users/{id}")
    @Operation(
            summary = "Update user (v2)",
            description = "Updates an existing user"
    )
    public ResponseEntity<ApiResponse<UserDto>> updateUser(
            @Parameter(description = "User ID") @PathVariable Long id,
            @RequestBody UserDto userDto) {
        log.info("V2 API called: PUT /api/v2/users/{}", id);
        userDto.setId(id);
        return ResponseEntity.ok(ApiResponse.success(userDto, "User updated successfully", "v2"));
    }

    @DeleteMapping("/users/{id}")
    @Operation(
            summary = "Delete user (v2)",
            description = "Deletes a user by ID"
    )
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @Parameter(description = "User ID") @PathVariable Long id) {
        log.info("V2 API called: DELETE /api/v2/users/{}", id);
        return ResponseEntity.ok(ApiResponse.success(null, "User deleted successfully", "v2"));
    }
}

