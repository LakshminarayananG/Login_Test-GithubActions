package com.test.cucumber.stepdefinitions;

import com.test.selenium.pageactions.AreaPage;

import io.cucumber.java.en.Then;

public class AreaCreateStepDef extends AreaPage{
	
	@Then("I create an area$")
	public void createAnArea() {
		createArea();
	}
	
	
	@Then("I validate the created area is displayed")
	public void validateArea() throws InterruptedException {
		validateAreaCreated();
		Thread.sleep(5000);
	}

   


}
