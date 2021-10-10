package MySessions.MySession;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Frameseg {
	
	WebDriver driver;
	
	
	@BeforeClass
	public void setBrowser() throws InterruptedException {
		String cudir = System.getProperty("user.dir");
		String path = cudir + "\\driver\\msedgedriver.exe";
		System.setProperty("webdriver.edge.driver", path);
		driver = new EdgeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
	}

	@Test
	public void action() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		Actions a =new Actions(driver);
		a.moveToElement(driver.findElement(By.cssSelector("a[id='nav-link-accountList']"))).build().perform();
		driver.findElement(By.xpath("//div[@id='nav-flyout-accountList'] //span[@class='nav-action-inner']")).click();
		driver.findElement(By.id("ap_email")).sendKeys("8919444742");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("umesh125");
		driver.findElement(By.id("auth-signin-button")).click();
		a.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("induction stove").build().perform();
		driver.findElement(By.xpath("//*[@id=\'nav-xshop\']/a[1]")).click();
	    
	}
	
	@Test
	public void amazonpay()
	{
		String strUrl = driver.getCurrentUrl();
	    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1)); //switches to new tab
	    driver.get(strUrl);
	    
	}

}
