package contracts.v3

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should return user by ID from v3 API"

    request {
        method GET()
        url "/api/v3/users/1"
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
            message: "User retrieved successfully",
            data: [
                id: 1,
                username: "user_1",
                email: "user1@example.com",
                firstName: "User",
                lastName: "1",
                status: "active"
            ],
            timestamp: $(consumer(anyDateTime()), producer('2026-03-09T20:00:00')),
            apiVersion: "v3"
        ])
        bodyMatchers {
            jsonPath('$.success', byEquality())
            jsonPath('$.data.id', byEquality())
        }
    }
}

