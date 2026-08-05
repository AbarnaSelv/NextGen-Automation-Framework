package com.abarna.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

	// ===========================
	// Logger
	// ===========================

	private static final Logger logger = LogManager.getLogger(LoginPage.class); // Create a logger for the LoginPage
																				// class.

	// ===========================
	// Constructor
	// ===========================

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// ===========================
	// Locators
	// ===========================

	@FindBy(xpath = "//a[text()=' Signup / Login']")
	private WebElement signupLoginOption;

	@FindBy(name = "email")
	private WebElement emailTextBox;

	@FindBy(name = "password")
	private WebElement passwordTextBox;

	@FindBy(xpath = "//button[text()='Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//a[normalize-space()='Logout']")
	private WebElement logoutOption;

	// ===========================
	// Actions
	// ===========================

	public void clickSignupLogin() {

		logger.info("Clicking Signup / Login option");

		actions.click(signupLoginOption);
	}

	public void enterEmail(String email) {

		logger.info("Entering email");

		actions.type(emailTextBox, email);
	}

	public void enterPassword(String password) {

		logger.info("Entering password");

		actions.type(passwordTextBox, password);
	}

	public void clickLogin() {

		logger.info("Clicking Login button");

		actions.click(loginButton);
	}

	// ===========================
	// Business Flow
	// ===========================

	@Step("Login using Email: {0}")
	public void login(String email, String password) {

		logger.info("Starting login process");

		clickSignupLogin();

		enterEmail(email);

		enterPassword(password);

		clickLogin();

		logger.info("Login process completed");
	}

	public boolean isLoginSuccessful() {

		logger.info("Verifying login success");

		return actions.isDisplayed(logoutOption);
	}
}