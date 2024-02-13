package vtiger.practise;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(vtiger.GenericUtilities.ListenerImplementation.class)
public class ListenerPractise {
	@Test
	public void demo()
	{
		Assert.fail();
		System.out.println("--------Hi-----");
	}
	
	@Test(dependsOnMethods = "demo")
	public void demo1()
	{
		System.out.println("-----Hiiiiii--------");
	}

}
