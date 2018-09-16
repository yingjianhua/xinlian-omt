package com.irille.omt.action.auth;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;

@Controller(module = "角色权限", name = "角色")
public class RoleAction {

	@RequestMapping(alias="列表", description="列表所有角色")
	public void list() {
		
	}
}
