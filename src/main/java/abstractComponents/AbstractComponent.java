package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent 
{
	WebDriver driver;

	public AbstractComponent(WebDriver driver) 
	{
		this.driver = driver;
	}

	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(20));
		wb.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForElementToDisAppear(By findBy)
	{
		WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(20));
		wb.until(ExpectedConditions.invisibilityOfElementLocated(findBy));

	}

	public void ScrollByPageHeight()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");	
	}
}
