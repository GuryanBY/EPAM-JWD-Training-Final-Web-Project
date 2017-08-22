package com.epam.kgd.victory.dao.query_builder;
/**
 * Special enumeration for description of  database table User
 */
public enum UserTable {
	
	/**
	 * Table name in database
	 */	
	USER_TABLE_NAME("`user`"),
	
	/**
	 * Columns names for building User type entity 
	 */
	USER_ENTITY("`u_id`, `u_role_id`, `u_login`, `u_password`, `u_first_name`, `u_last_name`, `u_email`, `u_phone`, `u_registr_date`, `u_status`"),
	
	/** 
	 * Entity for building update-type query 
	 * 
	 */
	USER_UPDATE_ENTITY("`u_role_id`=?, `u_login`=?, `u_password`=?, `u_first_name`=?, `u_last_name`=?, `u_email`=?, `u_phone`=?, `u_registr_date`=?, `u_status`=?"),
	
	/** 
	 * Column name of field userId
	 */
	USER_ID("`u_id`"),
	
	/**
	 * Special entity for building query by user id
	 */
	BY_ID("`u_id`=?"),
	
	/**
	 * Special entity for building query by user login
	 */
	BY_LOGIN("`u_login`=?"),
	/**
	 * Special entity for building query by user password
	 */
	BY_PASSWORD("`u_password` = ?"),
	/**
	 * Special entity for building query by email
	 */
	BY_EMAIL("`u_email`=?"),
	/**
	 * Special entity for building query, where user role is not admin
	 */
	BY_ROLE("`u_role_id`='2'");
	
	/**
	 * Part of SQL query 
	 */
	private final String value;
	
	private UserTable(String value){
		this.value = value;
	}
	public String getValue(){
		return value;
	}

}
