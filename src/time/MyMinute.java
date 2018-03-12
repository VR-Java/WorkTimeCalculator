package time;

import java.time.LocalTime;

public class MyMinute {

	private LocalTime minute;
	private double coefficient;

	public MyMinute(LocalTime t) {
		this.minute = t;
		this.coefficient = 0;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public double getCoefficient() {
		return this.coefficient;
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
		MyMinute other = (MyMinute) obj;
		if (minute == null) {
			if (other.minute != null)
				return false;
		} else if (!minute.equals(other.minute))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return minute + " k=" + coefficient;
	}
}