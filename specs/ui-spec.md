# UI Specification — Task Manager

## Pages

### Page 1: Task List (`/`)

**Purpose:** Shows all tasks with actions.

**Layout elements:**
- Page heading: "Task Manager" (h1)
- Flash message area: shows success (green) or error (red) messages after actions
- "+ New Task" button — navigates to `/tasks/new`
- Task table with columns: ID | Title | Description | Status | Created | Actions

**Table behaviour:**
- Each task row has `id="task-{id}"` (e.g. `id="task-1"`)
- Status badge: "Pending" (yellow) for incomplete tasks; "Done" (green) for completed tasks
- Completed task rows are visually dimmed (strikethrough text, grey colour)
- Description column shows "—" (em dash) when description is empty
- Created date formatted as: `dd MMM yyyy HH:mm` (e.g. "01 Jun 2024 10:00")

**Action buttons per row:**
- **Edit** button: visible only for pending tasks. Links to `/tasks/{id}/edit`
- **Complete** button: visible only for pending tasks. Submits POST to `/tasks/{id}/complete`
- **Delete** button: visible for all tasks. Submits POST to `/tasks/{id}/delete`. Shows browser confirm dialog before submitting.

**Empty state:**
- When no tasks exist, the table body shows: "No tasks yet. Create your first task!"

---

### Page 2: Create Task Form (`/tasks/new`)

**Purpose:** Form for creating a new task.

**Page heading:** "New Task"

**Form fields:**

| Field | Element | ID | Name | Required | Max length | Placeholder |
|-------|---------|-----|------|----------|------------|-------------|
| Title | `<input type="text">` | `title` | `title` | Yes | 100 | "Enter task title (max 100 characters)" |
| Description | `<textarea>` | `description` | `description` | No | 500 | "Optional description (max 500 characters)" |

**Character counters:**
- Below title input: live counter showing `{current}/100`
- Below description textarea: live counter showing `{current}/500`
- Counters update on every keystroke (no debounce)

**Buttons:**
- Submit: id=`submit-btn`, text "Create Task", submits POST to `/tasks`
- Cancel: text "Cancel", links back to `/`

**Validation:**
- Title field has HTML5 `required` attribute
- On successful creation: redirect to `/` with flash success message "Task created successfully"
- On validation error from server: redirect to `/` with flash error message

---

### Page 3: Edit Task Form (`/tasks/{id}/edit`)

**Purpose:** Form for editing an existing task.

**Page heading:** "Edit Task"

**Form:** Same fields as Create, pre-populated with current values.

**Differences from Create form:**
- Submit button text: "Update Task"
- Form submits POST to `/tasks/{id}`
- On successful update: redirect to `/` with flash success message "Task updated successfully"

**Access rules:**
- Completed tasks cannot be edited. The Edit button is not shown for completed tasks on the list page.
- If a user navigates directly to `/tasks/{id}/edit` for a completed task, the form is shown (server does not block it — this is intentional for the workshop, participants should notice this gap).

---

## Navigation flow

```
/ (list)
  ├── "+ New Task" → /tasks/new → POST /tasks → redirect /
  ├── "Edit" → /tasks/{id}/edit → POST /tasks/{id} → redirect /
  ├── "Complete" → POST /tasks/{id}/complete → redirect /
  └── "Delete" → confirm dialog → POST /tasks/{id}/delete → redirect /
```

## Flash messages

| Action | Success message | Error message |
|--------|----------------|---------------|
| Create task | "Task created successfully" | (server validation error text) |
| Update task | "Task updated successfully" | "Task not found" |
| Complete task | "Task marked as complete" | "Task is already completed" |
| Delete task | "Task deleted" | "Task not found" |

---

## Element selector reference (source of truth for test automation)

Concrete locators for the elements described above. Use these directly rather than guessing.

### Task List page (`/`)

| Element | Selector |
|---------|----------|
| Task table | `#task-table` |
| Task row | `#task-{id}` (e.g. `#task-1`) |
| Title cell (within a row) | `#task-{id} td:nth-child(2)` — cells are unclassed, so target positionally |
| Status badge (within a row) | `#task-{id} .badge` — modifier classes `.badge-pending` / `.badge-done` |
| **Complete** button (within a row) | `#task-{id} button.btn-success` |
| **Delete** button (within a row) | `#task-{id} button.btn-danger` — triggers a native `confirm()` dialog |
| **Edit** link (within a row, pending only) | `#task-{id} a.btn-secondary` |
| "+ New Task" button | `a.new-task-btn` (or link text "+ New Task") |
| Flash **success** message | `.alert-success` |
| Flash **error** message | `.alert-error` |
| Empty-state cell | `.empty-state` |

### Task Form page (`/tasks/new`, `/tasks/{id}/edit`)

| Element | Selector |
|---------|----------|
| Form | `#task-form` |
| Title input | `#title` |
| Description textarea | `#description` |
| Title character counter | `#title-count` (the count value; sibling text reads `{n}/100`) |
| Description character counter | `#desc-count` (the count value; sibling text reads `{n}/500`) |
| Submit button | `#submit-btn` |
| Cancel link | link text "Cancel" (styled `a.btn-secondary`) |

> **Per-row actions must be scoped to `#task-{id}`** — an unscoped `button.btn-success` matches the
> first row's Complete button, not the one you intend.
>
> **Delete** uses a **native browser `confirm()`** dialog — handle it in Selenium with
> `driver.switchTo().alert().accept()` (or `.dismiss()` to cancel), not a DOM element.
