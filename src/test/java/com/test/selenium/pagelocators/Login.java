package com.test.selenium.pagelocators;

import com.test.selenium.framework.FrameworkUtils;
import org.openqa.selenium.By;

public class Login extends FrameworkUtils {

    
    public final By txtUser = By.xpath("//input[@name='email']");
    public final By txtPassword = By.xpath("//input[@name='password']");
    public final By btnSignin = By.xpath("//button[@name='submit']");
    public final By loginSuccess = By.xpath("//button[@data-testid='menu-user']//span");
}
