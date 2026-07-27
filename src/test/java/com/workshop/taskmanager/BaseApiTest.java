package com.workshop.taskmanager;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {

    // Base URI is configurable so the same tests run locally and in CI.
    protected static final String BASE_URI =
        System.getProperty("base.url", "http://localhost:8080");

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URI;
    }
}
