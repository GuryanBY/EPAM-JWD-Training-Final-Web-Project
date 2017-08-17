package com.epam.kgd.victory.dao;

import java.util.List;

import com.epam.kgd.victory.bean.Good;
import com.epam.kgd.victory.dao.exception.DAOException;

public interface GoodDAO extends BaseDAO<Good> {

	List<Good> goodsInLimit(int minPrice, int maxPrice) throws DAOException;

	/**
	 * Add good in storage
	 * 
	 * @param good entity
	 * @return autogenerated id after adding in storage
	 * @throws DAOException
	 */
	int addGood(Good good) throws DAOException;
}