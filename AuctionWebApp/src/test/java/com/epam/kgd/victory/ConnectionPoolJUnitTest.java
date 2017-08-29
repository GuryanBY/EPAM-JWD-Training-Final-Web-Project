package com.epam.kgd.victory;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.kgd.victory.dao.pool.ConnectionPool;
import com.epam.kgd.victory.dao.pool.exception.ConnectionPoolException;



public class ConnectionPoolJUnitTest {
	
private static ConnectionPool connectionPool;
	
	@BeforeClass
	public static void connectionPoolInit() {
		connectionPool = ConnectionPool.getInstance();
	}
	
	
	@Test
	public void takeConnectionTest() throws SQLException, ConnectionPoolException {
		for (int i = 0; i < 100000; i++) {
			Connection connection = connectionPool.takeConnection();
			Assert.assertNotNull(connection);
			connection.close();
			
		}
	}
	
	@AfterClass
	public static void cleanConnectionPool() throws ConnectionPoolException {
		connectionPool.cleanConnectionPool();
		connectionPool = null;
	}

}
