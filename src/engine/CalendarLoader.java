package engine;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import time.WorkDay;

public class CalendarLoader {
	
	private static Path path = Paths.get("D:\\Java\\Projects\\WorkTime\\Recording Sheets\\2018");

	private static ExcellConnector shedule = new ExcellConnector(path + "\\" + "Calendar.xls");

	// reads array of Days in some moth from Calendar.xls file
	// if 1 - working day
	// if 0 - holiday
	// if -1 - day not exists
	public static WorkDay[] readWorkDays(String monthName) throws IOException {
		WorkDay[] wd = new WorkDay[31];

		int row = getRowOfMonth(monthName);
		if (row > 0) {
			for (int i = 1; i <= 31; i++) {
				if (shedule.readDoubleCellValue(row, i) == 0) {
					wd[i - 1] = new WorkDay(true);
				} else if (shedule.readDoubleCellValue(row, i) == 1) {
					wd[i - 1] = new WorkDay(false);
				}
			}
		}
		return wd;
	}

	// reads rowNumber of some month in Calendar
	// if month not found - return -1
	private static int getRowOfMonth(String monthName) {
		try {
			for (int i = 1; i < 13; i++) {
				String text;
				text = shedule.readStringCellValue(i, 0);
				if (monthName.equalsIgnoreCase(text)) {
					//System.out.println(text);
					return i;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	// read year from file
	public static int readYear() throws IOException {
		return (int) shedule.readDoubleCellValue(0, 0);
	}

}
