# Product Requirements Document — Task Manager

## Overview

A lightweight web-based Task Manager application that allows users to create, view, update, complete, and delete tasks. The application is used as a demonstration project for the "Spec-to-Suite" workshop.

## User Stories

| ID | As a… | I want to… | So that… |
|----|-------|-----------|----------|
| US-01 | user | create a new task with a title and optional description | I can track things I need to do |
| US-02 | user | see a list of all my tasks | I have an overview of pending and completed work |
| US-03 | user | edit a task's title and description | I can correct mistakes or update details |
| US-04 | user | mark a task as complete | I can track progress |
| US-05 | user | delete a task | I can remove tasks I no longer need |
| US-06 | user | see when a task was created | I have context for how old a task is |

## Functional Requirements

### FR-01: Task Creation
- A user can create a task by providing a title (required) and an optional description.
- The task is saved and immediately visible in the task list.
- A successfully created task returns HTTP 201 (API) or redirects to the list (UI).

### FR-02: Task Listing
- All tasks are displayed in the list view, both pending and completed.
- Each task row shows: ID, title, description (or dash if empty), status badge, creation date, and action buttons.

### FR-03: Task Editing
- A user can edit the title and description of a task.
- Completed tasks cannot be edited (the Edit button is hidden).
- Saving an edit redirects to the list and shows a success message.

### FR-04: Task Completion
- A user can mark a pending task as complete.
- Once completed, a task cannot be completed again (results in an error).
- Completion records the timestamp.

### FR-05: Task Deletion
- A user can delete any task (pending or completed).
- Deletion requires confirmation (UI) and is permanent.
- Deleting a non-existent task returns HTTP 404 (API).

### FR-06: Validation
- Task title is required. Empty or blank titles are rejected.
- Task title must not exceed 100 characters.
- Task description must not exceed 500 characters.

## Non-Functional Requirements

- The application must start within 10 seconds.
- The task list must load within 2 seconds for up to 100 tasks.
- No authentication is required (single-user, local tool).
- Data is stored in-memory — tasks are lost on restart (by design for workshop use).

## Out of Scope

- User authentication / multi-user support
- Task priorities or categories
- Due dates or reminders
- Search or filtering
- Pagination
