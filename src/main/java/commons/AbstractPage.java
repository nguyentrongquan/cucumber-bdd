package commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUI.CommonPageUI;

public abstract class AbstractPage {

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public void setImplicitWait(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public String getCurentUrl(WebDriver driver) {
		return driver.getCurrentUrl();

	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();

	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();

	}

	public void refreshCurrenPage(WebDriver driver) {
		driver.navigate().refresh();

	}

	public void fowardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String getAlertText(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void sendkeyToAlert(WebDriver driver, String text) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.sendKeys(text);
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public WebElement find(WebDriver driver, String xpathValue) {
		return driver.findElement(By.xpath(xpathValue));
	}

	public List<WebElement> finds(WebDriver driver, String xpathValue) {
		return driver.findElements(By.xpath(xpathValue));
	}

	public By byXpath(String xpathValue) {
		return By.xpath(xpathValue);
	}

	public void clickToElement(WebDriver driver, String xpathValue) {
		if(driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, xpathValue);
			sleepInSecond(3);
		}else {
			find(driver, xpathValue).click();
		}
		
	}

	public void clickToElement(WebDriver driver, String xpathValue, String... values) {
		if(driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, castToObject(xpathValue, values));
			sleepInSecond(3);
		}else {
			find(driver, castToObject(xpathValue, values)).click();
		}
		
		
	}

	public String castToObject(String xpathValue, String... values) {
		return String.format(xpathValue, (Object[]) values);
	}

	public void sendkeyToElement(WebDriver driver, String xpathValue, String text) {

		element = find(driver, xpathValue);
		element.clear();
		element.sendKeys(text);

	}

	public void sendkeyToElement(WebDriver driver, String xpathValue, String text, String... values) {

		element = find(driver, castToObject(xpathValue, values));
		element.clear();
		element.sendKeys(text);

	}

	public void selectItemInDropdown(WebDriver driver, String xpathValue, String itemValue) {
		select = new Select(find(driver, xpathValue));
		select.selectByVisibleText(itemValue);

	}

	public String getSelectedItemInDropdown(WebDriver driver, String xpathValue) {
		select = new Select(find(driver, xpathValue));
		return select.getFirstSelectedOption().getText();

	}

	public boolean isDropdownMutiple(WebDriver driver, String xpathValue) {
		select = new Select(find(driver, xpathValue));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
			String expectedItem) {
		find(driver, parentLocator).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childItemLocator)));

		List<WebElement> allItems = finds(driver, childItemLocator);

		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public String getAtributeValue(WebDriver driver, String xpathValue, String attributeName) {
		return find(driver, xpathValue).getAttribute(attributeName);
	}

	public String getTextElement(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).getText();

	}

	public String getTextElement(WebDriver driver, String xpathValue, String... values) {
		return find(driver, castToObject(xpathValue, values)).getText();

	}

	public int countElementNumber(WebDriver driver, String xpathValue) {
		return finds(driver, xpathValue).size();

	}

	public int countElementNumber(WebDriver driver, String xpathValue, String... values) {
		return finds(driver, castToObject(xpathValue, values)).size();

	}

	public void checkTheCheckbox(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		if (!element.isSelected()) {
			element.click();
		}

	}

	public void uncheckToTheCheckbox(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		if (element.isSelected()) {
			element.click();
		}

	}

	public boolean isElementDisplayed(WebDriver driver, String xpathValue) {
		try {
			return find(driver, xpathValue).isDisplayed();
		} catch (NoSuchElementException noSuchException) {
			return false;
		}
		

	}
	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		
		driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
		
	}
	public boolean isElementUndisplayed(WebDriver driver, String xpathValue) {
		overrideGlobalTimeout(driver,GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = driver.findElements(By.xpath(xpathValue));
		
		if(elements.size() == 0) {
			System.out.println("Element not in DOM");
			overrideGlobalTimeout(driver,GlobalConstants.LONG_TIMEOUT);
			return true;
		}else if(elements.size() >0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in Dom but not visible/displayed");
			overrideGlobalTimeout(driver,GlobalConstants.LONG_TIMEOUT);
			return true;
		}else {
			System.out.println("Element in Dom and visible/displayed");
			overrideGlobalTimeout(driver,GlobalConstants.LONG_TIMEOUT);
			return false;
		}
		
	}
	public boolean isElementUndisplayed(WebDriver driver, String xpathValue, String... values) {
		overrideGlobalTimeout(driver,GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = driver.findElements(By.xpath(castToObject(xpathValue, values)));
		
		if(elements.size() == 0) {
			System.out.println("Element not in DOM");
			overrideGlobalTimeout(driver,GlobalConstants.LONG_TIMEOUT);
			return true;
		}else if(elements.size() >0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in Dom but not visible/displayed");
			overrideGlobalTimeout(driver,GlobalConstants.LONG_TIMEOUT);
			return true;
		}else {
			System.out.println("Element in Dom and visible/displayed");
			overrideGlobalTimeout(driver,GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathValue, String... values) {
		return find(driver, castToObject(xpathValue, values)).isDisplayed();

	}

	public boolean isElementSelected(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isSelected();

	}

	public boolean isElementEnabled(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isEnabled();

	}

	public void switchToFrameOrIframe(WebDriver driver, String xpathValue) {
		driver.switchTo().frame(find(driver, xpathValue));

	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String xpathValue) {
		action = new Actions(driver);
		action.moveToElement(find(driver, xpathValue)).perform();

	}

	public void sendkeyBoardToElement(WebDriver driver, String xpathValue, Keys key) {
		action = new Actions(driver);
		action.sendKeys(find(driver, xpathValue), key).perform();

	}

	public void sendkeyBoardToElement(WebDriver driver, String xpathValue, Keys key, String... values) {
		action = new Actions(driver);
		action.sendKeys(find(driver, castToObject(xpathValue, values)), key).perform();

	}

	public Object executeForBrowser(String javaSript) {
		return jsExecutor.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	// Element
	public void highlightElement(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		String originalStyle = element.getAttribute("style");
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);

	}

	public void clickToElementByJS(WebDriver driver, String xpathValue) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", find(driver, xpathValue));
	}

	public void scrollToElement(WebDriver driver, String xpathValue) {

		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", find(driver, xpathValue));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xpathValue, String value) {

		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", find(driver, xpathValue));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathValue, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", find(driver, xpathValue));
	}
	public void removeAttributeInDOM(WebDriver driver, String xpathValue, String attributeRemove, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", find(driver, castToObject(xpathValue, values)));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathValue) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return  arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				find(driver, xpathValue));
		if (status) {
			return true;
		} else {
			return false;
		}

	}

	public void waitElementVisible(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(xpathValue)));
	}

	public void waitElementVisible(WebDriver driver, String xpathValue, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(xpathValue, values))));
	}

	public void waitElementClickable(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(xpathValue)));
	}

	public void waitElementClickable(WebDriver driver, String xpathValue, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(xpathValue, values))));
		
	}

	public void waitElementInvisible(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(xpathValue)));
	}
	public void waitElementsInvisible(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver,GlobalConstants.LONG_TIMEOUT);
		elements = finds(driver,xpathValue);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public boolean isNameSortAscending(WebDriver driver, String xpathValue) {
		ArrayList<String> arrayList = new ArrayList<>();
		List<WebElement> elementList = finds(driver,xpathValue);
		
		for(WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("-------- Dữ liệu trên UI:---------");
		for(String name: arrayList) {
			System.out.println(name);
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for(String child: arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("---------Dữ liệu đã SORT ASC:--------");
		for(String name:arrayList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
		
	}
	public boolean isNameSortDecending(WebDriver driver, String xpathValue) {
		ArrayList<String> arrayList = new ArrayList<>();
		List<WebElement> elementList = finds(driver,xpathValue);
		
		for(WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("-------- Dữ liệu trên UI:---------");
		for(String name: arrayList) {
			System.out.println(name);
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for(String child: arrayList) {
			sortedList.add(child);
		}
		//thuc hien sort asc
		Collections.sort(arrayList);
		System.out.println("---------Dữ liệu đã SORT ASC:--------");
		for(String name:arrayList) {
			System.out.println(name);
		}
		//rever data de sort des
		Collections.reverse(arrayList);
		System.out.println("---------Dữ liệu đã SORT DES:--------");
		for(String name:arrayList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
	}
	public boolean isPriceSortAscending(WebDriver driver, String xpathValue) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = finds(driver,xpathValue);
		
		for(WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$","").trim()));
		}
		System.out.println("-------- Dữ liệu trên UI:---------");
		for(Float name: arrayList) {
			System.out.println(name);
		}
		ArrayList<Float> sortedList = new ArrayList<Float>();
		for(Float child: arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("---------Dữ liệu đã SORT ASC:--------");
		for(Float name:arrayList) {
			System.out.println(name);
		}
		
		return sortedList.equals(arrayList);
	}
	public boolean isPriceSortDecending(WebDriver driver, String xpathValue) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = finds(driver,xpathValue);
		
		for(WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$","").trim()));
		}
		System.out.println("-------- Dữ liệu trên UI:---------");
		for(Float name: arrayList) {
			System.out.println(name);
		}
		ArrayList<Float> sortedList = new ArrayList<Float>();
		for(Float child: arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("---------Dữ liệu đã SORT ASC:--------");
		for(Float name:arrayList) {
			System.out.println(name);
		}
		//rever data de sort des
		Collections.reverse(arrayList);
		System.out.println("---------Dữ liệu đã SORT DES:--------");
		for(Float name:arrayList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
	}
	public boolean isDateSortAscending(WebDriver driver, String xpathValue) {
		ArrayList<Date> arrayList = new ArrayList<Date>();
		List<WebElement> elementList = finds(driver,xpathValue);
		
		for(WebElement element : elementList) {
			arrayList.add(convertStringToDate(element.getText()));
		}
		System.out.println("-------- Dữ liệu trên UI:---------");
		for(Date name: arrayList) {
			System.out.println(name);
		}
		ArrayList<Date> sortedList = new ArrayList<Date>();
		for(Date child: arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("---------Dữ liệu đã SORT ASC:--------");
		for(Date name:arrayList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
		
	}
	public Date convertStringToDate(String dateInString) {
		dateInString = dateInString.replace(",", "");
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	private Actions action;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	private Select select;
	private Alert alert;
	private WebElement element;
	private List<WebElement> elements;
}
