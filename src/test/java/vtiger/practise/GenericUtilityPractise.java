package vtiger.practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;

public class GenericUtilityPractise {
public static void main(String[] args) throws Throwable {
/*	WebDriverManager.edgedriver().setup();
	WebDriver driver=new EdgeDriver();
	
	ExcelFileUtility eUtil=new ExcelFileUtility();
	eUtil.readDataFromExcelSheet("System", 1, 1);
	eUtil.writeDataIntoExcel("TrialNow", 4, 2,"Selenium CRM Based Project Framework");
	*/
	JavaUtility jUtil=new JavaUtility();
	System.out.println(jUtil.getRandomNumber());
	System.out.println(jUtil.getSystemDate());
	
	}
}
