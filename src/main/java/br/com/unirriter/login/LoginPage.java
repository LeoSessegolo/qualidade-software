package br.com.unirriter.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.unirriter.driver.PageObjectDriver;

public class LoginPage extends PageObjectDriver {
	
	@FindBy(linkText="Log in")
	private WebElement logInButton;
	
	@FindBy(id="jsid-login-email-name")
	private WebElement email;
	
	@FindBy(id="login-email-password")
	private WebElement password;
	
	@FindBy(css="input.btn.left")
	private WebElement logIn;
	
	@FindBy(id="jsid-avatar")
	private WebElement picture;
	
	@FindBy(className="badge-toast-message")
	private WebElement successfulMessage;
	
	public LoginPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public void login(String email, String password) {
		this.logInButton.click();
		 
		this.enterEmail(email);
		this.enterPassword(password);
		this.clickLogin();
	}
	
	private void enterEmail(String email) {
		this.email.clear();
		this.email.sendKeys(email);
	}
	
	private void enterPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}
	
	private void clickLogin() {
		logIn.click();
	}
	
	public void clickPicture() {
		this.waitUntilFieldIsLocated("jsid-avatar", 10);
		picture.click();
	}
	
	public boolean isSuccessfulMessageShown() {
		return successfulMessage.isDisplayed();
	}

	public String getCallBackMessage() {
		return successfulMessage.getText();
	}
}
