package com.epam.kgd.victory.util;

public class CurrencyConverter {
	private static final double DOLLAR_EXCHANGE_RATE = 1.9457;
	private static final String RUSSIAN_LOCALE = "ru";

	/**
	 * If current language is russian, convert amount to belarusian rubles,
	 * otherwise return amount in dollars.
	 *
	 * @param amount
	 * @param locale
	 * @return
	 */
	public static double convertToCurrentCurrency(double amount, String locale) {
		if (locale.equals(RUSSIAN_LOCALE)) {
			amount *= DOLLAR_EXCHANGE_RATE;
		}
		return amount;
	}

	public static double convertToDollar(double amount, String locale) {
		if (locale.equals(RUSSIAN_LOCALE)) {
			amount /= DOLLAR_EXCHANGE_RATE;
		}
		return amount;
	}

}
