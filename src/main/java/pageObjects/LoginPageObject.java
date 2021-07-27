package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	private WebDriver driver;
	
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}


	public String getLoginPageUrl() {
		return getCurentUrl(driver);
	}


	public RegisterPageObject clickToHereLink(WebDriver driver) {
		waitElementClickable(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
		
	}


	public void inputToUserIDTextbox(String userValue) {
		waitElementVisible(driver, LoginPageUI.USERID_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERID_TEXTBOX, userValue);
		
	}


	public void inputToPasswordTextbox(String passwordValue) {
		waitElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passwordValue);
	}


	public HomePageObject clickToLoginButton(WebDriver driver) {
		waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
		
	}


}
