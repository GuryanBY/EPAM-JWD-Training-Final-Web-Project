package com.epam.kgd.victory.service.impl;

import java.util.List;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.dao.LotDAO;
import com.epam.kgd.victory.dao.UserDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.factory.DAOFactory;
import com.epam.kgd.victory.service.AdminService;
import com.epam.kgd.victory.service.exception.ServiceException;
import static com.epam.kgd.victory.service.util.Validation.*;

public class AdminServiceImpl implements AdminService {
	private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
	private static final UserDAO USER_DAO = DAO_FACTORY.getUserDAO();
	private static final LotDAO LOT_DAO = DAO_FACTORY.getLotDAO();

	@Override
	public List<User> getAllUsers() throws ServiceException {

		List<User> result = null;

		try {
			result = USER_DAO.getAll();
		} catch (DAOException e) {
			throw new ServiceException("Can't get all users from admin service", e);
		}

		return result;

	}

	@Override
	public User getUserById(int userId) throws ServiceException {

		if (!isIdValid(userId)) {
			throw new ServiceException("Invalid parameter");
		}
		User result = null;

		try {
			result = USER_DAO.getById(userId);
		} catch (DAOException e) {
			throw new ServiceException("Can't get  user by id from admin service", e);
		}

		return result;
	}

	@Override
	public int countNumberOfLotsByType(int auctionTypeId) throws ServiceException {
		if (!isAuctionTypeIdValid(auctionTypeId)) {
			throw new ServiceException("Invalid parameter");
		}
		int result = 0;
		try {
			List<Lot> allLots = LOT_DAO.getAll();
			for (Lot lot : allLots) {
				if (Integer.parseInt(lot.getAuctionTypeId()) == auctionTypeId) {
					result += 1;
				}
			}
		} catch (DAOException e) {
			throw new ServiceException("Can't count number of lots by auction type id from admin service", e);
		}

		return result;
	}

	@Override
	public int countBlockedUsers() throws ServiceException {
		int result = 0;
		int userBlockedStatus = 1;
		try {
			List<User> allUsers = USER_DAO.getAll();
			for (User user : allUsers) {
				if (user.getStatusId() == userBlockedStatus) {
					result += 1;
				}
			}
		} catch (DAOException e) {
			throw new ServiceException("Can't count number blocked users from admin service", e);
		}

		return result;
	}

}
