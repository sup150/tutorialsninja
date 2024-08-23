package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class Search extends Base{
	
	WebDriver driver;
	
	public Search()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct()
	{
		HomePage homePage = new HomePage(driver);
		homePage.enterProductIntoSearchboxField(dataprop.getProperty("validProduct"));
		//driver.findElement(By.name("search")).sendKeys(dataprop.getProperty("validProduct"));
		// xpath for search button//button[contains(@class, 'btn-default btn-lg')] 
		//   //span[@class='input-group-btn']/button
		//driver.findElement(By.xpath("//div[@id='search']/descendent::button")).click();
		//driver.findElement(By.xpath("//button[contains(@class, 'btn-default btn-lg')] ")).click(); - working
		
		homePage.clickOnSearchButton();
		//driver.findElement(By.xpath(" //span[@class='input-group-btn']/button")).click(); //working
		SearchPage searchPage = new SearchPage(driver);
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(), "Valid product HP is not displayed in the search results");
	//	Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct()
	{
		driver.findElement(By.name("search")).sendKeys(dataprop.getProperty("invalidProduct"));
		driver.findElement(By.xpath(" //span[@class='input-group-btn']/button")).click(); //working
		String actualsearchmessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualsearchmessage, dataprop.getProperty("NoProductTextInSearchResult"), "No product message in search result is displayed");
	}
	
	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct()
	{
		driver.findElement(By.xpath(" //span[@class='input-group-btn']/button")).click(); //working
		String actualsearchmessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualsearchmessage, dataprop.getProperty("NoProductTextInSearchResult"), "No product message in search result is displayed");
	}
	
	public void test()
	{
		System.out.println("test");
	}
}
