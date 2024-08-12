package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

public class Search extends Base{
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplicationURL("edge");
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct()
	{
		driver.findElement(By.name("search")).sendKeys("HP");
		// xpath for search button//button[contains(@class, 'btn-default btn-lg')] 
		//   //span[@class='input-group-btn']/button
		//driver.findElement(By.xpath("//div[@id='search']/descendent::button")).click();
		//driver.findElement(By.xpath("//button[contains(@class, 'btn-default btn-lg')] ")).click(); - working
		driver.findElement(By.xpath(" //span[@class='input-group-btn']/button")).click(); //working
		
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct()
	{
		driver.findElement(By.name("search")).sendKeys("Honda");
		driver.findElement(By.xpath(" //span[@class='input-group-btn']/button")).click(); //working
		String actualsearchmessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualsearchmessage, "There is no product that matches the search criteria.","No product message in search result is displayed");
	}
	
	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct()
	{
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath(" //span[@class='input-group-btn']/button")).click(); //working
		String actualsearchmessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualsearchmessage, "There is no product that matches the search criteria.","No product message in search result is displayed");
	}
}
