package br.com.unirriter.hot;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.unirriter.home.HomePage;
import br.com.unirriter.login.LoginPage;

public class HotPageTest {

	private HomePage homePage;
	private LoginPage loginPage;
	private HotPage hotPage;
	
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
		
		String email = "leonardo.sessegolo@gmail.com";
		String password = "plinio.marinez!12";
		
		loginPage.login(email, password);
	}
	
	@After
	public void tearDown() {
		homePage.closeHomePage();
	}
	
	@Test
	public void testUpvoteCountOnHotPost() {
		hotPage.enterHotSection();

		int pointsBeforeUpVote = hotPage.getNumberOfUpVotes();
		
		hotPage.upvoteFirstPost();
		
		int pointsAfterUpVote = hotPage.getNumberOfUpVotes();
		
		assertEquals(pointsBeforeUpVote + 1, pointsAfterUpVote);
	}
}