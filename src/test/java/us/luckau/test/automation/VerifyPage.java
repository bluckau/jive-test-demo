package us.luckau.test.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class VerifyPage
{
	private static WebDriver driver;
	@SuppressWarnings("unused")
	private static WebDriverWait wait;

	Logger logger = LogManager.getLogger("Test");
	
	private static final By previousButton = By.id("previousPageButton");
	private static final By summaryField = By.id("Field28");
	private static final By messageField = By.id("Field1");
	private static final By messageFieldArea = By.id("fo2li28");
	private static final By summaryTitle = By.id("Title28");
	private static final By messageTitle = By.id("Title1");
	
		

	public VerifyPage()
	{
		driver = Automation.getDriver();
		wait = Automation.getWait();
	}

	

	public void enterMessage(String string) {
		driver.findElement(messageField).sendKeys(string);
		
	}

	public void enterSummary(String string) {
		driver.findElement(summaryField).sendKeys(string);
		
	}

	public void clickPrevious()
	{
		driver.findElement(previousButton).click();
		
	}
		
		
	
	
	public boolean verifyStandardFieldLengths(String string)
	{
		
		return Automation.verifyStandardFieldLength(summaryField, string) && 
				Automation.verifyStandardFieldLength(messageField, string);

	}
	
	
	

}