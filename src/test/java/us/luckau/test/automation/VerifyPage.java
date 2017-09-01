package us.luckau.test.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class VerifyPage
{
	private static WebDriver driver;
	@SuppressWarnings("unused")
	private static WebDriverWait wait;
	 

	public VerifyPage()
	{
		driver = Automation.getDriver();
		wait = Automation.getWait();
	}



}