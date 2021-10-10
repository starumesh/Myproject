package MySessions.MySession;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UnitTest {

WebDriver driver;
	
	@BeforeTest(groups= {"kite"})
	public void setupBrowser() throws InterruptedException {
		String cudir = System.getProperty("user.dir");
		String path = cudir + "\\driver\\msedgedriver.exe";
		System.setProperty("webdriver.edge.driver", path);
		driver = new EdgeDriver();
		driver.get("https://vardenisiffror.se/unitlists");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"main-scroll-el\"]/div/div[2]/div/div/button")).click();

	}

	@Test(groups= {"kite"})
	public void newlist() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"main-scroll-el\"]/main/div/button")).click();// create new list
		driver.findElement(By.xpath("//*[@id=\"main-scroll-el\"]/main/div/ul/li[1]/div[1]/a/h2")).click();// go to list																								
		driver.findElement(By.xpath("//*[@id=\"unitlist-name\"]")).sendKeys("FirstList");// rename list
		driver.findElement(By.xpath("//*[@id=\'select-organizations-input\']")).sendKeys("Gotland");// search item
		
		List<WebElement> results = driver.findElements(By.xpath("//li[@role='option']"));// take into list
		for (WebElement result : results) {
			if (result.getText().equalsIgnoreCase("Stockholm - Gotlands sjukvårdsregion")) {
				result.click();// select into list
				break;
			}
		}
		driver.findElement(By.xpath("//*[@id=\"main-scroll-el\"]/main/div/div/div[1]/a")).click();// go back after save																								
	}

	@Test(dependsOnMethods= {"newlist"},groups= {"kite"})
	public void exstlist() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"main-scroll-el\"]/main/div/ul/li[2]/div[1]/a/h2")).click();
		driver.findElement(By.xpath("//*[@id=\"unitlist-name\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"unitlist-name\"]")).sendKeys("Secondlist");
		driver.findElement(By.xpath("//*[@id=\"main-scroll-el\"]/main/div/div/div[1]/a")).click();// go back after save
	}
	
	@AfterTest
	public void endtest() throws InterruptedException {
		
		Thread.sleep(3000);
		driver.close();
	}

}
