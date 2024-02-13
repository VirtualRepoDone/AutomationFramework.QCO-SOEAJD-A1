package vtiger.practise;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelFile {
	public static void main(String[] args) throws Throwable {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestDataExcel.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet("Organisations");
		Row rw = sh.getRow(1);
		Cell cl = rw.getCell(1);
		String data = cl.getStringCellValue();
		int data1 = rw.getPhysicalNumberOfCells();
		System.out.println(data1);
		System.out.println(data);
		wb.close();	
	}
}
