package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage{

    @FindBy(id = "paymentMethod")
    private WebElement paymentOption;

    @FindBy(xpath = "//button[@data-e2e='sendGift']")
    private WebElement continueButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        implicitWait(20);
    }

    public void chooseCreditCardPaymentOption() {
        paymentOption.sendKeys("Masterpass");
    }

    public String getButtonText() {
        return continueButton.getText();
    }
}
