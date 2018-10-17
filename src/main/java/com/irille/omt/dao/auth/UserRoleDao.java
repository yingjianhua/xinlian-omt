package com.irille.omt.dao.auth;

import java.util.List;

import com.irille.core.repository.EntityRepository;
import com.irille.omt.entity.auth.UserRole;
import com.irille.omt.entity.auth.UserRole.T;

public class UserRoleDao extends EntityRepository<UserRole>{
	
	public static List<Integer> listRoleByUser(Integer user) {
		return select(T.ROLE).FROM(UserRole.class).where(T.USER.eq(user)).queryList(Integer.class);
	}

}
