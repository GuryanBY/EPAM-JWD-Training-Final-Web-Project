package com.epam.kgd.victory.controller;

import java.util.HashMap;
import java.util.Map;

import com.epam.kgd.victory.controller.command.Command;
import com.epam.kgd.victory.controller.command.CommandName;
import com.epam.kgd.victory.controller.command.impl.AccountMainPage;
import com.epam.kgd.victory.controller.command.impl.AddGood;
import com.epam.kgd.victory.controller.command.impl.BlitzAuction;
import com.epam.kgd.victory.controller.command.impl.BuyBlitzAuction;
import com.epam.kgd.victory.controller.command.impl.ChangeLotStatus;
import com.epam.kgd.victory.controller.command.impl.ChangeUserStatus;
import com.epam.kgd.victory.controller.command.impl.DeleteLot;
import com.epam.kgd.victory.controller.command.impl.EnglandAuction;
import com.epam.kgd.victory.controller.command.impl.InternetAuction;
import com.epam.kgd.victory.controller.command.impl.Locale;
import com.epam.kgd.victory.controller.command.impl.LogIn;
import com.epam.kgd.victory.controller.command.impl.LogOut;
import com.epam.kgd.victory.controller.command.impl.MakeBid;
import com.epam.kgd.victory.controller.command.impl.Registration;
import com.epam.kgd.victory.controller.command.impl.ShowAllUsers;
import com.epam.kgd.victory.controller.command.impl.ShowLotDetails;
import com.epam.kgd.victory.controller.command.impl.ShowLotDetailsWithPagination;
import com.epam.kgd.victory.controller.command.impl.ShowSellerInfo;
import com.epam.kgd.victory.controller.command.impl.ShowUserDetails;
import com.epam.kgd.victory.controller.exception.ControllerException;

/**
 * The Class CommandProvider
 */
public final class CommandProvider {

	/** The constant repository */
	private final Map<CommandName, Command> commandRepository = new HashMap<>();

	/**
	 * Instantiates a new command provider.
	 */
	CommandProvider() {

		commandRepository.put(CommandName.LOG_IN, new LogIn());
		commandRepository.put(CommandName.REGISTRATION, new Registration());
		commandRepository.put(CommandName.ACCOUNT_MAIN_PAGE, new AccountMainPage());
		commandRepository.put(CommandName.LOCALE, new Locale());
		commandRepository.put(CommandName.LOG_OUT, new LogOut());
		commandRepository.put(CommandName.ADD_GOOD, new AddGood());
		commandRepository.put(CommandName.SHOW_USER_DETAILS, new ShowUserDetails());
		commandRepository.put(CommandName.DELETE_LOT, new DeleteLot());
		commandRepository.put(CommandName.SHOW_LOTS_DETAILS, new ShowLotDetails());
		commandRepository.put(CommandName.CHANGE_LOT_STATUS, new ChangeLotStatus());
		commandRepository.put(CommandName.BUY_BLITZ_LOT, new BuyBlitzAuction());
		commandRepository.put(CommandName.CHANGE_USER_STATUS, new ChangeUserStatus());
		commandRepository.put(CommandName.MAKE_BID, new MakeBid());
		commandRepository.put(CommandName.BLITZ_AUCTION, new BlitzAuction());
		commandRepository.put(CommandName.ENGLAND_AUCTION, new EnglandAuction());
		commandRepository.put(CommandName.INTERNET_AUCTION, new InternetAuction());
		commandRepository.put(CommandName.SHOW_LOTS_DETAILS_PAGINATION, new ShowLotDetailsWithPagination());
		commandRepository.put(CommandName.SHOW_ALL_USERS, new ShowAllUsers());
		commandRepository.put(CommandName.SHOW_SELLER_INFO, new ShowSellerInfo());
	}

	/**
	 * Get command.
	 *
	 * @param name
	 *            the name
	 * @return the command
	 * @throws ControllerException
	 *             the controller exception
	 */
	Command getCommand(String name) throws ControllerException {
		try {
			CommandName commandName = CommandName.valueOf(name.toUpperCase());
			return commandRepository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new ControllerException("Incorrect command", e);
		}
	}

}
