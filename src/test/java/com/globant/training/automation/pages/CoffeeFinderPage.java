package com.globant.training.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CoffeeFinderPage extends BasePage {
    private WebDriver driver;

    @FindBy(css = "#question1 button h3")
    private List<WebElement> questionOneOptions;

    @FindBy(css = "#question2 button h3")
    private List<WebElement> questionTwoOptions;

    @FindBy(css = "#question3 button h3")
    private List<WebElement> questionThreeOptions;

    @FindBy(css = "#question4 button h3")
    private List<WebElement> questionFourOptions;

    @FindBy(id = "find-my-coffee")
    private WebElement findMyCoffeeButton;

    public CoffeeFinderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void setOptionQuestionOne(String optionSelected) {
        setOptionToQuestion(questionOneOptions, optionSelected);
    }

    public void setOptionQuestionTwo(String optionSelected) {
        setOptionToQuestion(questionTwoOptions, optionSelected);
    }

    public void setOptionQuestionThree(String optionSelected) {
        setOptionToQuestion(questionThreeOptions, optionSelected);
    }

    public void setOptionQuestionFour(String optionSelected) {
        setOptionToQuestion(questionFourOptions, optionSelected);
    }

    void setOptionToQuestion(List<WebElement> questionOptions, String optionSelected) {
        WebElement button = questionOptions
                .stream()
                .filter(option -> option.getText().equals(optionSelected))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Option not available"));

        button.click();
    }

    public CoffeeFinderResultPage clickFindMyCoffee() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(findMyCoffeeButton));
        findMyCoffeeButton.click();
        return new CoffeeFinderResultPage(driver);
    }
}
