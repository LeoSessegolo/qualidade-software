package br.com.unirriter.upload;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.unirriter.driver.PageObjectDriver;
import br.com.unirriter.login.LoginPage;

public class UploadPage extends PageObjectDriver {

	@FindBy(linkText="Upload")
	private WebElement uploadButton;
	
	@FindBy(id="jsid-upload-url-btn")
	private WebElement pasteImageURLButton;
	
	@FindBy(id="jsid-upload-url-input")
	private WebElement URLTextField;
	
	@FindBy(id="jsid-upload-title")
	private WebElement describePostTextArea;
	
	@FindBy(className="badge-upload-section-list-item-profile-only")
	private WebElement myProfileOnlySection;
	
	@FindBy(linkText="Next")
	private WebElement nextButton;
	
	@FindBy(linkText="Post")
	private WebElement postButton;
	
	//no annotation, since it's an element after the execution
	private WebElement newPostTitle;
	
	private LoginPage loginPage;
	
	public UploadPage(WebDriver webDriver, LoginPage loginPage) {
		super(webDriver);
		this.loginPage = loginPage;
	}
	
	public void login(String email, String password) {
		loginPage.login(email, password);
	}
	
	public void clickUploadButton() {
		uploadButton.click();
	}
	
	public void uploadURLMeme(String url, String title) throws InterruptedException {
		pasteImageURLButton.click();
		
		URLTextField.clear();
		URLTextField.sendKeys(url);		
		
		nextButton.click();
		
		this.wait(2);
		describePostTextArea.sendKeys(title);
		
		nextButton.click();
		
		myProfileOnlySection.click();
		
		postButton.click();
	}
	
	public String getPostTitle() throws InterruptedException {
		this.wait(2);
		newPostTitle = this.searchFieldByClassName("badge-item-title");
		return newPostTitle.getText();
	}
}
