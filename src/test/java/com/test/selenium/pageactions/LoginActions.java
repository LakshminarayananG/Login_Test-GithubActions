package com.test.selenium.pageactions;

import com.test.selenium.pagelocators.Login;

public class LoginActions extends Login {

    public void enterUser(String value) {
        enterValue(txtUser, value);
    }

    public void enterPassword(String value) {
        enterValue(txtPassword, value);
    }

    public void clickSubmit() {
        click(btnSignin);
    }
    
    public String returnPageTitle() {
    	waitFor(6500);
    	return getPageTitle();
    }


}
