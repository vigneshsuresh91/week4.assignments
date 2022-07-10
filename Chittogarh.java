package week4.Assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chittogarh {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.get("https://www.chittorgarh.com/");
		driver.findElement(By.id("navbtn_stockmarket")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("NSE Bulk Deals")).click();
		
		WebElement tableWithBulkData = driver.findElement(By.xpath("//h2[text()='NSE Bulk Deals From Last Trading Session']/parent::div/div/table"));

		List<WebElement> bulkDataCompanyNames = tableWithBulkData.findElements(By.xpath("//tbody/tr/td[2]"));
		
		List<String> compList = new ArrayList<String>();
		for (WebElement webElement : bulkDataCompanyNames) {
			compList.add(webElement.getText());
		}
		
		Set<String> compSet = new LinkedHashSet<String>(compList);
		
		if(compList.size() != compSet.size()) {
			System.out.println("The total number of entries on the page are : "+compList.size()+" and the number of unique companies are : "+compSet.size());
			System.out.println("There has been duplicates in the Company names. The unique company names are :"+compSet);
		}
		driver.quit();
	}

}
