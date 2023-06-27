package com.test.cucumber.stepdefinitions;

import com.test.selenium.businesscomponents.GeneralComponents;
import com.test.selenium.pageactions.LoginActions;
import com.test.selenium.pageactions.OrderConfirmationActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class GeneralStepDef extends OrderConfirmationActions {

    GeneralComponents general = new GeneralComponents();
    LoginActions login = new LoginActions();

    @Given ("^I launch the (.+)$")
    public void i_launch_the_application(String application) throws InterruptedException {
        general.launchApplication(application);

    }

    @And("^I login to the application using (.+) and (.+)$")
    public void login_to_the_application(String userName, String password) throws InterruptedException{
        general.login(userName,password);
    }


}
