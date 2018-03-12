package time;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class MyClock {
	private static LocalTime startClock = LocalTime.parse("00:00:00");
	private static LocalTime finishClock = LocalTime.parse("23:59:59");

	// initialization of list of all minutes per day with standard coefficients
	public static List<MyMinute> getList() {
		List<MyMinute> list = createList(startClock, finishClock);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCoefficient(speadCoefficients(i));
		}
		return list;
	}

	// creation of list of minutes between startDay and finishDay
	// with empty coefficients (k = 0)
	public static List<MyMinute> createList(LocalTime start, LocalTime finish) {
		List<MyMinute> list = new ArrayList<>();
		LocalTime currTime;

		if (finish.isAfter(start)) {
			if (finish != startClock) {
				finish = finish.minus(1, ChronoUnit.MINUTES);
			}
			long workTime = ChronoUnit.MINUTES.between(start, finish);
			
			for (int i = 0; i <= (int) workTime; i++) {
				currTime = start.plus(i, ChronoUnit.MINUTES);
				list.add(new MyMinute(currTime));
			}
		} else {
			if (finish != startClock) {
				finish = finish.minus(1, ChronoUnit.MINUTES);
			}
			long workTimeBeforeModnight = ChronoUnit.MINUTES.between(start, startClock);
			long workTimeAfterMidnight = ChronoUnit.MINUTES.between(startClock, finish);
			
			System.out.println( workTimeBeforeModnight + workTimeAfterMidnight);
			
			for (int i = 0; i <= (int) workTimeBeforeModnight; i++) {
				currTime = start.plus(i, ChronoUnit.MINUTES);
				list.add(new MyMinute(currTime));
			}
			for (int i = 0; i <= (int) workTimeAfterMidnight; i++) {
				currTime = startClock.plus(i, ChronoUnit.MINUTES);
				list.add(new MyMinute(currTime));
			}
			
		}
	
		return list;
	}

	// sets coefficients for each minute per day
	public static double speadCoefficients(int i) {
		if (i >= 0 && i < THIRD_SHIFT_DINNER_START) {
			return 1.5;
		} else if (i >= THIRD_SHIFT_DINNER_START && i < THIRD_SHIFT_DINNER_FINISH) {
			return 0;
		} else if (i >= THIRD_SHIFT_DINNER_FINISH && i < THIRD_SHIFT_FINISH) {
			return 1.5;
		} else if (i >= THIRD_SHIFT_FINISH && i <FIRST_SHIFT_DINNER_START) {
			return 1;
		} else  if (i >= FIRST_SHIFT_DINNER_START && i < FIRST_SHIFT_DINNER_FINISH){
			return 0;
		} else  if (i >= FIRST_SHIFT_DINNER_FINISH && i < FIRST_SHIFT_FINISH){
			return 1;
		} else  if (i >= FIRST_SHIFT_FINISH && i < SECOND_SHIFT_FINISH){
			return 1.25;
		} else  if (i >= SECOND_SHIFT_FINISH && i <= DAY_FINISH){
			return 1.5;
		} else {
			return -1;
		}
	}
	
	// initialization of all times, when coefficient changes
	public static final int DAY_START = MyClock.minutesFrom00(startClock);
	public static final int THIRD_SHIFT_FINISH = MyClock.minutesFrom00(LocalTime.parse("07:30:00"));
	public static final int FIRST_SHIFT_DINNER_START = MyClock.minutesFrom00(LocalTime.parse("13:00:00"));
	public static final int FIRST_SHIFT_DINNER_FINISH = MyClock.minutesFrom00(LocalTime.parse("13:30:00"));
	public static final int FIRST_SHIFT_FINISH = MyClock.minutesFrom00(LocalTime.parse("17:30:00"));
	public static final int SECOND_SHIFT_FINISH = MyClock.minutesFrom00(LocalTime.parse("21:00:00"));
	public static final int THIRD_SHIFT_DINNER_START = MyClock.minutesFrom00(LocalTime.parse("01:00:00"));
	public static final int THIRD_SHIFT_DINNER_FINISH = MyClock.minutesFrom00(LocalTime.parse("01:30:00"));
	public static final int DAY_FINISH = MyClock.minutesFrom00(finishClock);
	
	//calculating of quantity of minutes from 00:00 to time in parameters
	public static int minutesFrom00(LocalTime time) {
		return (int)ChronoUnit.MINUTES.between(startClock, time);
	}

}
