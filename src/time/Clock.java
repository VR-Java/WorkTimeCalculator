package time;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Clock {

	private static List<Minute> list = new ArrayList<>();

	public static List<Minute> getList() {
		init();
		return list;
	}

	public static void init() {
		LocalTime start = LocalTime.parse("00:00:00");
		LocalTime finish = LocalTime.parse("23:59:59");

		list = createList(start, finish);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setKoef(koef(i));
		}
		// return list;
	}

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

	public static double koef(int i) {
		if (i < 450) {
			return 1.5;
		} else if (i >= 450 && i < 1050) {
			return 1;
		} else if (i >= 1050 && i < 1260) {
			return 1.25;
		} else {
			return 1.5;
		}
	}

}
