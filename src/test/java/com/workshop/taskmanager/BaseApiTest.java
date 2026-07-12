package com.workshop.taskmanager;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {

    // BUG 5: hardcoded URL — fails in CI where port differs
    // Fix: System.getProperty("base.url", "http://localhost:8080")
    protected static final String BASE_URI = "http://localhost:8080";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URI;
    }
}
