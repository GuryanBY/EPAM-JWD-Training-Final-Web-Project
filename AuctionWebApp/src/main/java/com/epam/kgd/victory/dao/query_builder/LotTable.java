package com.epam.kgd.victory.dao.query_builder;

/**
 * Special enumeration for description of  database table Lot
 */
public enum LotTable {
	
	/**
	 * Table name in database
	 */
	LOT_TABLE_NAME("`lot`"),
	
	/**
	 * Columns names for building Lot type entity 
	 */
	LOT_ENTITY("`l_id`, `l_user_id_buyer`, `l_user_id_seller`, `l_good_id`, `l_auction_type_id`,`l_name`, `l_good_amount`, `l_start_date`, `l_end_date`, `l_end_price`"),
	
	/** 
	 * Entity for building update-type query 
	 * 
	 */
	LOT_UPDATE_ENTITY("`l_user_id_buyer`=?, `l_user_id_seller`=?, `l_good_id`=?, `l_auction_type_id`=?, `l_name`=?, `l_good_amount`=?, `l_start_date`=?, `l_end_date`=?, `l_end_price`=?"),
	
	/** 
	 * Column name of field lotId
	 */
	LOT_ID("`l_id`"),
	
	/**
	 * Special entity for building query by lot id
	 */
	BY_ID("`l_id` = ?"),
	
	/**
	 * Special entity for building query by auction type
	 */
	BY_AUCTION_TYPE ("`l_auction_type_id` = ?"),
	
	/**
	 * Special entity for building query by seller id
	 */
	BY_ID_SELLER("`l_user_id_seller` = ?"),
	
	/**
	 * Special entity for building query by buyer id
	 */
	BY_BUYER_ID("`l_user_id_buyer` = ?");
	
	/**
	 * Part of SQL query 
	 */
	private final String value;

	private LotTable(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
