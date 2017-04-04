package br.com.unirriter.upload;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.unirriter.home.HomePage;
import br.com.unirriter.login.LoginPage;

public class UploadPageTest {

	private HomePage homePage;
	private UploadPage uploadPage;
	
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
		uploadPage = new UploadPage(chromeDriver, loginPage);
		
		String email = "leonardo.sessegolo@gmail.com";
		String password = "plinio.marinez!12";
		
		uploadPage.login(email, password);
	}
	
	@After
	public void tearDown() {
		homePage.closeHomePage();
	}
	
	@Test
	public void testUploadNewPost() throws InterruptedException {
		uploadPage.clickUploadButton();
		
		//Given
		String memeUrl = "http://i.memeful.com/memes/deAZzyd/Confession-Bear.jpg";
		String title = "Using Selenium for college and stuff";
		
		//When
		uploadPage.uploadURLMeme(memeUrl, title);
		
		//Then
		String postTitle = uploadPage.getPostTitle();
		assertEquals(title, postTitle);
	}
}