package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id = "input-email")
	private WebElement emailAddressField;
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneNumberField;
	
	@FindBy(id = "input-password")
	private WebElement passwordField;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;
	 
	@FindBy(name = "agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value=1]")
	private WebElement yesNewsletterOption;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailAddressWarning;
	
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneNumberWarning;
	
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstNameText)
	{
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText)
	{
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String emailAddressText)
	{
		emailAddressField.sendKeys(emailAddressText);
	}
	
	public void enterTelephoneNumber(String telephoneNumberText)
	{
		telephoneNumberField.sendKeys(telephoneNumberText);
	}
	
	public void enterPassword(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String passwordText)
	{
		confirmPasswordField.sendKeys(passwordText);
	}
	
	public void selectPrivacyPolicy()
	{
		privacyPolicyField.click();
	}
	
	public void clickOnContinueButton()
	{
		continueButton.click();
	}
	
	public void selectYesNewsletterOption()
	{
		yesNewsletterOption.click();
	}
	
	public String retrieveDuplicateEmailAddressWarning()
	{
		String duplicateEmailWarningText = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}
	
	public String retievePrivacyPolicyWarning()
	{
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	
	public String retrieveFirstNameWarning()
	{
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}
	
	public String retrieveLastNameWarning()
	{
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}
	
	public String retrieveEmailAddressWarning()
	{
		String emailAddressWarningText = emailAddressWarning.getText();
		return emailAddressWarningText;
	}
	
	public String retrieveTelephoneNumberWarning()
	{
		String telephoneNumberWarningText  = telephoneNumberWarning.getText();
		return telephoneNumberWarningText;
	}
	
	public String retrievePasswordWarning()
	{
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}
}
