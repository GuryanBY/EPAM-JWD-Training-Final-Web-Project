package com.epam.kgd.victory.controller.command.impl;

import static com.epam.kgd.victory.controller.command.util.CommandUtil.goToPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.util.JSPPath;
import com.epam.kgd.victory.controller.exception.ControllerException;

public class LogOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

		boolean isSessionAlive = (request.getSession().getAttribute("User") != null);
		if (isSessionAlive) {
			request.getSession().invalidate();
		}

		String targetPage = JSPPath.INDEX.getPath();
		goToPage(request, response, targetPage);

	}

}
