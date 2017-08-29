package com.epam.kgd.victory.bean.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static String convertDateToString(LocalDateTime date) {
		String dateString = null;
		if (date != null) {
			dateString = FORMATTER.format(date);
		}
		return dateString;
	}

	public static LocalDateTime convertStringToDate(String dateString) {
		final int MILLISECONDS_SYMBOLS = 2;
		LocalDateTime date = null;
		if (dateString != null) {
			date = LocalDateTime.parse(dateString.substring(0, dateString.length() - MILLISECONDS_SYMBOLS), FORMATTER);
		}
		return date;
	}

}
