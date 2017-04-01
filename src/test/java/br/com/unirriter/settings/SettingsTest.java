package br.com.unirriter.settings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.unirriter.home.HomePage;
import br.com.unirriter.login.LoginPage;
import br.com.unirriter.myProfile.MyProfilePage;

public class SettingsTest {

	private HomePage homePage;
	private LoginPage loginPage;
	private MyProfilePage myProfilePage;
	private SettingsPage settingsPage;
	
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
		settingsPage = new SettingsPage(chromeDriver, loginPage);
		myProfilePage = new MyProfilePage(chromeDriver, loginPage);
		
		String email = "leonardo.sessegolo@gmail.com";
		String password = "plinio.marinez!12";
		
		loginPage.login(email, password);
		settingsPage.enterSettings();
	}
	
	@After
	public void tearDown() {
		homePage.closeHomePage();
	}

	@Test
	public void testChangeCountryOnSettingsAndCheckNewInfo() {
		//Given
		boolean isUnitedStates = settingsPage.getCountrySelected().equals("us");
		
		//When
		settingsPage.changeCountry(isUnitedStates);
		
		//Then
		assertTrue(settingsPage.isSuccessfulMessageShown());

		String message = settingsPage.getCallBackMessage();
		assertEquals("Your settings have been saved.", message);
		
		settingsPage.enterSettings();
		
		String countrySelected = settingsPage.getCountrySelected();
		assertEquals(isUnitedStates ? "br" : "us", countrySelected);
	}
	
	@Test
	public void testChangeDescriptionOnSettingsAndCheckNewInfo() {
		//Given
		int random = new Random().nextInt(Integer.MAX_VALUE);
		String description = "I was updated by selenium webDriver! This is great! [" + random + "] :)";

		//When
		settingsPage.changeDescription(description);
		
		//Then
		assertTrue(settingsPage.isSuccessfulMessageShown());
		
		String message = settingsPage.getCallBackMessage();
		assertEquals("Your settings have been saved.", message);
		
		String infoUpdated = myProfilePage.getUserInformation();
		assertTrue(infoUpdated.contains(description));
	}
}
