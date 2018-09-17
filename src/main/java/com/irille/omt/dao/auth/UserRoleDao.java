package com.irille.omt.dao.auth;

import java.util.List;

import com.irille.core.repository.BaseDao;
import com.irille.omt.entity.auth.UserRole;
import com.irille.omt.entity.auth.UserRole.T;

public class UserRoleDao extends BaseDao<UserRole>{
	
	public static List<Integer> listRoleByUser(Integer user) {
		return SELECT(T.ROLE).FROM(UserRole.class).WHERE(T.USER, "=?", user).queryList(Integer.class);
	}

}
