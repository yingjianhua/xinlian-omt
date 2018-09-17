package com.irille.omt.config;

import java.sql.SQLException;

import com.irille.core.commons.annotation.Order;
import com.irille.core.web.config.AppConfig;
import com.irille.core.web.config.Configuration;
import com.irille.core.web.servlet.RunWithStartup;
import com.irille.omt.entity.auth.Role;
import com.irille.omt.service.auth.RoleMenuService;
import com.irille.omt.service.auth.RoleOperationService;
import com.irille.omt.service.auth.RoleService;
import com.irille.omt.service.sys.AccessService;
import com.irille.omt.service.sys.MenuService;
import com.irille.omt.view.sys.MenusView;

@Configuration
public class AccessConfiguration {

	@RunWithStartup
	@Order(1)
	public void initAccess() throws SQLException {
		try {
			AccessService.initAccess();
			AppConfig.db_connection_commit();
		} finally {
			AppConfig.db_connection_close();
		}
	}
	
	@RunWithStartup
	@Order(2)
	public void initMenu() throws Exception {
		try {
			MenusView list = AppConfig.objectMapper.readValue(MenuService.class.getResourceAsStream("/menu.json"), MenusView.class);
			MenuService.initMenu(list.getMenus());
			AppConfig.db_connection_commit();
		} finally {
			AppConfig.db_connection_close();
		}
	}
	
	@RunWithStartup
	@Order(3)
	public void initRole() throws Exception {
		try {
			Role role = RoleService.add("admin", 0);
			RoleMenuService.giveAllRoleMenu(role.getPkey());
			RoleOperationService.giveAllRoleOperation(role.getPkey());
			AppConfig.db_connection_commit();
		} finally {
			AppConfig.db_connection_close();
		}
	}
	
	@RunWithStartup
	@Order(4)
	public void initRoleMenuAndOperation() throws Exception {
		try {
			RoleMenuService.clearAllIfRoleNoExists();
			RoleOperationService.clearAllIfRoleNoExists();
			AppConfig.db_connection_commit();
		} finally {
			AppConfig.db_connection_close();
		}
	}
	
}
