package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.goToPage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.CommandUtil;
import com.epam.kgd.victory.controller.command.util.JSPPath;
import com.epam.kgd.victory.controller.command.util.LocaledMessage;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;
import com.epam.kgd.victory.service.util.Validation;

public class Registration implements Command {
	private final static String PARAM_NAME_LOGIN = "login";
	private final static String PARAM_NAME_PASSWORD = "pwd1";
	private final static String PARAM_NAME_FIRST_NAME = "firstName";
	private final static String PARAM_NAME_LAST_NAME = "lastName";
	private final static String PARAM_NAME_EMAIL = "eMail1";
	private final static String PARAM_NAME_PHONE = "phone";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);
		String firstName = request.getParameter(PARAM_NAME_FIRST_NAME);
		String lastName = request.getParameter(PARAM_NAME_LAST_NAME);
		String email = request.getParameter(PARAM_NAME_EMAIL);
		String phone = request.getParameter(PARAM_NAME_PHONE);

		boolean isDataFromRegistrationFormValid = Validation.validateSignUpData(login, password, firstName, lastName,
				email, phone);

		if (isDataFromRegistrationFormValid) {
			try {
				if (userService.isEmailUnique(email) && userService.isLoginUnique(login)) {
					userService.registrateUser(login, password, firstName, lastName, email, phone);
					String targetPage = JSPPath.SUCCESS_OPERATION.getPath();
					response.sendRedirect(targetPage);
				} else {
					request.setAttribute("loginOrEmailNotUniq", LocaledMessage
							.getMessage("reg.message.loginOrEmailNotUniq", CommandUtil.getCurrentLocale(request)));
					String targetPage = JSPPath.REGISTRATION.getPath();
					goToPage(request, response, targetPage);
				}

			} catch (ServiceException | IOException e) {
				goToPage(request, response, JSPPath.ERROR.getPath());
			}

		} else {
			request.setAttribute("invalidInputData",
					LocaledMessage.getMessage("reg.message.invalidInputData", CommandUtil.getCurrentLocale(request)));
			String targetPage = JSPPath.REGISTRATION.getPath();
			goToPage(request, response, targetPage);
		}
	}
}
