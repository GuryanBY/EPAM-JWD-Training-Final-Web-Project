package com.epam.kgd.victory.service.impl;

import java.time.LocalDateTime;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.dao.LotDAO;
import com.epam.kgd.victory.dao.UserDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.factory.DAOFactory;
import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.exception.ServiceException;
import com.epam.kgd.victory.service.util.Validation;

public class UserServiceImpl implements UserService {
	private final static DAOFactory DAO_FACTORY = DAOFactory.getInstance();
	private final static UserDAO USER_DAO = DAO_FACTORY.getUserDAO();
	private final static LotDAO LOT_DAO = DAO_FACTORY.getLotDAO();

	@Override
	public boolean isInStorage(String login, String password) throws ServiceException {

		try {
			return USER_DAO.checkLoginAndPassword(login, password);
		} catch (DAOException e) {
			throw new ServiceException("can't check login and password", e);
		}

	}

	@Override
	public User takeUserByLoginAndPassword(String login, String password) throws ServiceException {
		try {
			return USER_DAO.getUserByLoginAndPassword(login, password);
		} catch (DAOException e) {
			throw new ServiceException("can't take user by login and password", e);
		}
	}

	@Override
	public void registrateUser(String login, String password, String firstName, String lastName, String email,
			String phone) throws ServiceException {
		LocalDateTime currentMoment = LocalDateTime.now();
		int notBlockedStatus = 2;
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setRegistrationDate(currentMoment);
		user.setStatusId(notBlockedStatus);

		try {
			USER_DAO.add(user);
		} catch (DAOException e) {
			throw new ServiceException("Problem with adding a new user", e);
		}

	}

	@Override
	public boolean isLoginUnique(String login) throws ServiceException {
		try {
			return (USER_DAO.takeUserIdByLogin(login) == 0);
		} catch (DAOException e) {
			throw new ServiceException("can't check loqin unique", e);
		}
	}

	@Override
	public boolean isEmailUnique(String email) throws ServiceException {
		try {
			return (USER_DAO.takeUserIdByEmail(email) == 0);
		} catch (DAOException e) {
			throw new ServiceException("can't check email unique", e);
		}
	}

	@Override
	public void buyLotInBlitzAuction(int userId, int lotId) throws ServiceException {
		try {
			Lot lot = LOT_DAO.getById(lotId);
			lot.setBuyerId(userId);
			LocalDateTime currentMoment = LocalDateTime.now();
			lot.setBuyingDate(currentMoment);
			LOT_DAO.update(lotId, lot);

		} catch (DAOException e) {
			throw new ServiceException("can't by lot at blitz auction", e);
		}

	}

	@Override
	public void changeUserStatus(int userId) throws ServiceException {
		try {
			int blockedStatus = 1;
			int freeStatus = 2;
			User user = USER_DAO.getById(userId);
			int currentUserStatus = user.getStatusId();

			if (currentUserStatus == blockedStatus) {
				user.setStatusId(freeStatus);
			} else {
				user.setStatusId(blockedStatus);
			}
			USER_DAO.update(userId, user);

		} catch (DAOException e) {
			throw new ServiceException("can't change user blocking status", e);
		}

	}

	@Override
	public int makeBid(int userId, String lotId, String newPrice) throws ServiceException {
		int result;
		boolean isNewPriceValid = Validation.validateBid(newPrice);
		if (!isNewPriceValid) {
			result = -2;
			return result;
		}
		Lot lot = null;
		int lotIdint = Integer.parseInt(lotId);
		double newPriceDouble = Double.parseDouble(newPrice.replace(',', '.'));
		
		try {
			lot = LOT_DAO.getById(lotIdint);
			double currentPrice = lot.getEndPrice();

			if (newPriceDouble > currentPrice) {
				LOT_DAO.updateLotPrice(userId, lotIdint, newPriceDouble);
				result = 1;
			} else {
				result = -1;
			}
		} catch (DAOException e) {
			throw new ServiceException("can't make bid", e);
		}
		return result;

	}
	

}
