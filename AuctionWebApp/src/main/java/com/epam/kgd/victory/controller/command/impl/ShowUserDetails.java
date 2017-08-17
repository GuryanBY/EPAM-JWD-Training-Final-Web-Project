package com.epam.kgd.victory.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.CommandUtil;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.*;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.AdminService;
import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;
import com.epam.kgd.victory.service.util.JSPPath;

public class ShowUserDetails implements Command {
	private static final int ADMIN_ROLE_ID = 1;
	private final static String PARAM_NAME_USER_ID = "user_id";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();
		LotService lotService = serviceFactory.getLotService();
		boolean isSessionValid = checkSessionValidity(request);

		if (isSessionValid && takeUserRole(request) == ADMIN_ROLE_ID) {

			try {
				int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));

				User user = adminService.getUserById(userId);
				request.setAttribute("UserInfo", user);

				List<Lot> lotsWhereUserSeller = lotService.takeLotsBySellerId(userId);
				request.setAttribute("SellerLots", lotsWhereUserSeller);

				List<Lot> lotsWhereUserByer = lotService.takeLotsByBuyerId(userId);
				request.setAttribute("BuyerLots", lotsWhereUserByer);

				String pagePath = JSPPath.ADMIN_USER_DETAILS.getPath();
				CommandUtil.goToPage(request, response, pagePath);

			} catch (ServiceException e) {
				goToPage(request, response, JSPPath.ERROR.getPath());
			}

		} else {
			String pagePath = JSPPath.INDEX.getPath();
			goToPage(request, response, pagePath);

		}

	}

}
