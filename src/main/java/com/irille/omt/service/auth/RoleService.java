package com.irille.omt.service.auth;

import com.irille.omt.dao.auth.RoleDao;
import com.irille.omt.entity.auth.Role;

public class RoleService {
	
	public static Role add(String name, Integer sort) {
		Role bean = new Role();
		bean.setName(name);
		bean.setSort(sort==null?99:sort);
		return RoleDao.insertOrUpdateIfNameExists(bean);
	}
	
	public static Role findByName(String name) {
		return RoleDao.findByName(name);
	}
	
	public static void listAccessByRole() {
		
	}
}
