package com.globant.training.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CoffeeFinderPage extends BasePage {
    @FindBy(css = "#question1 button h3")
    private List<WebElement> questionOneAnswers;

    @FindBy(css = "#question2 button h3")
    private List<WebElement> questionTwoAnswers;

    @FindBy(css = "#question3 button h3")
    private List<WebElement> questionThreeAnswers;

    @FindBy(css = "#question4 button h3")
    private List<WebElement> questionFourAnswers;

    @FindBy(xpath = "//div[@class='collapsed-selection']/h4")
    private List<WebElement> selectedAnswers;

    @FindBy(id = "find-my-coffee")
    private WebElement findMyCoffeeButton;

    public CoffeeFinderPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Selects an answer for question one
     * @param answer Text of the answer selected
     */
    public void answerQuestionOne(String answer) {
        selectAnswerForQuestion(questionOneAnswers, answer);
    }

    /**
     * Selects an answer for question two
     * @param answer Text of the answer selected
     */
    public void answerQuestionTwo(String answer) {
        selectAnswerForQuestion(questionTwoAnswers, answer);
    }

    /**
     * Selects an answer for question three
     * @param answer Text of the answer selected
     */
    public void answerQuestionThree(String answer) {
        selectAnswerForQuestion(questionThreeAnswers, answer);
    }

    /**
     * Selects an answer for question four
     * @param answer Text of the answer selected
     */
    public void answerQuestionFour(String answer) {
        selectAnswerForQuestion(questionFourAnswers, answer);
    }

    /**
     * Returns the answer selected for question one
     * @return The text of the option selected
     */
    public String getFristAnswer() {
        return selectedAnswers.get(0).getText();
    }

    /**
     * Returns the answer selected for question two
     * @return The text of the option selected
     */
    public String getSecondAnswer() {
        return selectedAnswers.get(1).getText();
    }

    /**
     * Returns the answer selected for question three
     * @return The text of the option selected
     */
    public String getThirdAnswer() {
        return selectedAnswers.get(2).getText().isEmpty() ?
                (selectedAnswers.get(3).getText().isEmpty() ? selectedAnswers.get(4).getText() : selectedAnswers.get(3).getText())
                : selectedAnswers.get(2).getText();
    }

    /**
     * Returns the answer selected for question four
     * @return The text of the option selected
     */
    public String getFourthAnswer() {
        return selectedAnswers.get(5).getText();
    }

    /**
     * Iterates the given answer options and selects the one that matches the given text
     * @param answerOptions The option list of answers for a given question.
     * @param answerSelectedText The text of the answer selected
     */
    void selectAnswerForQuestion(List<WebElement> answerOptions, String answerSelectedText) {
        WebElement answerSelected = answerOptions
                .stream()
                .filter(answer -> answer.getText().equals(answerSelectedText))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Option not available"));;
        answerSelected.click();
    }

    public CoffeeFinderResultPage clickFindMyCoffee() {
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOf(findMyCoffeeButton));
        findMyCoffeeButton.click();
        return new CoffeeFinderResultPage(getDriver());
    }
}
