package com.irille.omt.dao.auth;

import java.util.List;

import com.irille.omt.entity.auth.RoleMenu;

import irille.pub.bean.Query;

public class RoleMenuDao {

	
	public static List<RoleMenu> list() {
		return Query.SELECT(RoleMenu.class).queryList();
	}
}
