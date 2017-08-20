package com.epam.kgd.victory.dao.query_builder.delete;
/**
 * Interface for building delete-type query  
 *  
 */
public interface DeleteQueryBuilder {
	
	/**
	 * Builds query according to parameters 
	 * 
	 * @param fromTable - table name in database
	 * @param criteria - according to this criteria will build query (part after "WHERE")  
	 * 
	 * @return returns complete SQL query
	 *  
	 */
	String getDeleteQuery(String fromTable, String... criteria);

}
