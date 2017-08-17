package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.checkSessionValidity;
import static com.epam.kgd.victory.controller.command.util.CommandUtil.goToPage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.CommandUtil;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;
import com.epam.kgd.victory.service.util.JSPPath;
import com.epam.kgd.victory.service.util.LocaledMessage;
import com.epam.kgd.victory.service.util.Validation;

public class MakeInternetBid implements Command{
	private final static String PARAM_NAME_NEW_PRICE = "price";
	private final static String PARAM_NAME_LOT_ID = "lot_id";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		boolean isSessionValid = checkSessionValidity(request);
		String pagePath = null;
		boolean isBidFormValid = Validation.validateBid(request.getParameter(PARAM_NAME_NEW_PRICE));
		if (!isBidFormValid) {
			request.setAttribute("invalidInputData",
					LocaledMessage.getMessage("eng.message.invalidInputData", CommandUtil.getCurrentLocale(request)));
			pagePath = JSPPath.USER_INTERNET_AUCTION.getPath();
			goToPage(request, response, pagePath);
		}

		if (isSessionValid && isBidFormValid ) {
			try {
				String price = request.getParameter(PARAM_NAME_NEW_PRICE);
				String lotId = request.getParameter(PARAM_NAME_LOT_ID);
				User user = (User) request.getSession().getAttribute("User");
				int userId = user.getId();
				userService.makeBid(userId, lotId, price);

				pagePath = JSPPath.SUCCESS_OPERATION.getPath();
				response.sendRedirect(pagePath);

			} catch (ServiceException | IOException e) {
				goToPage(request, response, JSPPath.ERROR.getPath());
			}
		} else {
			/*pagePath = JSPPath.INDEX.getPath();
			goToPage(request, response, pagePath);*/
		}

		
	}

}
