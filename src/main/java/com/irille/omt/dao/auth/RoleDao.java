package com.irille.omt.dao.auth;

import com.irille.omt.dao.BaseDao;
import com.irille.omt.entity.auth.Role;
import com.irille.omt.entity.auth.Role.T;

public class RoleDao extends BaseDao {

	public static Role findByName(String name) {
		return SELECT(Role.class).WHERE(T.NAME, "=?", name).query();
	}
	
}