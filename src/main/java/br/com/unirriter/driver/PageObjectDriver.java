package br.com.unirriter.driver;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Expose Web driver API for important functionalities
 * @author Leonardo Sessegolo
 *
 */
public abstract class PageObjectDriver {
	
	protected WebDriver webDriver;
	
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
	public PageObjectDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(this.webDriver, this);
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

	public WebElement searchFieldByCssSelector(String fieldCssSelector) {
		return webDriver.findElement(By.cssSelector(fieldCssSelector));
	}

	public WebElement searchFieldByLinkText(String fielcLinkText) {
		return webDriver.findElement(By.linkText(fielcLinkText));
	}
	
	public void switchWindow(String currentTab) {
		Set<String> tabs = webDriver.getWindowHandles();
		Iterator<String> iterator = tabs.iterator();
		
		while(iterator.hasNext()) {
			String index = iterator.next();
			if(!index.equals(currentTab)) {
				webDriver.switchTo().window(index);
			}
		}
	}
	
	public String getCurrentTab() {
		return webDriver.getWindowHandle();
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
	
	public void wait(int timeoutInSeconds) throws InterruptedException {
		Thread.sleep(timeoutInSeconds*1000);
	}
	
	public void closeBrowser() {
		webDriver.quit();
	}
}