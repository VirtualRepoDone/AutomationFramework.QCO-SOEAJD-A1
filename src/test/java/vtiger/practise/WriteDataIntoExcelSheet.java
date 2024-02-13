package vtiger.practise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcelSheet {
public static void main(String[] args) throws Throwable, IOException {
	FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestDataExcel.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sh = wb.createSheet("Trial");
	Row rw = sh.createRow(2);
	Cell cl = rw.createCell(5);
	cl.setCellValue("Selenium");
	
	FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\TestDataExcel.xlsx");
	wb.write(fos);
	wb.close();
	}
}
