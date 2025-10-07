package tutorialninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_RF_002 {
	public static void main(String[] args) throws Throwable {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//span[contains(text(),'Account & Lists')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Sign in')]")).click();
		//Thread.sleep(5);
		driver.findElement(By.xpath("//input[@id='ap_email_login']")).sendKeys("ucchawale@gmail.com");
		//driver.findElement(By.xpath("//input[@type='submit']")).click();
		//driver.findElement(null)
		
		driver.quit();
		
			
	}
	
	

}
