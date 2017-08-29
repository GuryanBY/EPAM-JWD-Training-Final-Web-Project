package com.epam.kgd.victory.service.impl;

import static com.epam.kgd.victory.service.util.Validation.*;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.dao.LotDAO;
import com.epam.kgd.victory.dao.UserDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.factory.DAOFactory;
import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
	private final static DAOFactory DAO_FACTORY = DAOFactory.getInstance();
	private final static UserDAO USER_DAO = DAO_FACTORY.getUserDAO();
	private final static LotDAO LOT_DAO = DAO_FACTORY.getLotDAO();

	private final static int USER_NOT_BLOCK_STATUS = 2;

	@Override
	public boolean isInStorage(String login, String password) throws ServiceException {

		if (!isLoginValid(login) || !isPasswordValid(password)) {
			throw new ServiceException("Invalid parameter");
		}
		try {
			return USER_DAO.checkLoginAndPassword(login, password);
		} catch (DAOException e) {
			throw new ServiceException("Can't check login and password", e);
		}

	}

	@Override
	public User takeUserByLoginAndPassword(String login, String password) throws ServiceException {

		if (!isLoginValid(login) || !isPasswordValid(password)) {
			throw new ServiceException("Invalid parameter");
		}
		try {
			return USER_DAO.getUserByLoginAndPassword(login, password);
		} catch (DAOException e) {
			throw new ServiceException("Can't take user by login and password", e);
		}
	}

	@Override
	public void registrateUser(String login, String password, String firstName, String lastName, String email,
			String phone) throws ServiceException {
		if (!isRegistrateFormValid(login, password, firstName, lastName, email, phone)) {
			throw new ServiceException("Invalid parameter");
		}

		LocalDateTime currentMoment = LocalDateTime.now();

		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setRegistrationDate(currentMoment);
		user.setStatusId(USER_NOT_BLOCK_STATUS);

		try {
			USER_DAO.add(user);
		} catch (DAOException e) {
			throw new ServiceException("Problem with adding a new user", e);
		}

	}

	@Override
	public boolean isLoginUnique(String login) throws ServiceException {
		if (!isLoginValid(login)) {
			throw new ServiceException("Invalid parameter");
		}
		try {
			return (USER_DAO.takeUserIdByLogin(login) == 0);
		} catch (DAOException e) {
			throw new ServiceException("Can't check loqin unique", e);
		}
	}

	@Override
	public boolean isEmailUnique(String email) throws ServiceException {
		if (!isEmailValid(email)) {
			throw new ServiceException("Invalid parameter");
		}
		try {
			return (USER_DAO.takeUserIdByEmail(email) == 0);
		} catch (DAOException e) {
			throw new ServiceException("Can't check email unique", e);
		}
	}

	@Override
	public void buyLotInBlitzAuction(int userId, int lotId) throws ServiceException {

		if (!isIdValid(userId) || !isIdValid(lotId)) {
			throw new ServiceException("Invalid parameter");
		}
		try {
			Lot lot = LOT_DAO.getById(lotId);
			lot.setBuyerId(userId);
			LocalDateTime currentMoment = LocalDateTime.now();
			lot.setBuyingDate(currentMoment);
			LOT_DAO.update(lotId, lot);

		} catch (DAOException e) {
			throw new ServiceException("Can't buy lot at blitz auction", e);
		}

	}

	@Override
	public void changeUserStatus(int userId) throws ServiceException {
		if (!isIdValid(userId)) {
			throw new ServiceException("Invalid parameter");
		}

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
			throw new ServiceException("Can't change user blocking status", e);
		}

	}

	@Override
	public int makeBid(int userId, String lotId, String newPrice) throws ServiceException {
		int result;
		if (!isBidValid(userId, lotId, newPrice)) {
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
			throw new ServiceException("Can't make bid", e);
		}
		return result;

	}

	@Override
	public List<User> getAllUsers(int offset, int numberOfRecords) throws ServiceException {
		if (!isNumberPositive(offset) || !isNumberPositive(numberOfRecords)) {
			throw new ServiceException("Invalid parameters");
		}
		List<User> result = null;
		try {
			result = USER_DAO.getAll(offset, numberOfRecords);
		} catch (DAOException e) {
			throw new ServiceException("Problem to take users with offset and number of users", e);
		}
		return result;
	}

	@Override
	public int getNumberOfAllUsers() throws ServiceException {
		int result = -1;
		try {
			result = USER_DAO.getNumberOfUsersInStorage();
		} catch (DAOException e) {
			throw new ServiceException("Problem to count number of all users", e);
		}
		return result;
	}

}
