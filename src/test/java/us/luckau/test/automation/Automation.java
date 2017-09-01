
package us.luckau.test.automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		element.sendKeys(string);
		String s2 = element.getText();
		return s2.length() < 256;
		
	}
	
}