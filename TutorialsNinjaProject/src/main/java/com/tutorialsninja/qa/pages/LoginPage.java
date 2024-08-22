package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	//Objects
	@FindBy(id = "input-email")
	WebElement emailAddressField;
	
	@FindBy(id = "input-password")
	WebElement passwordField;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	WebElement emailPasswordNotMatchingWarning;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Actions
	
	public void enterEmailAddress(String emailText)
	{
		emailAddressField.sendKeys();
	}
	
	public void enterPasswordField(String passwordText)
	{
		passwordField.sendKeys();	
	}
	
	public void clickOnLoginButton()
	{
		loginButton.click();
	}
	
	public String retirveEmailPasswordNotMatchingWarningMessageText()
	{
		String warningText =  emailPasswordNotMatchingWarning.getText();
		return warningText;
	}
}
