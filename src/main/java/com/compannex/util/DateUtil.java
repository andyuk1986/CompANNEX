package com.compannex.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static final String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
	
	public static int getDay(Date date) {

		if (date == null)
			return Integer.MIN_VALUE;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static String getMonth(Date date) {

		if (date == null)
			return "";

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return months[cal.get(Calendar.MONTH)];
	}
	
	public static String getDateAsString(Date date) {

		if (date == null)
			return "";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
}
