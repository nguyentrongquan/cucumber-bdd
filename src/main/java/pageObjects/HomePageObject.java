package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.HomePageUI;

public class HomePageObject  extends AbstractPage {
	private WebDriver driver;
	
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}


	public boolean isWelcomeMessageDisplay() {
		waitElementVisible(driver,HomePageUI.WELCOME_MESSAGE);
		return isElementDisplayed(driver, HomePageUI.WELCOME_MESSAGE);
		
		
	}
}
