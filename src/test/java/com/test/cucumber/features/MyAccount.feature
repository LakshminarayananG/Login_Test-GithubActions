Feature: My Account

  @Smoke @Regression
  Scenario Outline: Create Area in Testo
    Given I launch the <application>
    And I login to the application using <userName> and <password>
    
    Examples:
      |application        |userName              						 |password    |
      |testo              |saveris.rrm+jobTest.qa1@gmail.com |testJobTA123|