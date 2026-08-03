package com.workshop.taskmanager.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class TaskListPage {

    private final WebDriver driver;

    private final By taskTitles = By.cssSelector("#task-table tbody tr[id^='task-'] td:nth-child(2)");
    private final By successAlert = By.cssSelector(".alert-success");
    private final By newTaskButton = By.cssSelector("a.new-task-btn");

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
        driver.findElement(completeButton).click();
    }
}
