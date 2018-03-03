package config;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public enum Shift {

	�����_�̲��("07:30:00", "17:30:00"), 
	�����_�̲��("17:30:00", "21:00:00"), 
	�����_�̲��("21:00:00",	"07:30:00"), 
	��ղ����("00:00:00", "00:00:00");

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
