package us.bluckau.test.e2e;

import static org.junit.Assert.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import us.luckau.test.automation.Automation;
import us.luckau.test.automation.MainPage;


/**
 * Test Class for challenge one, get the page title
 *
 * @author Brian Luckau
 *
 */
public class Test1
{
	private static MainPage mainPage;


	private static Logger logger = LogManager.getLogger("Test");


	@BeforeClass
	public static void beforeClass()
	{
		mainPage = new MainPage();
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
		mainPage.pageLoad();
		String title = Automation.getPageTitle();
		assertEquals(title, verificationText);
	}
}