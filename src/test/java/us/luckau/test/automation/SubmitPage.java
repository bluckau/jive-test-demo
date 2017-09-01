package us.luckau.test.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SubmitPage
{
	private static final String HTTPS_URL = "https://jive.com/resources/support/submit-a-ticket/";
	private static final String HTTP_URL= "http://jive.com/resources/support/submit-a-ticket/";
	private static final String DEFAULT_URL = HTTPS_URL;
	private static WebDriver driver;
	@SuppressWarnings("unused")
	private static WebDriverWait wait;
	 

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
	
}