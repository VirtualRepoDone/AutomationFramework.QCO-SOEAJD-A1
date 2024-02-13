package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	//Declaration
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement OrgHeaderText;


	//Initialization
	public OrganizationInfoPage(WebDriver driver)
	{
	PageFactory.initElements(driver,this);
	}

	//Utilization
	public WebElement getOrgHeaderText() {
	return OrgHeaderText;
	}

	//Business Library
	/**
	 * This method will return the header text
	 */
	public String getOrgheader()
	{
		return OrgHeaderText.getText();
	}

}
