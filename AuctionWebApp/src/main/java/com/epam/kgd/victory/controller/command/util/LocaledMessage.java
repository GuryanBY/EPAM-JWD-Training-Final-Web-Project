package com.epam.kgd.victory.controller.command.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaledMessage {
	private final static ResourceBundle BUNDLE_RU = ResourceBundle.getBundle("resources.locale", new Locale("RU"));
	private final static ResourceBundle BUNDLE_EN = ResourceBundle.getBundle("resources.locale", new Locale("EN"));
	private final static String EN_LOCALE="EN";
	
	public static String getMessage(String key, String locale){
		if (EN_LOCALE.equalsIgnoreCase(locale)) {
			return BUNDLE_EN.getString(key);
		} else {
			return BUNDLE_RU.getString(key);
		}
		
	}

}
