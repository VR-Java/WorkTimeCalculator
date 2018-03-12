package engine;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import config.MyCalendar;
import employee.Employee;
import time.MyMonth;

public class WorkersLoader {

	private static List<Employee> workersList = new ArrayList<>();
	
	private static Path path = Paths.get("D:\\Java\\Projects\\WorkTime\\Recording Sheets\\2018\\01");

	private static ExcellConnector workers = new ExcellConnector(path +"\\" + "Workers.xls");
	
	
	public static int getYear() {
		try {
			return (int)workers.readDoubleCellValue(0, 0);
		} catch (Exception e) {
			return 0;
		}
	}
	
	//read Month from File "Workers.xls"
	private static String readMonth(){
		try {
			return workers.readStringCellValue(0, 1);			
		} catch (Exception e) {
			//e.printStackTrace();
			return "";
		}
	}
	
	//initialization of Month. Creating Enum-object MyCalendar
	public static MyCalendar getCurrentMonth() {
		String monthNameUA = readMonth().toUpperCase();
//		System.out.println(monthNameUA);
		//MyCalendar month = MyCalendar.valueOf(monthNameUA);
		return MyCalendar.valueOf(monthNameUA);
	}
	
// reading Employee from File "Workers.xls" 
	public static Employee readEmployee(int row, int col) throws IOException {
		String name = workers.readStringCellValue(row, col);
		Employee e = new Employee(name, row);
//		System.out.println(e);
		return e;
	}
	
	
	// reading all existing Employees from File "Workers.xls" 
	public static List<Employee> readAllEmployyes(int arg) {
		int row = 3;
		int col = 0;
		int val;
		Employee e;

		do {
			try {
				val = (int) workers.readDoubleCellValue(row, col);
			} catch (IOException | NullPointerException e2) {
				val = 0;
			}
			if (val != 0) {
				try {
					e = readEmployee(row, col + 1);
					workersList.add(e);
					if(arg == 1) {
						CopyFile.copy(path + "\\", e.getId()+"-"+e.getName() + ".xls");						
					} 
				} catch (IOException e1) {
					//e1.printStackTrace();
				}
			}
//			System.out.println(row);
			row++;
		} while (val > 0);

		workersList.forEach(System.out::println);
		return workersList;
	}
	
//	// generating RecordingSheets for each Employee
//	public static List<RecordingSheet> generateRecordingSheets() {
//		List<RecordingSheet> sheets = new ArrayList<>();
//		for (Employee e : workersList) {
//			CopyFile.copy(path + "\\", e.getId()+"-"+e.getName() + ".xls");
//			sheets.add(new RecordingSheet(e));
//		}
//		return sheets;
//	}
	
	
	
	
	
	
	// writes total sum of hours per each shift for each Employee to File "Workers.xls"
	public static void writeResultHoursToWorkers(Employee e, List<Double> list1, List<Double> list2,
			List<Double> list3) {
		List<Double> lists = new ArrayList<>();
		lists.addAll(list1);
		lists.addAll(list2);
		lists.addAll(list3);
		
		int row = e.getId()+2;
		int col = 4;

		for (int i = 0; i < lists.size(); i++) {
			workers.writeCell(row, col + i, 0, lists.get(i));
		}
	}

}
