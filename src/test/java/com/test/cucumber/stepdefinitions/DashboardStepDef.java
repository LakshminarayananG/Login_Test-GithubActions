package com.test.cucumber.stepdefinitions;


import com.test.selenium.pageactions.DashboardPage;

import io.cucumber.java.en.And;

public class DashboardStepDef extends DashboardPage{
	
	@And("I navigate to Areas")
	public void navigateToAreas() {
		navigateToAreasFromRestaurant();
	}

   


}
