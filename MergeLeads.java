package week4.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeLeads {

	public static void main(String[] args) throws InterruptedException {

		//For Setting up the environment
		WebDriverManager.chromedriver().setup();

		//Chrome Driver to for Talking to the Web Browser
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		//Navigating to URL
		driver.get("http://leaftaps.com/opentaps/control/login");

		//Entering Credentials
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");

		//Clicking on the Button
		driver.findElement(By.className("decorativeSubmit")).click();

		//Clicking on CRMSFA and then Clicking on Create Lead
		driver.findElement(By.linkText("CRM/SFA")).click();

		//Clicking on Contacts
		driver.findElement(By.linkText("Contacts")).click();

		//Clicking on Merge Contacts
		driver.findElement(By.linkText("Merge Contacts")).click();

		//Clicking on From Leads
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();

		//Get the Window Handles
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listOfWindow  = new ArrayList<String>(windowHandles);

		//Switching to the Child Window
		driver.switchTo().window(listOfWindow.get(1));

		Thread.sleep(3000);
		WebElement contactTable = driver.findElement(By.xpath("//span[text()='Contact List']/following::table[2]/tbody"));

		//Clicking the From Value for Contact
		Actions builder = new Actions(driver);
		builder.moveToElement(contactTable.findElement(By.xpath("//span[text()='Contact List']/following::table[2]/tbody/tr[1]/td[1]/div/a"))).build().perform();
		driver.findElement(By.xpath("//span[text()='Contact List']/following::table[2]/tbody/tr[1]/td[1]/div/a")).click();

		//Switching to the Parent Window
		driver.switchTo().window(listOfWindow.get(0));

		//Clicking on To Leads
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();

		//Switching to the Child Window
		//Get the Window Handles
		Set<String> windowHandlesTo = driver.getWindowHandles();
		List<String> listOfWindowTo  = new ArrayList<String>(windowHandlesTo);
		driver.switchTo().window(listOfWindowTo.get(1));
		
		//Clicking on the to value
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Contact List']/following::table[3]/tbody/tr[1]/td[1]/div/a")).click();
		
		//Switching Back to Parent Window
		driver.switchTo().window(listOfWindowTo.get(0));

		//Clicking on Merge Leads
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		
		//Accepting the Alert that comes
		driver.switchTo().alert().accept();
		
		//Verifying the title of the Page
		String title = driver.getTitle();
		if(title.equalsIgnoreCase("View Contact | opentaps CRM")) {
		System.out.println("The Title of the Page is as expected and is : "+title);
		}
		
		//Closing all the browsers
		driver.quit();
	}

}
