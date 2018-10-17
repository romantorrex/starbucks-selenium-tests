package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {
    private static final String PAYMENT_OPTION = "Masterpass";

    @FindBy(id = "paymentMethod")
    private WebElement paymentSelect;

    @FindBy(xpath = "//button[@data-e2e='sendGift']")
    private WebElement continueButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        implicitWait(20);
    }

    public void chooseCreditCardPaymentOption() {
        paymentSelect.sendKeys(PAYMENT_OPTION);
    }

    public String getButtonText() {
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOf(continueButton));
        return continueButton.getText();
    }
}
