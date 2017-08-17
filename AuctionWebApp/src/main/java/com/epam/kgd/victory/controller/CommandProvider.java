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
import com.epam.kgd.victory.controller.command.impl.MakeInternetBid;
import com.epam.kgd.victory.controller.command.impl.Registration;
import com.epam.kgd.victory.controller.command.impl.ShowLotDetails;
import com.epam.kgd.victory.controller.command.impl.ShowUserDetails;
import com.epam.kgd.victory.controller.exception.ControllerException;

/**
 * The Class CommandProvider
 */
public final class CommandProvider {

	/** The constant repository */
	private final Map<CommandName, Command> repository = new HashMap<>();

	/**
	 * Instantiates a new command provider.
	 */
	CommandProvider() {

		repository.put(CommandName.LOG_IN, new LogIn());
		repository.put(CommandName.REGISTRATION, new Registration());
		repository.put(CommandName.ACCOUNT_MAIN_PAGE, new AccountMainPage());
		repository.put(CommandName.LOCALE, new Locale());
		repository.put(CommandName.LOG_OUT, new LogOut());
		repository.put(CommandName.ADD_GOOD, new AddGood());
		repository.put(CommandName.SHOW_USER_DETAILS, new ShowUserDetails());
		repository.put(CommandName.DELETE_LOT, new DeleteLot());
		repository.put(CommandName.SHOW_LOTS_DETAILS, new ShowLotDetails());
		repository.put(CommandName.CHANGE_LOT_STATUS, new ChangeLotStatus());
		repository.put(CommandName.BUY_BLITZ_LOT, new BuyBlitzAuction());
		repository.put(CommandName.CHANGE_USER_STATUS, new ChangeUserStatus());
		repository.put(CommandName.MAKE_BID, new MakeBid());
		repository.put(CommandName.BLITZ_AUCTION, new BlitzAuction());
		repository.put(CommandName.ENGLAND_AUCTION, new EnglandAuction());
		repository.put(CommandName.INTERNET_AUCTION, new InternetAuction());
		repository.put(CommandName.MAKE_INTERNET_BID, new MakeInternetBid());
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
			return repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new ControllerException("Incorrect command", e);
		}
	}

}
