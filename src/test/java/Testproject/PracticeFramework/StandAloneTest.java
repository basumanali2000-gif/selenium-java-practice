package Testproject.PracticeFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Testproject.PracticeFramework.pageobject.LandingPage;
import Testproject.PracticeFramework.pageobject.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
//		driver.get("https://rahulshettyacademy.com/client/#/auth/login"); //==> Used on Landing page
		String expproduct = "ZARA";

		//Below code was used for registration, now its not used anywhere 
		
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
		
		
		//Sign in=> Used in landing page
//		driver.findElement(By.id("userEmail")).sendKeys("manali@yopmail.com");
//		driver.findElement(By.id("userPassword")).sendKeys("Abcd1234");
//		driver.findElement(By.id("login")).click();

		WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		//Wait used in abstract component class and css locator used in product catalog page 
//		wb.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		
		//Used in product catalog to locate products
//		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']"));


		LandingPage ob = new LandingPage(driver);
		ob.goTo();
		ob.loginApplication("manali@yopmail.com", "Abcd1234");
		ProductCatalogue plist = new ProductCatalogue(driver);
		List<WebElement> products = plist.getProductList();

		
		//For adding all products to cart------------------------------------>
		for(int i =0;i<products.size();i++)
		{
			products = driver.findElements(By.xpath("//div[@class='card-body']"));
			WebElement product = products.get(i);
			
			
			//Scrolling to the bottom to make sure no products are missed
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//js.executeScript("window.scrollBy(0,500)");
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			//js.executeScript("window.scrollTo(0, 900);");
			
			product.findElement(By.xpath(".//button[2]")).click();
			
			// Using below wait to visibility & invisibility of loader
			wb.until(ExpectedConditions.visibilityOfElementLocated(By.className("ngx-spinner-overlay")));
			wb.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngx-spinner-overlay")));
			
		}
		
		//Code For adding single product to cart--------------------------------->
//		for(WebElement product: products)
//		{
//			String actpro = product.findElement(By.xpath(".//h5/b")).getText();
//			if(actpro.equalsIgnoreCase(expproduct))
//			{
//				product.findElement(By.xpath(".//button[2]")).click();
//				break;
//			}
//		}
		
		//Scrolling up to click on cart button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-864)");

		// Using below wait to visibility & invisibility of loader
		wb.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wb.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngx-spinner-overlay")));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		
		//Waiting for visibility of My cart heading in the next page
		wb.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'My Cart')]")));
		
		
		//Storing the cart items in the list
		List<WebElement> cartitems = driver.findElements(By.xpath("//div[@class='infoWrap']/div[@class='cartSection']/h3"));
		
		
		boolean productFound = false;
		
		for(WebElement exptedproduct : cartitems)
		{
			String productname = exptedproduct.getText();
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			//Checking if the expected product is present in the cart then clicking buy now button	
			if(productname.toUpperCase().contains(expproduct.toUpperCase()))
				{
				productFound = true;
				System.out.println("Product "+ expproduct+" present in cart as " + productname);
				WebElement parent = exptedproduct.findElement(By.xpath("ancestor::div[@class='infoWrap']"));
				parent.findElement(By.xpath(".//button[@class='btn btn-primary']")).click();
				break;
				}
		}
		//Checking if the expected product is not present in the cart then scrolling to bottom and clicking checkout button	
		if(!productFound)
		
		{
					System.out.println("Product not found");
					WebElement element = wb.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Checkout')]")));
					
					
		//<=====================Below are different solutions tried to scroll but nothing works always==========================> 
					// System screen height (OS level)
//					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//					int systemScreenHeight = screenSize.height;
//
//					// Full page height (DOM level)
//					Long fullPageHeight = (Long) js.executeScript(
//					        "return document.body.scrollHeight;"
//					);

//					// Calculate difference
//					long difference = fullPageHeight - systemScreenHeight;
//
//					// Print to console
//					System.out.println("System Screen Height: " + systemScreenHeight);
//					System.out.println("Full Page Height: " + fullPageHeight);
//					System.out.println("Difference (Full Page - Screen): " + difference);
//
//			        
//					//JavascriptExecutor js = (JavascriptExecutor) driver;
//					//js.executeScript("arguments[0].scrollIntoView(true);", element);
//					//js.executeScript("window.scrollBy(0,500)");
//					//js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//					//js.executeScript("window.scrollTo(0, 900);");
//					js.executeScript("window.scrollBy(0, arguments[0]);", difference);
					long lastHeight = (long) js.executeScript(
					        "return document.body.scrollHeight;"
					);

					while (true) {
					    // Scroll to bottom
					    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

					    // Small pause for DOM to settle
					    Thread.sleep(500);

					    long newHeight = (long) js.executeScript(
					            "return document.body.scrollHeight;"
					    );

					    // Break when no more content loads
					    if (newHeight == lastHeight) {
					        break;
					    }
					    lastHeight = newHeight;
					}

					element.click();
		}
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");

		//Filling in all the card details 
		
		WebElement expmnt = driver.findElement(By.xpath("//select[@class='input ddl']"));
		
		Select option = new Select(expmnt);
		option.selectByContainsVisibleText("06");
		
		WebElement expyr = driver.findElement(By.xpath("(//select[@class='input ddl'])[2]"));
		Select option2 = new Select(expyr);
		option2.selectByContainsVisibleText("30");
		
		driver.findElement(By.xpath("//input[@class='input txt']")).sendKeys("122");
		driver.findElement(By.xpath("(//input[@class='input txt'])[2]")).sendKeys("Manali Basu");
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "Ind").build().perform();
		
		WebElement dropOption = wb.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='India']")));
		dropOption.click();
		
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		wb.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		
		//Checking thankyou message appears 
		String confmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confmsg.toUpperCase().contains("THANKYOU"));
		
	
	}

}
