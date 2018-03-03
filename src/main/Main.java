package main;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import engine.ExcellConnector;
import time.WorkDay;

public class Main {
	public static void main(String[] args) throws ParseException {
		
		WorkDay wd1 = new WorkDay();
		wd1.setDate("28.02.2018");
		wd1.setStartDay("09:30:00");
		wd1.setFinishDay("18:30:00");
		
		System.out.println(wd1.readShifts());
		
		ExcellConnector ec = new ExcellConnector("RecordingSheet.xls");
		ec.test();
		
////		
//		System.out.println(wd1.getShift1Minutes());
//		
//		LocalTime localTime = LocalTime.parse( "15:30:18" );
//		LocalTime localTime2 = LocalTime.parse( "15:40:18" );
//		System.out.println(localTime2.until(localTime, ChronoUnit.MINUTES));
//		System.out.println(ChronoUnit.MINUTES.between(localTime2, localTime));
//		
////		LocalTime localTime3 = localTime.minus(localTime2);
////		
////		long h = MINUTES.between(localTime2, localTime);
//		System.out.println(Shift.œ≈–ÿ¿_«Ã≤Õ¿.begin());
		
		//Clock clock = new Clock();
		//System.out.println(Clock.getList());
		
	}
}
