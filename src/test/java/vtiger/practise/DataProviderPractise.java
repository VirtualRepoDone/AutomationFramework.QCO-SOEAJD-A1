package vtiger.practise;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractise {
@Test(dataProvider = "getData")

public void addProductToCart(String name,String model,int price)
{
	System.out.println(name+" -"+model+" -"+price);	
}
@DataProvider
public Object[][] getData()
{							//row //column
	Object[][] data=new Object[3][3];//3set of data with 3 fields/info
	data[0][0]="Samsung";
	data[0][1]="A80";
	data[0][2]=15000;
	data[1][0]="Vivo";
	data[1][1]="Y21";
	data[1][2]=8000;
	data[2][0]="Oppo";
	data[2][1]="A12";
	data[2][2]=5000;
	return data;
	
}






}
