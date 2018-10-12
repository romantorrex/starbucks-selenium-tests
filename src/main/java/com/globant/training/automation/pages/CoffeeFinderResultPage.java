package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CoffeeFinderResultPage extends BasePage {
    private WebDriver driver;

    @FindBy(id = "featured-coffees-stack")
    private WebElement featuredCoffeesResult;

    public CoffeeFinderResultPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isFeaturedCoffesResulDisplayed() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(featuredCoffeesResult));
        return featuredCoffeesResult.isDisplayed();
    }
}
