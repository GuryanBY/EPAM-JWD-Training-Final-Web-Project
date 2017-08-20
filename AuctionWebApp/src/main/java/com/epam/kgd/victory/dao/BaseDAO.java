package com.epam.kgd.victory.dao;

import java.util.List;

import com.epam.kgd.victory.bean.BaseBean;
import com.epam.kgd.victory.dao.exception.DAOException;

/**
 * Main DAO interface, that contains methods for CRUD operation - common methods
 * for all types of DAO implementations
 */
public interface BaseDAO<T extends BaseBean> {

	/**
	 * Get all entities, that in storage
	 * 
	 * @throws DAOException
	 */
	List<T> getAll() throws DAOException;

	/**
	 * Get entity by id
	 * 
	 * @param id identify number of entity
	 * @return T-type entity
	 * @throws DAOException
	 */
	T getById(int id) throws DAOException;

	/**
	 * Add entity in storage
	 * 
	 * @param item, that have to be add in storage
	 * 
	 * @throws DAOException
	 */
	void add(T item) throws DAOException;

	/**
	 * Delete entity from storage
	 * 
	 * @param id - item id, that have to be delete from storage
	 * 
	 * @throws DAOException
	 */
	void delete(int id) throws DAOException;

	/**
	 * Change entity in storage
	 * 
	 * @param id - item id, that have to be updated
	 * @param updated - changed entity, or new entity, that will be with given id
	 * @throws DAOException
	 */
	void update(int id, T updated) throws DAOException;

}
