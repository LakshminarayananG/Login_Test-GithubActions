package com.test.selenium.pageactions;

import com.test.selenium.enums.Context;
import com.test.selenium.pagelocators.OrderConfirmation;

public class OrderConfirmationActions extends OrderConfirmation {

    public void navigateToTshirts() {
        click(megaMenuTshirt);
    }

    public String getProductPrice() {
		return null;
        
    }

    public void addToCartFromPlp() {
        click(btnAddToCart);
    }

    public void validateCartPrice() {
        
    }
    public void getCartAttributes() {
        

    }

    public void clickProceedToCheckout() {
        click(btnProceedToCheckOut);
    }

    public void validateSummaryPageAttributes(){
        
    }

    public void clickProceedToCheckoutInSummary() {
        click(btnSummaryProceedToCheckOut);
    }

    public void clickProceedToCheckoutInAddressPage() {
        click(btnAddressProceedToCheckOut);
    }

    public void selectPaymentMethod() {
        click(paymentMode);
        
    }

    public void checkTermsandConditions() {
        driver.findElement(checkBoxTermsOfService).click();
    }

    public void confirmOrder() {
        click(btnOrderConfirmation);
    }
    public void getOrderRef(){

        String orderRef = driver.findElement(orderReferenceNumber).getText().trim();
        int end = orderRef.indexOf(" in the subject of your bank wire.");
        orderRef = orderRef.substring(end-9,end);
        addStepLog("order Reference number is : " +orderRef);
        

    }

    public void clickProceedToCheckoutInShippingPage() {
        click(btnShippingConfirmation);
    }
}
