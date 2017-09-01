package us.bluckau.test.e2e;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import us.luckau.test.automation.Automation;
import us.luckau.test.automation.SubmitPage;


public class GeneralPageTests
{
	private static SubmitPage SubmitPage;


	private static Logger logger = LogManager.getLogger("Test");


	@BeforeClass
	public static void beforeClass()
	{
		SubmitPage = new SubmitPage();
	}


	@AfterClass
	public static void afterClass()
	{
		Automation.quit();
		Automation.setDriver(null);
	}


	@Test()
	public void testTitle()
	{
		String verificationText = "Submit a Ticket - Jive Resource Center";
		
		logger.info("testTitle");
		SubmitPage.pageLoad();
		String title = Automation.getPageTitle();
		assertEquals(title, verificationText);
	}
	
	@Test()
	public void testRedirect()
	{
		logger.info("testHttpRedirect");
		SubmitPage.pageLoadWithoutSSL();
		assertTrue(Automation.getDriver().getCurrentUrl().startsWith("https:"));
	}
	
	
	//TODO: provide a comprehensive list of the elemnets that need their contrast checked or provide a crawler.
	@Test()
	public void verifyContrast1()
	{
		logger.info("testColorContrast");
		SubmitPage.pageLoad();
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		assertTrue(Automation.verifyColorContrast(By.cssSelector("header h2")));
	}
	
	@Test()
	public void verifyContrast2()
	{
		
		logger.info("testColorContrast");
		SubmitPage.pageLoad();
		//Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		assertTrue(Automation.verifyColorContrast(By.cssSelector(".get-quote-header.inline_header_parts")));
	}
}