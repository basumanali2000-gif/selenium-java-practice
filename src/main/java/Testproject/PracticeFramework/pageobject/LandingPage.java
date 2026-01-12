package Testproject.PracticeFramework.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent
{
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
		
	@FindBy(id = "userEmail")
	WebElement useremail;
	@FindBy(id="userPassword")
	WebElement userpwd;
	@FindBy(id="login")
	WebElement submit;

	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	public void loginApplication(String email, String pwd)
	{
		useremail.sendKeys(email);
		userpwd.sendKeys(pwd);
		submit.click();
	}
	
	
	
	
}
