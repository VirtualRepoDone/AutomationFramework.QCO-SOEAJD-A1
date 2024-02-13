package vtiger.OrganizationTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

public class CreateMultipleOrgWithIndustryTest extends BaseClass {
	@Test(groups="RegressionSuite")
	public void createMultipleOrg(String ORG,String INDUSTRYTYPE) 
	{
		String ORGNAME = ORG+jUtil.getRandomNumber();
	
	
	//Step: 
	HomePage hp=new HomePage(driver);
	hp.clickOnOrganizationLink();
	
	//Step
	OrganizationsPage op=new OrganizationsPage(driver);
	op.clickOnCreateOrgLookUpImage();
	
	//Step
	CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
	cnop.CreateNewOrganization(ORGNAME, INDUSTRYTYPE);
	
	//Step: Validate
	OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	String orgHeader = oip.getOrgheader();
	
	//Step:
	Assert.assertTrue(orgHeader.contains(ORGNAME));
	System.out.println(orgHeader);
	//Assert.assertTrue(orgHeader.contains(ORGNAME));	
	Assert.assertEquals(true,true);	
	
	}
}