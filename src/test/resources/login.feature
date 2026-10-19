Feature: Login

  #Positive Test Case
  Scenario: Successful login with valid credentials
    Given User is on login page
    When User enters username "standard_user" and password "secret_sauce"
    And User clicks the login button
    Then User should be redirected to homepage

  #Negative Test Case
  Scenario: Failed login with invalid credentials
    Given User is on login page
    When User enters username "standard_user" and password "invalid_password"
    And User clicks the login button
    Then User should see an error message "Epic sadface: Username and password do not match any user in this service"

    #Empty Input Test Case
  Scenario: Failed login with empty credentials
    Given User is on login page
    When User enters username "" and password ""
    And User clicks the login button
    Then User should see an error message "Epic sadface: Username is required"