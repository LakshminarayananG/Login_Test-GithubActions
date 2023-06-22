/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   To set and get the execution parameters - browsers, execution mode from testng xml and
                       scenario object from cucumber
        * author   :   Lakshminarayanan Ganesan
--------------------------------------------------------------------------------------------------------------*/

package com.test.corelayer.testdata;

import com.test.orchestrationlayer.enums.Browser;
import com.test.orchestrationlayer.enums.ExecutionMode;

import io.cucumber.java.Scenario;

public class TestParameters {

    private ExecutionMode executionMode;
    private Browser browser;
    private Scenario scenario;
    private static final TestParameters instance = new TestParameters();

    // Singleton pattern to get the Test Parameter instance
    private TestParameters() {
    }
    public static TestParameters getInstance() {
        return instance;
    }

    /* Getter and setter methods for Test Execution mode
       @Author : Lakshminarayanan Ganesan
    */
    public ExecutionMode getExecutionMode() {
        return this.executionMode;
    }
    public void setExecutionMode(ExecutionMode executionMode) {
        this.executionMode = executionMode;
    }

    /* Getter and setter methods for Browser
       @Author : Lakshminarayanan Ganesan
    */
    public Browser getBrowser() {
        return this.browser;
    }
    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    /* Getter and setter methods for Scenario object
       @Author : Lakshminarayanan Ganesan
    */
    public Scenario getScenario() {
        return this.scenario;
    }
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }


}
