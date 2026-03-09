package com.spunit.apidocs.contract;

import com.spunit.apidocs.ApiDocumentationServiceApplication;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

/**
 * Base class for Spring Cloud Contract tests
 */
@SpringBootTest(classes = ApiDocumentationServiceApplication.class)
@ActiveProfiles("test")
public abstract class BaseContractTest {

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }
}

