package com.workshop.taskmanager;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TaskApiTest extends BaseApiTest {

    @Test
    void shouldCreateTask() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Buy groceries\", \"description\": \"Milk and eggs\"}")
        .when()
            .post("/api/tasks")
        .then()
            .statusCode(200)
            .body("title", equalTo("Buy groceries"));
    }

    @Test
    void shouldReturnAllTasks() {
        given()
        .when()
            .get("/api/tasks")
        .then()
            .statusCode(200)
            .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

    @Test
    void shouldReturn404ForMissingTask() {
        given()
        .when()
            .get("/api/tasks/99999")
        .then()
            .statusCode(404);
    }

    @Test
    void shouldReturn400WhenTitleMissing() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"description\": \"No title here\"}")
        .when()
            .post("/api/tasks")
        .then()
            .statusCode(400);
    }

    @Test
    void shouldCompleteTask() {
        // Create a task first
        int id = given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Task to complete\"}")
        .when()
            .post("/api/tasks")
        .then()
            .extract().path("id");

        given()
        .when()
            .patch("/api/tasks/" + id + "/complete")
        .then()
            .statusCode(200)
            .body("Title", equalTo("Task to complete"));
    }

    @Test
    void shouldReturn409WhenAlreadyCompleted() {
        int id = given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Double complete\"}")
        .when()
            .post("/api/tasks")
        .then()
            .extract().path("id");

        // Complete once
        given().when().patch("/api/tasks/" + id + "/complete").then().statusCode(200);

        // Complete again — should 409
        given()
        .when()
            .patch("/api/tasks/" + id + "/complete")
        .then()
            .statusCode(409);
    }

    @Test
    void shouldDeleteTask() {
        int id = given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"To be deleted\"}")
        .when()
            .post("/api/tasks")
        .then()
            .extract().path("id");

        given()
        .when()
            .delete("/api/tasks/" + id)
        .then()
            .statusCode(204);
    }
}
