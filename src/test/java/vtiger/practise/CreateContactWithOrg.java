package vtiger.practise;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrg {
	public static void main(String[] args) throws Throwable {

		Random ran=new Random();
		int random = ran.nextInt(100);
				
		WebDriverManager.edgedriver().setup();
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		driver.get("http://localhost:8888");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		
		driver.findElement(By.id("submitButton")).click();
//		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		String orgName="Infosys"+random;
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(orgName);
		System.out.println("Hello");
		
		driver.findElement(By.xpath("//input[@name='account_name']//following-sibling::img[@title='Select']")).click();
		//	driver.findElement(By.xpath("//input[@name='account_name'/following-sibling::img[@title='Select']")).click();
		System.out.println("AAAAAA");
		//switch the control to child window
		String mainWinID = driver.getWindowHandle();
		Set<String> allWinIds = driver.getWindowHandles();
for(String ID:allWinIds)
{
	if(ID!=mainWinID)
	{
	driver.switchTo().window(ID);
	System.out.println("Window switched to child");
	}
}
System.out.println("bbbbbbbbb");
		//Search for Organization
driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys("AAAAAAAAAAA");
driver.findElement(By.xpath("//input[@name='search']")).click();
	driver.findElement(By.xpath("//a[.='Qspiders']")).click();
		
	Thread.sleep(5000);
		//step 9:Control back to main window
		driver.switchTo().window(mainWinID);
		
		//save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//validation
		String contactheader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(contactheader.contains(orgName))
		{
			System.out.println("Pass");
			System.out.println(contactheader);
		}
		else
		{
			System.out.println("Fail");
		}
	//Logout
	WebElement mouseHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	Actions act =new Actions(driver);
	act.moveToElement(mouseHover).perform();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//a[.='Sign Out']")).click();
System.out.println("Logout Successful");
	driver.quit();
	}
}
