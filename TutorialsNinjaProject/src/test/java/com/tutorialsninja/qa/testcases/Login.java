package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class Login extends Base{
	
	WebDriver driver;
	
	public Login()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.linkText("Login")).click();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password)
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPasswordField(password);
		loginPage.clickOnLoginButton();
		
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfeditYourAccountInformationOption(), "Edit your account information is not displayed..");
		/*driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();*/
		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Edit your account information is not displayed..");
	}
	
	@DataProvider(name = "validCredentialsSupplier")
	public Object[] supplyTestData()
	{
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials()
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPasswordField(dataprop.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		//driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage = loginPage.retirveEmailPasswordNotMatchingWarningMessageText();
		//String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage =dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message not displayed..");
		
	}
	
	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword()
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPasswordField(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message not displayed..");
		driver.quit();
	}
	
	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword()
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPasswordField(dataprop.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		String actualWarningMessage = loginPage.retirveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message not displayed..");
		
	}
	
	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials()
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnLoginButton();
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage = loginPage.retirveEmailPasswordNotMatchingWarningMessageText();
		//String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage = dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message not displayed..");
	}
}
