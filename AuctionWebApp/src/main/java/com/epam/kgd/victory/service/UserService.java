package com.epam.kgd.victory.service;

import java.util.List;

import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.service.exception.ServiceException;

public interface UserService {
	boolean isInStorage(String login, String password) throws ServiceException;

	User takeUserByLoginAndPassword(String login, String password) throws ServiceException;

	void registrateUser(String login, String password, String firstName, String lastName, String email, String phone)
			throws ServiceException;

	boolean isLoginUnique(String login) throws ServiceException;

	boolean isEmailUnique(String email) throws ServiceException;

	void buyLotInBlitzAuction(int userId, int lotId) throws ServiceException;

	/** 
	 * Change current user status (blocked/unblocked) to opposite
	 * @param user id
	 * @throws ServiceException
	 * */
	void changeUserStatus(int userId) throws ServiceException;

	/**
	 * Make bid for lot
	 * 
	 * @param user id
	 * @param lot id
	 * @param price
	 * @return status of making bid (-2 - not valid parameters, -1 - new price <=
	 *         then old, 1 - new price > old)
	 * @throws ServiceException
	 */

	int makeBid(int userId, String lotId, String price) throws ServiceException;
	
	/**
	 * Gets all lots in persistence storage. Includes also users, admins, etc.
	 * 
	 * @param offset - offset of the query
	 * @param numberOfRecords - number of records in result of method work
	 * @return list of Users
	 * @throws ServiceException
	 *  */
	
	List<User> getAllUsers(int offset, int numberOfRecords) throws ServiceException;
	
	/**
	 * Counts number of users in persistent storage.
	 * @return numbers users
	 * @throws ServiceException
	 *  
	 *  */
	int getNumberOfAllUsers() throws ServiceException;
}
