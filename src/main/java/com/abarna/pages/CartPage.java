package com.abarna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class CartPage extends BasePage {

	// ===========================
	// Constructor
	// ===========================

	public CartPage(WebDriver driver) {
		super(driver);
	}

	// ===========================
	// Locators
	// ===========================

	@FindBy(xpath = "//td[@class='cart_description']/h4/a")
	private WebElement productName;

	@FindBy(xpath = "//td[@class='cart_price']/p")
	private WebElement unitPrice;

	@FindBy(xpath = "//td[@class='cart_quantity']/button")
	private WebElement quantity;

	@FindBy(xpath = "//td[@class='cart_total']/p")
	private WebElement totalPrice;

	@FindBy(xpath = "//a[contains(text(),'Proceed To Checkout')]")
	private WebElement proceedToCheckoutButton;

	// ===========================
	// Getter Methods
	// ===========================

	public String getProductName() {
		return actions.getText(productName);
	}

	public String getUnitPrice() {
		return actions.getText(unitPrice);
	}

	public String getQuantity() {
		return actions.getText(quantity);
	}

	public String getTotalPrice() {
		return actions.getText(totalPrice);
	}

	// ===========================
	// Actions
	// ===========================

	@Step("Proceed To Checkout")
	public void clickProceedToCheckout() {
		actions.click(proceedToCheckoutButton);
	}

}