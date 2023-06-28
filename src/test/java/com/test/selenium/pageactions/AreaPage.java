package com.test.selenium.pageactions;

import com.test.selenium.enums.Context;
import com.test.selenium.pagelocators.Area;

public class AreaPage extends Area {

    public void createArea() {
    	waitUntilElementClickable(btnAddArea, 10);
    	click(btnAddArea);
    	waitUntilElementVisible(txtAreaTitle, 10);
    	String AreaTitle = getRandomString("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 5);
    	setScenarioContext(Context.AREATITLE, AreaTitle);
    	String AreaDescription =getRandomString("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10);
    	setScenarioContext(Context.AREADESCRIPTION, AreaDescription);
    	enterValue(txtAreaTitle, AreaTitle);
    	enterValue(txtAreaDescription, AreaDescription);
    	click(btnApply);
    	waitUntilElementVisible(btnSave, 10);
    	click(btnSave);
    }
    
    public void validateAreaCreated() {
    	validateTextCase(getText(areaCreate(getScenarioContext(Context.AREATITLE))), getScenarioContext(Context.AREATITLE));
    }

}
