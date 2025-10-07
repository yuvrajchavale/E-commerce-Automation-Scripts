package tutorialninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_007 {
	@Test
	public void VerifyRegistrationPageDifferentWay() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']//h1[text()='Register Account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("(//a[text()='Register'])[2]")).isDisplayed());
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
		driver.findElement(By.linkText("Continue")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']//h1[text()='Register Account']")).isDisplayed());
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
		driver.findElement(By.xpath("//div[@class='list-group']/a[text()='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']//h1[text()='Register Account']")).isDisplayed());
		driver.quit();

}
}