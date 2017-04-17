package br.com.unirriter.hot;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import br.com.unirriter.driver.PageObjectDriver;
import br.com.unirriter.login.LoginPage;

public class HotPage extends PageObjectDriver {

	@FindBy(linkText="Hot")
	private WebElement hotSection;
	
	@FindAll({@FindBy(className = "badge-item-vote-up")})
	private List<WebElement> upVotes;

	@FindAll({@FindBy(className = "badge-item-comment")})
	private List<WebElement> comments;
	
	@FindBy(className="badge-item-love-count")
	private WebElement countPoints;
	
	private LoginPage loginPage;
	
	public HotPage(WebDriver webDriver, LoginPage loginPage) {
		super(webDriver);
		this.loginPage = loginPage;
	}
	
	public void login(String email, String password) {
		loginPage.login(email, password);
	}
	
	public void logout() {
		loginPage.logout();
	}
	
	public void enterHotSection() {
		hotSection.click();
	}
	
	public void upvoteFirstPost() {
		WebElement upVote = upVotes.get(0);
		upVote.click();
	}
	
	public void enterCommentsFirstPost() throws InterruptedException {
		String currentTab = this.getCurrentTab();
		
		WebElement comment = comments.get(0);
		comment.click();
		
		this.wait(2);
		
		this.switchWindow(currentTab);
	}
	
	public int getNumberOfUpVotes() {
		String text = countPoints.getText();
		String[] array = text.split("points");
		
		return Integer.parseInt(array[0].replace(",", ""));
	}
}
