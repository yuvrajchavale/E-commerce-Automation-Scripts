package tutorialninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_009 {
	
	@Test
	public void VerifyRegistrationWithExistingAccount() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Arun");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Motoori");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("amotoori1@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("09246812111");
		driver.findElement(By.id("input-password")).sendKeys("g@1233487");
		driver.findElement(By.id("input-confirm")).sendKeys("g@1233487");
		driver.findElement(By.xpath("(//input[@value='1'])[2]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		String expEmailVal = "Warning: E-Mail Address is already registered!";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(),expEmailVal);
		
		driver.quit();
		}
	
	public String generateNewMail() {
		
		return new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "")+ "@gmail.com";
	}



	
}
