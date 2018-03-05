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

	private List<Minute> workDayList = new ArrayList<>();

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

		shifts.add(timeShift1 > 1 ? timeShift1 : 0);
		shifts.add(timeShift2 > 1 ? timeShift2 : 0);
		shifts.add(timeShift3 > 1 ? timeShift3 : 0);
		shifts.add(timeHoliday > 1 ? timeHoliday : 0);

		return shifts;
	}

	// calculates number of minutes per each shift
	public void calculateShifts() {
		upgradeCoefficients();
		checkIfHoliday();

		for (Minute minute : workDayList) {
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
	}

	// upgrade all coefficients in workDaylist
	// according to standard list -> Clock.getList()
	private List<Minute> upgradeCoefficients() {
		workDayList = Clock.getList();
		workDayList.retainAll(Clock.createList(startDay, finishDay));
		return workDayList;
	}

	// if Holiday -> set all coefficients to 2
	private void checkIfHoliday() {
		if (isHoliday()) {
			for (Minute minute : workDayList) {
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

		return timeShift1 > 2 ? timeShift1 : 0;
	}

	public int getTimeShift2() {
		return timeShift2 > 2 ? timeShift2 : 0;
	}

	public int getTimeShift3() {
		return timeShift3 > 2 ? timeShift3 : 0;
	}

	public int getTimeHoliday() {
		return timeHoliday > 2 ? timeHoliday : 0;
	}

	@Override
	public String toString() {
		return "WorkDay [date=" + date + ", startDay=" + startDay + ", finishDay=" + finishDay + ", holiday=" + holiday
				+ "]";
	}

}
