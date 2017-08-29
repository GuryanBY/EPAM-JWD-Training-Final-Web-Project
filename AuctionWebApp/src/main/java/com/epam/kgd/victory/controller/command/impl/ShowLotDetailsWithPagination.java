package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.goToPage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.JSPPath;
import com.epam.kgd.victory.controller.exception.ControllerException;
import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.factory.ServiceFactory;

public class ShowLotDetailsWithPagination implements Command {
	private static final Logger LOGGER = Logger.getLogger(ShowLotDetailsWithPagination.class);
	private final static String PARAM_NAME_PAGE = "page";
	private final static int RECORDS_PER_PAGE = 4;
	private final static int START_PAGE = 1;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LotService lotService = serviceFactory.getLotService();
		int page = START_PAGE;

		if (request.getParameter(PARAM_NAME_PAGE) != null) {
			page = Integer.parseInt(request.getParameter(PARAM_NAME_PAGE));
		}

		try {
			List<Lot> list = lotService.takeAllLots((page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
			int numberOfRecords = lotService.numberOfAllLots();
			int noOfPages = (int) Math.ceil(numberOfRecords * 1.0 / RECORDS_PER_PAGE);
			request.setAttribute("lotList", list);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);

			String targetPage = JSPPath.ADMIN_LOT_DETAILS_PAGINATION.getPath();
			goToPage(request, response, targetPage);

		} catch (ServiceException e) {
			LOGGER.error("Problem in ShowLotDetailsWithPagination command", e);
			goToPage(request, response, JSPPath.ERROR.getPath());
		}

	}

}
