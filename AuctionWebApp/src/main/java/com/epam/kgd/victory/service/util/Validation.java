package com.epam.kgd.victory.service.util;

import java.util.regex.Pattern;

public class Validation {

	private final static String REGEX_EMAIL = "^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\\.)+[a-z]{2,6}$";
	private final static String REGEX_PHONE = "\\([0-9]{3}\\)\\s[0-9]{3}-[0-9]{2}-[0-9]{2}";
	private final static String REGEX_LOGIN = "^[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])$";
	private final static String REGEX_BID_AMOUNT = "(\\d+)|(\\d+\\.\\d{2})|(\\d+,\\d{2})";

	public static final boolean validateSignUpData(String login, String password, String firstName, String lastName,
			String email, String phone) {

		if (login == null || password == null || firstName == null || lastName == null || email == null
				|| phone == null) {
			return false;
		}

		if (!Pattern.matches(REGEX_EMAIL, email)) {
			return false;
		}
		if (!Pattern.matches(REGEX_PHONE, phone)) {
			return false;
		}
		if (!Pattern.matches(REGEX_LOGIN, login)) {
			return false;
		}
		return true;

	}

	public static final boolean validateAddGoodData(String categoryId, String conditionId, String name,
			String description, String startPrice) {
		if ((categoryId == null) || (conditionId == null) || (name == null) || (description == null)
				|| (startPrice == null)) {
			return false;
		} else {
			return true;
		}

	}

	public static final boolean validateBid(String bidAmount) {
		if (bidAmount == null || bidAmount.isEmpty()) {
			return false;
		}
		if (!Pattern.matches(REGEX_BID_AMOUNT, bidAmount)) {
			return false;
		}
		
		return true;

	}
}
