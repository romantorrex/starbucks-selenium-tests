package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Class that contains methods that are common for all the pages that are tested.
 */
public abstract class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return this.driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected void implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public void dispose() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
