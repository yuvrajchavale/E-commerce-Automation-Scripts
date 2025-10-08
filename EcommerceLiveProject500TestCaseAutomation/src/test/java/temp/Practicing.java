package temp;

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

public class Practicing {

	@Test
	public void VerifyRegistrationWithInvalidMails() throws Throwable {
	    WebDriver driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    driver.manage().window().maximize();
	    driver.get("https://tutorialsninja.com/demo/");

	    // invalid emails and their expected screenshot file names
	    String[][] testData = {
	        {"amotoori", "sc1Expected.png"},
	        {"amotoori@", "sc2Expected.png"},
	        {"amotoori@gmail", "sc3Expected.png"},
	        {"amotoori@gmail.", "sc4Expected.png"}
	    };

	    for (String[] data : testData) {
	        String email = data[0];
	        String expectedFile = data[1];

	        // Navigate fresh each time
	        driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
	        driver.findElement(By.xpath("//a[text()='Register']")).click();

	        driver.findElement(By.id("input-firstname")).sendKeys("Arun");
	        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
	        driver.findElement(By.id("input-email")).sendKeys(email);
	        driver.findElement(By.id("input-telephone")).sendKeys("09246812111");
	        driver.findElement(By.id("input-password")).sendKeys("g@1233487");
	        driver.findElement(By.id("input-confirm")).sendKeys("g@1233487");
	        driver.findElement(By.xpath("(//input[@value='1'])[2]")).click();
	        driver.findElement(By.name("agree")).click();
	        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

	        WebElement formElement = driver.findElement(By.xpath("//form[@class='form-horizontal']"));
	        Thread.sleep(2000);

	        // Save actual screenshot
	        File srcScreenShot = formElement.getScreenshotAs(OutputType.FILE);
	        String actualPath = System.getProperty("user.dir")+"\\Screenshots\\"+email.replaceAll("[^a-zA-Z0-9]", "_")+"_Actual.png";
	        FileHandler.copy(srcScreenShot, new File(actualPath));

	        // Compare with expected
	        BufferedImage actualBImg = ImageIO.read(new File(actualPath));
	        BufferedImage expectedBImg = ImageIO.read(new File(System.getProperty("user.dir")+"\\Screenshots\\"+expectedFile));

	        ImageDiffer imgDiffer = new ImageDiffer();
	        ImageDiff imageDifference = imgDiffer.makeDiff(expectedBImg, actualBImg);

	        Assert.assertFalse(imageDifference.hasDiff(), "Image difference found for email: " + email);

	        // back to home for next iteration
	        driver.get("https://tutorialsninja.com/demo/");
	    }

	    driver.quit();
	}

}
