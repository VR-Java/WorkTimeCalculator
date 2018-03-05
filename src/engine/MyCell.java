package engine;

public enum MyCell {

	EMPLOYEE(1,1), 
	DATE(2,1), 
	START_DAY(3, 1), FINISH_DAY(4,1), 
	SHIFT_1_HOURS(6, 1), SHIFT_2_HOURS(7, 1), SHIFT_3_HOURS(8, 1), HOLIDAY_HOURS(9, 1),
	;
	
	private int row;
	private int col;
	
	private MyCell(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
}
