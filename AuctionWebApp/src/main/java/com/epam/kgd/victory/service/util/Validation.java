package com.epam.kgd.victory.service.util;

import java.util.regex.Pattern;

public class Validation {

	private final static String REGEX_EMAIL = "^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\\.)+[a-z]{2,6}$";
	private final static String REGEX_PHONE = "\\([0-9]{3}\\)\\s[0-9]{3}-[0-9]{2}-[0-9]{2}";
	private final static String REGEX_LOGIN = "^[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])$";
	private final static String REGEX_BID_AMOUNT = "(\\d+)|(\\d+\\.\\d{2})|(\\d+,\\d{2})";
	private final static int INTERNET_AUCTION_TYPE_ID = 1;
	private final static int ENGLISH_AUCTION_TYPE_ID = 2;
	private final static int BLITZ_AUCTION_TYPE_ID = 3;
	private final static int MAX_STRING_LENGTH_IN_DB = 255;
	private final static int MAX_LOGIN_LENGTH = 30;
	private final static int MIN_PASSWORD_LENGTH = 3;
	private final static int MAX_PASSWORD_LENGTH = 30;

	public static final boolean isIdValid(int id) {
		if (id < 0) {
			return false;
		}

		return true;

	}

	public static final boolean isAuctionTypeIdValid(int auctionTypeId) {

		if (auctionTypeId != INTERNET_AUCTION_TYPE_ID && auctionTypeId != ENGLISH_AUCTION_TYPE_ID
				&& auctionTypeId != BLITZ_AUCTION_TYPE_ID) {
			return false;
		}

		return true;

	}

	public static final boolean isAddLotFormValid(int sellerId, int goodId, String auctionTypeId, String name,
			String goodAmount, String duration, String price) {

		if (!isStringValid(auctionTypeId, MAX_STRING_LENGTH_IN_DB)) {
			return false;
		}
		if (!isStringValid(name, MAX_STRING_LENGTH_IN_DB)) {
			return false;
		}
		if (!isStringValid(goodAmount, MAX_STRING_LENGTH_IN_DB)) {
			return false;
		}
		if (!isStringValid(duration, MAX_STRING_LENGTH_IN_DB)) {
			return false;
		}
		if (!isStringValid(price, MAX_STRING_LENGTH_IN_DB)) {
			return false;
		}

		int auctionTypeIdInt = Integer.parseInt(auctionTypeId);
		int goodAmountInt = Integer.parseInt(goodAmount);
		long durationLong = Long.parseLong(duration);
		double priceDouble = Double.parseDouble(price);

		if (goodAmountInt <= 0 || durationLong <= 0 || priceDouble <= 0) {
			return false;
		}

		if (!isIdValid(sellerId)) {
			return false;
		}
		if (!isIdValid(goodId)) {
			return false;
		}

		if (!isAuctionTypeIdValid(auctionTypeIdInt)) {
			return false;
		}
		return true;
	}

	public static final boolean isEmailValid(String email) {
		if (!isStringValid(email, MAX_STRING_LENGTH_IN_DB)) {
			return false;
		}
		if (!Pattern.matches(REGEX_EMAIL, email)) {
			return false;
		}
		return true;
	}

	public static final boolean isLoginValid(String login) {
		if (!isStringValid(login, MAX_LOGIN_LENGTH)) {
			return false;
		}
		if (!Pattern.matches(REGEX_LOGIN, login)) {
			return false;
		}
		return true;
	}

	public static final boolean isPasswordValid(String password) {

		if (!isStringValid(password, MAX_PASSWORD_LENGTH)) {
			return false;
		}
		if (password.length() < MIN_PASSWORD_LENGTH) {
			return false;

		}

		return true;
	}

	public static final boolean isRegistrateFormValid(String login, String password, String firstName, String lastName,
			String email, String phone) {
		if (!isLoginValid(login)) {
			return false;
		}
		if (!isPasswordValid(password)) {
			return false;
		}

		if (!isStringValid(firstName, MAX_STRING_LENGTH_IN_DB) || !isStringValid(lastName, MAX_STRING_LENGTH_IN_DB)
				|| !isStringValid(phone, MAX_STRING_LENGTH_IN_DB)) {
			return false;
		}
		if (!isEmailValid(email)) {
			return false;
		}

		if (!Pattern.matches(REGEX_PHONE, phone)) {
			return false;
		}
		return true;

	}

	public static final boolean isAddGoodDataValid(String categoryId, String conditionId, String name,
			String description, String startPrice) {
		if ((categoryId == null) || (conditionId == null) || (name == null) || (description == null)
				|| (startPrice == null)) {
			return false;
		} else {
			return true;
		}

	}

	public static final boolean isBidValid(int userId, String lotId, String newPrice) {
		if (!isIdValid(userId)) {
			return false;
		}
		if (!isStringValid(lotId, MAX_STRING_LENGTH_IN_DB)) {
			return false;
		}
		if (!isIdValid(Integer.parseInt(lotId))) {
			return false;
		}

		if (!isStringValid(newPrice, MAX_STRING_LENGTH_IN_DB)) {
			return false;
		}
		if (!Pattern.matches(REGEX_BID_AMOUNT, newPrice)) {
			return false;
		}

		return true;

	}

	public static final boolean isNumberPositive(int number){
		if(number < 0){
			return false;
		}
		
		return true;
	}

	private static final boolean isStringValid(String str, int maxStringLength) {
		if (str == null || str.isEmpty() || str.length() > maxStringLength) {
			return false;
		}
		return true;

	}

}
