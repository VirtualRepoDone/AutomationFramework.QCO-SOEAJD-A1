package vtiger.GenericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class WebDriverUtility {
	WebDriver driver;
	public void maximiseWindow(WebDriver driver)
	{
		
		driver.manage().window().maximize();
	}
	/**
	 * This method will open window in full screen mode
	 * @param driver
	 */
	public void fullScreenWindow(WebDriver driver) 
	{
		driver.manage().window().fullscreen();
	}
	//this method will wait for 10 sec for all webelements to load
	public void waitForPageLoad(WebDriver driver) 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void waitForElementToBeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void handleDropDown(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	public void handleDropDown(WebElement element,String value)
	{
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	
	public void handleDropDown(String text,WebElement element) {
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
		}
	//Actions
	
	public void mouseHoverAction(WebDriver driver,WebElement element) {
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
		}
	public void rightClickAction(WebDriver driver) {
		Actions act=new Actions(driver);
		act.contextClick().perform();
	}
	
	public void rightClickAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element);
	}
	public void doubleClickAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	public void dragAndDropAction(WebDriver driver,WebElement src,WebElement target)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(src, target).perform();
	}
	
	public void moveTheCursorAndClick(WebDriver driver,int x,int y) {
		Actions act=new Actions(driver);
		act.moveByOffset(x, y).click().perform();
	}
	
	//Scroll Actions
	public void scrollAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500);","");
	}
	
	public void scrollAction(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("argument[0].ScrollIntoView();",element);
	}
	 
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void dismissAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public String getTextAlert(WebDriver driver)
	{
	  return driver.switchTo().alert().getText();
	}
	public void handleFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void handleFrame(WebDriver driver,String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	public void handleFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	public void switchToDefaultFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	//Window Switch
	public void switchToWindow(WebDriver driver,String partialWindowTitle)
	{	//step 1:
		Set<String> allWinIds = driver.getWindowHandles();
		//Step 2:
		for(String ID:allWinIds)
		{
			String actTitle = driver.switchTo().window(ID).getTitle();
		
		//Step 3:
			if(actTitle.contains(partialWindowTitle))
			{
				break;
			}
		}
	}
	
	//TakeScreenShot 
	public File captureScreenShot(WebDriver driver,String screenShotName) throws Throwable
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dst = new File(".\\Screenshots\\"+screenShotName+".png");
		Files.copy(src,dst);
		
		return dst.getAbsoluteFile();//used in extent report
		
		
		
	}
	
	
	
	
	
}
