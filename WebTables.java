package week4.Assignment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTables {

	public static void main(String[] args) {


		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.get("http://www.leafground.com/pages/table.html");

		WebElement tableRow = driver.findElement(By.xpath("//table/tbody/tr"));
		List<WebElement> columnCount = tableRow.findElements(By.xpath("//th"));
		System.out.println("The Total Number of Columns is : "+columnCount.size());


		List<WebElement> rowCount = driver.findElements(By.xpath("//table/tbody/tr"));
		System.out.println("The Total Number of Columns is : "+rowCount.size());

		System.out.println("The Values in table is : ");
		List<WebElement> valuesOfPercentage = tableRow.findElements(By.xpath("//td[2]/font"));
		for (WebElement webElement : valuesOfPercentage) {
			System.out.println(webElement.getText());
		}	
		
		WebElement tableBody = driver.findElement(By.xpath("//table/tbody"));
		List<WebElement> rowElements = tableBody.findElements(By.xpath("//tr/td[2]/font"));
		for (WebElement webElement : rowElements) {
			if(webElement.getText().contains("100")) {
				tableBody.findElement(By.xpath("//tr/td[3]/input[@name='vital']")).click();
			}
		}
	}

}
