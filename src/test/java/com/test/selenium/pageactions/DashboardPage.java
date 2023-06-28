package com.test.selenium.pageactions;

import com.test.selenium.pagelocators.Dashboard;
import com.test.selenium.pagelocators.Login;

public class DashboardPage extends Dashboard {

    public void navigateToAreasFromRestaurant() {
    	waitUntilElementClickable(linkRestaurant, 20);
    	click(linkRestaurant);
    	waitUntilElementClickable(linkArea, 20);
    	click(linkArea);
    }

}
