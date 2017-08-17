package com.epam.kgd.victory.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.kgd.victory.dao.pool.ConnectionPool;
import com.epam.kgd.victory.dao.pool.exception.ConnectionPoolException;



public class ServletContextListenerImpl implements ServletContextListener{
	
	private static final Logger LOGGER = Logger.getLogger(ServletContextListenerImpl.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		String prefix = event.getServletContext().getRealPath("/");
	    String file = event.getServletContext().getInitParameter("log4j-config");
	    if(file != null) {
	      PropertyConfigurator.configure(prefix+file);
	      LOGGER.debug("Logger initialized");
	    }	
	    
	    int attemptsCounter = 3;
	    ConnectionPool connectionPool = null;
	    do {
	    	connectionPool = ConnectionPool.getInstance();
	    	
	    	if (connectionPool != null) {
	    		LOGGER.info("Connection pool has been initialized.");
	    	}
	    	
	    	attemptsCounter--;
	    } while (connectionPool == null && attemptsCounter != 0);
	    
	    if (connectionPool == null) {
	    	LOGGER.fatal("Error in initializing the connection pool!");
	    	throw new RuntimeException("Fatal Error! Connection pool can't be initialized!");
	    }
	    		
	}
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			if (connectionPool != null) {
				connectionPool.cleanConnectionPool();
			}
		} catch (ConnectionPoolException e) {
			LOGGER.error(e);
		}
		
	}

}