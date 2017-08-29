package com.epam.kgd.victory.controller.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.JSPPath;
import com.epam.kgd.victory.controller.command.util.LocaledMessage;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.*;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.GoodService;
import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;
import com.epam.kgd.victory.service.util.Validation;

public class AddGood implements Command {
	private static final Logger LOGGER = Logger.getLogger(AddGood.class);
	private final static String PARAM_NAME_CATEGORY = "category";
	private final static String PARAM_NAME_CONDITION = "condition";
	private final static String PARAM_NAME_GOOD_NAME = "name";
	private final static String PARAM_NAME_DESCRIPTION = "description";
	private final static String PARAM_NAME_START_PRICE = "start_price";
	private final static String PARAM_NAME_AUCTION_TYPE_ID = "auction_type";
	private final static String PARAM_NAME_LOT_SELLING_DURATION = "duration";
	private final static String PARAM_NAME_GOOD_AMOUNT = "good_amount";
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		GoodService goodService = serviceFactory.getGoodService();
		LotService lotService = serviceFactory.getLotService();

		String categoryId = request.getParameter(PARAM_NAME_CATEGORY);
		String conditionId = request.getParameter(PARAM_NAME_CONDITION);
		String name = request.getParameter(PARAM_NAME_GOOD_NAME);
		String description = request.getParameter(PARAM_NAME_DESCRIPTION);
		String startPrice = request.getParameter(PARAM_NAME_START_PRICE);
		String auctionTypeId = request.getParameter(PARAM_NAME_AUCTION_TYPE_ID);
		String lotSellingDuration = request.getParameter(PARAM_NAME_LOT_SELLING_DURATION);
		String goodAmount = request.getParameter(PARAM_NAME_GOOD_AMOUNT);

		boolean isDataFromAddGoodFormValid = Validation.isAddGoodDataValid(categoryId, conditionId, name, description,
				startPrice);
		if (isDataFromAddGoodFormValid) {
			try {
				User user = (User) request.getSession().getAttribute("User");
				int sellerId = user.getId();
				int goodId = goodService.addGood(categoryId, conditionId, name, description, startPrice);
				lotService.addLot(sellerId, goodId, auctionTypeId, name, goodAmount, lotSellingDuration, startPrice);

				String targetPage = JSPPath.SUCCESS_OPERATION.getPath();
				response.sendRedirect(targetPage);

			} catch (ServiceException | IOException e) {
				LOGGER.error("Problem in AddGood command", e);
				goToPage(request, response, JSPPath.ERROR.getPath());
			}
		} else {
			request.setAttribute("invalidInputDataAddGood",
					LocaledMessage.getMessage("add.good.invalidInputData", getCurrentLocale(request)));
			String targetPage = JSPPath.ADD_GOOD.getPath();
			goToPage(request, response, targetPage);

		}

	}

}
