package com.irille.omt.service.auth;

import java.util.List;

import com.irille.omt.dao.auth.RoleMenuDao;
import com.irille.omt.entity.auth.RoleMenu;

public class RoleMenuService {

	public static List<RoleMenu> list() {
		return RoleMenuDao.list();
	}
}
