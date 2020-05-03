package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactsPageTest {
	Properties prop;
	WebDriver driver;

	BasePage basepage;
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactspage;

	@BeforeTest
	public void setUp() {
		BasePage basepage = new BasePage();
		prop = basepage.init_prop();
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactspage = homepage.goToContactsPage();
	}
	@Test(priority = 1)
	public void verifyContactsPageTitle() {
		String title=contactspage.getContactsPageTitle();
		System.out.println("contacts page title is"+ title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	@DataProvider
    public Object[][] getContactsTestData() {
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
		
		
	}	
	
	@Test(priority = 2 , dataProvider ="getContactsTestData")
	public void createNewContactTest(String email,String firstname,String lastname,String jobtitle) {
	String name	= contactspage.createNewContact(email,firstname,lastname, jobtitle);
	Assert.assertEquals(name, firstname+" "+lastname);
	
	}

	@AfterTest()
	public void tearDown() {
		//driver.quit();
		
	}
	


}
