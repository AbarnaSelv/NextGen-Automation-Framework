package com.abarna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class OrderSuccessPage extends BasePage {

	// ===========================
	// Constructor
	// ===========================

	public OrderSuccessPage(WebDriver driver) {
		super(driver);
	}

	// ===========================
	// Locators
	// ===========================

	@FindBy(xpath = "//h2[@class='title text-center']/b")
	private WebElement orderPlacedMessage;

	@FindBy(xpath = "//a[contains(text(),'Download Invoice')]")
	private WebElement downloadInvoiceButton;

	@FindBy(xpath = "//a[contains(text(),'Continue')]")
	private WebElement continueButton;

	// ===========================
	// Getter Methods
	// ===========================

	public String getOrderPlacedMessage() {
		return actions.getText(orderPlacedMessage);
	}

	// ===========================
	// Actions
	// ===========================

	public void clickDownloadInvoice() {
		actions.click(downloadInvoiceButton);
	}

	public void clickContinue() {
		actions.click(continueButton);
	}

	// ===========================
	// Business Flow
	// ===========================

	@Step("Complete Order")
	public void completeOrder() {

		clickDownloadInvoice();

		clickContinue();
	}

}