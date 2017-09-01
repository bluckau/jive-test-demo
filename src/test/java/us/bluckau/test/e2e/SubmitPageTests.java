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
import us.luckau.test.automation.VerifyPage;


public class SubmitPageTests
{
	private static SubmitPage submitPage;
	private static VerifyPage verifyPage;

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger("Test");


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


	@Test
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
	
	
	@Test
	public static void testBadName()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		
		//submitPage.enterName("Tom Jones");
		submitPage.enterEmail("tjones@example.com");
		submitPage.enterCompany("tjones, inc.");
		submitPage.enterPhone("801","789","3358");

		submitPage.clickNext();
		
		assertTrue(submitPage.isNameError());
	}
	
	
	@Test
	public static void testForeignName()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		
		submitPage.enterName("Maève Hyphen-Name O'Connor");
		submitPage.enterEmail("tjones@example.com");
		submitPage.enterCompany("tjones, inc.");
		submitPage.enterPhone("801","789","3358");

		submitPage.clickNext();
		
		assertFalse(submitPage.isNameError());
	}
	
	@Test
	public static void testBadEmail1()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		
		submitPage.enterName("Tom Jones");
		submitPage.enterEmail("tjones@e");
		submitPage.enterCompany("tjones, inc.");
		submitPage.enterPhone("801","789","3358");
		submitPage.clickNext();
		
		assertTrue(submitPage.isNameError());
	}
	
	
	@Test
	public static void testBadEmail2()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		
		submitPage.enterName("Tom Jones");
		submitPage.enterEmail("tjones@e.e");
		submitPage.enterCompany("tjones, inc.");
		submitPage.enterPhone("801","789","3358");
		submitPage.clickNext();
		
		assertTrue(submitPage.isNameError());
	}
	
	
	@Test
	public static void testBadEmail3()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		
		submitPage.enterName("Tom Jones");
		
		submitPage.enterCompany("tjones, inc.");
		submitPage.enterPhone("801","789","3358");
		submitPage.clickNext();
		
		assertTrue(submitPage.isNameError());
	}
	
	@Test
	public static void testBadCompany()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		
		submitPage.enterName("Tom Jones");
		submitPage.enterEmail("tjones@example.com");
		//submitPage.enterCompany("tjones, inc.");
		submitPage.enterPhone("801","789","3358");
		submitPage.clickNext();

		
		assertTrue(submitPage.isCompanyError());
	}

	@Test
	public static void testBadPhone0()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		
		submitPage.enterName("Tom Jones");
		submitPage.enterEmail("tjones@e");
		submitPage.enterCompany("tjones, inc.");
		submitPage.enterPhone("abc","def","ghij");
		submitPage.clickNext();
		
		assertTrue(submitPage.isPhoneError());
	}
	

	@Test
	public static void testBadPhone1()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		
		submitPage.enterName("Tom Jones");
		submitPage.enterEmail("tjones@e");
		submitPage.enterCompany("tjones, inc.");
		submitPage.enterPhone("180","222","");
		submitPage.clickNext();
		
		assertTrue(submitPage.isPhoneError());
	}
	

	@Test
	public static void testBadPhone2()
	{
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		
		submitPage.enterName("Tom Jones");
		submitPage.enterEmail("tjones@e");
		submitPage.enterCompany("tjones, inc.");
		submitPage.clickNext();
		
		assertTrue(submitPage.isPhoneError());
	}
	

	@Test
	public static void testBadPhone3()
	{
		//Note currently fails, I think it should still fail though because it should
		//show that there is a bad phone# as well as bad e-mail. This illustrates the
		//need for more combinations.(DDT)
		submitPage.pageLoad();
		
		//workaround, probably only works in Firefox
		Automation.getDriver().get("https://jivecommunications.wufoo.com/forms/m7x4z5/");
		
		submitPage.enterName("Tom Jones");
		submitPage.enterEmail("tjones@e");
		submitPage.enterCompany("tjones, inc.");
		submitPage.enterPhone("123","456","789");
		submitPage.clickNext();
		
		assertTrue(submitPage.isPhoneError());
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
		//commenting out this assertion because element.clear() is currently not working
		//assertTrue(submitPage.verifyStandardFieldLengths("abc"));
		assertTrue(submitPage.verifyStandardFieldLengths("-------010-------020-------030-------040-------050-------060-------070-------080-------090-------100-------110-------120-------130-------140-------150-------160-------170-------180-------190-------200-------210-------220-------230-------240-------250-------260-------270-------280-------290-------300b"));
	}


	
}