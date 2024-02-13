package vtiger.practise;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationWithIndustry {
	
	public static void main(String[] args) throws Throwable {
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://localhost:8888");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		//driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		driver.findElement(By.xpath("//input[@name=\"accountname\"]")).sendKeys("PizzaNow");
		WebElement dropDown = driver.findElement(By.xpath("//select[@name='industry']"));
		Select sel=new Select(dropDown);
		sel.selectByValue("Chemicals");
		//save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//Validation
		String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgHeader.contains("PizzaNow"))
		{
			System.out.println("Pass");
			System.out.println(orgHeader);
		}else
		{
			System.out.println("Fail");
		}
		//logout of app
		
		WebElement mouseHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(mouseHover).perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		driver.quit();
		
		System.out.println("ABC");
	}
}