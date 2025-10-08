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
		
		BufferedImage actualBImg = ImageIO.read(new File(System.getProperty("user.dir")+"\\Screenshots\\sc1Actual.png"));
		BufferedImage expectedBImg = ImageIO.read(new File(System.getProperty("user.dir")+ "\\Screenshots\\sc1Expected.png"));
		
		ImageDiffer imgDiffer = new ImageDiffer();
		ImageDiff imageDifference = imgDiffer.makeDiff(expectedBImg, actualBImg);
		
		//We are getting false here because images are simmilar orelse it will give true
		boolean b = imageDifference.hasDiff();
		System.out.println(b);
		
		Assert.assertFalse(imageDifference.hasDiff());
		driver.quit();
}
	   
	
}