package com.abarna.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.epam.healenium.SelfHealingDriver;

public class AllureUtils {

	public static void attachScreenshot(WebDriver driver) {

		if (driver == null) {
			return;
		}

		SelfHealingDriver shDriver = (SelfHealingDriver) driver;

		WebDriver delegate = shDriver.getDelegate();

		byte[] image = ((TakesScreenshot) delegate).getScreenshotAs(OutputType.BYTES);
	}
}