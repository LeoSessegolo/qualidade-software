package br.com.unirriter.hot;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.unirriter.home.HomePage;
import br.com.unirriter.login.LoginPage;

public class PostPageTest {

	private HomePage homePage;
	private LoginPage loginPage;
	private HotPage hotPage;
	private PostPage postPage;
	
	@BeforeClass
	public static void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	}
	
	@Before
	public void setUp() {
		ChromeDriver chromeDriver = new ChromeDriver();
		homePage = new HomePage(chromeDriver);
		homePage.openHomePage();
		
		loginPage = new LoginPage(chromeDriver);
		hotPage = new HotPage(chromeDriver);
		postPage = new PostPage(chromeDriver);
		
		String email = "leonardo.sessegolo@gmail.com";
		String password = "plinio.marinez!12";
		
		loginPage.login(email, password);
	}
	
	@After
	public void tearDown() {
		homePage.closeHomePage();
	}
	
	@Test
	public void testCommentOnPost() throws InterruptedException {
		hotPage.enterHotSection();
		hotPage.enterCommentsFirstPost();
		
		//Given
		String comment = "This is so cool! I am commenting using Selenium WebDriver!";
		
		//When
		postPage.writeComment(comment);
		postPage.post();
		
		postPage.wait(1);
		
		//Then
		WebElement posted = postPage.getCommentedPost();
		assertTrue(posted.isDisplayed());
		assertTrue(posted.getText().contains("leonardosesseg"));
		assertTrue(posted.getText().contains(comment));
		
		postPage.deleteComment();
		
		postPage.wait(1);
		posted = postPage.getCommentedPost();
		assertFalse(posted.getText().contains(comment));
	}
}