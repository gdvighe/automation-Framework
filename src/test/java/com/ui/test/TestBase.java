package com.ui.test;

import static com.constant.Browser.*;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.Utility.BrowserUtility;
import com.Utility.LambdaTestUtility;
import com.Utility.LoggerUtility;
import com.constant.Browser;
import com.ui.pages.HomePage;

public class TestBase {

	protected HomePage homepage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;

	@Parameters({ "browser", "isLambdaTest", "isHeadless" })
	@BeforeMethod(description = "Load the homePage of the Website")
	public void setup(
		@Optional("chrome")	String browser, 
		@Optional("true")	boolean isLambdaTest, 
		@Optional("false")	boolean isHeadless, ITestResult result) {

		this.isLambdaTest = isLambdaTest;
		WebDriver lambdaDriver;
		if (isLambdaTest) {
			lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
			homepage = new HomePage(lambdaDriver);

		} else {

			// Running test on Local Machine
			logger.info("Load the homePage of the Website");
			homepage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);

		}
	}

	public BrowserUtility getInstance() {
		return homepage;
	}

	@AfterMethod(description = "Quit the Browser after the test execution")

	public void tearDown() {
		if (isLambdaTest) {
			LambdaTestUtility.quitLambdaTestSession();
		} else {
			logger.info("Quit the Browser after the test execution");
			homepage.getDriver().quit();
		}
	}
}
