package com.test.selenium.pageactions;

import com.test.selenium.enums.Context;
import com.test.selenium.pagelocators.MyAccount;

import java.util.Random;

public class MyAccountActions extends MyAccount {

    public void clickMyAccount(){
        click(myAccount);
    }
    public void clickOrderHistory(){
        click(orderHistory);
    }
    public void clickPersonalInformation(){
        click(personalInformation);
    }
    public void sortbyOrder(){
        clickUsingJS(dateSort,"Date sort");
    }
    public void clickOrderDetails(){
        click(btnOrderDetails);
    }
    public String getTotalPriceFromHistory(){
       return getText(totalPriceIncTax);
    }

    public void validateOrderDetails(){

       
    }

    public void updateFirstName(String updatedName,String password){
        
    }

    public void validateUpdatedFirstName(){
        
    }

    public void savePersonalInformationChanges(){
        click(btnSave);
    }
    public void validateSuccessMessage(){
        if (driver.findElements(successMessage).size() != 0)
            addStepLog("Success Message displayed as expected");
        else
            addStepError("Success message is not displayed");
    }

}
