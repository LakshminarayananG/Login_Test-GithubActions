package com.test.selenium.pagelocators;

import com.test.selenium.framework.FrameworkUtils;
import org.openqa.selenium.By;

public class Area extends FrameworkUtils {

    
    public final By btnAddArea = By.xpath("//button[text()='Add area']");
    public final By txtAreaTitle = By.id("input-title-areasDialog");
    public final By txtAreaDescription = By.id("input-description-areasDialog");
    public final By btnApply = By.xpath("//button[text()='Apply']");
    public final By btnSave = By.xpath("//button[text()='Save']");

    
    public By areaCreate(String area) {
    	return By.xpath("//div[starts-with(@class,'MuiGrid-root MuiGrid-container')]//span[starts-with(@class,'MuiTypography-root MuiTypography-h1') and text()='"+area+"']");
    }
}
