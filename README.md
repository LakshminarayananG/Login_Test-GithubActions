# Test Login

## Steps to execute the Test

1. From command line   : mvn clean test -PrunRegressionTest    ( make sure Java and maven are installed in the machine)

2. Using an IDE (Eclipse or Intellij )    : Run from the TestNG file in the path src/test/resources/TestNGXMLs/ (use RegressionTest.xml for regression tests and SmokeTest.xml for smoke tests)

3. Parallel execution can be achieved by modifying the data-provider-thread-count value in the testing XML file (Grid configurations yet to be done. However parallel execution in a single machine can be achieved )


## Reports

Extent HTML and Spark reports are configured and will be available in /Reports folder


## Configuration related to automation design

This is a hybrid framework buit using different component - Selenium with Java Binding , TestNG , Cucumber , Page Object Model , Singleton and factory design pattern with extent report

1. Property files in src/test/resources/ path
	-Application url and other application related properties are maintained in GlobalSettings.properties file
	-Extent , cucumber reporting configs maintained in corresponding property files

2. Execution Mode and Browser parameters are maintained in TestNG XMLS and valid values in enum (src/test/java/com.test.selenium.enums) should be used

3. Cucumber framework related components (Feature , Stepdefinition, runner files) maintained in package com.test.cucumber

4. Selenium framework related components maintained in package com.test.selenium

5. Design Framework hierarchy (multi level inheritance) is maintained as below

	FrameworkUtils --> pagelocators --> pageactions --> businesscomponents --> stepdefinitions 

6. CukeHooks class is used to manage the @Before and @After Scenario functions related to cucumber scenario logic

7. TestNG listener is the core driving component used to control the flow of execution

8. WebDriverManager Used for picking the driver file automatically

9. Can be easily integrated with Azure / AWS / Jenkins or any pipeline using the maven command provided in step 1 after git clone (parameterising can also be done)

10. Github actions is configured to run the login test on a daily basis inside a docker container