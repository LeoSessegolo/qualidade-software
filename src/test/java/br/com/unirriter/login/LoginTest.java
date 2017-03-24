package br.com.unirriter.login;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.unirriter.home.HomePage;

public class LoginTest {

	private HomePage homePage;
	private LoginPage loginPage;
	
	@BeforeClass
	public static void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/I852172/Downloads/chromedriver.exe");
	}
	
	@Before
	public void setUp() {
		homePage = new HomePage();
		homePage.openHomePage();
		
		loginPage = new LoginPage();
	}
	
	@After
	public void tearDown() {
		homePage.closeHomePage();
	}
	
	@Test
	public void testLogin() {
		//Given
		String email = "leonardo.sessegolo@gmail.com";
		String password = "plinio.marinez!12";
		
		//When
		loginPage.login(email, password);
		
		//Then
		String loginInfo = loginPage.getLoginInfo();
		assertTrue(loginInfo.contains("Leonardo Sessegolo"));
	}
}
