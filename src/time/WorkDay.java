package time;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class WorkDay {

	private Date date;
	private LocalTime startDay;
	private LocalTime finishDay;

	private int timeShift1 = 0;
	private int timeShift2 = 0;
	private int timeShift3 = 0;
	private int timeHoliday = 0;

	private boolean holiday = false;

	public boolean isHoliday() {
		return holiday;
	}

	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	public void setDate(String dateFromFile) throws ParseException {
		this.date = DATE_FORMAT.parse(dateFromFile);
	}

	// private static final SimpleDateFormat START_FINISH = new
	// SimpleDateFormat("HH:mm");

	public void setStartDay(String start) throws ParseException {
		this.startDay = LocalTime.parse(start);
	}

	public void setFinishDay(String finish) throws ParseException {
		this.finishDay = LocalTime.parse(finish);
	}

	private List<Minute> workDayList = new ArrayList<>();



	public List<Minute> readKoef() {
		workDayList = Clock.getList();
		workDayList.retainAll(Clock.createList(startDay, finishDay));
		//System.out.println(workDayList);
		return workDayList;
	}
	
	private void checkIfHoliday() {
		if(isHoliday()) {
			for (Minute minute : workDayList) {
				minute.setKoef(2);
			}
		}
	}
	
	public void calculateShifts() {
		readKoef();
		checkIfHoliday();
		
		for (Minute minute : workDayList) {
			if(minute.getKoef()==1) {
				timeShift1++;
			} else if (minute.getKoef()==1.25) {
				timeShift2++;
			} else if (minute.getKoef()==1.5) {
				timeShift3++;
			} else if (minute.getKoef()==2) {
				timeHoliday++;
			}
		}
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

	
	public List<Integer> readShifts() {
		List<Integer> shifts = new ArrayList<>();
		calculateShifts();
		
		shifts.add(timeShift1);
		shifts.add(timeShift2);
		shifts.add(timeShift3);
		shifts.add(timeHoliday);
		
		return shifts;
	}

}
