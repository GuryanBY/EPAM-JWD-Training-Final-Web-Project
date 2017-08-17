package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.checkSessionValidity;
import static com.epam.kgd.victory.controller.command.util.CommandUtil.getCurrentLocale;
import static com.epam.kgd.victory.controller.command.util.CommandUtil.goToPage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;
import com.epam.kgd.victory.service.util.JSPPath;
import com.epam.kgd.victory.service.util.LocaledMessage;

public class InternetAuction implements Command{
	private final static int INTERNET_AUCTION_TYPE_ID = 1;
	private final static int BLOCKING_MARK = 1;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LotService lotService = serviceFactory.getLotService();
		boolean isSessionValid = checkSessionValidity(request);
		if (isSessionValid) {User user = (User) request.getSession().getAttribute("User");
		int currentUserStatus = user.getStatusId();
		boolean isUserBlocked = (currentUserStatus == BLOCKING_MARK);
		if (!isUserBlocked) {
			try {
				List<Lot> activeNotSoldLots = lotService.takeActiveNotSoldLotsByAucType(INTERNET_AUCTION_TYPE_ID);
				request.getSession().setAttribute("ActiveInternetLots", activeNotSoldLots);

			} catch (ServiceException e) {
				goToPage(request, response, JSPPath.ERROR.getPath());
			}
		} else {
			request.setAttribute("UserIsBlocked",
					LocaledMessage.getMessage("user.blockingmessage", getCurrentLocale(request)));
		}
		String pagePath = JSPPath.USER_INTERNET_AUCTION.getPath();
		goToPage(request, response, pagePath);
	} else {
		String pagePath = JSPPath.INDEX.getPath();
		goToPage(request, response, pagePath);
	}
		
	}

}
