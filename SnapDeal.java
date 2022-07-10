package week4.Assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().browserVersion("103.0.5060.66").setup();

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.get("https://www.snapdeal.com/");

		//Entering the Running Shoes in Search Bar
		driver.findElement(By.id("inputValEnter")).sendKeys("Running Shoes");

		//Clicking on Search Button in Home Page
		Thread.sleep(3000);
		try {
			driver.findElement(By.className("searchTextSpan")).click();
		} catch (Exception e) {
			Thread.sleep(5000);
			driver.findElement(By.className("searchTextSpan")).click();
		}

		//Getting the total Number of Shoes appearing in the list
		Thread.sleep(2000);
		List<WebElement> runningShoesList = driver.findElements(By.xpath("//div[@class='product-tuple-image ']"));
		System.out.println("The total Number of Running Shoes appearing is : "+runningShoesList.size());

		//Sorting the values
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class,'sorting-sec')]")).click();

		//Clicking on Price Low to High
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul[@class='sort-value']/li[3]")).click();
		Thread.sleep(3000);

		//Fetching the prices of the Products and comparing to check if Sorting has happened as expected
		List<WebElement> priceList = driver.findElements(By.xpath("//span[contains(@class,'product-price')]"));
		for (int i = 0; i < priceList.size(); i++) {
			if( Integer.parseInt(priceList.get(0).getAttribute("display-price")) <  Integer.parseInt(priceList.get(1).getAttribute("display-price"))){
				System.out.println("The Products have been Sorted from Price Low to High as expected !! And the price of First Item is : "+Integer.parseInt(priceList.get(0).getAttribute("display-price"))+" and the Price of Second Item is : "+Integer.parseInt(priceList.get(1).getAttribute("display-price")));
				break;
			} else {
				System.out.println("The Products have not been sorted correctly !! Please check !!");
			}
		}

		//Entering From and To value to Filter Product
		WebElement fromPrice = driver.findElement(By.name("fromVal"));
		fromPrice.clear();
		fromPrice.sendKeys("900");
		WebElement toPrice = driver.findElement(By.name("toVal"));
		toPrice.clear();
		toPrice.sendKeys("1500",Keys.ENTER);

		//Selecting Blue Colour
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@data-filtername='Color_s']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[@for='Color_s-Blue']")).click();
		System.out.println("The Colour Blue is Selected : "+driver.findElement(By.id("Color_s-Blue")).isSelected());

		//Selecting the First Search Result
		try {
			Thread.sleep(500);
			driver.findElement(By.xpath("(//div[@class='product-tuple-image '])[1]")).click();
		} catch(StaleElementReferenceException e) {}
		
		//Switching to Child Window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listOfWindowHandles = new ArrayList<String>(windowHandles);
		driver.switchTo().window(listOfWindowHandles.get(1));
		
		//Printing the Details form the Item Selected
		System.out.println("The Price of the Product is : Rs."+driver.findElement(By.xpath("//span[@itemprop='price']")).getText());
		System.out.println("The Discount % of the Product is : "+driver.findElement(By.xpath("//span[@class='pdpDiscount ']/span")).getText()+" % OFF");

		//Taking Screenshot of the Product's main Image
		WebElement mainImage = driver.findElement(By.xpath("//img[@title='Campus VERON Blue Running Shoes' and @slidenum='0']"));
		File src = mainImage.getScreenshotAs(OutputType.FILE);
		File desc = new File("./src/main/1.jpg");
		FileUtils.copyFile(src, desc);
		
		//Closing all the windows and killing the Webdriver
		driver.quit();
	}
}
