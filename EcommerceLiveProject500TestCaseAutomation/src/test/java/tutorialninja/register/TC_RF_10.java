package tutorialninja.register;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class TC_RF_10 {

	@Test
	public void VerifyRegistrationWithInvalidMail() throws Throwable {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Arun");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Motoori");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("amotoori");
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("09246812111");
		driver.findElement(By.id("input-password")).sendKeys("g@1233487");
		driver.findElement(By.id("input-confirm")).sendKeys("g@1233487");
		driver.findElement(By.xpath("(//input[@value='1'])[2]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		WebElement formElement = driver.findElement(By.xpath("//form[@class='form-horizontal']"));
		Thread.sleep(5000);
		
		File srcScreenShot1 = formElement.getScreenshotAs(OutputType.FILE);		
		FileHandler.copy(srcScreenShot1, new File(System.getProperty("user.dir")+"\\Screenshots\\sc1Actual.png"));
		
		Assert.assertFalse(comparetwoScreenshot(System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png", 
				System.getProperty("user.dir") + "\\Screenshots\\sc1Expected.png"));
		 
		//We are getting false here because images are similar or else it will give true
		/*
		 * boolean b = imageDifference.hasDiff(); System.out.println(b);
		 */
		
		  //For Second TestCase
		
		  driver.findElement(By.xpath("//input[@id='input-email']")).clear();
		  driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("amotoori@");
		  driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		  Thread.sleep(5000);
		  File srcScreenShot2 = formElement.getScreenshotAs(OutputType.FILE);
		  FileHandler.copy(srcScreenShot2, new File(System.getProperty("user.dir")+"\\Screenshots\\sc2Actual.png"));
		
		  Assert.assertFalse(comparetwoScreenshot(System.getProperty("user.dir")+"\\Screenshots\\sc2Actual.png",
				  System.getProperty("user.dir")+"\\Screenshots\\sc2Expected.png"));
		  
		  //Third TestData:amotoori@gmail 
		  driver.findElement(By.xpath("//input[@id='input-email']")).clear();
		  driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("amotoori@gmail");
		  driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();		  
		  String expEmaiValidation = "E-Mail Address does not appear to be valid!";
		  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='text-danger']")).getText(), expEmaiValidation);
		  
		  //Fourth TestData amottori@gmail.
		  driver.findElement(By.xpath("//input[@id='input-email']")).clear();
		  driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("amotoori@gmail.");
		  driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();		  
		  
		  
		  WebElement formElement1 = driver.findElement(By.xpath("//form[@class='form-horizontal']"));
		  Thread.sleep(3000);
		  File srcScreenShot3 = formElement1.getScreenshotAs(OutputType.FILE);		
		  FileHandler.copy(srcScreenShot3, new File(System.getProperty("user.dir")+"\\Screenshots\\sc3Actual.png"));
		  
		  Assert.assertFalse(comparetwoScreenshot(System.getProperty("user.dir")+"\\Screenshots\\sc3Actual.png",
				  System.getProperty("user.dir")+"\\Screenshots\\sc3Expected.png"));
		  driver.quit();

	}
	public boolean comparetwoScreenshot(String actualImagePath,String ExpectedImagePath) throws Throwable {
		BufferedImage actualBImg = ImageIO.read(new File(actualImagePath));
		BufferedImage expectedBImg = ImageIO.read(new File(ExpectedImagePath));

		ImageDiffer imgDiffer = new ImageDiffer();
		ImageDiff imageDifference = imgDiffer.makeDiff(expectedBImg, actualBImg);
		return imageDifference.hasDiff();
	}
	   
	
}