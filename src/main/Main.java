package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import employee.Employee;
import engine.RecordingSheet;
import engine.WorkersLoader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		List<Employee> list = new ArrayList<>();
//		list = readWorkers(1);

		list = readWorkers(0);
		calculate(list);

	}
	
	
	
	
	// read all Employees from file
	// if arg == 1 - creates RecordingSheets files for each Employee
	// else - only reads List<Employees>
	private static List<Employee> readWorkers(int arg) {
		return WorkersLoader.readAllEmployyes(arg);
	}

	private static void calculate(List<Employee> list) {
		RecordingSheet rs;

		for (Employee employee : list) {
			rs = new RecordingSheet(employee);
			rs.fullfill();
		}
		System.out.println();
		System.out.println("Опрацьовано усіх працівників");
	}

}
