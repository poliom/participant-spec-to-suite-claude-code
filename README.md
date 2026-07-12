# workshop-spec-to-suite-participant

Participant repository for the **Spec-to-Suite** QA automation workshop. You'll use this
repo for all 6 exercises, writing API and UI tests against the Task Manager demo app.

## Setup

1. Clone this repo.
2. Make sure the Task Manager app is running (your instructor will share the URL, or run it
   locally from the demo app repo with `mvn spring-boot:run` — it starts on `http://localhost:8080`).
3. Verify your setup:

   ```bash
   mvn test
   ```

   This should build cleanly and run the existing tests. If the app isn't reachable at
   `http://localhost:8080`, either start it or pass a different URL:

   ```bash
   mvn test -Dbase.url=http://your-app-host:8080
   ```

## Project layout

```
src/test/java/com/workshop/taskmanager/
  BaseApiTest.java       # RestAssured base config
  BaseUITest.java        # Selenium/WebDriverManager base config
  TaskApiTest.java       # API tests
  TaskUITest.java        # UI tests
  pages/
    TaskListPage.java    # Page Object
    TaskFormPage.java    # Page Object
specs/                   # PRD, API spec, UI spec, acceptance criteria
CLAUDE.md                # Project context for Claude Code
```

## Requirements

- Java 17+
- Maven 3.9+
- Chrome (for Selenium UI tests)
- [Claude Code](https://claude.com/product/claude-code), installed and authenticated
