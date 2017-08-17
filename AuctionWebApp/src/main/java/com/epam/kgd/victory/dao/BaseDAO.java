package com.epam.kgd.victory.dao;

import java.util.List;

import com.epam.kgd.victory.bean.BaseBean;
import com.epam.kgd.victory.dao.exception.DAOException;

public interface BaseDAO<T extends BaseBean> {

	List<T> getAll() throws DAOException;

	T getById(int id) throws DAOException;

	void add(T item) throws DAOException;

	void delete(int id) throws DAOException;

	void update(int id, T updated) throws DAOException;

}
