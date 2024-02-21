package com.retailer.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatterUtil {

	private static final DateTimeFormatter YEAR_MONTH_DAY_IN_NUMBER_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("MMM-yyyy");
	
	public static String getCurrentDateAsString() {
		LocalDate localDate = LocalDate.now();
		return localDate.format(YEAR_MONTH_DAY_IN_NUMBER_FORMATTER);
	}
	
	public static String getMonthAndYearOfLocalDate(LocalDate date) {
		return date.format(YEAR_MONTH_FORMATTER);
	}
}
