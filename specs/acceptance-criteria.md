# Acceptance Criteria — Task Manager

> **Workshop note:** Some ACs below are intentionally ambiguous or incomplete.
> Lecture 2 asks participants to find them. Instructor annotations are in `[brackets]`.

---

## AC-01: Create a task with a title

**Given** I am on the New Task page  
**When** I enter a valid title and click "Create Task"  
**Then** the task appears in the task list with status "Pending"  
**And** a success message is shown  

---

## AC-02: Title is required

**Given** I am on the New Task page  
**When** I submit the form without a title  
**Then** the task is not created  
**And** an error is shown  

> **[AMBIGUOUS — Lecture 2 target]:** "An error is shown" — where? In the browser (HTML5 validation popup) or as a server-side flash message? Both behaviours exist; the spec doesn't say which is expected. AI will typically assume one without flagging the ambiguity.

---

## AC-03: Task title must be validated

**Given** I submit a task title  
**When** the title is invalid  
**Then** the task is not created and an error is returned  

> **[AMBIGUOUS — Lecture 2 target]:** What does "invalid" mean? Length limit? Special characters? The spec does not define the validation rules here. This AC is incomplete — the API spec (api-spec.md) defines the actual constraints (1–100 chars, not blank), but this AC does not reference them. AI will often assume a max length of 255 or invent its own rules.

---

## AC-04: Complete a task

**Given** a task exists with status "Pending"  
**When** I click "Complete"  
**Then** the task status changes to "Done"  
**And** the Edit and Complete buttons are no longer shown  
**And** the completion timestamp is recorded  

---

## AC-05: Cannot complete an already-completed task

**Given** a task exists with status "Done"  
**When** a request is made to complete it again  
**Then** an error response is returned  

> **[AI UNDER-COVERS THIS — Lecture 2 target]:** AI consistently generates a test for this scenario but asserts HTTP 400 instead of HTTP 409 (Conflict). The correct status code is defined in api-spec.md. Participants must catch this during the coverage audit.

---

## AC-06: Delete a task

**Given** a task exists  
**When** I click "Delete" and confirm  
**Then** the task is removed from the list  
**And** a success message is shown  

---

## AC-07: Edit a pending task

**Given** a task exists with status "Pending"  
**When** I edit the title or description and save  
**Then** the updated values are shown in the task list  

---

## AC-08: Completed tasks cannot be edited

**Given** a task exists with status "Done"  
**When** I view the task list  
**Then** the Edit button is not shown for that task  

---

## AC-09: Empty task list state

**Given** no tasks exist  
**When** I open the task list  
**Then** a message "No tasks yet. Create your first task!" is shown  

---

## AC-10: Delete a non-existent task via API

**Given** no task exists with ID 9999  
**When** I send DELETE /api/tasks/9999  
**Then** the response is HTTP 404  

> **[AI OVER-COVERS THIS — Lecture 2 target]:** AI typically generates 5–6 variations of the 404 scenario (different invalid IDs, negative IDs, string IDs) even though the spec only requires basic not-found handling. Participants must identify the invented cases during the coverage audit.

---

## AC-11: Task list shows creation date

**Given** a task was created at a specific time  
**When** I view the task list  
**Then** the creation date is shown in the format "dd MMM yyyy HH:mm"  

---

## AC-12: API returns correct response on task creation

**Given** I send a valid POST /api/tasks request  
**When** the task is created  
**Then** the response status is 201  
**And** the response body contains the created task with its assigned ID  
