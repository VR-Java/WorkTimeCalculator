package config;

public enum Calendar {
	Ѳ����(168), �����(160), ��������(167), 
	�²����(159), �������(159), �������(159), 
	������(176), �������(175), ��������(160), 
	�������(176), ��������(176),�������(158);
	
	private int workHours;

	private Calendar(int workHours) {
		this.workHours = workHours;
	}
	
	public int getWorkHours() {
		return workHours;
	}

}
