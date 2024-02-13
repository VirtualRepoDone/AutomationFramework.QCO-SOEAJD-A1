package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility {
	//Declaration
	@FindBy(name="accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name="industry")
	private WebElement IndustryDropDown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;

	//Initialization
	public CreateNewOrganizationPage(WebDriver driver)
	{
	PageFactory.initElements(driver,this);
	}

	//Utilization :Getters
	public WebElement getOrgNameEdt() {
	return getOrgNameEdt();
	}

	public WebElement getIndustryDropDown() {
	return IndustryDropDown;
	}

	public WebElement getSaveBtn() {
	return SaveBtn;
	}

	//Business Library
	public void createNewOrganization(String ORGNAME)
	{
	OrgNameEdt.sendKeys(ORGNAME);
	SaveBtn.click();
	}

	public void CreateNewOrganization(String ORGNAME,String INDUSTRY)
	{
	OrgNameEdt.sendKeys(ORGNAME);
	handleDropDown(IndustryDropDown,INDUSTRY);
	SaveBtn.click();
	}		
}
