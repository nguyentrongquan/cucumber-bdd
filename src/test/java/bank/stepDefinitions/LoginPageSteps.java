package bank.stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;

public class LoginPageSteps {
	WebDriver driver;
//	static String loginPageUrl;
	LoginPageObject loginPage;
	TestContext testContext;
	
	public LoginPageSteps(TestContext testContext) {
		this.driver = Hooks.openAndQuitBrowser();
		this.testContext = testContext;
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}
	
	@Given("^Get login page Url$")
	public void getLoginPageUrl() throws Throwable {
//		loginPageUrl = loginPage.getLoginPageUrl();
		testContext.getDataContext().setContext(Context.LOGIN_URL, loginPage.getLoginPageUrl()); 
	}
	@Given("^Open Register page$")
	public void openRegisterPage() throws Throwable {
		loginPage.clickToHereLink(driver);
	}
	
	@When("^Submit valid infor to login form$")
	public void submitValidInforToLoginForm() throws Throwable {
		loginPage.inputToUserIDTextbox(testContext.dataContext.getContext(Context.USER_ID));
		loginPage.inputToPasswordTextbox(testContext.dataContext.getContext(Context.PASSWORD));
//		loginPage.inputToUserIDTextbox(RegisterPageSteps.username);
//		loginPage.inputToPasswordTextbox(RegisterPageSteps.password);
		loginPage.clickToLoginButton(driver);
	}

}
