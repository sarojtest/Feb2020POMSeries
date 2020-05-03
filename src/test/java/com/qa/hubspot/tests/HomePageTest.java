package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

public class HomePageTest {
	
	Properties prop;
	WebDriver driver;
	
	BasePage basepage;
	LoginPage loginpage;
	HomePage homepage;
	
	
	
	@BeforeTest
	public void setUp() {
		BasePage basepage = new BasePage();
		prop= basepage.init_prop();
		driver = basepage.init_driver(prop);	
		loginpage = new LoginPage(driver);
	    homepage= loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	
	}
	@Test(priority = 1)
	public void verifyHomePageTitle() {
		String title =homepage.getHomepageTitle();
		System.out.println("home page title is"+ title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
		
    
	}
	@Test(priority = 2)
	public void verifyHomePageHeader() {
	String header	=homepage.getHomePageheader();
	System.out.println("home page header is "+ header);
	Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
		
	}
	@Test(priority = 3 ,enabled=false)
	public void verifyLoggedInUserTest() {
		String accountName=homepage.getAccountname();
		System.out.println("logged in account name is : "+ accountName);
		Assert.assertEquals(accountName,prop.getProperty("accountname"));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		
	}
	

}
