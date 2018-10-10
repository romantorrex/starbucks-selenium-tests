package com.globant.training.automation.tests;

import com.globant.training.automation.pages.StarbucksHomePage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTests {
    private MyDriver myDriver;
    private StarbucksHomePage home;


    @BeforeSuite(alwaysRun=true)
    public void beforeSuite() {
        myDriver = new MyDriver();
        home = new StarbucksHomePage(myDriver.getDriver());
    }

    @AfterSuite(alwaysRun=true)
    public void afterSuite() {
        home.dispose();
    }

    public StarbucksHomePage getHomePage() {
        myDriver.getDriver().get("https://www.starbucks.com/");
        return home;
    }
}
