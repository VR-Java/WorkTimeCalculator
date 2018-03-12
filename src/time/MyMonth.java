package time;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import config.MyCalendar;
import engine.CalendarLoader;

public class MyMonth {

	private WorkDay[] days;
	int workDays;
	int workHours;

	String monthName;
	int year;
	Month month;
	int monthOfYear;

	// initialization of Month
	//
	public MyMonth(MyCalendar myMonth) {
		this.monthName = myMonth.name(); // ukrainian name of month
		this.month = Month.valueOf(myMonth.getName()); // english name of month
		this.monthOfYear = month.getValue();
		try {
			// reading workdays and holidays from file "Calendar.xls"
			this.year = CalendarLoader.readYear();
			this.days = CalendarLoader.readWorkDays(monthName);
			setDate();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}

	public int getMonthOfYear() {
		return this.monthOfYear;
	}

	// setting Date to each day in month. From 1st to 31st
	private void setDate() {
		for (int i = 0; i < days.length; i++) {
			if (days[i] != null) {
				days[i].setDate(LocalDate.of(year, month, i + 1));
			}
		}
	}

	public WorkDay[] getDays() {
		return this.days;
	}

	@Override
	public String toString() {
		return "Month [monthName=" + monthName + ", days=" + Arrays.toString(days) + "]";
	}

}
