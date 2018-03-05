package time;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class Clock {

	// initialization of list of all minutes per day with standard coefficients
	public static List<Minute> getList() {
		List<Minute> list;
		LocalTime start = LocalTime.parse("00:00:00");
		LocalTime finish = LocalTime.parse("23:59:59");

		list = createList(start, finish);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCoefficient(speadCoefficients(i));
		}
		return list;
	}

	// creation of list of minutes between startDay and finishDay
	// with empty coefficients (k = 0)
	public static List<Minute> createList(LocalTime start, LocalTime finish) {
		long workTime = ChronoUnit.MINUTES.between(start, finish);
		List<Minute> list = new ArrayList<>();
		LocalTime currTime;

		for (int i = 0; i <= (int) workTime; i++) {
			currTime = start.plus(i, ChronoUnit.MINUTES);
			list.add(new Minute(currTime));
		}
		return list;
	}

	// sets coefficients for each minute per day
	public static double speadCoefficients(int i) {
		if (i < 450) {
			return 1.5;
		} else if (i > 451 && i <= 1049) {
			return 1;
		} else if (i > 1049 && i <= 1259) {
			return 1.25;
		} else {
			return 1.5;
		}
	}

}
