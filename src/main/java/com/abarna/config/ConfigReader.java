package com.abarna.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop = new Properties();

	static {

		try {

			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");

			prop.load(fis);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static String getBrowser() {

		return prop.getProperty("browser");
	}

	public static String getUrl() {

		return prop.getProperty("url");
	}

	public static String getEmail() {
		return prop.getProperty("email");
	}

	public static String getPassword() {
		return prop.getProperty("password");
	}

	public static String getUsername() {
		return prop.getProperty("username");
	}

	public static String getExecution() {
		return prop.getProperty("execution");
	}

}