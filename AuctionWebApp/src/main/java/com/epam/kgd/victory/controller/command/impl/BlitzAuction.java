package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.JSPPath;
import com.epam.kgd.victory.controller.command.util.LocaledMessage;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;

public class BlitzAuction implements Command {
	private static final Logger LOGGER = Logger.getLogger(BlitzAuction.class);
	private final static int BLITZ_AUCTION_TYPE_ID = 3;
	private final static int BLOCKING_MARK = 1;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LotService lotService = serviceFactory.getLotService();

		boolean isSessionValid = checkSessionValidity(request);
		if (isSessionValid) {
			User user = (User) request.getSession().getAttribute("User");
			int currentUserStatus = user.getStatusId();
			boolean isUserBlocked = (currentUserStatus == BLOCKING_MARK);
			if (!isUserBlocked) {
				try {
					List<Lot> activeNotSoldLots = lotService.takeActiveNotSoldLotsByAucType(BLITZ_AUCTION_TYPE_ID);
					request.getSession().setAttribute("ActiveLots", activeNotSoldLots);

				} catch (ServiceException e) {
					LOGGER.error("Problem in BlitzAuction", e);
					goToPage(request, response, JSPPath.ERROR.getPath());
				}
			} else {
				request.setAttribute("UserIsBlocked",
						LocaledMessage.getMessage("user.blockingmessage", getCurrentLocale(request)));
			}
			String pagePath = JSPPath.USER_BLITZ_AUCTION.getPath();
			goToPage(request, response, pagePath);
		} else {
			String pagePath = JSPPath.INDEX.getPath();
			goToPage(request, response, pagePath);
		}

	}

}
