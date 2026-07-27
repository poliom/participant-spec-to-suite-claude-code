package com.workshop.taskmanager.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TaskFormPage {

    private final WebDriver driver;

    private final By titleField = By.id("title");
    private final By descriptionField = By.id("description");
    private final By submitButton = By.id("submit-btn");

    public TaskFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillTitle(String title) {
        driver.findElement(titleField).clear();
        driver.findElement(titleField).sendKeys(title);
    }

    public void fillDescription(String description) {
        driver.findElement(descriptionField).clear();
        driver.findElement(descriptionField).sendKeys(description);
    }

    public TaskListPage submit() {
        driver.findElement(submitButton).click();
        return new TaskListPage(driver);
    }
}
