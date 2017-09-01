
package us.luckau.test.automation;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
//import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

/**
 *
 * @author Brian Luckau
 * @version %I%, %G%
 * @since 0.1
 */
public class Automation {
	private static WebDriver driver;
	static WebDriverWait wait;
	static Logger logger = LogManager.getLogger("Test");

	static final long WAIT_TIMEOUT = 15;
	public static WebDriver getDriver()
	{
		if (driver == null)
		{
			FirefoxDriverManager.getInstance().setup();
			driver = new FirefoxDriver();
			
			//ChromeDriverManager.getInstance().setup();
			//driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, WAIT_TIMEOUT);
		}

		return driver;
	}
	
	public static void setDriver(WebDriver driver) {
		Automation.driver = driver;
	}
	
	/**
	 *
	 * @return the instance of WebDriverWait
	 */
	public static WebDriverWait getWait()
	{
		return wait;
	}

	/**
	 *
	 * Snooze should not be used if possible. It is mainly used temporarily
	 *  while finding out if a needed wait is the cause of an issue.
	 *
	 * @param n
	 *            The number of seconds to snooze
	 */
	public static void snooze(int n)
	{
		try {
			Thread.sleep(n*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @return The page title of the current web page
	 */
	public static String getPageTitle()
	{
		return getDriver().getTitle();
	}

	public static void quit()
	{
		driver.quit();
		driver = null;
	}
	
	public static boolean verifyStandardFieldLength(By by,String string)
	{
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(string);
		String s2 = element.getText();
		
		
		if (string.length() <= 255)
		{
			//Make sure that it does not unduly modify the string
			return s2.equals(string);
		}
		else
		{
			return s2.length() < 256;
		}
		
	}
	
	public static boolean verifyColorContrast(By by)
	{
		WebElement elem = driver.findElement(by);
		String background = elem.getCssValue("background-color");
		String textColor = elem.getCssValue("color");
		
		String hexBackground = Color.fromString(background).asHex();
		String hexTextColor = Color.fromString(textColor).asHex();
		
		//logger.info("background color = " + rgbBackground);
		//logger.info("color = " + rgbTextColor);
		
		float contrast = 0;
		//we are chopping off the # when we pass to getRGBArray
		int[] rgb1 = getRGBArray(hexBackground.substring(1));
		int[] rgb2 = getRGBArray(hexTextColor.substring(1));
		
		contrast = getContrast(rgb1,rgb2);
		logger.debug(contrast);

		//Right now we are saying that contrast has to be at least 4.5. 
		//black vs white is a contrast of infinity which is OK.
		if (contrast > 4.5)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//From https://stackoverflow.com/questions/9733288/how-to-programmatically-calculate-the-contrast-ratio-between-two-colors
	//May need to review if this is accurate
	public static float getContrast(int[] rbg1,int [] rgb2)
	{
		
		float brightness1 = (299*rbg1[0] + 587*rbg1[1] + 114*rbg1[2]) / 1000;
		logger.debug("Brightness1 = " + brightness1);
		float brightness2 = (299*rgb2[0] + 587*rgb2[1] + 114*rgb2[2]) / 1000;
		logger.debug("Brightness2 = " + brightness2);
		float contrast = brightness2/brightness1;
		logger.debug("Contrast= " + contrast);
		return contrast;
	}
	
	 public static int[] getRGBArray(String rgb)
	 {
	     final int[] ret = new int[3];
	     for (int i = 0; i < 3; i++)
	     {
	         ret[i] = Integer.parseInt(rgb.substring(i * 2, i * 2 + 2), 16);
	     }
	     return ret;
	 }
	
	
}