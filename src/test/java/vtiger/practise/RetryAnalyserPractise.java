package vtiger.practise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyserPractise {
	@Test(retryAnalyzer=vtiger.GenericUtilities.RetryAnalyzerImplementation.class)
	public void sample()
	{
		Assert.fail();
		System.out.println("Hello");
	}
}
