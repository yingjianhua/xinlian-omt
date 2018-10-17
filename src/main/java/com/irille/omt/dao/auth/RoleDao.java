package com.irille.omt.dao.auth;

import java.util.List;

import com.irille.core.repository.EntityRepository;
import com.irille.omt.entity.auth.Role;
import com.irille.omt.entity.auth.Role.T;
import com.irille.omt.entity.auth.UserRole;

public class RoleDao extends EntityRepository<Role> {

	public static Role insertOrUpdateIfNameExists(Role bean) {
		Role dbBean = selectFrom(Role.class).where(T.NAME.eq(bean.getName())).query();
		if(dbBean != null)
			return dbBean;
		else {
			return save(bean);
		}
	}
	public static Role findByName(String name) {
		return selectFrom(Role.class).where(T.NAME.eq(name)).query();
	}
	public static List<Role> listByUser(Integer user) {
		return selectFrom(Role.class).leftJoin(UserRole.class, T.PKEY, UserRole.T.ROLE).where(UserRole.T.USER.eq(user)).queryList();
	}
}