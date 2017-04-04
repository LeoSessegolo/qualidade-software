package br.com.unirriter.hot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		
		LoginPage loginPage = new LoginPage(chromeDriver);
		HotPage hotPage = new HotPage(chromeDriver, loginPage);
		postPage = new PostPage(chromeDriver, loginPage, hotPage);
		
		String email = "leonardo.sessegolo@gmail.com";
		String password = "plinio.marinez!12";
		
		postPage.login(email, password);
	}
	
	@After
	public void tearDown() {
		homePage.closeHomePage();
	}
	
	@Test
	public void testCommentOnPost() throws InterruptedException {
		//Given
		postPage.enterPost();
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