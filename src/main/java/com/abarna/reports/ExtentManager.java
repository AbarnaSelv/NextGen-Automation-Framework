package com.abarna.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getExtentReports() {

		if (extent == null) {

			ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/AutomationReport.html");

			sparkReporter.config().setReportName("Automation Test Report");
			sparkReporter.config().setDocumentTitle("Abarna Selenium Framework");

			extent = new ExtentReports();

			extent.attachReporter(sparkReporter);

			extent.setSystemInfo("Framework", "Selenium Java");
			extent.setSystemInfo("Tester", "Abarna");
			extent.setSystemInfo("Environment", "QA");

		}

		return extent;
	}

}