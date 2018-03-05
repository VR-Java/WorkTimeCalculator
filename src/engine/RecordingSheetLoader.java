package engine;

import java.io.IOException;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import time.WorkDay;

public class RecordingSheetLoader {

	private static ExcellConnector recordingSheet = new ExcellConnector("RecordingSheet1.xls");

	public static LocalTime readStartDay(WorkDay wd) throws IOException {
		return recordingSheet.readTimeCell(3, wd.getDay());
	}

	public static LocalTime readFinishDay(WorkDay wd) throws IOException {
		return recordingSheet.readTimeCell(4, wd.getDay());
	}

	public static void writeShiftsTime(WorkDay wd) {
		int day = wd.getDay();
		int month = wd.getMonth() - 1;
		List<Integer> list = wd.getListOfShifts();

		for (int i = 0; i < 4; i++) {
			recordingSheet.writeCell(6 + i, day, month, list.get(i));
		}
	}

	// private static DecimalFormat df = new DecimalFormat("0.0000");

	public static void writeResultHours(List<Double> list1, List<Double> list2, List<Double> list3, Month month) {
		int m = month.getValue() - 1;
		List<List<Double>> lists = new ArrayList<>();
		lists.add(list1);
		lists.add(list2);
		lists.add(list3);

		for (int i = 0; i < lists.size(); i++) {
			for (int j = 0; j < 4; j++) {
				recordingSheet.writeCell(12 + j, i + 1, m, lists.get(i).get(j));
			}
		}

	}

}
