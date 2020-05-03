package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	
	ElementUtil elementUtil;
	
	//1. By Locators :
	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	//2. Constuctors of the page class to initialise driver  :
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
		
	}
	
	//3. page actions /methods:
	public String getLoginPageTitle() {
		//return driver.getTitle();
		return elementUtil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 10);
		
	}
	public boolean checkSignUpLink() {
		//return driver.findElement(signUpLink).isDisplayed();
		return elementUtil.doIsDisplayed(signUpLink);
		
		
	}
	public HomePage doLogin(String un,String pwd) {
//		driver.findElement(username).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();
		
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return new HomePage(driver);
		
		
	}



}
