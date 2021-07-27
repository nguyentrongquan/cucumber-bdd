package pageUI;

public class CommonPageUI {
	//Textbox
	public static final String DYNAMIC_TEXTBOX="//td[text()='%s']//following-sibling::td//input";
	
	//TextArea
	public static final String DYNAMIC_TEXTAREA="//td[text()='%s']//following-sibling::td//textarea";
	
	//Radio button
	public static final String DYNAMIC_RADIO_BUTTON="//input[@type='radio' and @value='%s']";
	
	//Link
	public static final String DYNAMIC_LINK="//a[text()='%s']";
	
	// Button
	public static final String DYNAMIC_BUTTON="//input[@Value='%s']";
	
	//Message/title
	public static final String DYNAMIC_MESSAGE="//p[@class='heading3' and text()='%s']";
	
	//Message error
	public static final String DYNAMIC_ERROR_MESSAGE="//td[text()='%s']//following-sibling::td//label";
	
	//column value
	public static final String DYNAMIC_BY_ROW_NAME="//td[contains(text(),'%s')]//following-sibling::td";
	
	
	
	
}
