package vtiger.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	public String readDataFromExcelSheet(String sheetName,int rowNum,int cellNum) throws Throwable
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestDataExcel.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
	}
	public void writeDataIntoExcel(String sheetName,int rowNum,int cellNum,String value) throws Throwable 
	{	FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestDataExcel.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.createSheet(sheetName).getRow(rowNum).getCell(cellNum).setCellValue(value);
		
		FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\TestDataExcel.xlsx");
		wb.write(fos);
		wb.close();
	
	}
	
	public Object[][] readMultipleDataFromExcel(String sheetName) throws Throwable
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestDataImp.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int lastRow = sh.getLastRowNum();
		short lastCel = sh.getRow(0).getLastCellNum();
		Object[][] data=new Object[lastRow][lastCel];
		for(int i=1;i<lastRow;i++)
		{
			for(int j=0;j<lastCel;j++)
			{
				data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
}
