package br.com.unirriter.home;

import org.openqa.selenium.WebDriver;

import br.com.unirriter.driver.PageObjectDriver;

public class HomePage extends PageObjectDriver {

	public static final String URL = "http://9gag.com/";

	public HomePage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public void openHomePage() {
		this.openBrowser(URL);
	}
	
	public void closeHomePage() {
		this.closeBrowser();
	}
}