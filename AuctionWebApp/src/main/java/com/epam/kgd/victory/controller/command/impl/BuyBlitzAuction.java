package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.checkSessionValidity;
import static com.epam.kgd.victory.controller.command.util.CommandUtil.getCurrentLocale;
import static com.epam.kgd.victory.controller.command.util.CommandUtil.goToPage;

import java.io.IOException;
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
import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;

public class BuyBlitzAuction implements Command {
	private static final Logger LOGGER = Logger.getLogger(BuyBlitzAuction.class);
	private final static String PARAM_NAME_LOT_ID = "lot_id";
	private final static int AUCTION_BLITZ_TYPE_ID = 3;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		LotService lotService = serviceFactory.getLotService();
		boolean isSessionValid = checkSessionValidity(request);

		if (isSessionValid) {
			try {
				User user = (User) request.getSession().getAttribute("User");
				int lotId = Integer.parseInt(request.getParameter(PARAM_NAME_LOT_ID));
				int userId = user.getId();
				userService.buyLotInBlitzAuction(userId, lotId);
				List<Lot> activeNotSoldLots = lotService.takeActiveNotSoldLotsByAucType(AUCTION_BLITZ_TYPE_ID);
				request.getSession().setAttribute("ActiveLots", activeNotSoldLots);
				request.setAttribute("BuyingAccept",
						LocaledMessage.getMessage("user.successbuying", getCurrentLocale(request)));
				/*String pagePath = JSPPath.USER_BLITZ_AUCTION.getPath();
				goToPage(request, response, pagePath);*/
				response.sendRedirect(JSPPath.SUCCESS_OPERATION.getPath());
				
			} catch (ServiceException | IOException e) {
				LOGGER.error("Problem in BuyBlitzAuction command", e);
				goToPage(request, response, JSPPath.ERROR.getPath());
			}
		} else {
			String pagePath = JSPPath.INDEX.getPath();
			goToPage(request, response, pagePath);
		}
	}

}
