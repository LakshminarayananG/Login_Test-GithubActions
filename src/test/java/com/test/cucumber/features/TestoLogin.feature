Feature: Login to Testo Application

  @Smoke @Regression @Login
  Scenario Outline: Login to Testo Application & validate
    Given I launch the application
    And I login to the application using and validate Login page Title
    Then I am in the Dashboard page and validate if the login is with right user