package com.epam.kgd.victory.service;

import java.util.List;

import com.epam.kgd.victory.bean.User;
import com.epam.kgd.victory.service.exception.ServiceException;

public interface AdminService {

	List<User> getAllUsers() throws ServiceException;

	User getUserById(int userId) throws ServiceException;
	
	int countNumberOfLotsByType(int auctionTypeId) throws ServiceException;
	
	int countBlockedUsers() throws ServiceException;

}
