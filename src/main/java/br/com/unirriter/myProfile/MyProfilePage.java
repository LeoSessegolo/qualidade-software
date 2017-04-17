package br.com.unirriter.myProfile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.unirriter.driver.PageObjectDriver;
import br.com.unirriter.login.LoginPage;

public class MyProfilePage extends PageObjectDriver {

	@FindBy(id="jsid-my-profile")
	private WebElement myProfileButton;
	
	@FindBy(className="info")
	private WebElement myInfo;
	
	private LoginPage loginPage;
	
	public MyProfilePage(WebDriver webDriver, LoginPage loginPage) {
		super(webDriver);
		this.loginPage = loginPage;
	}
	
	public void login(String email, String password) {
		loginPage.login(email, password);
	}
	
	public void logout() {
		loginPage.logout();
	}
	
	public boolean isSuccessfulMessageShown() {
		return loginPage.isSuccessfulMessageShown();
	}
	
	public String getCallBackMessage() {
		return loginPage.getCallBackMessage();
	}
	
	public String getUserInformation() {
		this.enterLoginInfo();
		
		return myInfo.getText();
	}
	
	private void enterLoginInfo() {
		loginPage.clickPicture();
		
		myProfileButton.click();
	}
}
