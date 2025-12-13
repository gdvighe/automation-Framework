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

	@Parameters({ "browser", "isLambdaTest", "isheadless" })
	@BeforeMethod(description = "Load the homePage of the Website")
	public void setup(
			@Optional("chrome")String browser,
			@Optional("false")	boolean isLambdaTest, 
			@Optional("true")boolean isheadless, ITestResult result) {
		this.isLambdaTest = isLambdaTest;
		WebDriver lambdaDriver;

		if (isLambdaTest) {
			lambdaDriver = LambdaTestUtility.initializeLambdaTestSession("browser", result.getMethod().getMethodName());
			homepage = new HomePage(lambdaDriver);

		}

		else {
			logger.info("Load the homePage of the Website");
			homepage = new HomePage(Browser.valueOf(browser.toUpperCase()), isheadless);

		}
	}

	public BrowserUtility getInstance() {
		return homepage;
	}

	@AfterMethod(description = "Tear down the Browser instance")
	public void tearDown() {
		if (isLambdaTest) {
			LambdaTestUtility.quitLambdaTestSession();
		} else {
			logger.info("Tear down the Browser instance");
			homepage.quitBrowser();
		}
	}

}
