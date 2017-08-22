package com.epam.kgd.victory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.bean.util.DateConverter;
import com.epam.kgd.victory.dao.UserDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.pool.ConnectionPool;
import com.epam.kgd.victory.dao.pool.exception.ConnectionPoolException;
import com.epam.kgd.victory.dao.query_builder.delete.DeleteQueryBuilder;
import com.epam.kgd.victory.dao.query_builder.delete.DeleteQueryBuilderImpl;
import com.epam.kgd.victory.dao.query_builder.select.SelectQueryBuilder;
import com.epam.kgd.victory.dao.query_builder.select.SelectQueryBuilderImpl;
import com.epam.kgd.victory.dao.query_builder.update.UpdateQueryBuilder;
import com.epam.kgd.victory.dao.query_builder.update.UpdateQueryBuilderImpl;

import static com.epam.kgd.victory.dao.query_builder.UserTable.*;

public class SQLUserDAO implements UserDAO {

	
	private final static SelectQueryBuilder SELECT_BUILDER = SelectQueryBuilderImpl.getInstance();
	private final static DeleteQueryBuilder DELETE_BUILDER = DeleteQueryBuilderImpl.getInstance(); 
	private final static UpdateQueryBuilder UPDATE_BUILDER = UpdateQueryBuilderImpl.getInstance();
	
	private static final String SQL_TAKE_ALL_USERS = SELECT_BUILDER.getSelectQuery(USER_ENTITY.getValue(), USER_TABLE_NAME.getValue(), BY_ROLE.getValue());
	private static final String SQL_TAKE_USER_BY_ID = SELECT_BUILDER.getSelectQuery(USER_ENTITY.getValue(), USER_TABLE_NAME.getValue(), BY_ID.getValue());
	private static final String SQL_TAKE_USER_BY_LOGIN_PASSWORD = SELECT_BUILDER.getSelectQuery(USER_ENTITY.getValue(), USER_TABLE_NAME.getValue(), BY_LOGIN.getValue(), BY_PASSWORD.getValue());
	private static final String SQL_LOGIN_PASSWORD_CHECK = SELECT_BUILDER.getSelectQuery(USER_ID.getValue(), USER_TABLE_NAME.getValue(), BY_LOGIN.getValue(), BY_PASSWORD.getValue());
	private static final String SQL_TAKE_USER_ID_BY_EMAIL = SELECT_BUILDER.getSelectQuery(USER_ID.getValue(), USER_TABLE_NAME.getValue(), BY_EMAIL.getValue());
	private static final String SQL_TAKE_USER_ID_BY_LOGIN = SELECT_BUILDER.getSelectQuery(USER_ID.getValue(), USER_TABLE_NAME.getValue(), BY_LOGIN.getValue());
		
	private static final String SQL_DELETE_USER = DELETE_BUILDER.getDeleteQuery(USER_TABLE_NAME.getValue(), BY_ID.getValue()); 
	private static final String SQL_CHANGE_USER = UPDATE_BUILDER.getUpdateQuery(USER_TABLE_NAME.getValue(), USER_UPDATE_ENTITY.getValue(),	BY_ID.getValue());		
	private static final String SQL_ADD_USER = "INSERT INTO `user` (`u_role_id`, `u_login`, `u_password`, `u_first_name`, `u_last_name`, `u_email`, `u_phone`, `u_registr_date`, `u_status`) VALUES ('2', ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

	@Override
	public List<User> getAll() throws DAOException {
		List<User> result = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_TAKE_ALL_USERS);

			result = getListUserFromResultSet(resultSet);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, statement, connection);
		}
		return result;
	}

	@Override
	public User getById(int userId) throws DAOException {
		User result = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_USER_BY_ID);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();

			result = getListUserFromResultSet(resultSet).get(0);
			
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}
		return result;
	}

	@Override
	public void add(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD_USER);

			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getPhone());
			preparedStatement.setString(7, DateConverter.convertDateToString(user.getRegistrationDate()));
			preparedStatement.setInt(8, user.getStatusId());

			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public void delete(int userId) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public void update(int userId, User updatedUser) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_CHANGE_USER);
			
			preparedStatement.setInt(1, updatedUser.getRoleId());
			preparedStatement.setString(2, updatedUser.getLogin());
			preparedStatement.setString(3, updatedUser.getPassword());
			preparedStatement.setString(4, updatedUser.getFirstName());
			preparedStatement.setString(5, updatedUser.getLastName());
			preparedStatement.setString(6, updatedUser.getEmail());
			preparedStatement.setString(7, updatedUser.getPhone());
			preparedStatement.setString(8, DateConverter.convertDateToString(updatedUser.getRegistrationDate()));
			preparedStatement.setInt(9, updatedUser.getStatusId());

			preparedStatement.setInt(10, userId);

			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public User getUserByLoginAndPassword(String login, String password) throws DAOException {
		User result = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_USER_BY_LOGIN_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			result = getListUserFromResultSet(resultSet).get(0);

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}
		return result;
	}

	public boolean checkLoginAndPassword(String login, String password) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_LOGIN_PASSWORD_CHECK);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			return resultSet.next();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}
	}

	@Override
	public int takeUserIdByLogin(String login) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_USER_ID_BY_LOGIN);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return 0;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public int takeUserIdByEmail(String email) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_USER_ID_BY_EMAIL);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return 0;
			}

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}
	}

	private void closeResources(ResultSet rs, Statement st, Connection con) throws DAOException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DAOException("Can't close ResultSet", e);
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DAOException("Can't close Statement", e);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DAOException("Can't close Connection", e);
			}
		}
	}

	/**
	 * Initialize User type entities base on ResultSet
	 * 
	 * @param resultSet - base for initializing
	 * @return ArrayList of User entities
	 * @throws SQLException
	 *  
	 *  */
	private ArrayList<User> getListUserFromResultSet(ResultSet resultSet) throws SQLException{
		ArrayList<User> result = new ArrayList<>();
		
		while (resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getInt(1));
			user.setRoleId(resultSet.getInt(2));
			user.setLogin(resultSet.getString(3));
			user.setPassword(resultSet.getString(4));
			user.setFirstName(resultSet.getString(5));
			user.setLastName(resultSet.getString(6));
			user.setEmail(resultSet.getString(7));
			user.setPhone(resultSet.getString(8));
			user.setRegistrationDate(DateConverter.convertStringToDate(resultSet.getString(9)));
			user.setStatusId(resultSet.getInt(10));
			
			result.add(user);
		}
		return result;
	}
}
