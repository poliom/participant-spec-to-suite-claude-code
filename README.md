# workshop-spec-to-suite-participant

Participant repository for the **Spec-to-Suite** QA automation workshop. You'll use this
repo for all 6 exercises, writing API and UI tests against the Task Manager demo app.

## Setup

1. Clone this repo.
2. The Task Manager app is hosted at **https://spec-to-suite.up.railway.app/** — point your
   tests there, or run it locally from the demo app repo with `mvn spring-boot:run` (starts on
   `http://localhost:8080`).
3. Verify your setup:

   ```bash
   mvn test -Dbase.url=https://spec-to-suite.up.railway.app
   ```

   This should build cleanly and run the existing tests. To run against a different host
   (e.g. a local instance), pass a different URL:

   ```bash
   mvn test -Dbase.url=http://localhost:8080
   ```

   Note: `BaseApiTest` currently hardcodes `http://localhost:8080` rather than reading
   `base.url` — that's intentional (see Bug 5 in the exercises), so API tests against the
   hosted app won't work until it's fixed.

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
