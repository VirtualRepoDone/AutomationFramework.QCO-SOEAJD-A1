package vtiger.practise;

import org.asynchttpclient.util.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsPractiseTest {
@Test
public void test()
{
	System.out.println("Hi Started");
	Assert.assertEquals("a","a");
	
	//Assertions.assertNotEmpty("a","b");
	System.out.println("Hello i'm here");

	}
@Test
public void test2()
{
	SoftAssert sa=new SoftAssert();
	System.out.println("Step 1");
	sa.assertTrue(false);
	System.out.println("Step 2");
	sa.assertEquals(false, true);
	System.out.println("Step 3");
	Assert.assertEquals(false, false);
	System.out.println("Step 4");
	sa.assertAll();
	
	}
}
