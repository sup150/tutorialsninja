package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends Base {

	WebDriver driver;
	
	public Register()
	{
		super();
	}
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		homePage.selectRegisterOption();
		//driver.findElement(By.linkText("Register")).click();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields()
	{
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		/*driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click(); */
		
		AccountSuccessPage accountSuccesspage = new AccountSuccessPage(driver);
		String actualSuccessHeading = accountSuccesspage.retrieveAccountSuccessPageHeading();
		//String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessHeading, dataprop.getProperty("accountSuccessfullyCreatedHeading"), "Account page created page not displayed");
		
	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields()
	{
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		/*
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value=1]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		*/
		AccountSuccessPage accountSuccesspage = new AccountSuccessPage(driver);
		String actualSuccessHeading = accountSuccesspage.retrieveAccountSuccessPageHeading();
		//String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessHeading, dataprop.getProperty("accountSuccessfullyCreatedHeading"), "Account page created page not displayed");
		
	}
	
	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress()
	{
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNumber(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
	
		/*
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value=1]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		*/
		String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
		//String actualWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		Assert.assertTrue(actualWarning.contains(dataprop.getProperty("duplicateEmailWarning")), "warning message regarding duplicate email address is not displayed");
		
	}
	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails()
	{
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.clickOnContinueButton();
		//driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualPrivacyPolicyWarning = registerPage.retievePrivacyPolicyWarning();
		//String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataprop.getProperty("privacyPolicyWarning")), "Privacy Policy Warning Message Is Not Displayed..");
		String actualFirstNameWarning = registerPage.retrieveFirstNameWarning();
		//String actualFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actualFirstNameWarning, dataprop.getProperty("firstNameWarning"), "First name warning message is not displayed");
		String actualLastNameWarning = registerPage.retrieveLastNameWarning();
		//String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actualLastNameWarning, dataprop.getProperty("lastNameWarning"), "Last name warning message is not displayed");
		String actualEmailWarning = registerPage.retrieveEmailAddressWarning();
		//String actualEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(actualEmailWarning, dataprop.getProperty("emailWarning"),  "Email address warning message is not displayed");
		String actualTelephoneWarning = registerPage.retrieveTelephoneNumberWarning();
		//String actualTelephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(actualTelephoneWarning, dataprop.getProperty("telephoneWarning"), "Telephone number warning message is not displayed");
		String actualPasswordWarning = registerPage.retrievePasswordWarning();
		//String actualPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actualPasswordWarning, dataprop.getProperty("passwordWarning"), "Password warning message is not displayed");
	
	}
	
	
	
}
