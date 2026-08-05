package com.abarna.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class ProductsPage extends BasePage {

	// ===========================
	// Constructor
	// ===========================

	public ProductsPage(WebDriver driver) {
		super(driver);
	}

	// ===========================
	// Locators
	// ===========================

	@FindBy(css = "h2.title.text-center")
	private WebElement allProductsHeading;

	@FindBy(id = "search_product")
	private WebElement searchTextBox;

	@FindBy(id = "submit_search")
	private WebElement searchButton;

	// ===========================
	// Getter Methods
	// ===========================

	public String getProductsHeading() {
		return actions.getText(allProductsHeading);
	}

	// ===========================
	// Actions
	// ===========================

	@Step("Search Product: {0}")
	public void searchProduct(String productName) {

		actions.type(searchTextBox, productName);

		actions.click(searchButton);
	}

	public boolean isProductDisplayed(String productName) {

		String xpath = "//p[text()='%s']";

		WebElement product = actions.findElement(By.xpath(String.format(xpath, productName)));

		return product.isDisplayed();
	}

	@Step("Open Product Details for: {0}")
	public void openProductDetails(String productName) {

		String xpath = "//p[text()='%s']/ancestor::div[@class='product-image-wrapper']//a[contains(text(),'View Product')]";

		WebElement viewProduct = actions.findElement(By.xpath(String.format(xpath, productName)));

		actions.click(viewProduct);
	}

}