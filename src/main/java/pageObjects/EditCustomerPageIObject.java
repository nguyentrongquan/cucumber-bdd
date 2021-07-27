package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.HomePageUI;

public class EditCustomerPageIObject  extends AbstractPage {
	private WebDriver driver;
	
	
	public EditCustomerPageIObject(WebDriver driver) {
		this.driver = driver;
	}

}
