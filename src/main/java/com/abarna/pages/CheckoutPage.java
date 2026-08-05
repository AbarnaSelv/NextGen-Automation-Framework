package com.abarna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class CheckoutPage extends BasePage {

	// ===========================
	// Constructor
	// ===========================

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	// ===========================
	// Locators
	// ===========================

	@FindBy(xpath = "//h2[text()='Address Details']")
	private WebElement addressHeading;

	@FindBy(id = "address_delivery")
	private WebElement deliveryAddress; // gets the text of all the child elements

	@FindBy(id = "address_invoice")
	private WebElement billingAddress;

	@FindBy(name = "message")
	private WebElement orderComment;

	@FindBy(xpath = "//a[contains(text(),'Place Order')]")
	private WebElement placeOrderButton;

	// ===========================
	// Getter Methods
	// ===========================

	public String getAddressHeading() {
		return actions.getText(addressHeading);
	}

	public String getDeliveryAddress() {
		return actions.getText(deliveryAddress);
	}

	public String getBillingAddress() {
		return actions.getText(billingAddress);
	}

	// ===========================
	// Actions
	// ===========================

	public void enterOrderComment(String comment) {
		actions.type(orderComment, comment);
	}

	public void clickPlaceOrder() {
		actions.click(placeOrderButton);
	}

	// ===========================
	// Business Flow
	// ===========================

	@Step("Proceed To Payment")
	public void proceedToPayment(String comment) {

		enterOrderComment(comment);

		clickPlaceOrder();
	}

}