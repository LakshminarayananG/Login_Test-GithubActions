package com.test.selenium.pagelocators;

import com.test.selenium.framework.FrameworkUtils;
import org.openqa.selenium.By;

public class Dashboard extends FrameworkUtils {

    
    public final By linkRestaurant = By.xpath("//div[@data-testid='menu-main-configuration']//p[text()='Restaurant Management']");
    public final By linkArea = By.linkText("Areas");
}
