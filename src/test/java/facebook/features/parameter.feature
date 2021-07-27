@parameter
Feature: Parameter Feature
	
  @no_param
  Scenario: Scenario no have parameter
    When Input to Username textbox
    And Input to Password textbox
    And click to Submit button

  @param_mark
  Scenario: Scenario have parameter
    When Input to Username textbox with "automationtest@gmail.com"
    And Input to Password textbox with "123456"
    And click to Submit button

  @param_no_mark
  Scenario: Scenario have parameter
    When Input to Username textbox with automationtest@gmail.com
    And Input to Password textbox with 123456
    And click to Submit button

  @mutiple_param
  Scenario: Scenario mutiple param
    When Input to Username with "automationtest@gmail.com" and Password with "123456"
    And click to Submit button

