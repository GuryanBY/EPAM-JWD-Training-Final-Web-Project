package com.epam.kgd.victory.dao.query_builder;

public enum GoodTable {
	
	GOOD_TABLE_NAME("`good`"),
	GOOD_ENTITY("`g_id`, `g_category_id`, `g_condition_id`, `g_name`, `g_description`, `g_price`"),
	GOOD_UPDATE_ENTITY("`g_category_id`=?, `g_condition_id`=?, `g_name`=?, `g_description`=?, `g_price`=?"),
	GOOD_ID("`g_id`"),
	
	BY_ID("`g_id`=?"),
	BY_PRICE_MORE("`g_price`>=?"),
	BY_PRICE_LESS("`g_price`<=?");;
	

	private final String value;
	
	private GoodTable(String value){
		this.value = value;
	}
	public String getValue(){
		return value;
	}

}
