package vtiger.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadDataFromPropertyFile {
public static void main(String[] args) throws Throwable {
	
	//step 1: Open the document in java readable format
	FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
	
	//step 2:
	Properties p=new Properties();
	//step 3:
	p.load(fis);
	//sTEP 4:
	String BROWSER = p.getProperty("browser");
	System.out.println(BROWSER);
	//Step 5:
	String USERNAME = p.getProperty("username");
	System.out.println(USERNAME);
	
	
	
}
}
