package com.irille.omt.dao.auth;

import java.util.List;

import com.irille.core.repository.BaseDao;
import com.irille.omt.entity.auth.Role;
import com.irille.omt.entity.auth.Role.T;
import com.irille.omt.entity.auth.UserRole;

public class RoleDao extends BaseDao<Role> {

	public static Role insertOrUpdateIfNameExists(Role bean) {
		Role dbBean = SELECT(Role.class).WHERE(T.NAME, "=?", bean.getName()).query();
		if(dbBean != null)
			return dbBean;
		else
			return bean.ins();
	}
	public static Role findByName(String name) {
		return SELECT(Role.class).WHERE(T.NAME, "=?", name).query();
	}
	public static List<Role> listByUser(Integer user) {
		return SELECT(Role.class).LEFT_JOIN(UserRole.class, T.PKEY, UserRole.T.ROLE).WHERE(UserRole.T.USER, "=?", user).queryList();
	}
}