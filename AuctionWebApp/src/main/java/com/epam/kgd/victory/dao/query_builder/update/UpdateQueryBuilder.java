package com.epam.kgd.victory.dao.query_builder.update;

/**
 * Interface for building select-type query  
 *  
 */
public interface UpdateQueryBuilder {
	
	/** Builds query according to parameters 
	 * 
	 * @param table - table name in database
	 * @param entity - what we want update(part between "SET" and WHERE),
	 * for example `u_role_id`=?, `u_login`=?, `u_password`=?
	 * @param criteria - according to this criteria will build query (part after "WHERE")  
	 * 
	 * @return returns complete query
	 *  
	 */
	String getUpdateQuery(String table,String entity, String... criteria);

}
