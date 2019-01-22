package com.sinoyoo.familyfunds.utils;

import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		Date date = new Date();
		date.setMonth(9);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		Date first = calendar.getTime();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		Date end = calendar.getTime();
		
		System.out.println(first);
		System.out.println(end);
		
	}
}
