package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	//declaration
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement CreateContactLookUpImg;
	
	//Initialization
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//Utilization
	public WebElement getCreateContactLookUpImg() 
	{
		return CreateContactLookUpImg;
	}
	
	//Business Library
	/**
	 * This method will click on
	 * 
	 */
	public void clickOnCreateContactLookUpImg()
	{
		CreateContactLookUpImg.click();
	}

}
