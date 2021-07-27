package bank.stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.DataHelper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

public class RegisterPageSteps {
	WebDriver driver;
	//static String username, password;
	RegisterPageObject registerPage;
	DataHelper dataTest;
	TestContext testContext;
	
	
	public RegisterPageSteps(TestContext testContext) {
		this.driver = Hooks.openAndQuitBrowser();
		this.testContext = testContext;
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		dataTest = DataHelper.getData();
		
	}

	@When("^Input to Email textbox$")
	public void inputToEmailTextbox() throws Throwable {
		registerPage.inputToEmailTextbox(dataTest.getEmail());
	}

	@When("^click to Submit$")
	public void clickToSubmit() throws Throwable {
		registerPage.clickToSubmitButton();
	}

	@Then("^get User and Password infor$")
	public void getUserAndPasswordInfor() throws Throwable {
		testContext.getDataContext().setContext(Context.USER_ID, registerPage.getUserIDText());
		testContext.getDataContext().setContext(Context.PASSWORD, registerPage.getPasswordText());
		
	//username = registerPage.getUserIDText();
	//	password = registerPage.getPasswordText();
	}

	@When("^Back to Login page$")
	public void backToLoginPage() throws Throwable {
		registerPage.openLoginPage(testContext.getDataContext().getContext(Context.LOGIN_URL), driver);
//		registerPage.openLoginPage(LoginPageSteps.loginPageUrl, driver);
	}
}
