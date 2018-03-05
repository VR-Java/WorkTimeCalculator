package config;

public enum MyCalendar {
	я╡вемэ(168, "JANUARY"), кчрхи(160, "FEBRUARY"), аепегемэ(167, "MARCH"), 
	йб╡ремэ(159, "APRIL"), рпюбемэ(159, "MAY"), вепбемэ(159, "JUNE"), 
	кхоемэ(176, "JULY"), яепоемэ(175, "AUGUST"), бепеяемэ(160, "SEPTEMBER"), 
	фнбремэ(176, "OCTOBER"), кхярноюд(176, "NOVEMBER"),цпсдемэ(158, "DECEMBER");
	
	private int workHours;
	private String name;

	private MyCalendar(int workHours, String nameEng) {
		this.workHours = workHours;
		this.name = nameEng;
	}
	
	public int getWorkHours() {
		return workHours;
	}
	
	public String getName() {
		return name;
	}

}
