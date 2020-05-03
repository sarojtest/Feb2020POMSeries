package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.OptionsManager;
import com.qa.hubspot.utils.TimeUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	OptionsManager optionsManager;
	
	//for achieving parallel execution to achieve the failure screenshot  .
	public static ThreadLocal<WebDriver>  tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
		
	}

	/**
	 * This method is used to init the browser on the basis of given browserName
	 * 
	 * @param browser
	 * @return driver
	 */

	// public WebDriver init_driver(String browser) { // here we change coding for
	// browser

	public WebDriver init_driver(Properties prop) {

		String browser = prop.getProperty("browser");
		System.out.println("browser name is " + browser);
		optionsManager = new OptionsManager(prop);

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions()); // will use on threadlocal
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			

		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxoptions()); // will use on threadlocal
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxoptions()));
			
		} else if (browser.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println(browser + "is not found, please pass the correct browserName");
		}

		// driver.get("https://app.hubspot.com/login"); // here changes
        
		//driver.manage().deleteAllCookies();
		getDriver().manage().deleteAllCookies();

		//driver.manage().window().maximize();
		getDriver().manage().window().maximize();

		//driver.get(prop.getProperty("url"));
		getDriver().get(prop.getProperty("url"));

		TimeUtil.MediumWait();
		
		//return driver;
		return getDriver();

	}

	/**
	 * This method is used to init/load the properties from config file
	 * 
	 * @return prop
	 */

	public Properties init_prop() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/hubspot/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
		

	}
	/**
	 * take screenshotUtil
	 * @return
	 */
	public String getScreenshot() {
		//File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File src= ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir")+"/screenshot/"+ System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
		
		
	}
	

	

}
