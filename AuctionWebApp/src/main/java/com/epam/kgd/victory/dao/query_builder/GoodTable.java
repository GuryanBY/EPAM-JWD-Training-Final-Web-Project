package com.epam.kgd.victory.dao.query_builder;
/**
 * Special enumeration for description of  database table Good
 */
public enum GoodTable {
	
	/**
	 * Table name in database
	 */
	GOOD_TABLE_NAME("`good`"),
	
	/**
	 * Columns names for building Good type entity 
	 */
	GOOD_ENTITY("`g_id`, `g_category_id`, `g_condition_id`, `g_name`, `g_description`, `g_price`"),
	
	/** 
	 * Entity for building update-type query 
	 * 
	 */
	GOOD_UPDATE_ENTITY("`g_category_id`=?, `g_condition_id`=?, `g_name`=?, `g_description`=?, `g_price`=?"),
	
	/** 
	 * Column name of field goodId
	 */
	GOOD_ID("`g_id`"),
	
	/**
	 * Special entity for building query by good id
	 */
	BY_ID("`g_id`=?");

	/**
	 * Part of SQL query 
	 */
	private final String value;
	
	private GoodTable(String value){
		this.value = value;
	}
	public String getValue(){
		return value;
	}

}
