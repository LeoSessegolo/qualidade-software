package br.com.unirriter.login;

import org.openqa.selenium.WebElement;

import br.com.unirriter.driver.Driver;

public class LoginPage {
	
	private WebElement email;
	private WebElement password;
	private WebElement loginButton;
	private WebElement picture;
	private WebElement myInfo;
	
	private Driver driver;
	
	public LoginPage() {
		this.driver = Driver.getInstance();
	}
	
	public void login(String email, String password) {
		driver.searchFieldByLinkText("Log in").click();
		 
		this.enterEmail(email);
		this.enterPassword(password);
		this.clickLogInButton();
	}
	
	private void enterEmail(String email) {
		this.email = driver.searchFieldByID("jsid-login-email-name");
		this.email.sendKeys(email);
	}
	
	private void enterPassword(String password) {
		this.password = driver.searchFieldByID("login-email-password");
		this.password.sendKeys(password);
	}
	
	private void clickLogInButton() {
		loginButton = driver.searchFieldByCssSelector("input.btn.left");
		loginButton.click();
	}

	public String getLoginInfo() {
		driver.waitUntilFieldIsLocated("jsid-avatar", 10);
		picture = driver.searchFieldByID("jsid-avatar");
		picture.click();
		
		driver.searchFieldByID("jsid-my-profile").click();
		
		myInfo = driver.searchFieldByClassName("info");
		
		return myInfo.getText();
	}
}
