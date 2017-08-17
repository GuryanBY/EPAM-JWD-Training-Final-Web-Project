package com.epam.kgd.victory.dao.query_builder;

public enum UserTable {
		
	USER_TABLE_NAME("`user`"),
	USER_ENTITY("`u_id`, `u_role_id`, `u_login`, `u_password`, `u_first_name`, `u_last_name`, `u_email`, `u_phone`, `u_registr_date`, `u_status`"),
	USER_UPDATE_ENTITY("`u_role_id`=?, `u_login`=?, `u_password`=?, `u_first_name`=?, `u_last_name`=?, `u_email`=?, `u_phone`=?, `u_registr_date`=?, `u_status`=?"),
	USER_ID("`u_id`"),
	
	BY_ID("`u_id`=?"),
	BY_LOGIN("`u_login`=?"),
	BY_PASSWORD("`u_password` = ?"),
	BY_EMAIL("`u_email`=?"),
	BY_ROLE("`u_role_id`='2'");
	
	
	private final String value;
	
	private UserTable(String value){
		this.value = value;
	}
	public String getValue(){
		return value;
	}

}
