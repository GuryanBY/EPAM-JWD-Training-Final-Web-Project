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

import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;

public class AccountMainPage implements Command {
	private static final int USER_ROLE_ID = 2;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LotService lotService = serviceFactory.getLotService();
		boolean isSessionValid = checkSessionValidity(request);

		if (isSessionValid && takeUserRole(request) == USER_ROLE_ID) {
			try {
				User user = (User) request.getSession().getAttribute("User");
				List<Lot> lots = lotService.takeLotsBySellerId(user.getId());
				request.getSession().setAttribute("Lots", lots);

				List<Lot> boughtLots = lotService.takeLotsByBuyerId(user.getId());
				request.getSession().setAttribute("BoughtLots", boughtLots);

				String pagePath = JSPPath.USER_MAIN.getPath();
				goToPage(request, response, pagePath);

			} catch (ServiceException e) {
				goToPage(request, response, JSPPath.ERROR.getPath());
			}
		} else {
			String pagePath = JSPPath.INDEX.getPath();
			goToPage(request, response, pagePath);
		}

	}

}
