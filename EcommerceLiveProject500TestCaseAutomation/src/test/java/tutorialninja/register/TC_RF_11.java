package tutorialninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_RF_11 {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
	}
	
	@Test
	public void VerifyRegistrationWithInvalidTelphoneNo() {
		
		
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("George");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Washinton");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(generateNewMail());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("111");
		driver.findElement(By.id("input-password")).sendKeys("g@1233487");
		driver.findElement(By.id("input-confirm")).sendKeys("g@1233487");
		driver.findElement(By.xpath("(//input[@name='newsletter'])[1]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		String expTelephoneValMsg = "Telephone must be between 3 and 32 characters!";
		//Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Telephone must be between 3 and 32 characters!']")).getText(), expTelephoneValMsg);
	    try {
	        // Check if validation message is shown
	        String actualMsg = driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between')]")).getText();
	        Assert.assertEquals(actualMsg, expTelephoneValMsg, "Validation message mismatch!");
	        System.out.println("Test Passed - Validation message displayed: " + actualMsg);

	    } catch (Exception e) {
	        // If no validation message, check what happened instead
	        String actualPageTitle = driver.getTitle();
	        String actualUrl = driver.getCurrentUrl();
	        Assert.fail("❌ Test Failed - Expected telephone validation, but account was created.\n"
	                + "Actual Page Title: " + actualPageTitle + "\n"
	                + "Actual URL: " + actualUrl);
	    }
		

}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
		
    public String generateNewMail() {
		
		return new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "")+ "@gmail.com";
	}

}

