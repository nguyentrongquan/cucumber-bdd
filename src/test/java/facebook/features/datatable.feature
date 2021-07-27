@datatable
Feature: Datatable Teature

  @datatable_step
  Scenario Outline: Create new Customer with email <Username>
    When Input to Username and Password
      | Username                   | Password |
      | automationtest01@gmail.com |   123456 |
      | automationtest02@gmail.com |   123456 |
      | automationtest03@gmail.com |   123456 |
    And click to Submit button

    Examples: 
      | Username                 | Password |
      | automationtest@gmail.com |   123456 |

  @datatable_scenario
  Scenario Outline: Data Table in Scenario
    When Input to Username textbox with <Username>
    And Input to Password textbox with <Password>
    And click to Submit button

    Examples: 
      | Username               | Password |
      | automation01@gmail.com |   123456 |
      | automation02@gmail.com |   123456 |
      | automation03@gmail.com |   123456 |
      | automation04@gmail.com |   123456 |
