package com.epam.kgd.victory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.bean.util.DateConverter;
import com.epam.kgd.victory.dao.LotDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.pool.ConnectionPool;
import com.epam.kgd.victory.dao.pool.exception.ConnectionPoolException;

public class SQLLotDAO implements LotDAO {

	private static final String SQL_TAKE_ALL_LOTS = "SELECT `l_id`, `l_user_id_buyer`, `l_user_id_seller`, `l_good_id`, `l_auction_type_id`,`l_name`, `l_good_amount`, `l_start_date`, `l_end_date`, `l_end_price`, `l_status_id`, `l_buy_date` FROM `lot`";
	private static final String SQL_TAKE_LOT_BY_ID = "SELECT `l_id`, `l_user_id_buyer`, `l_user_id_seller`, `l_good_id`, `l_auction_type_id`,`l_name`, `l_good_amount`, `l_start_date`, `l_end_date`, `l_end_price`, `l_status_id`, `l_buy_date` FROM `lot` WHERE `l_id` = ?";
	private static final String SQL_ADD_LOT = "INSERT INTO `lot` (`l_user_id_seller`, `l_good_id`, `l_auction_type_id`, `l_name`, `l_good_amount`, `l_start_date`, `l_end_date`, `l_end_price`, `l_buy_date`, `l_status_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_LOT = "DELETE FROM `lot` WHERE `l_id`=?";
	private static final String SQL_CHANGE_LOT = "UPDATE `lot` SET `l_user_id_buyer`=?, `l_user_id_seller`=?, `l_good_id`=?, `l_auction_type_id`=?, `l_name`=?, `l_good_amount`=?, `l_start_date`=?, `l_end_date`=?, `l_end_price`=?, `l_status_id`=?, `l_buy_date`=? WHERE `l_id`=?";
	private static final String SQL_TAKE_LOTS_BY_SELLER_ID = "SELECT `l_id`, `l_user_id_buyer`, `l_user_id_seller`, `l_good_id`, `l_auction_type_id`,`l_name`, `l_good_amount`, `l_start_date`, `l_end_date`, `l_end_price`, `l_status_id`, `l_buy_date` FROM `lot` WHERE `l_user_id_seller` = ?";
	private static final String SQL_TAKE_LOTS_BY_BUYER_ID = "SELECT `l_id`, `l_user_id_buyer`, `l_user_id_seller`, `l_good_id`, `l_auction_type_id`,`l_name`, `l_good_amount`, `l_start_date`, `l_end_date`, `l_end_price`, `l_status_id`, `l_buy_date` FROM `lot` WHERE `l_user_id_buyer` = ?";
	private static final String SQL_TAKE_ACTIVE_LOTS_BY_TYPE = "SELECT `l_id`, `l_user_id_buyer`, `l_user_id_seller`, `l_good_id`, `l_auction_type_id`,`l_name`, `l_good_amount`, `l_start_date`, `l_end_date`, `l_end_price`, `l_status_id`,`l_buy_date`  FROM `lot` WHERE `l_status_id`<> '1' AND  `l_auction_type_id`=? AND `l_buy_date`  IS NULL;";
	private static final String SQL_CHANGE_LOT_STATUS = "UPDATE `lot` SET `l_status_id`=? WHERE `l_id`=?;";
	private static final String SQL_TAKE_LOT_STATUS = "SELECT `l_status_id` FROM `lot` WHERE `l_id`=?;";
	private static final String SQL_UPDATE_LOT_PRICE = "UPDATE `lot` SET `l_user_id_buyer`=?, `l_end_price`=? WHERE `l_id`=?;";
	private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

	@Override
	public List<Lot> getAll() throws DAOException {
		List<Lot> result = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = CONNECTION_POOL.takeConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_TAKE_ALL_LOTS);
			
			result = getListLotFromResultSet(resultSet);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, statement, connection);
		}

		return result;
	}

	@Override
	public Lot getById(int lotId) throws DAOException {
		Lot result = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_LOT_BY_ID);
			preparedStatement.setInt(1, lotId);
			resultSet = preparedStatement.executeQuery();

			result = getListLotFromResultSet(resultSet).get(0);
		

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}
		return result;
	}

	@Override
	public void add(Lot lot) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD_LOT);
			preparedStatement.setInt(1, lot.getSellerId());
			preparedStatement.setInt(2, lot.getGoodId());
			preparedStatement.setString(3, lot.getAuctionTypeId());
			preparedStatement.setString(4, lot.getLotName());
			preparedStatement.setInt(5, lot.getGoodAmount());
			preparedStatement.setString(6, DateConverter.convertDateToString(lot.getStartSellingDate()));
			preparedStatement.setString(7, DateConverter.convertDateToString(lot.getEndSellingDate()));
			preparedStatement.setDouble(8, lot.getEndPrice());
			preparedStatement.setString(9, DateConverter.convertDateToString(lot.getBuyingDate()));
			preparedStatement.setInt(10, lot.getStatusId());
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}
	}

	@Override
	public void delete(int lotId) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_LOT);
			preparedStatement.setInt(1, lotId);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public void update(int lotId, Lot updatedLot) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_CHANGE_LOT);
			preparedStatement.setInt(1, updatedLot.getBuyerId());
			preparedStatement.setInt(2, updatedLot.getSellerId());
			preparedStatement.setInt(3, updatedLot.getGoodId());
			preparedStatement.setString(4, updatedLot.getAuctionTypeId());
			preparedStatement.setString(5, updatedLot.getLotName());
			preparedStatement.setInt(6, updatedLot.getGoodAmount());
			preparedStatement.setString(7, DateConverter.convertDateToString(updatedLot.getStartSellingDate()));
			preparedStatement.setString(8, DateConverter.convertDateToString(updatedLot.getEndSellingDate()));
			preparedStatement.setDouble(9, updatedLot.getEndPrice());
			preparedStatement.setInt(10, updatedLot.getStatusId());
			preparedStatement.setString(11, DateConverter.convertDateToString(updatedLot.getBuyingDate()));
			preparedStatement.setInt(12, lotId);

			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

	}

	@Override
	public List<Lot> getLotsBySellerId(int sellerId) throws DAOException {
		List<Lot> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_LOTS_BY_SELLER_ID);
			preparedStatement.setInt(1, sellerId);
			resultSet = preparedStatement.executeQuery();

			result = getListLotFromResultSet(resultSet);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

		return result;
	}

	@Override
	public List<Lot> getLotsByBuyerId(int buyerId) throws DAOException {
		List<Lot> result =null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_LOTS_BY_BUYER_ID);
			preparedStatement.setInt(1, buyerId);
			resultSet = preparedStatement.executeQuery();

			result = getListLotFromResultSet(resultSet);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

		return result;
	}
	
	@Override
	public List<Lot> takeActiveNotSoldLotsByAucType(int auctionTypeId) throws DAOException {
		List<Lot> result = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_ACTIVE_LOTS_BY_TYPE);
			preparedStatement.setInt(1, auctionTypeId);
			resultSet = preparedStatement.executeQuery();

			result = getListLotFromResultSet(resultSet);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}

		return result;
	}

	@Override
	public void changeLotStatus(int lotId, int newStatusId) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_CHANGE_LOT_STATUS);
			preparedStatement.setInt(1, newStatusId);
			preparedStatement.setInt(2, lotId);

			preparedStatement.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}
	}

	@Override
	public int getCurrentBlockingSatus(int lotId) throws DAOException {
		int result = 2;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_LOT_STATUS);
			preparedStatement.setInt(1, lotId);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();

			result = resultSet.getInt(1);
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			closeResources(resultSet, preparedStatement, connection);
		}
		return result;

	}

	@Override
	public void updateLotPrice(int buyerId, int lotId, double price) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = CONNECTION_POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE_LOT_PRICE);
			preparedStatement.setInt(1, buyerId);
			preparedStatement.setDouble(2, price);
			preparedStatement.setInt(3, lotId);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException | ConnectionPoolException e) {
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
	 * Initialize Lot type entities base on ResultSet
	 * 
	 * @param resultSet - base for initializing
	 * @return ArrayList of Lot entities
	 * @throws SQLException
	 * 
	 *  
	 *  */
    private ArrayList<Lot> getListLotFromResultSet(ResultSet resultSet) throws SQLException{
    	ArrayList<Lot> result = new ArrayList<>();
    	
    	while (resultSet.next()) {
			Lot lot = new Lot();
			lot.setId(resultSet.getInt(1));
			lot.setBuyerId(resultSet.getInt(2));
			lot.setSellerId(resultSet.getInt(3));
			lot.setGoodId(resultSet.getInt(4));
			lot.setAuctionTypeId(resultSet.getString(5));
			lot.setLotName(resultSet.getString(6));
			lot.setGoodAmount(resultSet.getInt(7));
			lot.setStartSellingDate(DateConverter.convertStringToDate(resultSet.getString(8)));
			lot.setEndSellingDate(DateConverter.convertStringToDate(resultSet.getString(9)));
			lot.setEndPrice(resultSet.getDouble(10));
			lot.setStatusId(resultSet.getInt(11));
			lot.setBuyingDate(DateConverter.convertStringToDate(resultSet.getString(12)));

			result.add(lot);
		}
      	return result;
    }
}
