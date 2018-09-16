package com.irille.omt.action.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irille.omt.entity.auth.RoleMenu;
import com.irille.omt.service.auth.RoleMenuService;

import irille.action.BeanAction;
import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;

@Controller(module = "角色权限", name = "角色菜单")
public class RoleMenuAction extends BeanAction<RoleMenu, Integer> {

	private static final long serialVersionUID = 1L;

	@RequestMapping(alias="列表", description="列表角色的所有菜单")
	public void list() {
		try {
			String json = new ObjectMapper().writeValueAsString(RoleMenuService.list());
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		String json = new ObjectMapper().writeValueAsString(RoleMenuService.list());
		System.out.println(json);
	}


}
