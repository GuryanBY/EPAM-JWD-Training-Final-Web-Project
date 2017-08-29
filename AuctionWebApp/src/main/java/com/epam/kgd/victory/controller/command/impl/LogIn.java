package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.JSPPath;
import com.epam.kgd.victory.controller.command.util.LocaledMessage;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.AdminService;
import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;

public class LogIn implements Command {

	private static final Logger LOGGER = Logger.getLogger(LogIn.class);
	private final static String PARAM_NAME_LOGIN = "login";
	private final static String PARAM_NAME_PASSWORD = "password";
	private final static int ROLE_ADMIN = 1;
	
	private final static int INTERNET_AUCTION_TYPE_ID = 1;
	private final static int ENGLISH_AUCTION_TYPE_ID = 2;
	private final static int BLITZ_AUCTION_TYPE_ID = 3;
	
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		LotService lotService = serviceFactory.getLotService();
		AdminService adminService = serviceFactory.getAdminService();
		HttpSession session = request.getSession();

		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);

		try {
			boolean isVeryfied = userService.isInStorage(login, password);

			if (isVeryfied) {
				User user = userService.takeUserByLoginAndPassword(login, password);

				if (user.getRoleId() == ROLE_ADMIN) {
					request.getSession().setAttribute("User", user);
					
					int numberOfUserRecords = userService.getNumberOfAllUsers();
					int numberOfLotRecords = lotService.numberOfAllLots();
					
					int numberOfInternetLots = adminService.countNumberOfLotsByType(INTERNET_AUCTION_TYPE_ID);
					int numberOfEnglandLots = adminService.countNumberOfLotsByType(ENGLISH_AUCTION_TYPE_ID);
					int numberOfBlitzLots = adminService.countNumberOfLotsByType(BLITZ_AUCTION_TYPE_ID);
					int numberOfBlockedUsers = adminService.countBlockedUsers();
					
					session.setAttribute("numberOfUsers", numberOfUserRecords);
					session.setAttribute("numberOfLots", numberOfLotRecords);
					session.setAttribute("internetLots", numberOfInternetLots);
					session.setAttribute("englandLots", numberOfEnglandLots);
					session.setAttribute("blitzLots", numberOfBlitzLots);
					session.setAttribute("numberOfBlockedUsers", numberOfBlockedUsers);
									
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
					response.sendRedirect(JSPPath.SUCCESS_LOGIN.getPath());
					
				}
			} else {
				request.setAttribute("invalidLogOrPass",
						LocaledMessage.getMessage("index.message.invalidLoginOrPass", getCurrentLocale(request)));
				String targetPage = JSPPath.INDEX.getPath();
				goToPage(request, response, targetPage);
			}
		} catch (ServiceException | IOException e) {
			LOGGER.error("Problem in LogIn command", e);
			goToPage(request, response, JSPPath.INDEX.getPath());
		}
	}
}
