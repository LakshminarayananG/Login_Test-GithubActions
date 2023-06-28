Feature: Login to Testo Application

  @Smoke @Regression @Login
  Scenario Outline: Login to Testo Application & validate
    Given I launch the <application>
    And I login to the application using <userName>, <password> and validate <pageTitle>
    And I am in the <landing> page and validate if the login is with right user
    
    Examples:
      |application        |userName              						 |password    |pageTitle |landing  |
      |testo              |saveris.rrm+jobTest.qa1@gmail.com |testJobTA123|Login     |Dashboard|