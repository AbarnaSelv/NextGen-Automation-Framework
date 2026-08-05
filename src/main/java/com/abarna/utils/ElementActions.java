package com.abarna.utils;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abarna.constants.FrameworkConstants;

public class ElementActions {

	private WebDriver driver;
	private WebDriverWait wait;

	public ElementActions(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));
	}

	public void click(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		wait.until(ExpectedConditions.visibilityOf(element));

		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		try {

			wait.until(ExpectedConditions.elementToBeClickable(element));

			element.click();

		} catch (Exception e) {
			e.printStackTrace();

			js.executeScript("arguments[0].click();", element);
		}
	}

	public void type(WebElement element, String text) {

		wait.until(ExpectedConditions.visibilityOf(element));

		element.clear();

		element.sendKeys(text);
	}

	public String getText(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));

		return element.getText();
	}

	public WebElement findElement(By locator) {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public boolean isDisplayed(WebElement element) {

		return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}

	public void scrollDown(int pixels) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0," + pixels + ")");
	}

	public void scrollUp() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,0)");
	}

	public void jsClick(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		removeAds();

		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		js.executeScript("arguments[0].click();", element);
	}

	public void scrollIntoView(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView({behavior:'instant', block:'center'});", element);
	}

	public void removeAds() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		List<WebElement> ads = driver.findElements(By.cssSelector("iframe"));

		for (WebElement ad : ads) {

			try {

				js.executeScript("arguments[0].remove();", ad);

			} catch (Exception e) {

				// Ignore if the iframe disappears while removing it

			}
		}
	}

}