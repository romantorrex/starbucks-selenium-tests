package com.globant.training.automation.tests;

import com.globant.training.automation.pages.CoffeeFinderPage;
import com.globant.training.automation.pages.CoffeeFinderResultPage;
import com.globant.training.automation.pages.StarbucksHomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StarbucksTests extends BaseTests {
    private StarbucksHomePage homePage;

    @BeforeMethod
    public void beforeTest() {
        homePage = getHomePage();
    }

    @Test
    public void menu() {
        String[] expectedElements = {"COFFEE", "TEA", "MENU", "COFFEEHOUSE", "SOCIAL IMPACT", "STARBUCKS REWARDS", "BLOG", "GIFT CARDS"};

        List<String> menu = homePage.getMenu();

        assertThat(menu).asList().hasSize(expectedElements.length);
        assertThat(menu).asList().contains(expectedElements);
    }

    @Test
    public void findYourPerfectCoffee() {
        CoffeeFinderPage page = homePage.clickFindYourPerfectCoffee();
        page.setOptionQuestionOne("Lighthearted and sunny");
        page.setOptionQuestionTwo("A group of friends");
        page.setOptionQuestionThree("Cocoa");
        page.setOptionQuestionFour("I like things simple");
        CoffeeFinderResultPage resultPage = page.clickFindMyCoffee();
        assertThat(resultPage.getCurrentUrl()).isEqualTo("https://athome.starbucks.com/coffee-finder/");
        assertThat(resultPage.isFeaturedCoffesResulDisplayed()).isTrue();
    }
}
