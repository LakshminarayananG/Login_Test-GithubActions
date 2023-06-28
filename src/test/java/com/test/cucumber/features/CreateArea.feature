Feature: Create Area Test

  @Smoke @Regression @CreateArea
  Scenario Outline: Login to Testo Application & create area
    Given I launch the <application>
    And I login to the application using <userName>, <password> and validate Login page Title
    And I am in the Dashboard page and validate if the login is with right user
    And I navigate to Areas
    And I create an area
    Then I validate the created area is displayed
    
    
    Examples:
      |application        |userName              						 |password    |
      |testo              |saveris.rrm+jobTest.qa1@gmail.com |testJobTA123|