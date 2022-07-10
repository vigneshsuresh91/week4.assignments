package week4.Assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.get("https://www.nykaa.com/");

		//Clicking on Brands
		WebElement findElement = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(findElement).build().perform();
		builder.clickAndHold().build().perform();

		//Entering the Search Text
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");

		//Clicking on the Search Result
		Thread.sleep(3000);
		driver.findElement(By.linkText("L'Oreal Paris")).click();

		//Validating the Title of the Page
		if(driver.getTitle().contains("L'Oreal Paris")) {
			System.out.println("The Title of the page is as expected!");
		}

		driver.findElement(By.xpath("//span[contains(text(),'Sort By')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'customer')]")).click();


		//Clicking on Category in the Side Bar
		driver.findElement(By.xpath("//span[contains(text(),'Category')]")).click();
		waiter();
		driver.findElement(By.xpath("//span[contains(text(),'Hair')]")).click();
		waiter();
		driver.findElement(By.xpath("//span[contains(text(),'Hair Care')]")).click();
		waiter();
		driver.findElement(By.xpath("//span[contains(text(),'Shampoo')]")).click();
		waiter();

		//Finding if Shampoo Search is successful
		List<WebElement> findElements = driver.findElements(By.xpath("//div[@id='product-list-wrap']//a//div/img"));
		if(findElements.get(0).getAttribute("alt").contains("Shampoo")) {
			System.out.println("Shampoo Search is successful as expected");
		}
		
		for (WebElement webElement : findElements) {
			if(webElement.getAttribute("alt").contains("LOreal Paris Dream Lengths Restoring Shampoo")) {
				webElement.click();
				break;
			}
		}
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowsToSwitch = new ArrayList<>(windowHandles);
		driver.switchTo().window(windowsToSwitch.get(1));
		
		//Selecting the Volume of the SHampoo
		WebElement shampooBottleSelection = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select mlOfShampooSelection = new Select(shampooBottleSelection);
		mlOfShampooSelection.selectByVisibleText("1000ml");

		//Printing the Price of the Shampoo
		System.out.println("The Price of the Shampoo is "+driver.findElement(By.xpath("//div[text()='inclusive of all taxes']/preceding::span[starts-with(text(),'MRP')]/following-sibling::span")).getText());
		
	}
	
	public static void waiter() throws InterruptedException {
		Thread.sleep(3000);
	}
}