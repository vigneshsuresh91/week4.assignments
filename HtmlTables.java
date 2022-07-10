package week4.Assignment;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HtmlTables {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//Navigating to URL
		driver.get("https://html.com/tags/table/");

		//Finding the Table's Webelement
		WebElement tableElement = driver.findElement(By.xpath("//caption[text()='The Three Most Popular JavaScript Libraries']/parent::table"));

		//Find the Number of Columns in the Table
		List<WebElement> totalNumberOfColumns = tableElement.findElements(By.xpath("//thead/tr/th"));

		System.out.println("The total number of Columns are : "+totalNumberOfColumns.size());

		//Finding the number of Rows in the Table
		List<WebElement> totalNumberOfRows = driver.findElements(By.xpath("//caption[text()='The Three Most Popular JavaScript Libraries']/parent::table/tbody/tr"));
		System.out.println("The total number of Rows are : "+totalNumberOfRows.size());

		//Printing the Values
		for (WebElement webElement : totalNumberOfRows) {
			System.out.println(webElement.getText());
		}
	}
}