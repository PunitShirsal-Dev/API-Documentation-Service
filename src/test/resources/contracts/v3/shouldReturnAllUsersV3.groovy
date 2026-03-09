package contracts.v3

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should return all users from v3 API"

    request {
        method GET()
        url "/api/v3/users"
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
            message: "Users retrieved successfully",
            data: [
                [
                    id: 1,
                    username: "john_doe",
                    email: "john@example.com",
                    firstName: "John",
                    lastName: "Doe",
                    status: "active"
                ]
            ],
            timestamp: $(consumer(anyDateTime()), producer('2026-03-09T20:00:00')),
            apiVersion: "v3"
        ])
        bodyMatchers {
            jsonPath('$.success', byEquality())
            jsonPath('$.apiVersion', byEquality())
            jsonPath('$.data', byType())
        }
    }
}

