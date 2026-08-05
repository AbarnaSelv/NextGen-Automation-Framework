package com.abarna.listeners;

import org.testng.ITestContext;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.abarna.driver.DriverFactory;
import com.abarna.reports.ExtentManager;
import com.abarna.reports.ExtentTestManager;

import com.abarna.utils.ScreenshotUtils;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

	@Override
	public void onStart(ITestContext context) {

		ExtentManager.getExtentReports();
	}

	@Override
	public void onTestStart(ITestResult result) {

		ExtentTestManager.setTest(ExtentManager.getExtentReports().createTest(result.getMethod().getMethodName()));
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		if (DriverFactory.getDriver() != null) {

			String screenshotPath = ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(),
					result.getMethod().getMethodName());

			try {
				ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ExtentTestManager.getTest().fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {

		ExtentManager.getExtentReports().flush();

		ExtentTestManager.unload();
	}
}