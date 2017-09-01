package us.bluckau.test.e2e;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import us.luckau.test.automation.Automation;
import us.luckau.test.automation.SubmitPage;


public class verifyPageTests
{
	private static SubmitPage submitPage;


	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger("Test");


	@BeforeClass
	public static void beforeClass()
	{
		submitPage = new SubmitPage();
	}


	@AfterClass
	public static void afterClass()
	{
		//Automation.quit();

	}


	//@Test()
	public static void testGoodSubmittal()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		
		submitPage.enterName("Tom Jones");
		submitPage.enterEmail("tjones@example.com");
		submitPage.enterCompany("tjones, inc.");
		submitPage.enterPhone("801","789","3358");
		submitPage.clickNext();
		assertFalse(submitPage.isPhoneError());
		assertFalse(submitPage.isNameError());
		assertFalse(submitPage.isEmailError());
		assertFalse(submitPage.isCompanyError());
	}
	
	//@Test
	public static void testBadSubmittal()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		
		//submitPage.enterName("Tom Jones");
		submitPage.enterEmail("tjones@m");
		//submitPage.enterCompany("tjones, inc.");
		submitPage.enterPhone("801","789","335");
		submitPage.clickNext();
		assertTrue(submitPage.isPhoneError());
		assertTrue(submitPage.isNameError());
		assertTrue(submitPage.isEmailError());
		assertTrue(submitPage.isCompanyError());
	}
	
	@Test
	public static void fieldLengths()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		assertTrue(submitPage.verifyPhoneFieldLengths("1111","2222","33333"));
		
	}
	
	@Test
	public static void testFieldLengths()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		assertTrue(submitPage.verifyStandardFieldLengths("abc"));
		assertTrue(submitPage.verifyStandardFieldLengths("-------010-------020-------030-------040-------050-------060-------070-------080-------090-------100-------110-------120-------130-------140-------150-------160-------170-------180-------190-------200-------210-------220-------230-------240-------250-------260-------270-------280-------290-------300b"));
	}


	
}