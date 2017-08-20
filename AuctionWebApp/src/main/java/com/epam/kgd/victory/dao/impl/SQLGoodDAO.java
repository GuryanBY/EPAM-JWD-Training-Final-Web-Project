package com.epam.kgd.victory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.kgd.victory.bean.Good;
import com.epam.kgd.victory.dao.GoodDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.pool.ConnectionPool;
import com.epam.kgd.victory.dao.pool.exception.ConnectionPoolException;

public class SQLGoodDAO implements GoodDAO {

	private static final String SQL_TAKE_ALL_GOODS = "SELECT `g_id`, `g_category_id`, `g_condition_id`, `g_name`, `g_description`, `g_price` FROM `good`";
	private static final String SQL_TAKE_GOOD_BY_ID = "SELECT `g_id`, `g_category_id`, `g_condition_id`, `g_name`, `g_description`, `g_price` FROM `good` WHERE `g_id`=?";
	private static final String SQL_ADD_GOOD = "INSERT INTO `good` ( `g_category_id`, `g_condition_id`, `g_name`, `g_description`, `g_price`) VALUES ( ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_GOOD = "DELETE FROM `good` WHERE `g_id`=?";
	private static final String SQL_CHANGE_GOOD = "UPDATE `good` SET `g_category_id`=?, `g_condition_id`=?, `g_name`=?, `g_description`=?, `g_price`=?  WHERE `g_id`=?";

	private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

	@Override
	public List<Good> getAll() throws DAOException, RuntimeException {
		List<Good> result = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = CONNECTION_POOL.takeConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_TAKE_ALL_GOODS);

			while (resultSet.next()) {
				Good good = new Good();
				good.setId(resultSet.getInt(1));
				good.setCategoryId(resultSet.getInt(2));
				good.setConditionId(resultSet.getInt(3));
				good.setName(resultSet.getString(4));
				good.setDescription(resultSet.getString(5));
				good.setStartPrice(resultSet.getDouble(6));

				result.add(good);

			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, statement, connection);
		}
		return result;
	}

	@Override
	public Good getById(int goodId) throws DAOException {
		Good result = new Good();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_GOOD_BY_ID);
			preparedStatement.setInt(1, goodId);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			result.setId(resultSet.getInt(1));
			result.setCategoryId(resultSet.getInt(2));
			result.setConditionId(resultSet.getInt(3));
			result.setName(resultSet.getString(4));
			result.setDescription(resultSet.getString(5));
			result.setStartPrice(resultSet.getDouble(6));

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

		return result;
	}

	@Override
	public void add(Good good) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD_GOOD);
			preparedStatement.setInt(1, good.getCategoryId());
			preparedStatement.setInt(2, good.getConditionId());
			preparedStatement.setString(3, good.getName());
			preparedStatement.setString(4, good.getDescription());
			preparedStatement.setDouble(5, good.getStartPrice());

			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public void delete(int goodId) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_GOOD);
			preparedStatement.setInt(1, goodId);
			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public void update(int goodId, Good updatedGood) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_CHANGE_GOOD);

			preparedStatement.setInt(1, updatedGood.getCategoryId());
			preparedStatement.setInt(2, updatedGood.getConditionId());
			preparedStatement.setString(3, updatedGood.getName());
			preparedStatement.setString(4, updatedGood.getDescription());
			preparedStatement.setDouble(5, updatedGood.getStartPrice());
			preparedStatement.setInt(6, goodId);

			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public int addGood(Good good) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int idAfterInsertInDB = -1;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD_GOOD, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, good.getCategoryId());
			preparedStatement.setInt(2, good.getConditionId());
			preparedStatement.setString(3, good.getName());
			preparedStatement.setString(4, good.getDescription());
			preparedStatement.setDouble(5, good.getStartPrice());

			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next()) {
				idAfterInsertInDB = resultSet.getInt(1);
			}
			return idAfterInsertInDB;
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

}
