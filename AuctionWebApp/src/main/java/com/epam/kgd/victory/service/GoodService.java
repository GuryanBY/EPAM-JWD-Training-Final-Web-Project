package com.epam.kgd.victory.service;

import com.epam.kgd.victory.service.exception.ServiceException;

public interface GoodService {
	/**
	 * Add good in persistence storage
	 * 
	 * @param category id
	 * @param condition id
	 * @param name
	 * @param description
	 * @param start price
	 * 
	 * @return the id of good after insert in persistence storage
	 * @throws ServiceException
	 */
	int addGood(String categoryId, String conditionId, String name, String description, String startPrice)
			throws ServiceException;

}
