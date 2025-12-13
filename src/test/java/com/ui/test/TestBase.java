package com.ui.test;

import static com.constant.Browser.*;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import com.Utility.BrowserUtility;
import com.Utility.LoggerUtility;

import com.ui.pages.HomePage;

public class TestBase {

	public HomePage homepage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	@BeforeMethod(description = "Load the homePage of the Website")
	public void setup() {
		logger.info("Load the homePage of the Website");
		homepage = new HomePage(CHROME,true);

	}

	public BrowserUtility getInstance() {
		return homepage;
	}

}
