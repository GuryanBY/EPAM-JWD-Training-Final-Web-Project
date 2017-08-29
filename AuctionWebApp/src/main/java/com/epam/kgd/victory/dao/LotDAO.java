package com.epam.kgd.victory.dao;

import java.util.List;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.dao.exception.DAOException;
/**
 * DAO interface for work (mainly) with Lot type entities
 */
public interface LotDAO extends BaseDAO<Lot> {
	
	/**
	 * Get all lots, where seller id is the same with given id.
	 * Return also sold and not sold, blocked and unblocked lots
	 * 
	 *  @param sellerId - id of seller, which we want to collect lots
	 *  @return list of lots
	 *  @throws DAOException
	 *  */
	List<Lot> getLotsBySellerId(int sellerId) throws DAOException;
	
	/**
	 * Get all lots, where buyer id is the same with given id.
	 * Return also sold and not sold, blocked and unblocked lots
	 * 
	 *  @param byerId - id of buyer, which we want to collect lots
	 *  @return list of lots
	 *  @throws DAOException
	 *  */
	List<Lot> getLotsByBuyerId(int byerId) throws DAOException;

	/** 
	 * Get all active and not sold lots by auction type id
	 * Active lots - not blocked lots (field "statusId" is not equals 1)
	 * Not sold lots - where buyer id is null
	 * 
	 * @param auctionTypeId - auction type id (1-Internet auction, 2-England auction, 3-Blitz auction)
	 * @return list of lots
	 * @throws DAOException
	 * 
	 * */
	List<Lot> takeActiveNotSoldLotsByAucType(int auctionTypeId) throws DAOException;

	/**
	 *  Method to block/unblock lot
	 * @param lotId - id lot for blocking/unblocking
	 * @param newStatusId - new status for updating
	 * @throws DAOException
	 *  
	 *  */
	void changeLotStatus(int lotId, int newStatusId) throws DAOException;

	/** 
	 * Get current blocking status
	 * @param lotId - lot id for checking status
	 * @return code of blocking status (1 - is blocked, 2 - unblocked)
	 * @throws DAOException
	 * */
	int getCurrentBlockingSatus(int lotId) throws DAOException;
	
	/** 
	 * Record new buyer id and new price with given id
	 * @param buyerId - new buyer
	 * @param lotId - lot for updating price
	 * @param price - new price
	 * 
	 * @throws DAOException
	 * */
	void updateLotPrice(int buyerId, int lotId, double price) throws DAOException;
	
	/**
	 * Gets lots with specified offset and number of records.
	 * Mainly this method used for pagination 
	 * @param offset - offset of fetch
	 * @param numberOfRecords - specified number of records
	 * 
	 * @return list of lots
	 * @throws DAOException
	 *  
	 *  */
	List<Lot> getAllLots(int offset, int numberOfRecords)throws DAOException;
	
	/**
	 * Counts number of records in permanent storage
	 * @return number of records
	 * @throws DAOException
	 *  */
	int getNumberOfLotInStorage()throws DAOException;
}
