package com.epam.kgd.victory.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.exception.ControllerException;

/** The Class Controller. */
public final class Controller extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5625464452754425320L;

	/** The Constant provider. */
	private final CommandProvider provider = new CommandProvider();

	/** The logger. */
	private static Logger LOGGER = Logger.getLogger(Controller.class);

	/** The Constant command. */
	private static final String PARAM_NAME_COMMAND = "command";

	/** Instantiates a new controller. */
	public Controller() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Process POST request.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlets exception
	 * @throws IOException signals that an Input/Output exception has occurred
	 *             
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName = request.getParameter(PARAM_NAME_COMMAND);
		try {
			Command command = provider.getCommand(commandName);
			command.execute(request, response);
		} catch (ControllerException e) {
			LOGGER.error("Can't do method", e);
		}

	}

}
