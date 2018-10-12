package com.globant.training.automation.tests;

import com.globant.training.automation.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StarbucksTests extends BaseTests {
    public static final String USERNAME = "tae.globant.training@gmail.com";
    public static final String PASSWORD = "GlobantTAE_1";
    public static final String COFFEE_FINDER_URL = "https://athome.starbucks.com/coffee-finder/";
    public static final String SIGN_IN_URL = "https://www.starbucks.com/account/signin";
    public static final String RECIPIENT_NAME = "Roman";
    public static final String RECIPIENT_EMAIL = "roman.torres@globant.com";
    public static final int GIFT_AMOUNT = 15;
    public static final String GIFT_MESSAGE = "This is testing gift card";
    public static final String SEND_GIFT_BUTTON_TEXT = "Send gift";
    public static final String CONTINUE_BUTTON_TEXT = "Continue";
    private StarbucksHomePage homePage;

    @BeforeMethod
    public void beforeTest() {
        homePage = getHomePage();
    }

    @DataProvider(name = "coffeeFinderData")
    public Object[][] dataForFindYourCoffee() {
        return new Object[][]{
                {"Lighthearted and sunny", "A group of friends", "Cocoa", "I like things simple"},
                {"Balanced and easy-going", "A quiet moment", "Citrus", "I can mix it up sometimes"},
                {"Bold and complex", "A busy day", "Nuts and spices", "I love to try something new and different"}
        };
    }

    @Test(enabled = false)
    public void menu() {
        String[] expectedElements = {"COFFEE", "TEA", "MENU", "COFFEEHOUSE", "SOCIAL IMPACT", "STARBUCKS REWARDS", "BLOG", "GIFT CARDS"};

        List<String> menu = homePage.getMenu();

        assertThat(menu).asList().hasSize(expectedElements.length);
        assertThat(menu).asList().contains(expectedElements);
    }


    @Test(dataProvider = "coffeeFinderData", enabled = false)
    public void findYourPerfectCoffee(String optOne, String optionTwo, String optionThree, String optionFour) {
        CoffeeFinderPage page = homePage.clickFindYourPerfectCoffee();
        page.setOptionQuestionOne(optOne);
        page.setOptionQuestionTwo(optionTwo);
        page.setOptionQuestionThree(optionThree);
        page.setOptionQuestionFour(optionFour);
        CoffeeFinderResultPage resultPage = page.clickFindMyCoffee();
        assertThat(resultPage.getCurrentUrl()).isEqualTo(COFFEE_FINDER_URL);
        assertThat(resultPage.isFeaturedCoffesResulDisplayed()).isTrue();
    }

    @Test
    public void giftCards() {
        SignInPage signInPage = homePage.goToSignInPage();
        assertThat(signInPage.getCurrentUrl()).isEqualTo(SIGN_IN_URL);
        StarbucksAppPage appPage = signInPage.signIn(USERNAME, PASSWORD);
        appPage.clickGiftLink();
        GiftCardPage giftPage = appPage.selectGiftcard();
        giftPage.setRecipientName(RECIPIENT_NAME);
        giftPage.setRecipientEmail(RECIPIENT_EMAIL);
        giftPage.setAmount(GIFT_AMOUNT);
        giftPage.setMessage(GIFT_MESSAGE);
        CheckoutPage checkoutPage = giftPage.checkout();
        assertThat(checkoutPage.getButtonText()).isEqualTo(SEND_GIFT_BUTTON_TEXT);
        checkoutPage.chooseCreditCardPaymentOption();
        assertThat(checkoutPage.getButtonText()).isEqualTo(CONTINUE_BUTTON_TEXT);
    }
}
