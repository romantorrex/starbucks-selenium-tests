package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StarbucksAppPage extends BasePage {
    @FindBy(xpath = "*//a[@data-product-category='Cards']")
    private List<WebElement> giftCards;

    @FindBy(xpath = "//nav/ul/li/a[text() = 'Gift']")
    private WebElement giftMenu;

    @FindBy(xpath = "//div[@class='py6']")
    private WebElement giftCardsDiv;

    public StarbucksAppPage(WebDriver driver) {
        super(driver);
        implicitWait(10);
    }

    public void clickGiftLink() {
        giftMenu.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOf(giftCardsDiv));
    }

    public GiftCardPage selectGiftcard() {
        giftCards.get(0).click();
        return new GiftCardPage(getDriver());
    }
}
