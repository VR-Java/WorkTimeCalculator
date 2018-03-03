package config;

public enum Calendar {
	я╡вемэ(168), кчрхи(160), аепегемэ(167), 
	йб╡ремэ(159), рпюбемэ(159), вепбемэ(159), 
	кхоемэ(176), яепоемэ(175), бепеяемэ(160), 
	фнбремэ(176), кхярноюд(176),цпсдемэ(158);
	
	private int workHours;

	private Calendar(int workHours) {
		this.workHours = workHours;
	}
	
	public int getWorkHours() {
		return workHours;
	}

}
