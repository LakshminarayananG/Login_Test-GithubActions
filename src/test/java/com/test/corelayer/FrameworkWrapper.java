/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   Wrapper class which acts as an core abstraction layer for all the framework utilities
        * author   :   Lakshminarayanan Ganesan
--------------------------------------------------------------------------------------------------------------*/
package com.test.corelayer;

import com.test.corelayer.drivermanager.DriverManager;
import com.test.corelayer.libraries.web.BrowserActions;
import com.test.corelayer.logging.LoggingUtilities;
import com.test.corelayer.testdata.PropertySettings;
import com.test.corelayer.testdata.TestParameters;
import com.test.orchestrationlayer.enums.Browser;
import com.test.orchestrationlayer.enums.Context;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FrameworkWrapper {

    private static final ThreadLocal<TestParameters> testParameters = new ThreadLocal<TestParameters>();
    private static Map<String, Object> scenarioContext;

    /* Getter and setter methods for web driver
       @Author : Lakshminarayanan Ganesan
    */
    public static void setWebDriver(Browser browser) {
        DriverManager.getInstance().instantiateWebDriver(browser);
        browser().implicitlyWait(Integer.parseInt(globalProperties().getProperty("defaultWait")));

    }
    public static WebDriver getWebDriver() {
        return DriverManager.getInstance().getDriver();
    }

    /* Initialisation method , Getter and setter methods for Scenario context -
       To transfer data between the step definitions
       @Author : Lakshminarayanan Ganesan
    */
    public static void ScenarioContext() {
        scenarioContext = new HashMap<>();
    }
    public void setScenarioContext(Context key, Object value) {
        scenarioContext.put(key.toString(), value);
    }
    public Object getScenarioContext(Context key) {
        return scenarioContext.get(key.toString());
    }

    /* Getter and setter methods for Test Parameters read from the TestNG xml and cucumber scenario object
       @Author : Lakshminarayanan Ganesan
    */
    public static void setParameters(TestParameters parameters) {
        testParameters.set(parameters);
    }
    public TestParameters getTestParameters() {
        return testParameters.get();
    }

    /* Function to return all the browser action methods
       @param  : n/a
       @return : n/a
       @Author : Lakshminarayanan Ganesan
    */
    public static BrowserActions browser() {
        return  BrowserActions.getInstance();
    }

    /* Function to return all the logging methods
       @param  : n/a
       @return : n/a
       @Author : Lakshminarayanan Ganesan
    */
    public static LoggingUtilities logger() {
        return new LoggingUtilities();
    }

    /* Function to return all the global settings properties
       @param  : n/a
       @return : n/a
       @Author : Lakshminarayanan Ganesan
    */
    public static Properties globalProperties() {
        return  PropertySettings.getGlobalSettingsPropertyInstance();
    }

}
