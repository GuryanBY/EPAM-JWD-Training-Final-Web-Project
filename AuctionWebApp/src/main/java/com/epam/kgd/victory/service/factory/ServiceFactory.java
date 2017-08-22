package com.epam.kgd.victory.service.factory;

import com.epam.kgd.victory.service.AdminService;
import com.epam.kgd.victory.service.GoodService;
import com.epam.kgd.victory.service.LotService;
import com.epam.kgd.victory.service.UserService;
import com.epam.kgd.victory.service.impl.AdminServiceImpl;
import com.epam.kgd.victory.service.impl.GoodServiceImpl;
import com.epam.kgd.victory.service.impl.LotServiceImpl;
import com.epam.kgd.victory.service.impl.UserServiceImpl;

public final class ServiceFactory {
	private static final ServiceFactory INSTANCE = new ServiceFactory();

	private ServiceFactory() {
	}

	private final UserService userService = new UserServiceImpl();
	private final AdminService adminService = new AdminServiceImpl();
	private final LotService lotService = new LotServiceImpl();
	private final GoodService goodService = new GoodServiceImpl();

	public static ServiceFactory getInstance() {
		return INSTANCE;
	}

	public UserService getUserService() {
		return userService;
	}
	
	public AdminService getAdminService(){
		return adminService;
		
	}
	public LotService getLotService(){
		return lotService;
		
	}
	public GoodService getGoodService(){
		return goodService;
		
	}

}
