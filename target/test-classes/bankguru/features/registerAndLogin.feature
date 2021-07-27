@customer
Feature: Register/ Login and new Customer

  @register_login
  Scenario: Regiter to system and login
    # Open Url -> Hooks
    Given Get login page Url
    When Open Register page
    And Input to Email textbox
    And click to Submit
    Then get User and Password infor
    When Back to Login page
    And Submit valid infor to login form
    Then Home page displayed

  @new_customer
  Scenario Outline: New Customer
    Given Open "New Customer" page
    When Input to "Customer Name" textbox with value "<Customer Name>"
    And Click to "f" radio button
    And Input to "Date of Birth" textbox with value "<Dob In>"
    And Input to "Address" textarea with value "<Address>"
    And Input to "City" textbox with value "<City>"
    And Input to "State" textbox with value "<State>"
    And Input to "PIN" textbox with value "<Pin>"
    And Input to "Mobile Number" textbox with value "<Phone>"
    And Input to "E-mail" textbox with value "<Email>"
    And Input to "Password" textbox with value "<Password>"
    
    And Click to "Submit" button
    
    Then Success message displayed with "Customer Registered Successfully!!!"
    
    And The valid text displayed at "Customer Name" with value "<Customer Name>"
    And The valid text displayed at "Gender" with value "<Gender>"
    And The valid text displayed at "Birthdate" with value "<Dob Out>"
    And The valid text displayed at "Address" with value "<Address>"
    And The valid text displayed at "City" with value "<City>"
    And The valid text displayed at "State" with value "<State>"
    And The valid text displayed at "Pin" with value "<Pin>"
    And The valid text displayed at "Mobile No." with value "<Phone>"
    And The valid text displayed at "Email" with value "<Email>"
 

    Examples: 
      | Customer Name | Dob In     | Dob Out    | Gender | Address  | City        | State   | Pin    | Phone      | Email             | Password |
      | Tony Teo      | 09/13/1995 | 1995-09-13 | female | 08 LeLai | Ho Chi Minh | Thu Duc | 600000 | 0974111222 | Tonyteo					  |   123456 |
