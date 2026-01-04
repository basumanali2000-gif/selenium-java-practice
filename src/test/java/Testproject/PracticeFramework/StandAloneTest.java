package Testproject.PracticeFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
//		driver.findElement(By.xpath("//p[@class='login-wrapper-footer-text']")).click();
//		driver.findElement(By.id("firstName")).sendKeys("Manali");
//		driver.findElement(By.id("lastName")).sendKeys("Basu");
//		driver.findElement(By.id("userEmail")).sendKeys("manali@yopmail.com");
//		driver.findElement(By.id("userMobile")).sendKeys("9837646677");
//		
//		WebElement k = driver.findElement(By.xpath("//select[@formcontrolname='occupation']"));
//		Select s = new Select(k);
//		s.selectByValue("3: Engineer");
//		driver.findElement(By.xpath("//input[@value='Female']")).click();
//		driver.findElement(By.id("userPassword")).sendKeys("Abcd1234");
//		driver.findElement(By.id("confirmPassword")).sendKeys("Abcd1234");
//		driver.findElement(By.xpath("//input[@class='ng-untouched ng-pristine ng-invalid']")).click();
//		driver.findElement(By.id("login")).click();

		driver.findElement(By.id("userEmail")).sendKeys("manali@yopmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abcd1234");
		driver.findElement(By.id("login")).click();

		WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(20));
		wb.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));

		String expproduct = "Shoe";

		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']"));
		
		//For selecting all products
		for(int i =0;i<products.size();i++)
		{
			products = driver.findElements(By.xpath("//div[@class='card-body']"));
			WebElement product = products.get(i);
			
			product.findElement(By.xpath(".//button[2]")).click();
			wb.until(ExpectedConditions.visibilityOfElementLocated(By.className("ngx-spinner-overlay")));

			wb.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngx-spinner-overlay")));
			
		}
		
		//For selecting single product
//		for(WebElement product: products)
//		{
//			String actpro = product.findElement(By.xpath(".//h5/b")).getText();
//			if(actpro.equalsIgnoreCase(expproduct))
//			{
//				product.findElement(By.xpath(".//button[2]")).click();
//				break;
//			}
//		}
//		
		//wb.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		
		//wb.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngx-spinner-overlay")));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		wb.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'My Cart')]")));
		
		List<WebElement> cartitems = driver.findElements(By.xpath("//div[@class='infoWrap']/div[@class='cartSection']/h3"));
		
		boolean productFound = false;
		
		for(WebElement exptedproduct : cartitems)
		{
			String productname = exptedproduct.getText();
			if(productname.toUpperCase().contains(expproduct.toUpperCase()))
				{
				productFound = true;
				System.out.println("Product "+ expproduct+" present in cart as " + productname);
				WebElement parent = exptedproduct.findElement(By.xpath("ancestor::div[@class='infoWrap']"));
				parent.findElement(By.xpath(".//button[@class='btn btn-primary']")).click();
				break;
				}
		}
		if(!productFound)
		
		{
					System.out.println("Product not found");
					WebElement element = wb.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Checkout')]")));

					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);", element);
					//js.executeScript("window.scrollBy(0,500)");
					
					element.click();
		}
		
		WebElement expmnt = driver.findElement(By.xpath("//select[@class='input ddl']"));
		
		Select option = new Select(expmnt);
		option.selectByContainsVisibleText("06");
		
		WebElement expyr = driver.findElement(By.xpath("(//select[@class='input ddl'])[2]"));
		Select option2 = new Select(expmnt);
		option2.selectByContainsVisibleText("30");
		
		driver.findElement(By.xpath("//input[@class='input txt']")).sendKeys("122");
		driver.findElement(By.xpath("(//input[@class='input txt'])[2]")).sendKeys("Manali Basu");
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "Ind").build().perform();
		
		WebElement dropOption = wb.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='India']")));
		dropOption.click();
		
	
	}

}
