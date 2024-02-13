package vtiger.GenericUtilities;

import java.io.File;

import org.openqa.selenium.WrapsDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener {
	ExtentReports report;
	ExtentTest test;
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stubxx
		
		String methodName = result.getMethod().getMethodName();
		
	 test=report.createTest(methodName);
		System.out.println("-------Test Script Execution started-----");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS,methodName+"-----Pass-------");
		//System.out.println(methodName+"-----Pass-------");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
	
		WebDriverUtility w=new WebDriverUtility();
		JavaUtility j=new JavaUtility();
		String methodName = result.getMethod().getMethodName();
		String screenShotName=methodName+j.getSystemDate();
		System.out.println(result.getThrowable());
		System.out.println(methodName+"-----Pass----");
		test.log(Status.FAIL,methodName+"--Fail--");
		test.log(Status.INFO,result.getThrowable());
		try 
		{
		 File path = w.captureScreenShot(BaseClass.sdriver, screenShotName);
		}
		catch (Throwable e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(methodName+"-----------Fail----------");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println("-------Skip---------");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println("");
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//Configure the Extent reports
		ExtentSparkReporter htmlreport=new ExtentSparkReporter(".\\ExtentReports\\Report-"+new JavaUtility().getSystemDate()+".html");
		htmlreport.config().setDocumentTitle("Vtiger execution Report");
		htmlreport.config().setDocumentTitle("Automation Execution Report");
		htmlreport.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("Base Platform","Windows");
		report.setSystemInfo("Base Browser","Firefox");
		report.setSystemInfo("Base URL","http://localhost:8888");
		report.setSystemInfo("Base Envirnment","Testing");
		report.setSystemInfo("Reporter Name","Chitra");
		
		System.out.println("Hiiiii");
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush();
		
	}
	

}
