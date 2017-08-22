package com.epam.kgd.victory.controller.command.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.kgd.victory.bean.User;



public class CommandUtil {
	private final static String EN_LOCALE = "EN";

	public static final boolean checkSessionValidity(HttpServletRequest request) {
		return (request.getSession().getAttribute("User") != null);
	}

	public static final String getCurrentLocale(HttpServletRequest request) {
		String locale = (String) request.getSession().getAttribute("locale");
		if (locale != null) {
			return locale;
		} else {
			return EN_LOCALE;
		}
	}
	public static final int takeUserRole(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("User");
		return user.getRoleId();
		
	}
	
	public static void goToPage(HttpServletRequest request, HttpServletResponse response, String targetPage) {
		RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// log
		}
	}

}
