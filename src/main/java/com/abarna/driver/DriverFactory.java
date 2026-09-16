package com.abarna.driver;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.abarna.config.ConfigReader;
import com.abarna.constants.FrameworkConstants;
import com.epam.healenium.SelfHealingDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver initializeDriver(String browser) {

		
		String execution = ConfigReader.getExecution();

		switch (browser.toLowerCase()) {

		case "chrome":

			ChromeOptions options = new ChromeOptions();

			options.addArguments("--disable-blink-features=AutomationControlled");
			options.addArguments("--window-size=960,1080");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-save-password-bubble");

			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

			options.setExperimentalOption("useAutomationExtension", false);

			Map<String, Object> prefs = new HashMap<>();

			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("autofill.profile_enabled", false);
			prefs.put("autofill.credit_card_enabled", false);

			options.setExperimentalOption("prefs", prefs);

			WebDriver chromeDriver;

			if (execution.equalsIgnoreCase("remote")) {

				try {

					chromeDriver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

				} catch (Exception e) {

					throw new RuntimeException(e);
				}

			} else {

				WebDriverManager.chromedriver().setup();

				chromeDriver = new ChromeDriver(options);

			}

			driver.set(SelfHealingDriver.create(chromeDriver));

			break;

		case "firefox":

			WebDriverManager.firefoxdriver().setup();

			driver.set(new FirefoxDriver());

			break;

		case "edge":

			WebDriverManager.edgedriver().setup();

			driver.set(new EdgeDriver());

			break;

		default:

			throw new RuntimeException("Browser not supported : " + browser);
		}

		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(FrameworkConstants.IMPLICIT_WAIT));

		getDriver().get(ConfigReader.getUrl());

		return getDriver();
	}

	public static WebDriver getDriver() {

		return driver.get();
	}

	public static void quitDriver() {

		if (driver.get() != null) {

			driver.get().quit();

			driver.remove();
		}
	}

}