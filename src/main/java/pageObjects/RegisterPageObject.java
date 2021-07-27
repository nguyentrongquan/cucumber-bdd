package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	private WebDriver driver;
	
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}


	public void inputToEmailTextbox(String emailValue) {
		waitElementVisible(driver,RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailValue);
		
	}


	public void clickToSubmitButton() {
		waitElementClickable(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
		
	}


	public String getUserIDText() {
		
		waitElementVisible(driver, RegisterPageUI.USERID_TEXT);
		return getTextElement(driver,RegisterPageUI.USERID_TEXT);
		
	}


	public String getPasswordText() {
		waitElementVisible(driver, RegisterPageUI.PASWORD_TEXT);
		return getTextElement(driver,RegisterPageUI.PASWORD_TEXT);
	}


	public LoginPageObject openLoginPage(String loginPageUrl,WebDriver driver) {
		openPageUrl(driver,loginPageUrl);
		return PageGeneratorManager.getLoginPage(driver);
		
		
	}


}
