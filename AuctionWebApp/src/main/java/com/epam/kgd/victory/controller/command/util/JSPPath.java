package com.epam.kgd.victory.controller.command.util;

public enum JSPPath {
	
	INDEX("index.jsp"),
	REGISTRATION("jsp/registration.jsp"),
	USER_MAIN("jsp/user_page.jsp"),
	ADMIN_MAIN("jsp/admin_page.jsp"),
	ERROR("jsp/error_page.jsp"),
	ADD_GOOD("jsp/add_good.jsp"),
	ADMIN_USER_DETAILS("jsp/admin_user_details.jsp"),
	ADMIN_LOT_DETAILS("jsp/admin_lot_details.jsp"),
	ADMIN_LOT_DETAILS_PAGINATION("jsp/admin_lot_details_pagination.jsp"),
	USER_BLITZ_AUCTION("jsp/user_blitz_auction.jsp"),
	USER_ENGLAND_AUCTION("jsp/user_england_auction.jsp"),
	USER_INTERNET_AUCTION("jsp/user_internet_auction.jsp"),
	SUCCESS_OPERATION("jsp/success_operation.jsp"),
	SUCCESS_LOGIN("jsp/success_login.jsp"),
	ADMIN_ALL_USERS("jsp/admin_all_users_pagination.jsp"),
	USER_SHOW_SELLER_INFO("jsp/user_show_seller_info.jsp");
	
	private final String value;

	private JSPPath(String value) {
		this.value = value;
	}
	public String getPath() {
		return value;
	}
}
