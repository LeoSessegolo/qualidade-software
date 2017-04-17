package br.com.unirriter.myProfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.unirriter.home.HomePage;
import br.com.unirriter.login.LoginPage;

public class MyProfileTest {

	private HomePage homePage;
	private MyProfilePage myProfilePage;
	
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
		myProfilePage = new MyProfilePage(chromeDriver, loginPage);
	}
	
	@After
	public void tearDown() {
		myProfilePage.logout();
		
		homePage.closeHomePage();
	}
	
	@Test
	public void testLoginAndCheckMyProfileName() {
		//Given
		String email = "leonardo.sessegolo@gmail.com";
		String password = "plinio.marinez!12";
		
		//When
		myProfilePage.login(email, password);
		
		//Then
		assertTrue(myProfilePage.isSuccessfulMessageShown());
		String successfulMessage = myProfilePage.getCallBackMessage();
		assertEquals("Awww Yeah! Welcome back.", successfulMessage);
		
		String loginInfo = myProfilePage.getUserInformation();
		assertTrue(loginInfo.contains("Leonardo Sessegolo"));
	}
}
