package com.epam.kgd.victory.service.impl;

import java.util.List;

import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.dao.UserDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.factory.DAOFactory;
import com.epam.kgd.victory.service.AdminService;
import com.epam.kgd.victory.service.exception.ServiceException;

public class AdminServiceImpl implements AdminService {
	private static final DAOFactory daoFactory = DAOFactory.getInstance();
	private static final UserDAO sqlUserDAO = daoFactory.getUserDAO();

	@Override
	public List<User> getAllUsers() throws ServiceException {
		
		List<User> result = null;

		try {
			result = sqlUserDAO.getAll();
		} catch (DAOException e) {
			throw new ServiceException("can't get all users from admin service", e);
		}

		return result;

	}
	@Override
	public User getUserById(int userId) throws ServiceException{
		User result = null;
		
		try {
			result = sqlUserDAO.getById(userId);
		} catch (DAOException e) {
			throw new ServiceException("can't get  user by id from admin service", e);
		}
		
		return result;
	}

}
