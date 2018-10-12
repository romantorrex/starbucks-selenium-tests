package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {
    @FindBy(id = "username")
    private WebElement usernameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(xpath = "//form//button[@type='submit']")
    private WebElement signInButton;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public StarbucksAppPage signIn(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signInButton.click();
        return new StarbucksAppPage(getDriver());
    }
}
