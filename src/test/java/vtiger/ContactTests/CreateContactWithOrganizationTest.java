package vtiger.ContactTests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.BaseClass;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.ObjectRepository.ContactInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.CreateNewContactPage;
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;
@Listeners(vtiger.GenericUtilities.ListenerImplementation.class)
public class CreateContactWithOrganizationTest  extends BaseClass {
	
	@Test(groups="SmokeSuite")
	public void createContactWithOrgTest() throws Throwable {
		
		//Step 1:
	
		
		//Read Data from excel file
		String ORGNAME = eUtil.readDataFromExcelSheet("Contacts",4,2)+jUtil.getRandomNumber();
		String LASTNAME = eUtil.readDataFromExcelSheet("Contacts",4,3);
		
		//Step 3: Navigate To Organization Link
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganizationLink();
		 
		//Step 4:Click on create Organization lookup image
		OrganizationsPage op=new OrganizationsPage(driver);
		op.clickOnCreateOrgLookUpImage();

		//Step 5:Create Organization with mandatory Information
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.CreateNewOrganization(ORGNAME,LASTNAME);
		
		//Assert.fail();
		
		//Step 6: Validate
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String orgHeader = oip.getOrgheader();
		
		//if pass  assertion pass script will run further otherwise fail execution from there on
		//Assert.assertTrue(orgHeader.contains(ORGNAME));
		System.out.println(orgHeader);
		
		//Step:
		hp.clickOnContactLink();
		
		//Step:
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactLookUpImg();
		
		//Step:Create contact Using Organization
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME, ORGNAME, driver);
		
		//Step: Validate for contacts
		ContactInfoPage cip=new ContactInfoPage(driver);
		String ContactHeader = cip.getContactHeader();
		
		//Assert.assertTrue(ContactHeader.contains(LASTNAME));
		System.out.println(ContactHeader);
		
		}
	@Test
	public void demo()
	{
		System.out.println("Demo");
	}
	
	
}


