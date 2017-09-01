package us.bluckau.test.e2e;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import us.luckau.test.automation.Automation;
import us.luckau.test.automation.SubmitPage;
import us.luckau.test.automation.VerifyPage;


public class verifyPageTests
{
	private static SubmitPage submitPage;
	private static VerifyPage verifyPage;
	
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger("Test");
	private static WebDriver driver = Automation.getDriver();
	private static final By nameField = By.id("Field26");
	private static final By messageField = By.id("Field1");
	private static final By summaryField = By.id("Field28");

	@BeforeClass
	public static void beforeClass()
	{
		submitPage = new SubmitPage();
		verifyPage = new VerifyPage();
	}


	@AfterClass
	public static void afterClass()
	{
		//Automation.quit();

	}

	public static boolean verifyStandardFieldLengths(String string)
	{

		return Automation.verifyStandardFieldLength(messageField, string) &&
				Automation.verifyStandardFieldLength(summaryField, string);
				
	}

	@Test
	public static void testClickPrevious()
	{
		SubmitPage.goToVerify();
		verifyPage.clickPrevious();
		assertTrue(driver.findElement(nameField).isDisplayed());
	}

	@Test
	public static void testFieldLengths()
	{
		SubmitPage.pageLoad();
		SubmitPage.goToVerify();
		//commenting this assertion out for now because element.clear is not currently
		//working
		//assertTrue(verifyStandardFieldLengths("abc"));

		assertTrue(verifyStandardFieldLengths("-------010-------020-------030-------040-------050-------060-------070-------080-------090-------100-------110-------120-------130-------140-------150-------160-------170-------180-------190-------200-------210-------220-------230-------240-------250-------260-------270-------280-------290-------300b"));
	}
	
}