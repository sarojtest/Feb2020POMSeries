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
//precondition ...> Test ..> ac vs  exp ....> post condition
	// launch browser, url ....> test google title ..>Google vs Google....> close browser
	//@BeforeTest ....> @Test.....>Assert....>@AfterTest

public class LoginPageTest {
	
	
	Properties prop;
	WebDriver driver;
	
	BasePage basepage;
	LoginPage loginpage;
	
	
	
	@BeforeTest
	public void setUp() {
		BasePage basepage = new BasePage();
		prop= basepage.init_prop();
		driver = basepage.init_driver(prop);		
		loginpage = new LoginPage(driver);
	
	}
	
	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		String title =loginpage.getLoginPageTitle();
		System.out.println("login page title is "+ title );
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	
	}
	
	@Test(priority = 2)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginpage.checkSignUpLink());
	}
	
	@Test(priority = 3)
	public void loginTest() {
		HomePage homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String accountname=homepage.getAccountname();
		System.out.println(accountname);
	   Assert.assertEquals(homepage.getAccountname(),prop.getProperty(accountname));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		
	}
	
	
	

}
