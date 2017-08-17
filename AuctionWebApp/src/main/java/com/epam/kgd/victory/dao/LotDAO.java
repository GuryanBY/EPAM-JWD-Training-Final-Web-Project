package com.epam.kgd.victory.dao;

import java.util.List;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.dao.exception.DAOException;

public interface LotDAO extends BaseDAO<Lot> {
	
	
	List<Lot> getLotsBySellerId(int sellerId) throws DAOException;

	List<Lot> getLotsByBuyerId(int byerId) throws DAOException;

	/** 
	 * Active lots - not blocked lots
	 * Not sold lots - where buyer id is null
	 * 
	 * @param auction type id
	 * @return list of lots
	 * @throws DAOException
	 * 
	 * */
	List<Lot> takeActiveNotSoldLotsByAucType(int auctionTypeId) throws DAOException;

	/**
	 * Block/unblock lot
	 * @param lot id
	 * @param new status for updating
	 * @throws DAOException
	 *  
	 *  */
	void changeLotStatus(int lotId, int newStatusId) throws DAOException;

	/** 
	 * Get current blocking status
	 * @param lot id
	 * @return code of blocking status (1 - is blocked, 2 - unblocked)
	 * @throws DAOException
	 * */
	
	int getCurrentBlockingSatus(int lotId) throws DAOException;
	
	void updateLotPrice(int buyerId, int lotId, double price) throws DAOException;
}
