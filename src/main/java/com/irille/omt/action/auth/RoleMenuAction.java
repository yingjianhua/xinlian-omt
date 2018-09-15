package com.irille.omt.action.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irille.omt.entity.auth.RoleMenu;
import com.irille.omt.service.auth.RoleMenuService;

import irille.action.BeanAction;

public class RoleMenuAction extends BeanAction<RoleMenu, Integer> implements IRoleMenuAction{

	private static final long serialVersionUID = 1L;

	@Override
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
