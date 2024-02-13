package vtiger.practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;

public class CreateOrgWithIndustryUsingDDT {
public static void main(String[] args) throws Throwable {
	JavaUtility jUtil=new JavaUtility();
	WebDriverUtility wUtil=new WebDriverUtility();
	PropertyFileUtility pUtil=new PropertyFileUtility();
	ExcelFileUtility eUtil=new ExcelFileUtility();
	WebDriver driver=null;
	//PropertyFile 
	String BROWSER = pUtil.readDataFromPropertyFile("browser");
	String URL = pUtil.readDataFromPropertyFile("url");
	String USERNAME = pUtil.readDataFromPropertyFile("username");
	String PASSWORD = pUtil.readDataFromPropertyFile("password");
	
	//ExcelFile
	String ORGNAME = eUtil.readDataFromExcelSheet("Organisations",4,2)+jUtil.getRandomNumber();
	String INDUSTRYTYPE = eUtil.readDataFromExcelSheet("Organisations",4,3);
	
	//Browser 
	if(BROWSER.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
	}
	else if(BROWSER.equalsIgnoreCase("Chrome"))
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
	}
	else if(BROWSER.equalsIgnoreCase("firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
	}
	else
	{
		System.out.println("Invalid Browser Name"); 
	}
	
	wUtil.maximiseWindow(driver);
	wUtil.waitForPageLoad(driver);
	driver.get(URL);
	
	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	
	driver.findElement(By.id("submitButton")).click();
	driver.findElement(By.xpath("//a[text()='Organizations']")).click();
	//driver.findElement(By.linkText("Organizations")).click();
	driver.findElement(By.xpath("//a[text()='Organizations']")).click();
	driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
	
	driver.findElement(By.xpath("//input[@name=\"accountname\"]")).sendKeys(ORGNAME);
	
	WebElement dropDown = driver.findElement(By.xpath("//select[@name='industry']"));
	wUtil.handleDropDown(dropDown, INDUSTRYTYPE);
		
	//Select sel=new Select(dropDown);
	//sel.selectByValue("INDUSTRY");
	
	//save
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	//Validation
	String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(orgHeader.contains(ORGNAME))
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
	System.out.println(BROWSER);
	
	
}
}
