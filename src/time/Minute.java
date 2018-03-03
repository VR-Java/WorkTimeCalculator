package time;

import java.time.LocalTime;

public class Minute {
	private LocalTime minute;
	private double koef;

	public Minute(LocalTime t) {
		this.minute = t;
		this.koef = 0;
	}

	@Override
	public String toString() {
		return minute + " k=" + koef;
	}

	public void setKoef(double koef) {
		this.koef = koef;
	}

	public double getKoef() {
		return this.koef;
	}

	public LocalTime getMinute() {
		return this.minute;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((minute == null) ? 0 : minute.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Minute other = (Minute) obj;
		if (minute == null) {
			if (other.minute != null)
				return false;
		} else if (!minute.equals(other.minute))
			return false;
		return true;
	}
}