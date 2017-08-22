package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.checkSessionValidity;
import static com.epam.kgd.victory.controller.command.util.CommandUtil.goToPage;
import static com.epam.kgd.victory.controller.command.util.CommandUtil.takeUserRole;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.JSPPath;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.AdminService;
import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;

public class DeleteLot implements Command {

	private final static String PARAM_NAME_LOT_ID = "lot_id";
	private final static String PARAM_NAME_USER_ID = "user_id";
	private static final int ADMIN_ROLE_ID = 1;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();
		LotService lotService = serviceFactory.getLotService();

		boolean isSessionValid = checkSessionValidity(request);

		if (isSessionValid && takeUserRole(request) == ADMIN_ROLE_ID) {

			try {
				lotService.deleteLot(Integer.parseInt(request.getParameter(PARAM_NAME_LOT_ID)));
				int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));
				User user = adminService.getUserById(userId);
				request.setAttribute("UserInfo", user);
				List<Lot> lotsWhereUserSeller = lotService.takeLotsBySellerId(userId);
				request.setAttribute("SellerLots", lotsWhereUserSeller);
				List<Lot> lotsWhereUserByer = lotService.takeLotsByBuyerId(userId);
				request.setAttribute("BuyerLots", lotsWhereUserByer);
				goToPage(request, response, JSPPath.ADMIN_USER_DETAILS.getPath());
			} catch (NumberFormatException | ServiceException e) {
				goToPage(request, response, JSPPath.ERROR.getPath());
			}
		} else {
			String pagePath = JSPPath.INDEX.getPath();
			goToPage(request, response, pagePath);
		}
	}
}
