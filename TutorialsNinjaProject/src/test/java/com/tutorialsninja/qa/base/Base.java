package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	WebDriver driver;
	public Properties prop;
	
	public Base() 
	{
		prop = new Properties();
		File propFile = new File(prop.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
	
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}

	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
}
