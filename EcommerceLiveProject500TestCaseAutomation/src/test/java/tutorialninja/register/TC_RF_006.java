package tutorialninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_006 {


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
		//driver.findElement(By.xpath("(//input[@name='newsletter'])[1]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
				
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
		
		driver.findElement(By.linkText("Continue")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	
		/*
		 * Actions actions = new Actions(driver);
		 * actions.sendKeys(Keys.PAGE_DOWN).build().perform();
		 */
		
		  driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();

		  WebElement radioButtonNotSelect = driver.findElement(By.xpath("//input[@value='0']"));

		  Assert.assertTrue(radioButtonNotSelect.isSelected());
			/*
			 * if (!radioButtonNotSelect.isSelected()) {
			 * System.out.println("The radio button is not selected.");
			 * radioButtonNotSelect.click(); } else {
			 * System.out.println("The radio button selected."); }
			 */
		driver.quit();

		}
	

	public String generateNewMail() {
		
		return new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "")+ "@gmail.com";
	}

}
