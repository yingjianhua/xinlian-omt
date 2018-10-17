package com.irille.omt.action.auth;

import com.irille.omt.action.OmtAction;
import com.irille.omt.entity.auth.Role;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;

@Controller(module = "角色权限", name = "角色")
public class RoleAction extends OmtAction<Role, Integer>{

	@RequestMapping(alias="列表", description="列表所有角色")
	public void list() {
		
	}
	
	public void loadMenuAndOperation() {
		
	}
}
