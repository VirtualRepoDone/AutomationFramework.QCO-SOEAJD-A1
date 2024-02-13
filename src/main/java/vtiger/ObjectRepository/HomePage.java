package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class HomePage  extends WebDriverUtility{

	@FindBy(linkText="Organizations")
	private WebElement organizationLnk;
	@FindBy(linkText="Contacts")
	private WebElement contactLnk;
	@FindBy(linkText="Products")
	private WebElement productLnk;
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorLnk;
	@FindBy(linkText="Sign Out")
	private WebElement signoutLnk;
	
	//initialization
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	//Utilization
	public WebElement getOrganizationLnk() {
		return organizationLnk;
	}

	public WebElement getContactLnk() {
		return contactLnk;
	}

	public WebElement getProductLnk() {
		return productLnk;
	}

	public WebElement getAdministratorLnk() {
		return administratorLnk;
	}

	public WebElement getSignoutLnk() {
		return signoutLnk;
	}
	
	//Business Library
	public void logoutOfApp(WebDriver driver) throws Throwable
	{
		mouseHoverAction(driver, administratorLnk);
		Thread.sleep(1000);
		signoutLnk.click();
	}
	
	public void clickOnOrganizationLink()
	{
		organizationLnk.click();
	}

	public void clickOnContactLink()
	{
	contactLnk.click();
	}
	
	
}
