package us.luckau.test.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SubmitPage
{
	private static final String HTTPS_URL = "https://jive.com/resources/support/submit-a-ticket/";
	private static final String HTTP_URL= "http://jive.com/resources/support/submit-a-ticket/";
	private static final String DEFAULT_URL = HTTPS_URL;
	private static WebDriver driver;
	@SuppressWarnings("unused")
	private static WebDriverWait wait;

	Logger logger = LogManager.getLogger("Test");
	
	private static final By nextButton = By.id("nextPageButton");
	private static final By nameField = By.id("Field26");
	private static final By emailField = By.id("Field12");
	private static final By companyField = By.id("Field14");
	private static final By phone1 = By.id("Field27");
	private static final By phone2 = By.id("Field27-1");
	private static final By phone3 = By.id("Field27-2");
	private static final By phoneError=By.cssSelector(".phone.notranslate.error");
	private static final By companyError=By.id("fo2li14");
	private static final By nameError=By.id("fo2li26");
	private static final By emailError=By.id("fo2li12");
	
	
			

	public SubmitPage()
	{
		driver = Automation.getDriver();
		wait = Automation.getWait();
	}

	public void pageLoad()
	{
		driver.get(DEFAULT_URL);
	}
	
	public void pageLoadWithoutSSL()
	{
		driver.get(HTTP_URL);
	}

	public void enterName(String string) {
		driver.findElement(nameField).sendKeys(string);
		
	}

	public void enterEmail(String string) {
		driver.findElement(emailField).sendKeys(string);
		
	}

	public void enterCompany(String string) {

		driver.findElement(companyField).sendKeys(string);
	}

	public void enterPhone(String string1, String string2, String string3) {
		
		driver.findElement(phone1).sendKeys(string1);
		driver.findElement(phone2).sendKeys(string2);
		driver.findElement(phone3).sendKeys(string3);
		
	}
	
	public void clickNext()
	{
		driver.findElement(nextButton).click();
	}
	
	public boolean isPhoneError()
	{
		return driver.findElements(phoneError).size()>0;
		//return driver.findElement(phoneError).isDisplayed();
	}
	
	public boolean isEmailError()
	{
		return driver.findElements(emailError).size()>0;
		//return driver.findElement(phoneError).isDisplayed();
	}
	
	public boolean isCompanyError()
	{
		return driver.findElements(companyError).size()>0;
		//return driver.findElement(phoneError).isDisplayed();
	}
	
	public boolean isNameError()
	{
		return driver.findElements(nameError).size()>0;
		//return driver.findElement(phoneError).isDisplayed();
	}
	
	public boolean verifyPhoneFieldLengths(String string1, String string2, String string3)
	{
		logger.info("Verifying Phone field lengths");
		WebElement element = driver.findElement(phone1);
		element.sendKeys(string1);
		String s2 = element.getText();
		if (s2.length() > 3)
		{
			return false;
		}
					
		
		element = driver.findElement(phone2);
		element.sendKeys(string2);
		s2 = element.getText();
		if (s2.length() > 3)
		{
			return false;
		}

		
		element = driver.findElement(phone3);
		element.sendKeys(string3);
		s2 = element.getText();
		if (s2.length() > 3)
		{
			return false;
		}
		
		return true;

	}
	
	
	public boolean verifyStandardFieldLengths(String string)
	{
		return Automation.verifyStandardFieldLength(nameField, string) &&
				Automation.verifyStandardFieldLength(emailField, string)&&
				Automation.verifyStandardFieldLength(companyField, string);

	}
	

}