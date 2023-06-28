Feature: Login to Testo Application

  @Smoke @Regression @Login
  Scenario Outline: Login to Testo Application & validate
    Given I launch the <application>
    And I login to the application using <userName>, <password> and validate Login page Title
    Then I am in the Dashboard page and validate if the login is with right user
    
    Examples:
      |application        |userName              						 |password    |
      |testo              |saveris.rrm+jobTest.qa1@gmail.com |testJobTA123|