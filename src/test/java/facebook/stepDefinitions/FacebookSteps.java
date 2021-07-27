package facebook.stepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookSteps {
	WebDriver driver;

	@Before("@parameter")
	public void openFacebookApplication() throws Throwable {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	@After("@parameter")
	public void closeApplication() {
		driver.quit();
	}

	@When("^Input to Username textbox$")
	public void inputToUsernameTextbox() throws Throwable {

	}

	@And("^Input to Password textbox$")
	public void inputToPasswordTextbox() throws Throwable {

	}

	@And("^Input to Username textbox with \"([^\"]*)\"$")
	public void input_to_Username_textbox_with(String name) throws Throwable {
		driver.findElement(By.id("email")).sendKeys(name);
	}

	@And("^Input to Password textbox with \"([^\"]*)\"$")
	public void input_to_Password_textbox_with(String password) throws Throwable {
		driver.findElement(By.id("pass")).sendKeys(password);
	}

	@And("^Input to Username textbox with ([^\"]*)$")
	public void input_to_Username_textbox(String name) throws Throwable {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(name);
	}

	@And("^Input to Password textbox with ([^\"]*)$")
	public void input_to_Password_textbox(String password) throws Throwable {
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(password);
	}

	@And("^Input to Username with \"([^\"]*)\" and Password with \"([^\"]*)\"$")
	public void input_to_username_with_something_and_password_with_something(String name, String password)
			throws Throwable {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(name);
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(password);
	}

	@And("^Input to Username and Password$")
	public void inputToUsernameAndPassword(DataTable table) throws Throwable {
		List<Map<String, String>> customer = table.asMaps(String.class, String.class);
//		driver.findElement(By.id("email")).clear();
//		driver.findElement(By.id("email")).sendKeys(customer.get(0).get("Username"));
//		
//		driver.findElement(By.id("pass")).clear();
//		driver.findElement(By.id("pass")).sendKeys(customer.get(0).get("Password"));
		
		
		for(Map<String, String> loginInfor :table.asMaps(String.class, String.class)) {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(loginInfor.get("Username"));
		
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(loginInfor.get("Password"));
		}
	}

	@And("^click to Submit button$")
	public void clickToSubmitButton() throws Throwable {
		driver.findElement(By.name("login")).click();
	}



}
