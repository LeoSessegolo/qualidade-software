package br.com.unirriter.hot;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import br.com.unirriter.driver.PageObjectDriver;

public class PostPage extends PageObjectDriver {

	@FindBy(className="post-text-area")
	private WebElement writeComment;
	
	@FindBy(linkText="Post")
	private WebElement postButton;
	
	@FindAll({@FindBy(className = "comment-entry")})
	private List<WebElement> comments;
	
	@FindBy(className="drop")
	private WebElement arrow;
	
	@FindBy(linkText="Delete comment")
	private WebElement deleteCommentButton;
	
	public PostPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public void writeComment(String comment) {
		writeComment.clear();
		writeComment.sendKeys(comment);
	}
	
	public void post() {
		postButton.click();
	}
	
	public WebElement getCommentedPost() {
		WebElement post = comments.get(0);
		return post;
	}
	
	public void deleteComment() {
		arrow.click();
		deleteCommentButton.click();
	}
}