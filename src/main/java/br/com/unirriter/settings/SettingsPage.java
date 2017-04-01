package br.com.unirriter.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import br.com.unirriter.driver.PageObjectDriver;
import br.com.unirriter.login.LoginPage;

public class SettingsPage extends PageObjectDriver {

	@FindBy(linkText = "Settings")
	private WebElement settingsButton;

	@FindBy(linkText = "Profile")
	private WebElement profileItem;

	@FindBy(name = "country")
	private WebElement country;
	private Select countrySelect;

	@FindBy(name = "blogTitle")
	private WebElement tellMeWhoYouAre;
	
	@FindBy(css="input[type='submit']")
	private WebElement saveChangesButton;
	
	@FindBy(className="badge-toast-message")
	private WebElement successfulMessage;

	private LoginPage loginPage;

	public SettingsPage(WebDriver webDriver, LoginPage loginPage) {
		super(webDriver);
		this.loginPage = loginPage;
	}

	public void changeCountry(boolean isUnitedStates) {
		if (countrySelect != null) {
			countrySelect.selectByValue(isUnitedStates ? "br" : "us");
			this.saveChangesButton.click();
		}
	}
	
	public void changeDescription(String description) {
		tellMeWhoYouAre.clear();
		tellMeWhoYouAre.sendKeys(description);
		
		this.saveChangesButton.click();		
	}

	public void enterSettings() {
		loginPage.clickPicture();
		settingsButton.click();

		profileItem.click();
		
		//country select option
		this.countrySelect = new Select(country);
	}
	
	public boolean isSuccessfulMessageShown() {
		return successfulMessage.isDisplayed();
	}
	
	public String getCallBackMessage() {
		return successfulMessage.getText();
	}

	public String getCountrySelected() {
		return countrySelect != null ? 
				countrySelect.getFirstSelectedOption().getAttribute("value") 
				: null;
	}
}
