package com.abarna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class HomePage extends BasePage {

	// ===========================
	// Constructor
	// ===========================

	public HomePage(WebDriver driver) {
		super(driver);

	}

	// ===========================
	// Locators
	// ===========================

	@FindBy(xpath = "//a[contains(text(),'Logged in as')]")
	private WebElement loggedInUser;

	@FindBy(xpath = "//a[@href='/products']")
	private WebElement productsOption;

	@FindBy(xpath = "//a[contains(text(),'Cart')]")
	private WebElement cartOption;

	@FindBy(xpath = "//a[contains(text(),'Home')]")
	private WebElement homeOption;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	private WebElement logoutOption;

	// ===========================
	// Getter Methods
	// ===========================

	public String getCurrentLoggedInUsername() {

		String text = actions.getText(loggedInUser);

		return text.replace("Logged in as ", "").trim();
	}

	// ===========================
	// Actions
	// ===========================

	@Step("Open Products Page")
	public void openProductsPage() {

		actions.scrollIntoView(productsOption);

		actions.click(productsOption);

	}

	public void openCart() {
		actions.click(cartOption);
	}

	public void openHome() {
		actions.click(homeOption);
	}

	public void logout() {
		actions.click(logoutOption);
	}

}