package engine;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import time.MyMonth;
import time.WorkDay;

public class RecordingSheetLoader {

	// read hours when Employee arrived to workplace
	public static LocalTime readStartDay(ExcellConnector ec, WorkDay wd) throws IOException {
		return ec.readTimeCell(3, wd.getDay());
	}

	// read hours when Employee finished his workday
	public static LocalTime readFinishDay(ExcellConnector ec, WorkDay wd) throws IOException {
		return ec.readTimeCell(4, wd.getDay());
	}

	// writes total quantity of minutes of each shift per day
	public static void writeShiftsTime(ExcellConnector ec, WorkDay wd) {
		int day = wd.getDay();
		int month = wd.getMonth() - 1;
		List<Integer> list = wd.getListOfShifts();

		for (int i = 0; i < 4; i++) {
			ec.writeCell(6 + i, day, month, list.get(i));
		}
	}

	// writes total summ of hours per first half, second half and total sum of hours
	// per month
	public static void writeResultHours(ExcellConnector ec, List<Double> list1, List<Double> list2, List<Double> list3,
			MyMonth month)throws IOException {
		int m = month.getMonthOfYear() - 1;
		List<List<Double>> lists = new ArrayList<>();
		lists.add(list1);
		lists.add(list2);
		lists.add(list3);

		for (int i = 0; i < lists.size(); i++) {
			for (int j = 0; j < 4; j++) {
				ec.writeCell(12 + j, i + 1, m, lists.get(i).get(j));
			}
		}

	}

}
