package com.test.cucumber.stepdefinitions;

import com.test.selenium.businesscomponents.GeneralComponents;
import com.test.selenium.pageactions.LoginActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class GeneralStepDef {

    GeneralComponents general = new GeneralComponents();
    LoginActions login = new LoginActions();

    @Given ("^I launch the application$")
    public void i_launch_the_application() throws InterruptedException {
        general.launchApplication();

    }
    
    @And("^I login to the application using and validate Login page Title$")
    public void login_to_the_application() throws InterruptedException{
        general.login();
    }
    
    @And("^I am in the Dashboard page and validate if the login is with right user$")
    public void validateLandingPageAfterLogin() throws InterruptedException{
        general.validateLandingAfterLogin();
        
    }
    


}
