package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}
	public static EditCustomerPageIObject getEditCustomerPage(WebDriver driver) {
		return new EditCustomerPageIObject(driver);
	}
	
	public static CommonPageObject getCommonPage(WebDriver driver) {
		return new CommonPageObject(driver);
	}
	
	
}
