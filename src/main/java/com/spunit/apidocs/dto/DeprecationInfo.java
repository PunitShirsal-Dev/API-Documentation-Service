package com.spunit.apidocs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * API Deprecation Info DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeprecationInfo {
    private String endpoint;
    private String version;
    private boolean deprecated;
    private String deprecatedSince;
    private String sunsetDate;
    private String replacement;
    private String migrationGuide;
    private String reason;
    private LocalDateTime lastChecked;
}

