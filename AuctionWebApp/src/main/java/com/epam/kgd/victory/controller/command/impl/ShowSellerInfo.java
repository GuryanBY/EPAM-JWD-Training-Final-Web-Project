package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.checkSessionValidity;
import static com.epam.kgd.victory.controller.command.util.CommandUtil.goToPage;

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
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;

public class ShowSellerInfo implements Command {

	private static final Logger LOGGER = Logger.getLogger(ShowSellerInfo.class);
	private final static String PARAM_NAME_LOT_ID = "lot_id";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LotService lotService = serviceFactory.getLotService();
		AdminService adminService = serviceFactory.getAdminService();
		boolean isSessionValid = checkSessionValidity(request);

		if (isSessionValid) {
			try {
				int lotId = Integer.parseInt(request.getParameter(PARAM_NAME_LOT_ID));
				Lot lot = lotService.getLotById(lotId);
				int sellerId = lot.getSellerId();
				User seller = adminService.getUserById(sellerId);

				request.setAttribute("seller", seller);
				request.setAttribute("lot", lot);

				goToPage(request, response, JSPPath.USER_SHOW_SELLER_INFO.getPath());

			} catch (ServiceException e) {
				LOGGER.error("Problem in ShowSellerInfo command", e);
				goToPage(request, response, JSPPath.ERROR.getPath());
			}
		} else {
			goToPage(request, response, JSPPath.INDEX.getPath());
		}

	}

}
