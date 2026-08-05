package com.abarna.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.abarna.driver.DriverFactory;
import com.abarna.utils.AllureUtils;

public class BaseTest {

	protected WebDriver driver;

	@BeforeSuite(alwaysRun = true)
	public void copyEnvironment() throws IOException {

		Files.createDirectories(Paths.get("target/allure-results"));

		Files.copy(Paths.get("src/test/resources/environment.properties"),
				Paths.get("target/allure-results/environment.properties"), StandardCopyOption.REPLACE_EXISTING);
	}

	@BeforeMethod
	public void setup() {

		driver = DriverFactory.initializeDriver();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			AllureUtils.attachScreenshot(driver);
		}
		DriverFactory.quitDriver();
	}

}