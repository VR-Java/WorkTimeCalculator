package engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcellConnector {
	Workbook wb;
	FileOutputStream fos;
	// fileName = "myFile.xls"
	
	public ExcellConnector(String fileName) {
		try {
			wb = new HSSFWorkbook(new FileInputStream(fileName));
			//fos = new FileOutputStream(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void test() {
		
//		Workbook wb = new HSSFWorkbook();
//		Sheet sheet0 = wb.createSheet("Працівники");
//		
//		Row row = sheet0.createRow(3);
//		Cell cell = row.createCell(3);
//		cell.setCellValue("Рольський Віталій");
//		
//		Sheet sheet1 = wb.createSheet("Години");
//		Sheet sheet2 = wb.createSheet("Зарплати");
//
//		FileOutputStream fos = new FileOutputStream("myFirstFile.xls");
//		
//		wb.write(fos);		
//		fos.close();
		
		
		//Workbook wb = new HSSFWorkbook(new FileInputStream("myFile.xls"));

		System.out.println(readStringCell(wb, 0, 1));
		System.out.println(readDateCell(wb, 4, 1));
		System.out.println(readDateCell(wb, 5, 1));
		
		Date dateStart = readDateCell(wb, 4, 1);
		System.out.println(convertDateToLocalTime(dateStart));
		
		
		Date dateFinish = readDateCell(wb, 5, 1);
		System.out.println(convertDateToLocalTime(dateFinish));
	}
	
	public String readStringCell(Workbook wb, int row, int cell) {
		return wb.getSheetAt(0).getRow(row).getCell(cell).getStringCellValue();
	}
	
	public Date readDateCell(Workbook wb, int row, int cell) {
		return wb.getSheetAt(0).getRow(row).getCell(cell).getDateCellValue();
	}

	public LocalTime convertDateToLocalTime(Date date) {
		Instant instant = Instant.ofEpochMilli(date.getTime());
		System.out.println(instant);
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
	}
	
	
}
