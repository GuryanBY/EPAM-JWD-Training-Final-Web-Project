package com.epam.kgd.victory.service;

import java.util.List;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.service.exception.ServiceException;

public interface LotService {

	List<Lot> takeLotsBySellerId(int sellerId) throws ServiceException;

	List<Lot> takeLotsByBuyerId(int buyerId) throws ServiceException;

	void deleteLot(int lotId) throws ServiceException;

	List<Lot> takeAllLots() throws ServiceException;
	
	/** 
	 * Change lot status (blocked/unblocked) 
	 *  @param lot id
	 *  @throws ServiceException
	 * 
	 * */

	void changeLotStatus(int lotId) throws ServiceException;
	
	/** 
	 * Active lots - it is lots where blocking parameter is "unblocked"
	 * Not sold lots - lots where field buyer id is null
	 *  
	 * @param auction type id
	 * @return list of lots
	 * @throws ServiceException
	 * 
	 * 
	 * */
    List<Lot> takeActiveNotSoldLotsByAucType(int auctionTypeId) throws ServiceException;

	Lot getLotById(int lotId) throws ServiceException;

	void addLot(int sellerId, int goodId, String auctionTypeId, String name, String goodAmount, String duration,
			String price) throws ServiceException;
}
