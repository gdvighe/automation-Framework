package com.ui.test;

import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Utility.LoggerUtility;
import com.ui.pages.HomePage;
import com.ui.pojo.User;

@Listeners({ com.ui.listeners.TestListeners.class })

public class LoginTest extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	@Test(description = "Verify valid user is able to login to the application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvide.class, dataProvider = "LoginTestDataProvider")
	public void loginTest(User user) {

		assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUseName(),
				"Garry Vijay");

	}
	/*
	 * @Test(description = "Verify valid user is able to login to the application",
	 * groups = { "e2e", "sanity" }, dataProviderClass =
	 * com.ui.dataproviders.LoginDataProvide.class, dataProvider =
	 * "LoginTestCSVDataProvider") public void loginCSVTest(User user) {
	 * 
	 * assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(),
	 * user.getPassword()).getUseName(), "Garry Vijay");
	 * 
	 * }
	 * 
	 * @Test(description = "Verify valid user is able to login to the application",
	 * groups = { "e2e", "sanity" }, dataProviderClass =
	 * com.ui.dataproviders.LoginDataProvide.class, dataProvider =
	 * "LoginTestExcelDataProvider", retryAnalyzer =
	 * com.ui.listeners.MyRetryAnalyzer.class) public void loginExcelTest(User user)
	 * {
	 * 
	 * assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(),
	 * user.getPassword()).getUseName(), "Garry Vijay"); }
	 */

}
