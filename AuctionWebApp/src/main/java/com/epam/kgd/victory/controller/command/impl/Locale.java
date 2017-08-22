package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.goToPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.JSPPath;
import com.epam.kgd.victory.controller.exception.ControllerException;

public class Locale implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		HttpSession session = request.getSession(true);
		session.setAttribute("locale", request.getParameter("locale"));

		goToPage(request, response, JSPPath.INDEX.getPath());

	}

}
