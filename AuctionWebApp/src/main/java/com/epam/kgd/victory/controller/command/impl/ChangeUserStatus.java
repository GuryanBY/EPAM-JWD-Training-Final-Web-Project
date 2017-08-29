package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.checkSessionValidity;
import static com.epam.kgd.victory.controller.command.util.CommandUtil.goToPage;
import static com.epam.kgd.victory.controller.command.util.CommandUtil.takeUserRole;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.JSPPath;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.AdminService;
import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;

public class ChangeUserStatus implements Command {

	private static final Logger LOGGER = Logger.getLogger(ChangeUserStatus.class);
	private static final int ADMIN_ROLE_ID = 1;
	private final static String PARAM_NAME_USER_ID = "user_id";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LotService lotService = serviceFactory.getLotService();
		UserService userService = serviceFactory.getUserService();
		AdminService adminService = serviceFactory.getAdminService();
		boolean isSessionValid = checkSessionValidity(request);

		if (isSessionValid && takeUserRole(request) == ADMIN_ROLE_ID) {
			try {
				int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));
				userService.changeUserStatus(userId);

				User user = adminService.getUserById(userId);
				request.setAttribute("UserInfo", user);

				List<Lot> lotsWhereUserSeller = lotService.takeLotsBySellerId(userId);
				request.setAttribute("SellerLots", lotsWhereUserSeller);

				List<Lot> lotsWhereUserByer = lotService.takeLotsByBuyerId(userId);
				request.setAttribute("BuyerLots", lotsWhereUserByer);

				String pagePath = JSPPath.ADMIN_USER_DETAILS.getPath();
				goToPage(request, response, pagePath);
			} catch (ServiceException e) {
				LOGGER.error("Problem in ChangeUserStatus command", e);
				goToPage(request, response, JSPPath.ERROR.getPath());
			}
		} else {
			String pagePath = JSPPath.INDEX.getPath();
			goToPage(request, response, pagePath);
		}
	}
}
