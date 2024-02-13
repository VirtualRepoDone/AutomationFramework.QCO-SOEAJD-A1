package vtiger.GenericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber()
	{
		Random r=new Random();
		return r.nextInt(1000);
	}
	public String getSystemDate() 
	{
		Date d=new Date();
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
		String date=formatter.format(d);
		return date;	
	}
}
