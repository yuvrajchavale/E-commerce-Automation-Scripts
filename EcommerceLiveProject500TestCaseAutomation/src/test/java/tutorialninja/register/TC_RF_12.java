package tutorialninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_RF_12 {
WebDriver driver;

	
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		Actions actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
	}
	
	@Test
	public void KeyboaredActionRegister() throws Throwable {
		
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Daren" + Keys.TAB 
				+ "Roger" + Keys.TAB + "george12@gmail.com" + Keys.TAB 
				+ "1234567890" + Keys.TAB + "12345" + Keys.TAB
				+ "12345");
		
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		Thread.sleep(10000);
		
		String expTxt1 = "Your Account Has Been Created!";
		String expTxt2 = "Congratulations! Your new account has been successfully created!";
		String expTxt3 = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String expTxt4 = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String expTxt5 = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.";
		
		String actTxt = driver.findElement(By.id("content")).getText();
		Assert.assertTrue(actTxt.contains(expTxt1));
		Assert.assertTrue(actTxt.contains(expTxt2));
		Assert.assertTrue(actTxt.contains(expTxt3));
		Assert.assertTrue(actTxt.contains(expTxt4));
		Assert.assertTrue(actTxt.contains(expTxt5));

	} 
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
