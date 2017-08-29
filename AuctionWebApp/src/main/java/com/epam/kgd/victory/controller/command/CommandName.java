package com.epam.kgd.victory.controller.command;

/**
 * The Enum CommandName.
 */
public enum CommandName {

	/** Command name to login */
	LOG_IN, 
	/** Command name to registrate */
	REGISTRATION, 
	/** Command name of wrong request */
	WRONG_REQUEST, 
	/** Command name to change locale */
	LOCALE, 
	/** Command name to log out */
	LOG_OUT,
	/** Command name to add good(lot) */
	ADD_GOOD, 
	/** Command name to show user details */
	SHOW_USER_DETAILS,
	/** Command name to delete lot */
	DELETE_LOT, 
	/** Command name to show lots details */
	SHOW_LOTS_DETAILS,
	SHOW_LOTS_DETAILS_PAGINATION,
	/** Command name to change status(blocked/unblocked) of  lot*/
	CHANGE_LOT_STATUS,
	/** Command name to participate in auction */
	AUCTION_PARTICIPATE,
	/** Command name to buy lot in auction with blitz type */
	BUY_BLITZ_LOT,
	/** Command name to block/unblock user */
	CHANGE_USER_STATUS,
	/** Command name to make bid in england type auction */
	MAKE_BID,
	/** Command name to participate in blitz auction*/
	BLITZ_AUCTION, 
	/** Command name to participate in england auction*/
	ENGLAND_AUCTION,
	/** Command name to participate in Internet auction*/
	INTERNET_AUCTION,
	/** Command go to main page of account*/
	ACCOUNT_MAIN_PAGE,
	/** Command to show seller info in Internet type auction*/
	SHOW_SELLER_INFO,
	/**Command to show all users */
	SHOW_ALL_USERS
}
