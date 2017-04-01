package br.com.unirriter.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.unirriter.myProfile.MyProfileTest;
import br.com.unirriter.settings.SettingsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ MyProfileTest.class, 
						SettingsTest.class })
public class TestsSuite {

	/**
	 * Class that run all tests
	 */
}