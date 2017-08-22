package com.epam.kgd.victory.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setAttribute("locale", "en");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
	}
	

}
