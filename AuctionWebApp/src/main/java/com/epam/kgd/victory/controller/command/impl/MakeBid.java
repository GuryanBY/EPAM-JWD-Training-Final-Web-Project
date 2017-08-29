package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.checkSessionValidity;
import static com.epam.kgd.victory.controller.command.util.CommandUtil.getCurrentLocale;
import static com.epam.kgd.victory.controller.command.util.CommandUtil.goToPage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.JSPPath;
import com.epam.kgd.victory.controller.command.util.LocaledMessage;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;

public class MakeBid implements Command {
	private static final Logger LOGGER = Logger.getLogger(MakeBid.class);
	private final static String PARAM_NAME_NEW_PRICE = "new_price";
	private final static String PARAM_NAME_LOT_ID = "lot_id";
	private final int BAD_PARAMETER_STATUS = -2;
	private final int BAD_NEW_PRICE_STATUS = -1;
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		boolean isSessionValid = checkSessionValidity(request);
		String pagePath = null;

		if (isSessionValid) {
			try {
				String price = request.getParameter(PARAM_NAME_NEW_PRICE);
				String lotId = request.getParameter(PARAM_NAME_LOT_ID);
				User user = (User) request.getSession().getAttribute("User");
				int userId = user.getId();
				int resultOfBid = userService.makeBid(userId, lotId, price);
				
				if(resultOfBid == BAD_PARAMETER_STATUS){
					request.setAttribute("invalidParameter", LocaledMessage.getMessage("eng.message.invalidprice", getCurrentLocale(request)));
					goToPage(request, response, JSPPath.USER_ENGLAND_AUCTION.getPath());
				}else if(resultOfBid == BAD_NEW_PRICE_STATUS){
					request.setAttribute("badNewPrice", LocaledMessage.getMessage("eng.message.badprice", getCurrentLocale(request)));
					goToPage(request, response, JSPPath.USER_ENGLAND_AUCTION.getPath());
				}else {
					pagePath = JSPPath.SUCCESS_OPERATION.getPath();
					response.sendRedirect(pagePath);
				}

			} catch (ServiceException | IOException e) {
				LOGGER.error("Problem in MakeBid command", e);
				goToPage(request, response, JSPPath.ERROR.getPath());
			}
		} else {
			pagePath = JSPPath.INDEX.getPath();
			goToPage(request, response, pagePath);
		}

	}

}
