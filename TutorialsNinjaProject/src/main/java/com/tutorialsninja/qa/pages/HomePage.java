package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	//objects
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	@FindBy(id = "search")
	private WebElement  searchBoxField;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
		//actions
	
	public void clickOnMyAccount()
	{
		myAccountDropMenu.click();
	}
	
	public void selectLoginOption()
	{
		loginOption.click();
	}
	
	public void selectRegisterOption()
	{
		registerOption.click();
	}
	
	public void enterProductIntoSearchboxField(String productText)
	{
		searchBoxField.sendKeys(productText);
	}
}
