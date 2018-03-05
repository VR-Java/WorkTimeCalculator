package main;

import java.io.IOException;

import config.MyCalendar;
import time.MyMonth;

public class Main {

	public static void main(String[] args) throws IOException {

		MyMonth m1 = new MyMonth(MyCalendar.я╡вемэ);
		MyMonth m2 = new MyMonth(MyCalendar.кчрхи);
		MyMonth m3 = new MyMonth(MyCalendar.аепегемэ);

		m1.writeWorkTime();
		m1.writeResult();
		m2.writeWorkTime();
		m2.writeResult();
		m3.writeWorkTime();
		m3.writeResult();

		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);

	}
}
