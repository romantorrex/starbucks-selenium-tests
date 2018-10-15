package com.globant.training.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyDriver {
    private WebDriver driver;

    public MyDriver(String browser) {
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Browser not supported" + browser);
        }
    }

    public MyDriver() {
        this("firefox");
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
