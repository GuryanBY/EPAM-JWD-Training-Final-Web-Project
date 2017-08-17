package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.AdminService;
import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;
import com.epam.kgd.victory.service.util.JSPPath;
import com.epam.kgd.victory.service.util.LocaledMessage;

public class LogIn implements Command {

	private final static String PARAM_NAME_LOGIN = "login";
	private final static String PARAM_NAME_PASSWORD = "password";
	private final static int ROLE_ADMIN = 1;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		AdminService adminService = serviceFactory.getAdminService();
		LotService lotService = serviceFactory.getLotService();

		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);

		try {
			boolean isVeryfied = userService.isInStorage(login, password);

			if (isVeryfied) {
				User user = userService.takeUserByLoginAndPassword(login, password);

				if (user.getRoleId() == ROLE_ADMIN) {
					request.getSession().setAttribute("User", user);
					List<User> users = adminService.getAllUsers();
					request.getSession().setAttribute("Users", users);
					String targetPage = JSPPath.ADMIN_MAIN.getPath();
					goToPage(request, response, targetPage);
				} else {
					request.getSession().setAttribute("User", user);
					List<Lot> lots = lotService.takeLotsBySellerId(user.getId());
					request.getSession().setAttribute("Lots", lots);
					List<Lot> boughtLots = lotService.takeLotsByBuyerId(user.getId());
					request.getSession().setAttribute("BoughtLots", boughtLots);
					/*String targetPage = JSPPath.USER_MAIN.getPath();
					goToPage(request, response, targetPage);*/
					
					try {
						response.sendRedirect("jsp/success_login.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				request.setAttribute("invalidLogOrPass",
						LocaledMessage.getMessage("index.message.invalidLoginOrPass", getCurrentLocale(request)));
				String targetPage = JSPPath.INDEX.getPath();
				goToPage(request, response, targetPage);
			}
		} catch (ServiceException e) {
			goToPage(request, response, JSPPath.ERROR.getPath());
		}
	}
}
