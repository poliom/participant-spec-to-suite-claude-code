# API Specification — Task Manager

Base URL: `http://localhost:8080/api` locally, or `https://spec-to-suite.up.railway.app/api` for the
hosted app. Tests target whichever host you pass via `-Dbase.url`; the paths and status codes below are the same either way.

All request and response bodies use `Content-Type: application/json`.

---

## Endpoints

### GET /tasks
Returns all tasks (pending and completed).

**Response 200 OK**
```json
[
  {
    "id": 1,
    "title": "Buy groceries",
    "description": "Milk, eggs, bread",
    "completed": false,
    "createdAt": "2024-06-01T10:00:00",
    "completedAt": null
  }
]
```
Returns empty array `[]` when no tasks exist.

---

### GET /tasks/{id}
Returns a single task by ID.

**Response 200 OK** — task found
```json
{
  "id": 1,
  "title": "Buy groceries",
  "description": "Milk, eggs, bread",
  "completed": false,
  "createdAt": "2024-06-01T10:00:00",
  "completedAt": null
}
```

**Response 404 Not Found** — no task with that ID exists (empty body)

---

### POST /tasks
Creates a new task.

**Request body**
```json
{
  "title": "Buy groceries",
  "description": "Milk, eggs, bread"
}
```

| Field | Type | Required | Constraints |
|-------|------|----------|-------------|
| title | string | yes | 1–100 characters, not blank |
| description | string | no | max 500 characters |

**Response 201 Created** — task created successfully
```json
{
  "id": 2,
  "title": "Buy groceries",
  "description": "Milk, eggs, bread",
  "completed": false,
  "createdAt": "2024-06-01T10:05:00",
  "completedAt": null
}
```

**Response 400 Bad Request** — validation failure (missing title, blank title, title too long)
```json
{
  "error": "Title is required"
}
```

---

### PUT /tasks/{id}
Updates the title and/or description of an existing task.

**Request body**
```json
{
  "title": "Buy groceries and coffee",
  "description": "Milk, eggs, bread, coffee"
}
```

Same field constraints as POST.

**Response 200 OK** — updated task
**Response 404 Not Found** — no task with that ID

---

### PATCH /tasks/{id}/complete
Marks a pending task as complete.

**Request body** — none

**Response 200 OK** — task now completed
```json
{
  "id": 1,
  "title": "Buy groceries",
  "description": "Milk, eggs, bread",
  "completed": true,
  "createdAt": "2024-06-01T10:00:00",
  "completedAt": "2024-06-01T11:30:00"
}
```

**Response 404 Not Found** — no task with that ID

**Response 409 Conflict** — task is already completed
```json
{
  "error": "Task 1 is already completed"
}
```

---

### DELETE /tasks/{id}
Deletes a task permanently.

**Response 204 No Content** — deleted successfully (empty body)

**Response 404 Not Found** — no task with that ID (empty body)

---

## Error conventions

| HTTP Status | When |
|-------------|------|
| 200 | Success with body |
| 201 | Resource created |
| 204 | Success, no body |
| 400 | Invalid request (validation failure) |
| 404 | Resource not found |
| 409 | Conflict (e.g. already completed) |
