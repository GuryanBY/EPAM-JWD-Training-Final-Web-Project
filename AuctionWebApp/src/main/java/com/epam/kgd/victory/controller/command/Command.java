package com.epam.kgd.victory.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.kgd.victory.controller.exception.ControllerException;

/** Interface Command */
public interface Command {
	/**
	 * Execute request and gives response
	 * 
	 * @param request  the request
	 * @param response the response
	 * @throws ControllerException the controller exception
	 *             
	 */
	void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException;
}
