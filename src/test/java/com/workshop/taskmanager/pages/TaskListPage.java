package com.workshop.taskmanager.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class TaskListPage {

    private final WebDriver driver;

    private final By taskTitles = By.cssSelector("td.task-title");
    private final By successAlert = By.cssSelector(".alert-success");
    private final By newTaskButton = By.id("new-task-btn");

    // BUG 4: unscoped selector — matches the FIRST complete button on the page
    // Fix: scope to the specific task row using By.cssSelector("#task-" + taskId + " button.btn-success")
    private final By completeButton = By.cssSelector("button.btn-success");

    public TaskListPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getTaskTitles() {
        return driver.findElements(taskTitles)
            .stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
    }

    public String getSuccessMessage() {
        List<WebElement> alerts = driver.findElements(successAlert);
        return alerts.isEmpty() ? null : alerts.get(0).getText();
    }

    public TaskFormPage clickNewTask() {
        driver.findElement(newTaskButton).click();
        return new TaskFormPage(driver);
    }

    public void clickComplete(long taskId) {
        // BUG 4: ignores taskId — always clicks the first complete button
        driver.findElement(completeButton).click();
    }
}
