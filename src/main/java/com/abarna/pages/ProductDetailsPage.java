package com.abarna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class ProductDetailsPage extends BasePage {

	// ===========================
	// Constructor
	// ===========================

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}

	// ===========================
	// Locators
	// ===========================

	@FindBy(xpath = "//div[@class='product-information']/h2")
	private WebElement productName;

	@FindBy(xpath = "//div[@class='product-information']/p[contains(text(),'Category')]")
	private WebElement category;

	@FindBy(xpath = "//div[@class='product-information']/span/span")
	private WebElement price;

	@FindBy(xpath = "//div[@class='product-information']/p[contains(text(),'Availability')]")
	private WebElement availability;

	@FindBy(xpath = "//div[@class='product-information']/p[contains(text(),'Condition')]")
	private WebElement condition;

	@FindBy(xpath = "//div[@class='product-information']/p[contains(text(),'Brand')]")
	private WebElement brand;

	@FindBy(id = "quantity")
	private WebElement quantity;

	@FindBy(xpath = "//button[@class='btn btn-default cart']")
	private WebElement addToCartButton;

	@FindBy(xpath = "//u[text()='View Cart']")
	private WebElement viewCartButton;

	// ===========================
	// Getter Methods
	// ===========================

	public String getProductName() {
		return actions.getText(productName);
	}

	public String getCategory() {
		return actions.getText(category);
	}

	public String getPrice() {
		return actions.getText(price);
	}

	public String getAvailability() {
		return actions.getText(availability);
	}

	public String getCondition() {
		return actions.getText(condition);
	}

	public String getBrand() {
		return actions.getText(brand);
	}

	// ===========================
	// Actions
	// ===========================

	public void enterQuantity(String qty) {

		actions.type(quantity, qty);
	}

	public void clickAddToCart() {

		actions.jsClick(addToCartButton);
	}

	public void clickViewCart() {

		actions.click(viewCartButton);

	}

	// ===========================
	// Business Flow
	// ===========================

	@Step("Add Product To Cart with Quantity: {0}")
	public void addProductToCartAndOpenCart(String qty) {

		enterQuantity(qty);

		clickAddToCart();

		clickViewCart();
	}

}