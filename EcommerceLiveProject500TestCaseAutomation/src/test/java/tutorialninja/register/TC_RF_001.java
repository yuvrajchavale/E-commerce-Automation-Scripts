package tutorialninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class TC_RF_001 {

	@Test
	public void VerifyRegistrationWithMandatoryFields() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("George");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Washinton");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(generateNewMail());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("5835354127");
		driver.findElement(By.id("input-password")).sendKeys("g@1233487");
		driver.findElement(By.id("input-confirm")).sendKeys("g@1233487");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());

	}
	
	public String generateNewMail() {
		
		return new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "")+ "@gmail.com";
	}

}
