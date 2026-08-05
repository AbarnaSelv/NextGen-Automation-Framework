package com.abarna.tests;

import io.qameta.allure.*;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.abarna.base.BaseTest;
import com.abarna.config.ConfigReader;
import com.abarna.constants.FrameworkConstants;
import com.abarna.pages.CartPage;
import com.abarna.pages.CheckoutPage;
import com.abarna.pages.HomePage;
import com.abarna.pages.LoginPage;
import com.abarna.pages.OrderSuccessPage;
import com.abarna.pages.PaymentPage;
import com.abarna.pages.ProductDetailsPage;
import com.abarna.pages.ProductsPage;

@Epic("E-Commerce Automation")
@Feature("Place Order")
public class PlaceOrderTest extends BaseTest {

	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify that a logged-in user can place an order successfully.")
	@Test
	public void verifyUserCanPlaceOrderSuccessfully() {

		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		ProductsPage productsPage = new ProductsPage(driver);
		ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
		CartPage cartPage = new CartPage(driver);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		PaymentPage paymentPage = new PaymentPage(driver);
		OrderSuccessPage orderSuccessPage = new OrderSuccessPage(driver);

		// Login
		loginPage.login(ConfigReader.getEmail(), ConfigReader.getPassword());

		Assert.assertTrue(loginPage.isLoginSuccessful());

		Assert.assertEquals(homePage.getCurrentLoggedInUsername(), ConfigReader.getUsername());

		// Open Products
		homePage.openProductsPage();

		Assert.assertEquals(productsPage.getProductsHeading(), "ALL PRODUCTS");

		// Search Product
		String productName = FrameworkConstants.PRODUCT_NAME;

		productsPage.searchProduct(productName);

		Assert.assertTrue(productsPage.isProductDisplayed(productName));

		// Open Product DetailsaddProductToCartAndOpenCart
		productsPage.openProductDetails(productName);

		Assert.assertEquals(productDetailsPage.getProductName(), productName);

		// Add Product To Cart
		productDetailsPage.addProductToCartAndOpenCart(FrameworkConstants.PRODUCT_QUANTITY);

		// Verify Cart
		Assert.assertEquals(cartPage.getProductName(), productName);

		cartPage.clickProceedToCheckout();

		// Checkout
		Assert.assertEquals(checkoutPage.getAddressHeading(), "Address Details");

		checkoutPage.proceedToPayment(FrameworkConstants.ORDER_COMMENT);

		// Payment
		paymentPage.completePayment(FrameworkConstants.CARD_NAME, FrameworkConstants.CARD_NUMBER,
				FrameworkConstants.CARD_CVC, FrameworkConstants.CARD_EXPIRY_MONTH, FrameworkConstants.CARD_EXPIRY_YEAR);

		// Order Success
		Assert.assertEquals(orderSuccessPage.getOrderPlacedMessage(), "ORDER PLACED!");

		orderSuccessPage.completeOrder();

	}
}