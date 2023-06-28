package com.test.selenium.businesscomponents;
import com.test.selenium.pageactions.LoginActions;

import org.testng.Assert;

import com.test.selenium.enums.Context;
import com.test.selenium.framework.FrameworkUtils;
import com.test.selenium.framework.Settings;

import java.util.Properties;

public class GeneralComponents extends FrameworkUtils {
    private static Properties globalProperties = Settings.getInstance();
    LoginActions login= new LoginActions();

    /*    Function to launch the application
        @param : application name
    */

    public void launchApplication(String application){
        String url = null;
        url = globalProperties.getProperty(application);
        if (url == null)
            addStepError("Application URL is not defined");
        driver.get(url);
        waitForPageLoad(20);
        addStepLog(" Application Launched : " +url);
    }

    /*    Function to login to the application
        @param : String Username and parameter
    */

    public void login(String userName,String password) throws InterruptedException{
    	Assert.assertEquals(pageTitleProperties.getProperty("LoginPage"), login.returnPageTitle());
        login.enterUser(userName);
        login.enterPassword(password);
        login.clickSubmit();
        setScenarioContext(Context.USERNAME, userName);
        waitForPageLoad(20);
    }
    
    /*
     * Function to validate the landing page after login
     */
    
    public void validateLandingAfterLogin() {
    	validateTextCase(pageTitleProperties.getProperty("LandingPage"), login.returnPageTitle());
    	Assert.assertEquals(pageTitleProperties.getProperty("LandingPage"), login.returnPageTitle());
    	waitUntilElementVisible(login.loginSuccess, 20);
    	validateTextCase(getScenarioContext(Context.USERNAME), getText(login.loginSuccess));
    	
    }

}
