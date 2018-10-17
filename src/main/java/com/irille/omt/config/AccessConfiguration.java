package com.irille.omt.config;

import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.irille.core.commons.annotation.Order;
import com.irille.core.controller.JsonWriter;
import com.irille.core.repository.db.ConnectionManager;
import com.irille.core.web.config.Configuration;
import com.irille.core.web.config.Configuration.RunWithStartup;
import com.irille.omt.entity.auth.Role;
import com.irille.omt.service.auth.RoleMenuService;
import com.irille.omt.service.auth.RoleOperationService;
import com.irille.omt.service.auth.RoleService;
import com.irille.omt.service.sys.AccessService;
import com.irille.omt.service.sys.MenuService;
import com.irille.omt.view.sys.MenusView;

@Configuration
@Order(2)
public class AccessConfiguration {

	@RunWithStartup
	@Order(1)
	public void initAccess() throws SQLException {
		try {
			AccessService.initAccess();
			ConnectionManager.commitConnection();
		} finally {
//			AppConfig.db_connection_close();
		}
	}
	
	@RunWithStartup
	@Order(2)
	public void initMenu() throws Exception {
		try {
			MenusView list = JsonWriter.defaultMapper().readValue(MenuService.class.getResourceAsStream("/menu.json"), MenusView.class);
			MenuService.MenuBuilder b = new MenuService.MenuBuilder(list.getMenus());
			b.init();
//			MenuService.initMenu(list.getMenus());
			ConnectionManager.commitConnection();
		} finally {
			//ConnectionManager.closeConnection();
		}
	}
	
	@RunWithStartup
	@Order(3)
	public void initRole() throws Exception {
		try {
			Role admin = RoleService.add(Role.admin, 0);
			Role anonymous = RoleService.add(Role.anonymous, 99);
			RoleMenuService.giveAllRoleMenu(admin.getPkey());
			RoleOperationService.giveAllRoleOperation(admin.getPkey());
			ConnectionManager.commitConnection();
		} finally {
			//ConnectionManager.closeConnection();
		}
	}
	
	@RunWithStartup
	@Order(4)
	public void initRoleMenuAndOperation() throws Exception {
		try {
			RoleMenuService.clearAllIfRoleOrMenuNoExists();
			RoleOperationService.clearAllIfRoleOrOperationNoExists();
			ConnectionManager.commitConnection();
		} finally {
			//ConnectionManager.closeConnection();
		}
	}
	@RunWithStartup
	@Order(5)
	public void aa() throws JsonProcessingException {
		JsonWriter.toConsole(RoleMenuService.listViewByRole(1));
	}
	
}
