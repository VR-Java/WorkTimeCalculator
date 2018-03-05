package time;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import config.MyCalendar;
import engine.CalendarLoader;
import engine.RecordingSheetLoader;

public class MyMonth {

	private WorkDay[] days;
	int workDays;
	int workHours;

	String monthName;
	int year;
	Month month;

	public MyMonth(MyCalendar myMonth) {
		this.monthName = myMonth.name();

		this.month = Month.valueOf(myMonth.getName());

		try {
			this.year = CalendarLoader.readYear();
			this.days = CalendarLoader.readWorkDays(monthName);
			setDate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setDate() {
		for (int i = 0; i < days.length; i++) {
			if (days[i] != null) {
				days[i].setDate(LocalDate.of(year, month, i + 1));
			}
		}
	}

	private void readWorkTime(WorkDay wd) throws IOException {
		wd.setStartDay(RecordingSheetLoader.readStartDay(wd));
		wd.setFinishDay(RecordingSheetLoader.readFinishDay(wd));
	}

	public void writeWorkTime() {
		for (int i = 0; i < days.length; i++) {
			if (days[i] != null) {
				try {
					readWorkTime(days[i]);
					RecordingSheetLoader.writeShiftsTime(days[i]);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private List<Double> calculateHalfOfMonth(int first, int last) {
		int sumShift1 = 0;
		int sumShift2 = 0;
		int sumShift3 = 0;
		int sumHoliday = 0;
		List<Double> list = new ArrayList<>();
		for (int i = first - 1; i < last - 1; i++) {
			if (days[i] != null) {
				sumShift1 += days[i].getTimeShift1();
				sumShift2 += days[i].getTimeShift2();
				sumShift3 += days[i].getTimeShift3();
				sumHoliday += days[i].getTimeHoliday();
			}
		}
		list.add((double) Math.round(100 * sumShift1 / 60) / 100);
		list.add((double) Math.round(100 * sumShift2 / 60) / 100);
		list.add((double) Math.round(100 * sumShift3 / 60) / 100);
		list.add((double) Math.round(100 * sumHoliday / 60) / 100);
		return list;
	}

	public void writeResult() {
		List<Double> firstHalf = calculateHalfOfMonth(1, 15);
		List<Double> secondHalf = calculateHalfOfMonth(16, 31);
		List<Double> all = new ArrayList<>();
		for (int i = 0; i < firstHalf.size(); i++) {
			all.add(firstHalf.get(i) + secondHalf.get(i));
		}
		System.out.println(firstHalf);
		System.out.println(secondHalf);
		System.out.println(all);
		RecordingSheetLoader.writeResultHours(firstHalf, secondHalf, all, month);
	}

	@Override
	public String toString() {
		return "Month [monthName=" + monthName + ", days=" + Arrays.toString(days) + "]";
	}

}
