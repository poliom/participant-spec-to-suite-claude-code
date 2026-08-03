package com.workshop.taskmanager;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {

    protected static final String BASE_URI = "https://spec-to-suite.up.railway.app";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URI;
    }
}
