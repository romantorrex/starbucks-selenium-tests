package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Predicate;

public class CoffeeFinderPage extends BasePage {
    @FindBy(css = "#question1 button h3")
    private List<WebElement> questionOneOptions;

    @FindBy(xpath = "//div[@class='collapsed-selection']/h4")
    private List<WebElement> answers;

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

    public String getFristAnswer() {
        return answers.get(0).getText();
    }

    public String getSecondAnswer() {
        return answers.get(1).getText();
    }

    public String getThirdAnswer() {
        return answers.get(2).getText().isEmpty() ?
                (answers.get(3).getText().isEmpty() ? answers.get(4).getText() : answers.get(3).getText())
                : answers.get(2).getText();
    }

    public String getFourthAnswer() {
        return answers.get(5).getText();
    }

    private WebElement findButton(List<WebElement> answers, Predicate<WebElement> predicate) {
        WebElement button = answers
                .stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Option not available"));

        return button;
    }

    void setOptionToQuestion(List<WebElement> questionOptions, String optionSelected) {
        WebElement button = findButton(questionOptions, option -> option.getText().equals(optionSelected));
        button.click();
    }

    public CoffeeFinderResultPage clickFindMyCoffee() {
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOf(findMyCoffeeButton));
        findMyCoffeeButton.click();
        return new CoffeeFinderResultPage(getDriver());
    }
}
