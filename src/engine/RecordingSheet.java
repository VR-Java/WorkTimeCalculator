package engine;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import config.MyCalendar;
import employee.Employee;
import time.MyMonth;
import time.WorkDay;

public class RecordingSheet {

	private Employee employee;
	private ExcellConnector ec = null;
	private MyMonth month;
	private WorkDay[] days;
	private String path; // Path to directory with file with RecordingSheet

	public RecordingSheet(Employee e) {
		this.employee = e;
		setMonth();
		System.out.println(getPath() + e.getId() + "-" + e.getName() + ".xls");
		ec = new ExcellConnector(getPath() + e.getId() + "-" + e.getName() + ".xls");
	}

	public String getPath() {
		setPath();
		return this.path;
	}

	private void setPath() {
		Path p = Paths.get("D:\\Java\\Projects\\WorkTime\\Recording Sheets\\");
		if (month.getMonthOfYear() < 10) {
			this.path = p + "\\" + WorkersLoader.getYear() + "\\0" + month.getMonthOfYear() + "\\";
		} else {
			this.path = p + "\\" + WorkersLoader.getYear() + "\\" + month.getMonthOfYear() + "\\";
		}
	}

	private void setMonth() {
		MyCalendar currMonth = WorkersLoader.getCurrentMonth();
		this.month = new MyMonth(currMonth);
		this.days = this.month.getDays();
	}

	public void fullfill() {
		System.out.println("Працівник " + employee.getName() + " опрацьовується...");
		try {
			writeWorkTime();
			writeResult();	
			WorkersLoader.writeResultHoursToWorkers(employee, firstHalf, secondHalf, all);
		} catch (Exception e) {
			System.out.println("Файл \"" + employee.getId() + "-" + employee.getName() + ".xls\" не знайдено");
		}
	}
	
	
	// reading of Working hours from File
	private void readWorkTime(WorkDay wd) throws IOException {
		wd.setStartDay(RecordingSheetLoader.readStartDay(ec, wd));
		wd.setFinishDay(RecordingSheetLoader.readFinishDay(ec, wd));
	}

	// writing quantity of minutes per each shift to File
	public void writeWorkTime() {
		for (int i = 0; i < days.length; i++) {
			if (days[i] != null) {
				try {
					readWorkTime(days[i]);
					RecordingSheetLoader.writeShiftsTime(ec, days[i]);
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}
	}

	// calculation of hours per each shift between FISRT and LAST days of month
	private List<Double> calculateHalfOfMonth(int first, int last) {
		int sumShift1 = 0;
		int sumShift2 = 0;
		int sumShift3 = 0;
		int sumHoliday = 0;
		List<Double> list = new ArrayList<>();
		for (int i = first - 1; i <= last - 1; i++) {
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

	// writing result to File
	// - first half of Month
	// - second half of Month
	// - full Month
	
	private List<Double> firstHalf = new ArrayList<>();
	private List<Double> secondHalf = new ArrayList<>();
	private List<Double> all = new ArrayList<>();
	
	public void writeResult() {
		firstHalf = calculateHalfOfMonth(1, 15);
		secondHalf = calculateHalfOfMonth(16, 31);
		all = new ArrayList<>();
		for (int i = 0; i < firstHalf.size(); i++) {
			all.add(firstHalf.get(i) + secondHalf.get(i));
		}
		System.out.println("Години : [ x1 , x1.25 , x1.5 , x2 ]");
		System.out.println(" 1 - 15: " + firstHalf);
		System.out.println("16 - 31: " + secondHalf);
		System.out.println(" 1 - 31: " + all);
		System.out.println();
		try {
			RecordingSheetLoader.writeResultHours(ec, firstHalf, secondHalf, all, month);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}

}
