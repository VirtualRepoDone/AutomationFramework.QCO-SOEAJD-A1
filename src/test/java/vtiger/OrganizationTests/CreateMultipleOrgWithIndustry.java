package vtiger.OrganizationTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

public class CreateMultipleOrgWithIndustry extends ExcelFileUtility 
{
	JavaUtility jUtil=new JavaUtility();
	PropertyFileUtility pUtil=new PropertyFileUtility();
	WebDriverUtility wUtil=new WebDriverUtility();
	ExcelFileUtility eUtil=new ExcelFileUtility();
	
@Test(dataProvider="getData")
public void createMultipleOrg(String ORG,String INDUSTRYTYPE) throws Throwable
{
	
	WebDriver driver=null;
	
	String BROWSER=pUtil.readDataFromPropertyFile("browser");
	String URL = pUtil.readDataFromPropertyFile("url");
	String USERNAME = pUtil.readDataFromPropertyFile("username");
	String PASSWORD = pUtil.readDataFromPropertyFile("password");
	
	String ORGNAME=ORG+jUtil.getRandomNumber();
	//Read Data from excel file
	
	
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

	//driver.manage().window().maximize();
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	wUtil.maximiseWindow(driver);
	wUtil.waitForPageLoad(driver);
	driver.get(URL);
	
	//Login To Application
	LoginPage lp=new LoginPage(driver);
	lp.LoginToApp(USERNAME, PASSWORD);
	
	//Step 3: Navigate To Organization Link
	HomePage hp=new HomePage(driver);
	hp.clickOnOrganizationLink();
	
	//Step 4:Click on create Organization lookup image
	OrganizationsPage op=new OrganizationsPage(driver);
	op.clickOnCreateOrgLookUpImage();
	
	//Step 5:Create Organization with mandatory Information
	CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
	cnop.CreateNewOrganization(ORGNAME,INDUSTRYTYPE);
	
	//Step 6:Validate
	OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	String orgHeader = oip.getOrgheader();
	
	if(orgHeader.contains(ORGNAME))
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
	
	//step 10: Close the Browser
	driver.quit();
	
}
@DataProvider
public Object[][] getData() throws Throwable
{
	Object[][] data = eUtil.readMultipleDataFromExcel("MultipleOrg");
	return data;
	
}
}