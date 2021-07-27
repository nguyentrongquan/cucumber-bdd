package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.DataHelper;
import pageUI.CommonPageUI;

public class CommonPageObject extends AbstractPage {
	WebDriver driver;
	
	public CommonPageObject(WebDriver driver) {
		this.driver = driver;
		
	}
	public void inputToDynamicTextbox(WebDriver driver, String fieldName, String inputValue) {
		waitElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX, fieldName);
		if(fieldName.equals("Date of Birth")) {
			removeAttributeInDOM(driver, CommonPageUI.DYNAMIC_TEXTBOX, "type", fieldName);
		}
		sendkeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX, inputValue, fieldName);
		
	}
	public void inputToDynamicTextarea(WebDriver driver, String fieldName, String inputValue) {
		waitElementVisible(driver, CommonPageUI.DYNAMIC_TEXTAREA, fieldName);
		sendkeyToElement(driver, CommonPageUI.DYNAMIC_TEXTAREA, inputValue, fieldName);	
	}
	public void clickToDynamicRadioButton(WebDriver driver, String radioButtonValue) {
		waitElementClickable(driver, CommonPageUI.DYNAMIC_RADIO_BUTTON, radioButtonValue);
		clickToElement(driver, CommonPageUI.DYNAMIC_RADIO_BUTTON, radioButtonValue);
	}
	public void clickToDynamicButton(WebDriver driver, String buttonValue) {
		waitElementClickable(driver, CommonPageUI.DYNAMIC_BUTTON, buttonValue);
		clickToElement(driver, CommonPageUI.DYNAMIC_BUTTON, buttonValue);
	}
	public void clickToDynamicLink(WebDriver driver, String linkPageName) {
		waitElementClickable(driver, CommonPageUI.DYNAMIC_LINK, linkPageName);
		clickToElement(driver, CommonPageUI.DYNAMIC_LINK, linkPageName);
	}
	public boolean isDynamicMessageDisplayed(WebDriver driver, String messageText) {
		waitElementVisible(driver,CommonPageUI.DYNAMIC_MESSAGE,messageText);
		return isElementDisplayed(driver,CommonPageUI.DYNAMIC_MESSAGE,messageText);
		
	}
	public String getDynamicValueRowName(WebDriver driver, String rowName) {
		waitElementVisible(driver,CommonPageUI.DYNAMIC_BY_ROW_NAME, rowName);
		return getTextElement(driver, CommonPageUI.DYNAMIC_BY_ROW_NAME, rowName);
	}

}
