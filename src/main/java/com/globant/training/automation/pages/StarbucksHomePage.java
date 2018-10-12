package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class StarbucksHomePage extends BasePage {
    private WebDriver driver;

    @FindBy(css = "a.tab strong")
    private List<WebElement> menuItems;

    @FindBy(css = "#menu_coffee div ol.blocks.blocks-four-up li:nth-child(2) p a")
    private WebElement findYourPerfectCoffee;

    @FindBy(id ="nav_coffee")
    private WebElement menuCoffee;

    @FindBy(id = "signIn")
    private WebElement signInLink;

    public StarbucksHomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public List<String> getMenu() {
        menuItems.forEach(i -> System.out.println(i.getText()));
        return menuItems.stream().map(element -> element.getText()).collect(Collectors.toList());
    }

    public CoffeeFinderPage clickFindYourPerfectCoffee() {
        Actions actions = new Actions(driver);

        actions.moveToElement(menuCoffee).perform();
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(findYourPerfectCoffee));
        findYourPerfectCoffee.click();

        return new CoffeeFinderPage(driver);
    }

    public void dispose() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    public SignInPage goToSignInPage() {
        signInLink.click();

        return new SignInPage(driver);
    }
}
