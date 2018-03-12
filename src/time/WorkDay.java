package time;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkDay {

	private LocalDate date;
	private LocalTime startDay;
	private LocalTime finishDay;

	private int timeShift1 = 0;
	private int timeShift2 = 0;
	private int timeShift3 = 0;
	private int timeHoliday = 0;

	private boolean holiday = false;

//	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	private List<MyMinute> workDayList = new ArrayList<>();

	public WorkDay(boolean holiday) {
		this.holiday = holiday;
	}

	// create list of all shifts:
	// list[0] - shift 1 (minutes)
	// list[1] - shift 2 (minutes)
	// list[2] - shift 3 (minutes)
	// list[3] - holiday (minutes)
	public List<Integer> getListOfShifts() {
		List<Integer> shifts = new ArrayList<>();
		calculateShifts();

		shifts.add(timeShift1);
		shifts.add(timeShift2);
		shifts.add(timeShift3);
		shifts.add(timeHoliday);

		return shifts;
	}

	// calculates number of minutes per each shift
	public void calculateShifts() {
		upgradeCoefficients();
		checkIfHoliday();

		for (MyMinute minute : workDayList) {
			if (minute.getCoefficient() == 1) {
				timeShift1++;
			} else if (minute.getCoefficient() == 1.25) {
				timeShift2++;
			} else if (minute.getCoefficient() == 1.5) {
				timeShift3++;
			} else if (minute.getCoefficient() == 2) {
				timeHoliday++;
			}
		}
		correctShifts();
	}
	
	// correct Shifts. If timeShift < 2 -> timeShift = 0
	private void correctShifts() {
		timeShift1 = timeShift1 > 2 ? timeShift1 : 0;
		timeShift2 = timeShift2 > 2 ? timeShift2 : 0;
		timeShift3 = timeShift3 > 2 ? timeShift3 : 0;
		timeHoliday = timeHoliday > 2 ? timeHoliday : 0;
	}

	// upgrade all coefficients in workDaylist
	// according to standard list -> Clock.getList()
	private List<MyMinute> upgradeCoefficients() {
		workDayList = MyClock.getList();
		workDayList.retainAll(MyClock.createList(startDay, finishDay));
		return workDayList;
	}

	// if Holiday -> set all coefficients to 2
	private void checkIfHoliday() {
		if (isHoliday()) {
			for (MyMinute minute : workDayList) {
				minute.setCoefficient(2);
			}
		}
	}

	public int getDay() {
		return date.getDayOfMonth();
	}

	public int getMonth() {
		return date.getMonthValue();
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public boolean isHoliday() {
		return holiday;
	}

	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}

	public LocalTime getStartDay() {
		return this.startDay;
	}

	public LocalTime getFinishDay() {
		return this.finishDay;
	}

	public void setStartDay(String start) throws ParseException {
		this.startDay = LocalTime.parse(start);
	}

	public void setStartDay(LocalTime time) {
		this.startDay = time;
	}

	public void setFinishDay(String finish) throws ParseException {
		this.finishDay = LocalTime.parse(finish);
	}

	public void setFinishDay(LocalTime time) {
		this.finishDay = time;
	}

	public int getTimeShift1() {

		return timeShift1;
	}

	public int getTimeShift2() {
		return timeShift2;
	}

	public int getTimeShift3() {
		return timeShift3;
	}

	public int getTimeHoliday() {
		return timeHoliday;
	}

	@Override
	public String toString() {
		return "WorkDay [date=" + date + ", startDay=" + startDay + ", finishDay=" + finishDay + ", holiday=" + holiday
				+ "]";
	}

}
