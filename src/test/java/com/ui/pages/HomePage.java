package com.ui.pages;

import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Utility.BrowserUtility;
import com.Utility.JSONUtility;
import com.Utility.LoggerUtility;

import static com.Utility.PropertiesUtil.*;
import com.constant.Browser;
import static com.constant.Env.*;

public class HomePage extends BrowserUtility {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign in\")]");

	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless);// to call the parent class constructor from child class constructor
		goToWebsite(JSONUtility.readJSON(QA).getUrl());

	}

	public HomePage(WebDriver driver) {
		super(driver);// to call the parent class constructor from child class constructor
		goToWebsite(JSONUtility.readJSON(QA).getUrl());
		// goToWebsite(readProperty(QA, "URL"));

	}

	public LoginPage goToLoginPage() {
		logger.info("Trying to perform click to go to Sign in page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;

	}

	

}
