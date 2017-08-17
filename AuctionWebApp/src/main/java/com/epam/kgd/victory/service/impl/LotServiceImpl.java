package com.epam.kgd.victory.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.kgd.victory.bean.Lot;
import com.epam.kgd.victory.dao.LotDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.factory.DAOFactory;
import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.exception.ServiceException;

public class LotServiceImpl implements LotService {
	private static final DAOFactory DAO_FACTORY = DAOFactory.getInstance();
	private static final LotDAO LOT_DAO = DAO_FACTORY.getLotDAO();

	@Override
	public List<Lot> takeLotsBySellerId(int sellerId) throws ServiceException {
		try {
			return LOT_DAO.getLotsBySellerId(sellerId);
		} catch (DAOException e) {
			throw new ServiceException("Problem to take lots by Seller ID", e);
		}
	}

	@Override
	public List<Lot> takeLotsByBuyerId(int buyerId) throws ServiceException {
		try {
			return LOT_DAO.getLotsByBuyerId(buyerId);
		} catch (DAOException e) {
			throw new ServiceException("Problem to take lots by Buyer ID", e);
		}
	}

	@Override
	public void deleteLot(int lotId) throws ServiceException {
		try {
			LOT_DAO.delete(lotId);
		} catch (DAOException e) {
			throw new ServiceException("Problem to delete lot by ID", e);
		}

	}

	@Override
	public List<Lot> takeAllLots() throws ServiceException {
		List<Lot> result = null;
		try {
			result = LOT_DAO.getAll();
		} catch (DAOException e) {
			throw new ServiceException("Problem to get all lots", e);
		}
		return result;
	}

	@Override
	public void changeLotStatus(int lotId) throws ServiceException {
		int blockedStatus = 1;
		int freeStatus = 2;

		try {
			int currentBlockingStatus = LOT_DAO.getCurrentBlockingSatus(lotId);

			if (currentBlockingStatus == blockedStatus) {
				LOT_DAO.changeLotStatus(lotId, freeStatus);
			} else {
				LOT_DAO.changeLotStatus(lotId, blockedStatus);
			}

		} catch (DAOException e) {
			throw new ServiceException("Problem to change lot status", e);
		}

	}

	@Override
	public List<Lot> takeActiveNotSoldLotsByAucType(int auctionTypeId) throws ServiceException {
		List<Lot> result = null;
		try {
			result = LOT_DAO.takeActiveNotSoldLotsByAucType(auctionTypeId);
		} catch (DAOException e) {
			throw new ServiceException("Problem to take lots by type status", e);
		}
		return result;
	}

	@Override
	public Lot getLotById(int lotId) throws ServiceException {
		Lot result = null;

		try {
			result = LOT_DAO.getById(lotId);
		} catch (DAOException e) {
			throw new ServiceException("Problem to take lot by id", e);
		}
		return result;
	}

	@Override
	public void addLot(int sellerId, int goodId, String auctionTypeId, String name, String goodAmount,
			String duration, String price) throws ServiceException{
		final int freeStatus = 2;
		LocalDateTime currentMoment = LocalDateTime.now();
		Lot lotForAdding = new Lot();
		lotForAdding.setSellerId(sellerId);
		lotForAdding.setGoodId(goodId);
		lotForAdding.setAuctionTypeId(auctionTypeId);
		lotForAdding.setStatusId(freeStatus);
		lotForAdding.setLotName(name);
		lotForAdding.setGoodAmount(Integer.parseInt(goodAmount));
		lotForAdding.setStartSellingDate(currentMoment);
		lotForAdding.setEndSellingDate(currentMoment.plusDays(Long.parseLong(duration)));
		lotForAdding.setEndPrice(Double.parseDouble(price));
		
		try {
			LOT_DAO.add(lotForAdding);
		} catch (DAOException e) {
			throw new ServiceException("Problem to add lot", e);
		}
		
		
		
	}

}
