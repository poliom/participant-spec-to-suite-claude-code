package com.workshop.taskmanager;

import com.workshop.taskmanager.pages.TaskFormPage;
import com.workshop.taskmanager.pages.TaskListPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskUITest extends BaseUITest {

    private TaskListPage listPage;

    @BeforeEach
    void openApp() {
        driver.get(BASE_URL);
        listPage = new TaskListPage(driver);
    }

    @Test
    void shouldShowEmptyStateWhenNoTasks() {
        assertTrue(listPage.getTaskTitles().isEmpty()
            || listPage.getTaskTitles() != null);
    }

    @Test
    void shouldCreateTaskAndSeeItInList() throws InterruptedException {
        TaskFormPage formPage = listPage.clickNewTask();
        formPage.fillTitle("My UI Test Task");
        formPage.fillDescription("Created via Selenium");
        formPage.submit();

        // BUG 3: Thread.sleep instead of explicit wait
        // Fix: use WebDriverWait with ExpectedConditions.visibilityOfElementLocated
        Thread.sleep(2000);

        assertTrue(listPage.getTaskTitles().contains("My UI Test Task"));
    }

    @Test
    void shouldShowSuccessMessageAfterCreate() throws InterruptedException {
        TaskFormPage formPage = listPage.clickNewTask();
        formPage.fillTitle("Success message task");
        formPage.submit();

        Thread.sleep(2000); // BUG 3 again

        assertNotNull(listPage.getSuccessMessage());
    }
}
