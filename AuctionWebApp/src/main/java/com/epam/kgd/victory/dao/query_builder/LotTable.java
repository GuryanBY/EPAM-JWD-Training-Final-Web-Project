package com.epam.kgd.victory.dao.query_builder;

public enum LotTable {
	LOT_TABLE_NAME("`lot`"),
	LOT_ENTITY("`l_id`, `l_user_id_buyer`, `l_user_id_seller`, `l_good_id`, `l_auction_type_id`,`l_name`, `l_good_amount`, `l_start_date`, `l_end_date`, `l_end_price`"),
	LOT_UPDATE_ENTITY("`l_user_id_buyer`=?, `l_user_id_seller`=?, `l_good_id`=?, `l_auction_type_id`=?, `l_name`=?, `l_good_amount`=?, `l_start_date`=?, `l_end_date`=?, `l_end_price`=?"),
	LOT_ID("`l_id`"),
	
	BY_ID("`l_id` = ?"),
	BY_AUCTION_TYPE ("`l_auction_type_id` = ?"),
	BY_ID_SELLER("`l_user_id_seller` = ?"),
	BY_BUYER_ID("`l_user_id_buyer` = ?");
	
	
	private final String value;

	private LotTable(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
