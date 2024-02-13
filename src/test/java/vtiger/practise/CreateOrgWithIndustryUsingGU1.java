package vtiger.practise;

import java.io.FileInputStream;
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
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

public class CreateOrgWithIndustryUsingGU1 {
	public static void main(String[] args, String INDUSTRYTYPE) throws Throwable {
	
		/**
		 * Random r=new Random();
		 */
	//	int random = r.nextInt(100);
		/*
		/*
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
	*/	
		//Create objects of all Utility class
		JavaUtility jUtil=new JavaUtility();
		PropertyFileUtility pUtil=new PropertyFileUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		WebDriver driver=null;
		
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		
		//Read Data from excel file
		String ORGNAME = eUtil.readDataFromExcelSheet("Organizations",4,2)+jUtil.getRandomNumber();
		INDUSTRYTYPE=eUtil.readDataFromExcelSheet("Organizations",4, 3);
		
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
		//Login To Application
		LoginPage lp=new LoginPage(driver);
		lp.LoginPage(USERNAME, PASSWORD);
		//lp.getUserNameEdt().sendKeys(USERNAME);
		//lp.getPasswordEdt().sendKeys(PASSWORD);
		//lp.getLoginBtn().click();
		
		//Step 3: Navigate To Organization Link
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganizationLink();
		
		//Step 4:Click on create Organization lookup image
		OrganizationsPage op=new OrganizationsPage(driver);
		op.clickOnCreateOrgLookUpImage();
		
		//Step 5:Create Organization with mandatory Information
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.CreateNewOrganization(ORGNAME,INDUSTRYTYPE);
		
		//Validate
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		WebElement orgHeader = oip.getOrgHeaderText();
		
		if(orgHeader.equals(ORGNAME))
		{
			System.out.println("Pass");
			System.out.println(orgHeader);
		}
		else
		{
			System.out.println("Fail");
		}
		
		//Step 9: Logout of Application
	
	hp.logoutOfApp(driver);
	driver.quit();
	}
}
