package tutorialninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_004 {
	
	@Test
	public void VerifyRegistrationWithMandatoryFields() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		
		String expPolicyWarning = "Warning: You must agree to the Privacy Policy!";
		String expFirstNameVal ="First Name must be between 1 and 32 characters!";
		String expLastNameVal ="Last Name must be between 1 and 32 characters!";
		String expEmailVal ="E-Mail Address does not appear to be valid!";
		String expTelephoneVal ="Telephone must be between 3 and 32 characters!";
		String expPasswordVal = "Password must be between 4 and 20 characters!";
		
		String actPolicyWarning = driver.findElement(By.xpath("//div[text()='Warning: You must agree to the Privacy Policy!']")).getText();
		String actFirstNameVal = driver.findElement(By.xpath("//div[text()='First Name must be between 1 and 32 characters!']")).getText();
		String actLastNameVal = driver.findElement(By.xpath("//div[text()='Last Name must be between 1 and 32 characters!']")).getText();
		String actEmailNameVal = driver.findElement(By.xpath("//div[text()='E-Mail Address does not appear to be valid!']")).getText();
		String actTelephoneVal = driver.findElement(By.xpath("//div[text()='Telephone must be between 3 and 32 characters!']")).getText();
		String actPasswordVal = driver.findElement(By.xpath("//div[text()='Password must be between 4 and 20 characters!']")).getText();
		
		
		Assert.assertEquals(expPolicyWarning,actPolicyWarning);
		Assert.assertEquals(expFirstNameVal,actFirstNameVal);
		Assert.assertEquals(expLastNameVal,actLastNameVal);
		Assert.assertEquals(expEmailVal,actEmailNameVal);
		Assert.assertEquals(expTelephoneVal,actTelephoneVal);
		Assert.assertEquals(expPasswordVal,actPasswordVal);
		driver.quit();

}
}
