package com.workshop.taskmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseUITest {

    protected WebDriver driver;
    protected static final String BASE_URL =
        System.getProperty("base.url", "https://spec-to-suite.up.railway.app");

    @BeforeEach
    void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless", "--no-sandbox",
                "--disable-dev-shm-usage", "--disable-gpu");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
