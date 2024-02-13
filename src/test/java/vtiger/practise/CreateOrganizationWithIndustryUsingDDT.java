package vtiger.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.ObjectRepository.LoginPage;

public class CreateOrganizationWithIndustryUsingDDT 
{
	public static void main(String[] args) throws Throwable
	{
		Random r=new Random();
		int random = r.nextInt(100);
		WebDriver driver=null;
		
		FileInputStream fisp=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p=new Properties();
		p.load(fisp);
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
	
		//Read data from Excel File
		FileInputStream fise=new FileInputStream(".\\src\\test\\resources\\TestDataExcel.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		Sheet sh=wb.getSheet("Organisations");
		String ORGNAME =sh.getRow(4).getCell(2).getStringCellValue()+random;
		String INDUSTRY = sh.getRow(4).getCell(2).getStringCellValue();
		
		//Step 2:Launch The Browser/ Run Time Polymorphism -driver
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

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		driver.get(URL);
		
		LoginPage lp=new LoginPage(driver);
		//lp.LoginPage(USERNAME, PASSWORD);
		lp.getUserNameEdt().sendKeys(USERNAME);
		lp.getPasswordEdt().sendKeys(PASSWORD);
		lp.getLoginBtn().click();
		
		
		
		//driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		//driver.findElement(By.id("submitButton")).click();
		//driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		//driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		
		driver.findElement(By.xpath("//input[@name=\"accountname\"]")).sendKeys(ORGNAME);
		
		WebElement dropDown = driver.findElement(By.xpath("//select[@name='industry']"));
		Select sel=new Select(dropDown);
		sel.selectByValue("INDUSTRY");
		
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
		
	}
}
