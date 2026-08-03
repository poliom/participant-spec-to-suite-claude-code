# Spec-to-Suite Workshop — Participant Repo

Hands-on repository for the **Spec-to-Suite** QA automation workshop. Across 6 lectures
(3 sessions) you'll go from a PRD to a green, CI-run test suite for the Task Manager demo
app — using Claude Code to draft, and your own judgement to verify.

## How the branches work

**Clone once, then check out the branch for each session.** Each branch builds on the last,
so start every session by switching to the right branch.

| Branch | Session | Lectures | What's in it |
|--------|---------|----------|--------------|
| `main` | Session 1 | L1–L2 | Specs, PRD, acceptance criteria, `CLAUDE.md`, `pom.xml` — no test code yet |
| `session-2` | Session 2 | L3–L4 | Adds clean base classes + a Page Object to build your UI & API tests on |
| `session-3` | Session 3 | L5–L6 | Adds a test suite to review, debug and harden, plus a CI workflow to complete |

```bash
# Before Session 1 (Lectures 1–2)
git checkout main

# Before Session 2 (Lectures 3–4)
git fetch origin
git checkout session-2

# Before Session 3 (Lectures 5–6)
git fetch origin
git checkout session-3
```

## The app under test

The Task Manager app is **hosted** at **https://spec-to-suite.up.railway.app/** — point your tests
there. (This repo has no `app/`; running a local instance would require the separate demo-app repo.)

## Running tests (Session 2 onward)

Tests run against the hosted app by default — `mvn test` just works. Override the target if needed:

```bash
mvn test
# or explicitly:
mvn test -Dbase.url=https://spec-to-suite.up.railway.app
```

## Project layout

```
specs/                   # PRD, API spec, UI spec, acceptance criteria (all branches)
CLAUDE.md                # Project context for Claude Code (all branches)
pom.xml                  # JUnit 5 + RestAssured + Selenium + WebDriverManager
src/test/java/com/workshop/taskmanager/
  BaseApiTest.java       # RestAssured base config      (session-2 onward)
  BaseUITest.java        # Selenium / WebDriverManager   (session-2 onward)
  pages/TaskFormPage.java# Page Object                   (session-2 onward)
  ...                    # UI & API test suites          (session-3)
```

## Requirements

- Java 17+
- Maven 3.9+
- Chrome (for Selenium UI tests)
- [Claude Code](https://claude.com/product/claude-code), installed and authenticated
