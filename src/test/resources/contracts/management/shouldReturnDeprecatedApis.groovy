package contracts.management

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should return list of deprecated APIs"

    request {
        method GET()
        url "/api/management/deprecations"
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body([
            success: true,
            message: "Deprecated APIs retrieved successfully",
            data: [
                [
                    endpoint: "/api/v1/users",
                    version: "v1",
                    deprecated: true,
                    deprecatedSince: "2025-01-01",
                    sunsetDate: "2026-09-01",
                    replacement: "/api/v3/users",
                    migrationGuide: "https://api.example.com/migration/v1-to-v3",
                    reason: "Enhanced security and performance improvements in v3",
                    lastChecked: $(consumer(anyDateTime()), producer('2026-03-09T20:00:00'))
                ]
            ],
            timestamp: $(consumer(anyDateTime()), producer('2026-03-09T20:00:00')),
            apiVersion: "v3"
        ])
        bodyMatchers {
            jsonPath('$.success', byEquality())
            jsonPath('$.data[0].deprecated', byEquality())
        }
    }
}

