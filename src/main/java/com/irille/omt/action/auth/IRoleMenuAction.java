package com.irille.omt.action.auth;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;

@Controller(module = "角色权限", name = "菜单")
public interface IRoleMenuAction {

	@RequestMapping(alias = "列表")
	public void list();
}
