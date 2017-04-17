package br.com.unirriter.hot;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.unirriter.home.HomePage;
import br.com.unirriter.login.LoginPage;

public class HotPageTest {

	private HomePage homePage;
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
		
		LoginPage loginPage = new LoginPage(chromeDriver);
		hotPage = new HotPage(chromeDriver, loginPage);
		
		String email = "leonardo.sessegolo@gmail.com";
		String password = "plinio.marinez!12";
		
		hotPage.login(email, password);
	}
	
	@After
	public void tearDown() {
		hotPage.logout();
		
		homePage.closeHomePage();
	}
	
	@Test
	public void testUpvoteCountOnHotPost() {
		hotPage.enterHotSection();
		
		//Given
		int pointsBeforeUpVote = hotPage.getNumberOfUpVotes();
		
		//When
		hotPage.upvoteFirstPost();

		//Then
		int pointsAfterUpVote = hotPage.getNumberOfUpVotes();
		assertEquals(pointsBeforeUpVote + 1, pointsAfterUpVote);
	}
}