package vtiger.practise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTestNGPriority {
//@Test(priority=1)//default value of priority is 0 @Test(priority=0)
//@Test(priority=1,invocationCount=1)
@Test
public void createCustomer()
{
	//Assert.fail();
	System.out.println("Create");
}
//@Test(priority=-2)
//@Test(invocationCount=1,priority=-2)

@Test(dependsOnMethods="createCustomer")
public void modifyCustomer()
{
	System.out.println("modify");
}
//@Test(priority=-6)
//@Test(invocationCount=2,priority=3)
@Test(invocationCount=1)
public void deleteCustomer()
{
	System.out.println("Delete");
}
}
