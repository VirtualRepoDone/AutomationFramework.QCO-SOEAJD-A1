package vtiger.practise;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BaseClassPractise {
@Test
public void test()
{
	System.out.println("Test");
}
@BeforeSuite
public void bs()
{
	System.out.println("Before Suite");
}
@AfterSuite
public void as()
{
	System.out.println("After Suite");
}
@BeforeClass
public void bc()
{
	System.out.println("Before Class");
}
@AfterClass
public void ac()
{
	System.out.println("After Class");
}
@BeforeMethod()
public void bm()
{
	System.out.println("Before Method");
}
@AfterMethod()
public void am()
{
	System.out.println("After Method");
}


}
