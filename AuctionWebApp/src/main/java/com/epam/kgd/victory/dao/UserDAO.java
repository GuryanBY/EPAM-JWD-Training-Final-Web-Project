package com.epam.kgd.victory.dao;

import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.dao.exception.DAOException;

public interface UserDAO extends BaseDAO<User> {

	User getUserByLoginAndPassword(String login, String password) throws DAOException;
	/** 
	 * Check is login and password in the storage
	 * 
	 * @param login
	 * @param password
	 * @throws DAOException
	 * @return true if exist in storage
	 * */
	boolean checkLoginAndPassword(String login, String password) throws DAOException;
	
	int takeUserIdByLogin(String login) throws DAOException;
	
	int takeUserIdByEmail(String email) throws DAOException;
   
}
