package com.epam.kgd.victory.dao;

import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.dao.exception.DAOException;
/**
 * DAO interface for work (mainly) with User type entities
 */
public interface UserDAO extends BaseDAO<User> {
	/**
     * Takes user entity according to login and password
     * 
     * @param login - user's login
     * @param password - user's password
     *  
     * @return user entity. If no exist user with specified login and password returns null
     * 
     * @throws DAOException
     *  */
	User getUserByLoginAndPassword(String login, String password) throws DAOException;
	/** 
	 * Check is login and password in the storage
	 * 
	 * @param login - user's login
	 * @param password - user's password
	 * @throws DAOException
	 * @return returns true if and only if exist user with given login and password in storage
	 * */
	boolean checkLoginAndPassword(String login, String password) throws DAOException;
	
	/**
	 * Takes user id from storage by specified login.
	 * If no exist user with given login, method returns 0
	 * 
	 * @param login - user's login
	 * @throws DAOException
	 * @return user's id
	 *  */	
	int takeUserIdByLogin(String login) throws DAOException;
	
	/**
	 * Takes user id from storage by specified email.
	 * If no exist user with given email, method returns 0
	 * 
	 * @param email - user's email
	 * @throws DAOException
	 * @return user's id
	 *  */	
	int takeUserIdByEmail(String email) throws DAOException;
   
}
