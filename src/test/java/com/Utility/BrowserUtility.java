package com.Utility;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constant.Browser;

public class BrowserUtility {

	protected static  ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	protected WebDriverWait wait;

	Logger logger = LoggerUtility.getLogger(this.getClass());

	// Getter for WebDriver
	public WebDriver getDriver() {
		return driver.get();
	}

	// Constructor with direct WebDriver instance
	public BrowserUtility(WebDriver driverInstance) {
		driver.set(driverInstance);
		this.wait = new WebDriverWait(driver.get(), Duration.ofSeconds(40));
	}

	public BrowserUtility(Browser browserName) {
		logger.info("Launching the Browser for " + browserName);

		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		}

		else if (browserName == Browser.EDGE)

		{
			driver.set(new EdgeDriver());

		}

		else if (browserName == Browser.FIREFOX) {

			driver.set(new FirefoxDriver());
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching the Browser: " + browserName + " | Headless: " + isHeadless);

		switch (browserName) {
		case CHROME:
			ChromeOptions chromeOptions = new ChromeOptions();
			if (isHeadless) {
				chromeOptions.addArguments("--headless=new");
				chromeOptions.addArguments("--window-size=1920,1080");
			
			}
			driver.set(new ChromeDriver(chromeOptions));
			break;
			
				
		

		/*
		 * case FIREFOX: FirefoxOptions firefoxOptions = new FirefoxOptions(); if
		 * (isHeadless) { firefoxOptions.setHeadless(true); // more reliable than
		 * addArguments } driver.set(new FirefoxDriver(firefoxOptions)); break;
		 */

		case EDGE:
			EdgeOptions edgeOptions = new EdgeOptions();
			if (isHeadless) {
				edgeOptions.addArguments("--headless=new");
				edgeOptions.addArguments("--window-size=1920,1080");
			}
			driver.set(new EdgeDriver(edgeOptions));
			break;

		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}

		this.wait = new WebDriverWait(driver.get(), Duration.ofSeconds(40));
	}

	public void goToWebsite(String url) {
		logger.info("Visiting the website" + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the browser window ");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding element with locator " + locator);
		WebElement element = driver.get().findElement(locator);

		logger.info("Element found and performing Click");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found now enter text " + textToEnter);
		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and returning the visible text " + element.getText());
		return element.getText();
	}

	public static String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();

		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		java.util.Date date = new java.util.Date();

		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		String path = System.getProperty("user.dir") + "//screenshot//" + name + "-" + timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
			return path;
		} catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
			return null;
		}

	}

}
