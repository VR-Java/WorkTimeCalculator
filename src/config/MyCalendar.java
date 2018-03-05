package config;

public enum MyCalendar {
	Ѳ����(168, "JANUARY"), �����(160, "FEBRUARY"), ��������(167, "MARCH"), 
	�²����(159, "APRIL"), �������(159, "MAY"), �������(159, "JUNE"), 
	������(176, "JULY"), �������(175, "AUGUST"), ��������(160, "SEPTEMBER"), 
	�������(176, "OCTOBER"), ��������(176, "NOVEMBER"),�������(158, "DECEMBER");
	
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
