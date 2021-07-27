package bank.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import commons.DataHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.CommonPageObject;
import pageObjects.PageGeneratorManager;

public class CommonPageSteps {
	WebDriver driver;
	CommonPageObject commonPage;
	DataHelper dataFaker;
	String email;
	
	public CommonPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
		commonPage = PageGeneratorManager.getCommonPage(driver);
		dataFaker = DataHelper.getData();
		email =dataFaker.getEmail();
	}
	@Given("^Open \"([^\"]*)\" page$")
	public void openPage(String pageName) throws Throwable {
		commonPage.clickToDynamicLink(driver, pageName);
	   
	}

	@When("^Input to \"([^\"]*)\" textbox with value \"([^\"]*)\"$")
	public void inputToTextboxWithValue(String fieldName, String inputValue) throws Throwable {
		if(fieldName.equals("E-mail"))
		{
			inputValue =email;
		}
		commonPage.inputToDynamicTextbox(driver, fieldName, inputValue);
	   
	}

	@When("^Click to \"([^\"]*)\" radio button$")
	public void clickToRadioButton(String radioButtonValue) throws Throwable {
		commonPage.clickToDynamicRadioButton(driver, radioButtonValue);
	   
	}

	@When("^Input to \"([^\"]*)\" textarea with value \"([^\"]*)\"$")
	public void inputToTextareaWithValue(String fieldName, String inputValue) throws Throwable {
	   commonPage.inputToDynamicTextarea(driver, fieldName, inputValue);
	   
	}

	@When("^Click to \"([^\"]*)\" button$")
	public void clickToButton(String buttonValue) throws Throwable {
		commonPage.clickToDynamicButton(driver, buttonValue);
	   
	}

	@Then("^Success message displayed with \"([^\"]*)\"$")
	public void successMessageDisplayedWith(String expectedMessage) throws Throwable {
		Assert.assertTrue(commonPage.isDynamicMessageDisplayed(driver, expectedMessage));
	}

	@Then("^The valid text displayed at \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void theValidTextDisplayedAtWithValue(String rowName, String expectedValue) throws Throwable {
		if(rowName.equals("Email"))
		{
			expectedValue =email;
		}
		Assert.assertEquals(commonPage.getDynamicValueRowName(driver, rowName),expectedValue);
	   
	}
}
