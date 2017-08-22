package com.epam.kgd.victory.dao.query_builder.select;
/**
 * Interface for building select-type query  
 *  
 */
public interface SelectQueryBuilder {
	
	/** Builds query according to parameters 
	 * 
	 * @param queryResult - columns names, that we want to select (part between "SELECT" and "FROM")
	 * for example "*", "good_id, good_price"
	 * @param fromTable - table name in database
	 * @param criteria - according to this criteria will build query (part after "WHERE")  
	 * 
	 * @return returns complete query
	 *  
	 */
	String getSelectQuery(String queryResult, String fromTable, String... criteria );

}
