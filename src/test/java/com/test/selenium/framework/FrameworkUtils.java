package com.test.selenium.framework;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.test.selenium.enums.Context;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class FrameworkUtils {

    protected WebDriver driver = DriverManager.getInstance().getDriver();
    protected static Properties pageTitleProperties = Settings.getPageTitle();
    

    /*    Function to attach screen shot to Extent report
          @param  : Cucumber Scenario
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */

    public void addScreenshot(Scenario scenario) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
        scenario.attach(fileContent, "image/png", "screenshot");
        

    }
    
    
    
    

    /*    Function to add message to log and Extent report
          @param  : String message
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */

    public static void addStepLog(String message) {

        Calendar calendar = Calendar.getInstance();
        ExtentCucumberAdapter.addTestStepLog(message);
        Logger logger = LoggerFactory.getLogger(calendar.getTime() + "\t" + "Step");
        logger.info(message);


    }

    /*    Function to add error message to log and Extent report
          @param  : String message
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */

    public static void addStepError(String message) {

        Calendar calendar = Calendar.getInstance();
        ExtentCucumberAdapter.addTestStepLog(message);
        Logger logger = LoggerFactory.getLogger(calendar.getTime() + "\t" + "Step");
        logger.error(message);
        Assert.fail(message);

    }

    /*    Functions to add and transfer data between step definitions
          @param  : String message
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */

    public void setScenarioContext(Context key, Object value){
        DriverManager.getInstance().setContext(key,value);
    }

    public String getScenarioContext(Context key){
        return DriverManager.getInstance().getContext(key).toString().trim();
    }

    /*    Function to make driver sleep / wait for particular time
          @param  : time in milliseconds
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */

    public void waitFor(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*    Function to make the driver wait until element visible
          @param  : By , timeout in seconds
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */

    public void waitUntilElementVisible(By by, long timeOutInSeconds) {
        (new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /*    Function to make the driver wait until element clickable
          @param  : By , timeout in seconds
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */

    public void waitUntilElementClickable(By by, long timeOutInSeconds) {
    	 (new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)))
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    /*    Function to wait until the page loads completely
          @param : timeOutInSeconds
          @return : n/a
          @Author : Lakshminarayanan Ganesan
     */
    public void waitForPageLoad(long timeOutInSeconds) {

        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(pageLoadCondition);
        } catch (Exception e) {
            addStepError("Timeout waiting for Page Load Request to complete.<br><b>Exception : </b>" + e);
        }

    }

    /*    Function to enter value in text field
          @param : by , String value to be entered
          @return : n/a
          @Author : Lakshminarayanan Ganesan
     */

    public void enterValue(By by, String Value) {
        try {
            waitUntilElementVisible(by, 90);
            WebElement field = driver.findElement(by);
            if (field.isDisplayed()) {
                try {
                    field.clear();
                } catch (Exception e) {
                    addStepLog("Warning - Element must be user-editable in order to clear it");
                }
                field.sendKeys(Value);
                addStepLog(Value + " entered in " + by);
            }

        } catch (NoSuchElementException e) {
            addStepError("Unable to find the element -- " + by + " The exception message is : "
                    + e.getMessage());
        } catch (Exception e) {
            addStepError("Operation Failed, Exception: " + e.getMessage());
        }
    }

    /*    Function to click on a webelement
          @param  : By
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */

    public void click(By by) {
        try {
            waitUntilElementClickable(by,20);
            driver.findElement(by).click();
        }
        catch (Exception e){
            addStepError("unable to click the element :" +by+ " caused by exception : " + e);
        }
        waitForPageLoad(20);
    }

    /*    Function to click on a webelement using javscript
          @param  : By
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */

    public void clickUsingJS(By by, String name) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(by));
            addStepLog(name + " is clicked");
        } catch (NoSuchElementException e) {
            addStepError(name + " is not clicked" + e.getMessage());
        }
    }

    /*    Function to select a specified value from the list
          @param  : By and String item to select
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */
    public void selectListItem(By by, String item) {
        waitUntilElementVisible(by,20);
        Select dropDownList = new Select(driver.findElement(by));
        dropDownList.selectByVisibleText(item);
    }

    /*    Function to scroll to a specific locator
          @param  : By
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */

    protected void scrollByLocator(By by ) {

            try {
                waitUntilElementVisible(by, 30);
                WebElement element = driver.findElement(by);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
//                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-400);");
                addStepLog("Page Scrolled down to " +by+ " successfully!");
            } catch (Exception e) {
                addStepError("Operation Failed with Exception:" + e.getMessage());
            }
        }

    /*    Function to mouse hover to a webelement
      @param  : By and message
      @return : n/a
      @Author : Lakshminarayanan Ganesan
    */

    protected void mouseHoverTo(By by,String msgdesc) {
        try {
            waitUntilElementVisible(by, 20);
            if(driver.findElement(by).isEnabled()) {
                Actions actions = new Actions(driver);
                actions.moveToElement(driver.findElement(by)).build().perform();
                addStepLog(msgdesc + " is hovered");
            }

        } catch (NoSuchElementException e) {
            addStepError("Unable to find the element -- " + by +". The exception message is :" + e.getMessage());

        } catch (Exception e1) {
            addStepError("Operation Failed and Exception:" + e1.getMessage());
        }
    }


    /*    Function to Validate if Element present
          @param  : By
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */

    protected void validateText(String actual , String expected) {

        if (actual.equals(expected))
            addStepLog("Actual and Expected text match : " + actual);
        else
            addStepError("Actual Text : " + actual + " is not same as expected : " + expected);

    }
    
    /*    Function to Validate if Element present
    @param  : By
    @return : n/a
    @Author : Lakshminarayanan Ganesan
*/

	protected void validateTextCase(String actual , String expected) {
	
	  if (actual.equalsIgnoreCase(expected))
	      addStepLog("Actual and Expected text match : " + actual);
	  else
	      addStepError("Actual Text : " + actual + " is not same as expected : " + expected);
	
	}

    protected void validateTextContains(String actual , String expected) {

        if (actual.contains(expected))
            addStepLog("Actual and Expected text match : " + actual);
        else
            addStepError("Actual Text : " + actual + " is not same as expected : " + expected);

    }

    /*    Function to get text attribute from a webElement
          @param  : By
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */
    protected String getText(By by) {
        try {
            waitUntilElementVisible(by, 10);
            WebElement element = driver.findElement(by);
            String result = element.getText().toString().trim();
            return result;
        } catch (NoSuchElementException e) {
            addStepError("Unable to find the element -- " + by +". The exception message is :" + e.getMessage());
            return null;

        } catch (Exception e1) {
            addStepError("Operation Failed and Exception:" + e1.getMessage());
            return null;
        }
    }

    /*    Function to generate random string
          @param  : characters and length
          @return : n/a
          @Author : Lakshminarayanan Ganesan
    */

    protected String getRandomString(String chars, int length) {
        StringBuilder x = new StringBuilder();
        Random rnd = new Random();
        while (x.length() < length) {
            int index = (int) (rnd.nextFloat() * chars.length());
            x.append(chars.charAt(index));
        }
        String Str = x.toString();
        return Str;

    }

    
    /*    Function to return the page title
    @param  : Cucumber Scenario
    @return : n/a
    @Author : Lakshminarayanan Ganesan
*/

	protected String getPageTitle() {
		String pagetitle = driver.getTitle();
		System.out.println(pagetitle);
	  return pagetitle;
	}

}

