package br.com.unirriter.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.unirriter.hot.HotPageTest;
import br.com.unirriter.hot.PostPageTest;
import br.com.unirriter.myProfile.MyProfileTest;
import br.com.unirriter.settings.SettingsTest;
import br.com.unirriter.upload.UploadPageTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ MyProfileTest.class, 
						SettingsTest.class,
						HotPageTest.class,
						PostPageTest.class,
						UploadPageTest.class })
public class TestsSuite {

	/**
	 * Class that run all tests
	 */
}