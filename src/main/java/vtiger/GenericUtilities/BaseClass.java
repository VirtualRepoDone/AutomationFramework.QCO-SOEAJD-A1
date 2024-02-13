package vtiger.GenericUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;

public class BaseClass	 {
	/**
	 * This is a generic class consisting of all basic configuration annotation of TESTNG
	*@author avido
	 */
	public ExcelFileUtility eUtil=new ExcelFileUtility();
	public JavaUtility jUtil=new JavaUtility();
	public PropertyFileUtility pUtil=new PropertyFileUtility();
	public WebDriverUtility wUtil=new WebDriverUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver;
	
	@BeforeSuite(groups= {"RegressionSuite","SmokeSuite"})
	public void bsConfig()
	{
		System.out.println("====DB Connection Successful====");
	}
	
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(alwaysRun=true)
	public void bcConfig(/*String BROWSER*/) throws Throwable
	{
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
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
		sdriver=driver;
		System.out.println(" Browser Launched");
	}
	
	@AfterClass(alwaysRun=true)
	public void acConfig()
	{
		driver.quit();
		System.out.println("Browser Closed");
	}
	
	@BeforeMethod(alwaysRun=true)
	public void bmConfig() throws Throwable
	{
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.LoginToApp(USERNAME, PASSWORD);
		System.out.println("Login Successful");
	
	}
	
	@AfterMethod(alwaysRun=true)
	public void amConfig() throws Throwable {
	HomePage hp=new HomePage(driver);
	hp.logoutOfApp(driver);
	System.out.println("Logout Successful");
	
	}
	
	@AfterSuite(alwaysRun=true)
	public void asConfig()
	{
		System.out.println("====DB Connection closed=====");
	}
}
