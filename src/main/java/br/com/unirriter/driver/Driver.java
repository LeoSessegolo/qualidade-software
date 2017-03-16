package br.com.unirriter.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Expose Web driver API for important functionalities
 * @author Leonardo Sessegolo
 *
 */
public class Driver {
	
	private static Driver instance;
	private WebDriver webDriver;
	
	private Driver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	/**
	 * Initialize the driver class passing the webDriver used.
	 * 
	 * Example:
	 * FirefoxDriver firefoxDriver = new FirefoxDriver();
	 * Driver driverExample = Driver.getInstance(firefoxDriver);
	 * 
	 * or
	 * 
	 * Driver driverExample = Driver.getInstance(new ChromeDriver());
	 * 
	 * 
	 * WebDrivers: 
	 * InternetExplorerDriver, ChromeDriver, FirefoxDriver, 
	 * OperaDriver, SafariDriver
	 */
	public static Driver getInstance(WebDriver webDriver) {
		if (instance == null) {
			instance = new Driver(webDriver);
		}
		return instance;
	}
	
	public void openBrowser(String url) {
		webDriver.get(url);
	}
	
	public WebElement searchFieldByName(String fieldName) {
		return webDriver.findElement(By.name(fieldName));
	}
	
	public WebElement searchFieldByID(String fieldID) {
		return webDriver.findElement(By.id(fieldID));
	}
	
	public WebElement searchFieldByClassName(String fieldClassName) {
		return webDriver.findElement(By.className(fieldClassName));
	}
	
	public WebDriver switchWindow(String windowName) {
		return webDriver.switchTo().window(windowName);
	}
	
	public WebDriver switchFrameInsideWindow(String frameName) {
		return webDriver.switchTo().frame(frameName);
	}
	
	public Alert switchToAlertPopup() {
		return webDriver.switchTo().alert();
	}
	
	public void navigateForward() {
		webDriver.navigate().forward();
	}
	
	public void navigateBackward() {
		webDriver.navigate().back();
	}
	
	public void waitScreenToLoad(String screenName, int timeout) {
		(new WebDriverWait(webDriver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(screenName);
            }
        });
	}
	
	public void waitUntilFieldIsLocated(String fieldID, int timeout) {
		(new WebDriverWait(webDriver, timeout))
			.until(ExpectedConditions.presenceOfElementLocated(By.id(fieldID)));
	}
	
	public void wait(int timeoutInSeconds) {
		webDriver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
	}
	
	public void closeBrowser() {
		webDriver.quit();
	}
}
