package com.epam.kgd.victory.service.impl;

import com.epam.kgd.victory.bean.Good;
import com.epam.kgd.victory.dao.GoodDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.factory.DAOFactory;
import com.epam.kgd.victory.service.GoodService;
import com.epam.kgd.victory.service.exception.ServiceException;
import static com.epam.kgd.victory.service.util.Validation.*;

public class GoodServiceImpl implements GoodService {
	private final static DAOFactory DAO_FACTORY = DAOFactory.getInstance();
	private final static GoodDAO GOOD_DAO = DAO_FACTORY.getGoodDAO();

	@Override
	public int addGood(String categoryId, String conditionId, String name, String description, String startPrice)
			throws ServiceException {

		boolean isDataFromAddGoodFormValid = isAddGoodDataValid(categoryId, conditionId, name, description,
				startPrice);
		int idAfterInsertInDB = -1;
		if (!isDataFromAddGoodFormValid) {
			return idAfterInsertInDB;
		}

		Good good = new Good();
		good.setCategoryId(Integer.parseInt(categoryId));
		good.setConditionId(Integer.parseInt(conditionId));
		good.setName(name);
		good.setDescription(description);
		good.setStartPrice(Double.parseDouble(startPrice));

		try {
			idAfterInsertInDB = GOOD_DAO.addGood(good);
		} catch (DAOException e) {
			throw new ServiceException("Problem with adding a new good", e);
		}

		return idAfterInsertInDB;

	}

}