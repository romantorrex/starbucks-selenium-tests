package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GiftCardPage extends BasePage {
    @FindBy(name = "recipientName")
    private WebElement recipientNameInput;
    @FindBy(name = "recipientEmail")
    private WebElement recipientEmailInput;
    @FindBy(name = "amount")
    private WebElement amountSelect;
    @FindBy(name = "message")
    private WebElement messageInput;
    @FindBy(xpath = "//form//button")
    private WebElement checkoutButton;

    public GiftCardPage(WebDriver driver) {
        super(driver);
    }

    public void setRecipientName(String name) {
        recipientNameInput.sendKeys(name);
    }

    public void setRecipientEmail(String email) {
        recipientEmailInput.sendKeys(email);
    }

    public void setAmount(Integer amount) {
        amountSelect.sendKeys(amount.toString());
    }

    public void setMessage(String message) {
        messageInput.sendKeys(message);
    }

    public CheckoutPage checkout() {
        checkoutButton.click();

        return new CheckoutPage(getDriver());
    }


}
