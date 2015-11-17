package com.airamerica.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class NullString {
	
	public static String CheckNullString(String string) {
		if (string == null) {
			string = "---";
		}
		return string;
	}
	
	public static DateTime CheckNullDate(String string) {
		DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd");
		
		DateTime date = null;
		
		if (string == null) {
			date = formatDate.parseDateTime("0000-01-01");
			
		} else {
			date = formatDate.parseDateTime(string);
			
		}
		
		return date;
	}
	
	public static DateTime CheckNullTime(String string) {
		DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");
		
		DateTime time = null;
		
		if (string == null) {
			time = formatTime.parseDateTime("00:00");
			
		} else {
			time = formatTime.parseDateTime(string);
			
		}
		
		return time;
		
	}
	
}
