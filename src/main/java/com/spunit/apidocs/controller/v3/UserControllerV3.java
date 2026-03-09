package com.spunit.apidocs.controller.v3;

import com.spunit.apidocs.dto.ApiResponse;
import com.spunit.apidocs.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * API Version 3 Controller - LATEST
 * Latest version with enhanced security, performance, and features
 */
@Slf4j
@RestController
@RequestMapping("/api/v3")
@Tag(name = "API v3 (Latest)", description = "Version 3 APIs - Latest version with all features")
public class UserControllerV3 {

    @GetMapping("/users")
    @Operation(
            summary = "Get all users (v3)",
            description = "Returns a paginated list of all users with advanced filtering, sorting, and search capabilities",
            security = @SecurityRequirement(name = "bearer-jwt")
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved users",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid parameters"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            )
    })
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers(
            @Parameter(description = "Filter by status") @RequestParam(required = false) String status,
            @Parameter(description = "Search query") @RequestParam(required = false) String search,
            @Parameter(description = "Sort field") @RequestParam(required = false, defaultValue = "id") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(required = false, defaultValue = "asc") String sortDir,
            @Parameter(description = "Page number") @RequestParam(required = false, defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(required = false, defaultValue = "10") int size) {

        log.info("V3 API called: GET /api/v3/users with status={}, search={}, sortBy={}, sortDir={}, page={}, size={}",
                status, search, sortBy, sortDir, page, size);

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
                        .build(),
                UserDto.builder()
                        .id(3L)
                        .username("bob_wilson")
                        .email("bob@example.com")
                        .firstName("Bob")
                        .lastName("Wilson")
                        .status("inactive")
                        .build()
        );

        return ResponseEntity.ok(ApiResponse.success(users, "Users retrieved successfully", "v3"));
    }

    @GetMapping("/users/{id}")
    @Operation(
            summary = "Get user by ID (v3)",
            description = "Returns a user by ID with complete profile information",
            security = @SecurityRequirement(name = "bearer-jwt")
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved user"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    public ResponseEntity<ApiResponse<UserDto>> getUserById(
            @Parameter(description = "User ID", required = true) @PathVariable Long id) {
        log.info("V3 API called: GET /api/v3/users/{}", id);

        UserDto user = UserDto.builder()
                .id(id)
                .username("user_" + id)
                .email("user" + id + "@example.com")
                .firstName("User")
                .lastName("" + id)
                .status("active")
                .build();

        return ResponseEntity.ok(ApiResponse.success(user, "User retrieved successfully", "v3"));
    }

    @PostMapping("/users")
    @Operation(
            summary = "Create user (v3)",
            description = "Creates a new user with comprehensive validation and security checks",
            security = @SecurityRequirement(name = "bearer-jwt")
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "User created successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "User already exists"
            )
    })
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Valid @RequestBody UserDto userDto) {
        log.info("V3 API called: POST /api/v3/users");
        userDto.setId(System.currentTimeMillis());
        userDto.setStatus("active");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(userDto, "User created successfully", "v3"));
    }

    @PutMapping("/users/{id}")
    @Operation(
            summary = "Update user (v3)",
            description = "Updates an existing user with full or partial data",
            security = @SecurityRequirement(name = "bearer-jwt")
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "User updated successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    public ResponseEntity<ApiResponse<UserDto>> updateUser(
            @Parameter(description = "User ID", required = true) @PathVariable Long id,
            @Valid @RequestBody UserDto userDto) {
        log.info("V3 API called: PUT /api/v3/users/{}", id);
        userDto.setId(id);
        return ResponseEntity.ok(ApiResponse.success(userDto, "User updated successfully", "v3"));
    }

    @PatchMapping("/users/{id}")
    @Operation(
            summary = "Partially update user (v3)",
            description = "Partially updates user fields",
            security = @SecurityRequirement(name = "bearer-jwt")
    )
    public ResponseEntity<ApiResponse<UserDto>> patchUser(
            @Parameter(description = "User ID", required = true) @PathVariable Long id,
            @RequestBody UserDto userDto) {
        log.info("V3 API called: PATCH /api/v3/users/{}", id);
        userDto.setId(id);
        return ResponseEntity.ok(ApiResponse.success(userDto, "User partially updated successfully", "v3"));
    }

    @DeleteMapping("/users/{id}")
    @Operation(
            summary = "Delete user (v3)",
            description = "Soft deletes a user by ID",
            security = @SecurityRequirement(name = "bearer-jwt")
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "User deleted successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @Parameter(description = "User ID", required = true) @PathVariable Long id) {
        log.info("V3 API called: DELETE /api/v3/users/{}", id);
        return ResponseEntity.ok(ApiResponse.success(null, "User deleted successfully", "v3"));
    }

    @GetMapping("/users/search")
    @Operation(
            summary = "Search users (v3)",
            description = "Advanced user search with multiple criteria",
            security = @SecurityRequirement(name = "bearer-jwt")
    )
    public ResponseEntity<ApiResponse<List<UserDto>>> searchUsers(
            @Parameter(description = "Search query") @RequestParam String query,
            @Parameter(description = "Search fields") @RequestParam(required = false) List<String> fields) {
        log.info("V3 API called: GET /api/v3/users/search with query={}, fields={}", query, fields);

        List<UserDto> users = Arrays.asList(
                UserDto.builder()
                        .id(1L)
                        .username("john_doe")
                        .email("john@example.com")
                        .firstName("John")
                        .lastName("Doe")
                        .status("active")
                        .build()
        );

        return ResponseEntity.ok(ApiResponse.success(users, "Search completed successfully", "v3"));
    }
}

