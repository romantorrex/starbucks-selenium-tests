package com.globant.training.automation.tests;

import com.globant.training.automation.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StarbucksTests extends BaseTests {
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
        assertThat(resultPage.getCurrentUrl()).isEqualTo("https://athome.starbucks.com/coffee-finder/");
        assertThat(resultPage.isFeaturedCoffesResulDisplayed()).isTrue();
    }

    @Test
    public void giftCards() {
        SignInPage signInPage = homePage.goToSignInPage();
        assertThat(signInPage.getCurrentUrl()).isEqualTo("https://www.starbucks.com/account/signin");
        StarbucksAppPage appPage = signInPage.signIn("tae.globant.training@gmail.com", "GlobantTAE_1");
        appPage.clickGiftLink();
        GiftCardPage giftPage = appPage.selectGiftcard();
        giftPage.setRecipientName("Roman");
        giftPage.setRecipientEmail("roman.torres@globant.com");
        giftPage.setAmount(15);
        giftPage.setMessage("This is testing gift card");
        CheckoutPage checkoutPage = giftPage.checkout();
        //assertThat(checkoutPage.getButtonText()).isEqualTo("Send gift");
        checkoutPage.chooseCreditCardPaymentOption();
        assertThat(checkoutPage.getButtonText()).isEqualTo("");
    }
}
