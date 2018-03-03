package config;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public enum Shift {

	œ≈–ÿ¿_«Ã≤Õ¿("07:30:00", "17:30:00"), 
	ƒ–”√¿_«Ã≤Õ¿("17:30:00", "21:00:00"), 
	“–≈“ﬂ_«Ã≤Õ¿("21:00:00",	"07:30:00"), 
	¬»’≤ƒÕ»…("00:00:00", "00:00:00");

	private LocalTime begin;
	private LocalTime end;

	private Shift(String begin, String end) {
		try {
			this.begin = LocalTime.parse(begin);
			this.end = LocalTime.parse(end);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
		}
	}

	public LocalTime begin() {
		return begin;
	}

	public LocalTime end() {
		return end;
	}

}
