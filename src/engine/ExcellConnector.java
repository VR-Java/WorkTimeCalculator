package engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcellConnector {

	private String fileName;

	public ExcellConnector(String fileName) {
		this.fileName = fileName;
	}

	// read file "fileName"
	private HSSFWorkbook readFile() throws IOException {
		try (FileInputStream fis = new FileInputStream(fileName)) {
			return new HSSFWorkbook(fis); // NOSONAR - should not be closed here
		}
	}

	// write INTEGER data to cell
	public void writeCell(MyCell myCell, int val) {
		try (HSSFWorkbook wb = readFile()) {
			HSSFSheet sheet = wb.getSheetAt(0);

			HSSFRow row = sheet.getRow(myCell.getRow());
			HSSFCell cell = row.getCell(myCell.getCol());
			cell.setCellValue(val);

			try (FileOutputStream stream = new FileOutputStream(fileName)) {
				wb.write(stream);
				System.out.println("OK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeCell(int row, int col, int sheet, double val) {
		try (HSSFWorkbook wb = readFile()) {
			HSSFSheet sheetToWrite = wb.getSheetAt(sheet);

			HSSFRow rowToWrite = sheetToWrite.getRow(row);
			HSSFCell cell = rowToWrite.getCell(col);
			cell.setCellValue(val);

			try (FileOutputStream stream = new FileOutputStream(fileName)) {
				wb.write(stream);
				System.out.println("OK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// reads value of Cell if CELL_TYPE = STRING
	public String readStringCellValue(MyCell myCell) throws IOException {
		HSSFWorkbook wb = readFile();
		return wb.getSheetAt(0).getRow(myCell.getRow()).getCell(myCell.getCol()).getStringCellValue();
	}

	public String readStringCellValue(int row, int col) throws IOException {
		HSSFWorkbook wb = readFile();
		return wb.getSheetAt(0).getRow(row).getCell(col).getStringCellValue();
	}

	// reads value of Cell if CELL_TYPE = DOUBLE
	public double readDoubleCellValue(MyCell myCell) throws IOException {
		HSSFWorkbook wb = readFile();
		return wb.getSheetAt(0).getRow(myCell.getRow()).getCell(myCell.getCol()).getNumericCellValue();
	}

	public double readDoubleCellValue(int row, int col) throws IOException {
		HSSFWorkbook wb = readFile();
		return wb.getSheetAt(0).getRow(row).getCell(col).getNumericCellValue();
	}

	// reads value of Cell if CELL_TYPE = DATE
	public Date readDateCell(MyCell myCell) throws IOException {
		HSSFWorkbook wb = readFile();
		return wb.getSheetAt(0).getRow(myCell.getRow()).getCell(myCell.getCol()).getDateCellValue();
	}

	public Date readDateCell(int row, int col) throws IOException {
		HSSFWorkbook wb = readFile();
		return wb.getSheetAt(0).getRow(row).getCell(col).getDateCellValue();
	}

	// extracts TIME from cell, where CELL_TYPE = DATE
	public LocalTime readTimeCell(MyCell myCell) throws IOException {
		return convertDateToLocalTime(readDateCell(myCell));
	}

	public LocalTime readTimeCell(int row, int col) throws IOException {
		return convertDateToLocalTime(readDateCell(row, col));
	}

	private LocalTime convertDateToLocalTime(Date date) {
		String stringDate = null;
		try {
			stringDate = DataMatcher.matchData(date.toString(), "\\d{2}+[:]{1}+\\d{2}+[:]{1}+\\d{2}");
		} catch (Exception e) {
			stringDate = "00:00:00";
			// e.printStackTrace();
		}
		return LocalTime.parse(stringDate);
	}

}
