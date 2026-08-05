package com.abarna.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class PaymentPage extends BasePage {

	private static final Logger logger = LogManager.getLogger(PaymentPage.class);

	// ===========================
	// Constructor
	// ===========================

	public PaymentPage(WebDriver driver) {
		super(driver);
	}

	// ===========================
	// Locators
	// ===========================

	@FindBy(name = "name_on_card")
	private WebElement nameOnCard;

	@FindBy(name = "card_number")
	private WebElement cardNumber;

	@FindBy(name = "cvc")
	private WebElement cvc;

	@FindBy(name = "expiry_month")
	private WebElement expiryMonth;

	@FindBy(name = "expiry_year")
	private WebElement expiryYear;

	@FindBy(id = "submit")
	private WebElement payAndConfirmOrderButton;

	// ===========================
	// Actions
	// ===========================

	public void enterNameOnCard(String name) {

		logger.info("Entering Card Holder Name");

		actions.type(nameOnCard, name);
	}

	public void enterCardNumber(String number) {

		logger.info("Entering Card Number");

		actions.type(cardNumber, number);
	}

	public void enterCVC(String cvcNumber) {

		logger.info("Entering CVC");

		actions.type(cvc, cvcNumber);
	}

	public void enterExpiryMonth(String month) {

		logger.info("Entering Expiry Month");

		actions.type(expiryMonth, month);
	}

	public void enterExpiryYear(String year) {

		logger.info("Entering Expiry Year");

		actions.type(expiryYear, year);
	}

	public void clickPayAndConfirmOrder() {

		logger.info("Clicking Pay And Confirm Order");

		actions.click(payAndConfirmOrderButton);

	}

	// ===========================
	// Business Flow
	// ===========================

	@Step("Complete Payment")
	public void completePayment(String name, String number, String cvcNumber, String month, String year) {

		logger.info("Starting Payment");

		enterNameOnCard(name);
		enterCardNumber(number);
		enterCVC(cvcNumber);
		enterExpiryMonth(month);
		enterExpiryYear(year);
		clickPayAndConfirmOrder();

		logger.info("Payment Completed Successfully");
	}

}