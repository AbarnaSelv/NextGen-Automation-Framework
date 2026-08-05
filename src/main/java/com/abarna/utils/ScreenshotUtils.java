package com.abarna.utils;

import java.io.File;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.epam.healenium.SelfHealingDriver;

public class ScreenshotUtils {

	public static String captureScreenshot(WebDriver driver, String testName) {

		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

		String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

		SelfHealingDriver shDriver = (SelfHealingDriver) driver;

		WebDriver delegate = shDriver.getDelegate();

		File source = ((TakesScreenshot) delegate).getScreenshotAs(OutputType.FILE);

		File destination = new File(path);

		try {

			FileUtils.copyFile(source, destination);

		} catch (IOException e) {

			e.printStackTrace();

		}

		return path;
	}

}