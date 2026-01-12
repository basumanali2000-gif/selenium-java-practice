package Testproject.PracticeFramework.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent
{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
		
	@FindBy(xpath = "//div[@class='card-body']")
	List<WebElement> products;
	
	
	By productBy = By.cssSelector(".col-lg-4");
	By OverLayVisibile = By.id("toast-container");
	By OverlayInvisible = By.className("ngx-spinner-overlay");

	
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String expproduct)
	{
		for(WebElement product: getProductList())
		{
			String actpro = product.findElement(By.xpath(".//h5/b")).getText();
			if(actpro.toUpperCase().contains(expproduct.toUpperCase()))
			return product;
		}
		return null;
	}
	
	public void AddSingleToCart(WebElement product)
	{
		product.findElement(By.xpath(".//button[2]")).click();
	}

	public List<WebElement> getAllProduct()
	{
		for(int i =0;i<getProductList().size();i++)
		{
			WebElement currentproduct = products.get(i);
			currentproduct.findElement(By.xpath(".//button[2]")).click();
			ScrollByPageHeight();
		}
			return currentproduct;
			
			
			
		}
		

		public void AddAllToCart(WebElement currentproduct)
		{
			currentproduct.findElement(By.xpath(".//button[2]")).click();
		}

	}

	
	
}
