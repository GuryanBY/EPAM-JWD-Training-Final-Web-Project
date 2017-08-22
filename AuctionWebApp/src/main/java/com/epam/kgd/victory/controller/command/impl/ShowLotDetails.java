package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.JSPPath;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;

public class ShowLotDetails implements Command {
	private static final int ADMIN_ROLE_ID = 1;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LotService lotService = serviceFactory.getLotService();

		boolean isSessionValid = checkSessionValidity(request);

		if (isSessionValid && takeUserRole(request) == ADMIN_ROLE_ID) {
			try {
				List<Lot> lots = lotService.takeAllLots();
				request.getSession().setAttribute("Lots", lots);
				
				String pagePath = JSPPath.ADMIN_LOT_DETAILS.getPath();
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
