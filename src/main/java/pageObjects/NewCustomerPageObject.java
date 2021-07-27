package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.HomePageUI;

public class NewCustomerPageObject  extends AbstractPage {
	private WebDriver driver;
	
	
	public NewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
