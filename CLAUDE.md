# Task Manager — Claude Code Project Context

## What this project is

A Spring Boot 3 Task Manager application used as the demo project for the "Spec-to-Suite" QA automation workshop. It has a REST API and a Thymeleaf web UI.

## Application structure

```
app/
  src/main/java/com/workshop/taskmanager/
    TaskManagerApplication.java     # Entry point
    model/Task.java                 # JPA entity
    repository/TaskRepository.java  # Spring Data repo
    service/TaskService.java        # Business logic + custom exceptions
    controller/TaskController.java  # REST API (/api/tasks)
    controller/TaskWebController.java # UI routes (/, /tasks/*)
  src/main/resources/
    templates/tasks/list.html       # Task list page
    templates/tasks/form.html       # Create/edit form
    application.yml                 # H2 in-memory DB, port 8080
```

## Running the app

```bash
cd app
mvn spring-boot:run
# App starts at http://localhost:8080
# H2 console at http://localhost:8080/h2-console
```

## REST API summary

| Method | Path | Success | Error |
|--------|------|---------|-------|
| GET | /api/tasks | 200 list | — |
| GET | /api/tasks/{id} | 200 | 404 |
| POST | /api/tasks | **201** | 400 |
| PUT | /api/tasks/{id} | 200 | 404 |
| PATCH | /api/tasks/{id}/complete | 200 | 404, **409** |
| DELETE | /api/tasks/{id} | **204** | 404 |

> POST returns 201, DELETE returns 204, already-completed returns 409. These are the three status codes AI most often gets wrong.

## Test conventions

- Framework: **JUnit 5** (`@Test`, `@BeforeEach`, `@AfterEach`)
- API tests: **RestAssured** with `given().when().then()` style
- UI tests: **Selenium 4** with **Page Object Model**
- Base URI for API tests: configurable via `System.getProperty("base.url", "http://localhost:8080")`
- Never use `Thread.sleep()` — always use `WebDriverWait` with explicit conditions
- Never hardcode `localhost:8080` — always use the base URI property
- Test isolation: each test must clean up after itself (`@AfterEach` delete created resources)

## Page Object conventions

```java
public class TaskListPage {
    private final WebDriver driver;

    public TaskListPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods return page objects or extracted values, never WebElements
    public String getSuccessMessage() { ... }
    public List<String> getTaskTitles() { ... }
    public TaskFormPage clickNewTask() { ... }
}
```

- Page Objects live in `src/test/java/com/workshop/taskmanager/pages/`
- Test classes live in `src/test/java/com/workshop/taskmanager/`
- One Page Object per page/component

## What good generated tests look like

1. Each test has a clear name describing the scenario: `shouldReturn201WhenCreatingValidTask()`
2. Tests are independent — no shared mutable state between tests
3. `@BeforeEach` sets up only what's needed, `@AfterEach` cleans up
4. Assertions are specific: check exact status code, check specific field values
5. Selenium tests use `WebDriverWait` with `ExpectedConditions`, never `Thread.sleep()`
6. RestAssured tests use the configurable `BASE_URI`, never a hardcoded URL

## Specs location

- `specs/PRD.md` — product requirements
- `specs/api-spec.md` — REST API contract (source of truth for status codes)
- `specs/ui-spec.md` — UI behaviour and element IDs
- `specs/acceptance-criteria.md` — BDD-style acceptance criteria

## CI

`.github/workflows/tests.yml` — runs on push to main. Starts the app, then runs API tests, then UI tests (headless Chrome).
