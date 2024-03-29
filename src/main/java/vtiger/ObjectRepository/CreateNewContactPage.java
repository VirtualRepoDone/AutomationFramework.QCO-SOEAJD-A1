package vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class CreateNewContactPage  extends WebDriverUtility{
	
	//Declaration
	@FindBy(name="lastname")
	private WebElement LastNameEdt;

	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;

	@FindBy(xpath="//input[@name='account_name']/following-sibling::img[@title='Select']")
	private WebElement OrgLookUpImg;

	@FindBy(name="search_text")
	private  WebElement OrgSearchEdt;

	@FindBy(name="search")
	private WebElement OrgSearchBtn;

//Initialization
	public CreateNewContactPage(WebDriver driver)
	{
	PageFactory.initElements(driver,this);
	}

//Utilization
	public WebElement getLastNameEdt()
	{
	return LastNameEdt;
	}

public WebElement getSaveBtn() {
		return SaveBtn;
	}

	public WebElement getOrgLookUpImg() {
		return OrgLookUpImg;
	}

	public WebElement getOrgSearchEdt() {
		return OrgSearchEdt;
	}

	public WebElement getOrgSearchBtn() {
		return OrgSearchBtn;
	}

//Business Library
/**
 * This method will create organization using mandatory fields
 * @param ORGNAME
 */
	public void createNewContact(String LASTNAME)
	{
	LastNameEdt.sendKeys(LASTNAME);
	SaveBtn.click();
	}
	
	public void createNewContact(String LASTNAME,String ORGNAME,WebDriver driver)
	{
	LastNameEdt.sendKeys(LASTNAME);
	OrgLookUpImg.click();
	switchToWindow(driver, "account");//partial url title "account"
	OrgSearchBtn.sendKeys(ORGNAME);
	OrgSearchBtn.click();
	//driver.findElement(By.xpath("//a[.='Sony3']"));
	driver.findElement(By.xpath("//a[.=' " +ORGNAME+"']")); //dynamic path
	//driver.findElement(By.partialLinkText(ORGNAME));
	switchToWindow(driver,"Accounts");
	SaveBtn.click();
	
	}
}
