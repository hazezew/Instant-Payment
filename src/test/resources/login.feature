#  Author: Habitamu Azezew
#  Date: May 16, 2023
#  Project name: Instant Payment System
#  Module: User Management
#  Activity: Login
#  Project owner: EthSwitch
#  Vendor: PBC
#  language: en
#  Test Environment: EthSwitch Test

@Regression
@UserManagement

Feature: User Login
  Background: user opened web browser and maximizes it
    When user has entered Instant Payment System URL

    Scenario: Login with valid username and password
      And user enters "admin" into username textfield
      And user enters "admin1" into password textfield
      And user clicks on Login button
      Then Instant Payment System displays Admin dashboard page
      And on Admin dashboard page user clicks on logged in user profile link
      And on Admin dashboard page user clicks on Logout link
      And user clicks on Yes button on popup alert to confirm the logout
      Then Instant Payment system logs out and displays login page

  Scenario: Login with with empty username
    And user enters "admin1" into password textfield
    And user clicks on Login button
    Then login page displays username validation message "Username required."

  Scenario: Login with with empty password
    And user enters "admin" into username textfield
    And user clicks on Login button
    Then login page displays password validation message "Password required."

  Scenario: Login with valid username but password
    And user enters "admin" into username textfield
    And user enters "admin123456" into password textfield
    And user clicks on Login button
    Then login page displays password validation message "Invalid username or password."

  Scenario: Login with invalid username but password
    And user enters "admin1234" into username textfield
    And user enters "admin1" into password textfield
    And user clicks on Login button
    Then login page displays password validation message "Invalid username or password."
