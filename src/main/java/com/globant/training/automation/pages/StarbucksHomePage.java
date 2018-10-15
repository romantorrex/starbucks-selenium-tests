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
    @FindBy(css = "a.tab strong")
    private List<WebElement> menuItems;

    @FindBy(xpath = "//li[@id='menu_coffee']//a[text() = 'Find Your Perfect Coffee']")
    private WebElement findYourPerfectCoffee;

    @FindBy(id = "nav_coffee")
    private WebElement menuCoffee;

    @FindBy(id = "signIn")
    private WebElement signInLink;

    public StarbucksHomePage(WebDriver driver) {
        super(driver);
    }

    public List<String> getMenu() {
        return menuItems.stream().map(element -> element.getText()).collect(Collectors.toList());
    }

    public CoffeeFinderPage clickFindYourPerfectCoffee() {
        Actions actions = new Actions(getDriver());

        actions.moveToElement(menuCoffee).perform();
        new WebDriverWait(getDriver(), 20).until(ExpectedConditions.visibilityOf(findYourPerfectCoffee));
        findYourPerfectCoffee.click();

        return new CoffeeFinderPage(getDriver());
    }

    public SignInPage goToSignInPage() {
        signInLink.click();

        return new SignInPage(getDriver());
    }
}
