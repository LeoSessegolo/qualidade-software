package br.com.unirriter.home;

import br.com.unirriter.driver.Driver;

public class HomePage {

	public static final String URL = "http://9gag.com/";

	private Driver driver;
	
	public HomePage() {
		this.driver = Driver.getInstance();
	}
	
	public void openHomePage() {
		driver.openBrowser(URL);
	}
	
	public void closeHomePage() {
		driver.closeBrowser();
	}
}
