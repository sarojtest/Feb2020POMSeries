package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	
	//locators 
	By header = By.cssSelector("h1.private-page__title");
//	By accountName = By.cssSelector("span.account-name ");
//	By accountName = By.xpath("//a/span[@class]");
	By accountMenu =By.cssSelector("#account-menu");

	By accountName = By.cssSelector("div.navAccount-accountName");
	
	By contactsLinkPrimary = By.id("nav-primary-contacts-branch");
	By contactsLinkSecondary = By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	//Verify PageTitle
	public String getHomepageTitle() {
		// return driver.getTitle();
		   return  elementUtil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);
		
	}
	// Verify Sales Dashboard--
	public String getHomePageheader() {
		//if (driver.findElement(header).isDisplayed()) {
			if (elementUtil.doIsDisplayed(header)) {
		//	return driver.findElement(header).getText();
			return elementUtil.doGetText(header);
			
		}
		return null;
	}
	// Verify AccountName--
	public String getAccountname() {
		
		driver.findElement(accountMenu).click();
		elementUtil.waitForElementToBePresent(accountName, 10);
	//	if (driver.findElement(accountName).isDisplayed()) {
		if (elementUtil.doIsDisplayed(accountName)) {
    //		return driver.findElement(accountName).getText();
           return  elementUtil.doGetText(accountName);
		
			
		}
		return null;
		
	}
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
		
	}
	
	private void clickOnContacts() {
		elementUtil.waitForElementToBePresent(contactsLinkPrimary, 10);
		elementUtil.doClick(contactsLinkPrimary);
		elementUtil.waitForElementToBePresent(contactsLinkSecondary, 5);
		elementUtil.doClick(contactsLinkSecondary);
		
	}
	
	
	

}
