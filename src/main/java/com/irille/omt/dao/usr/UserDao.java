package com.irille.omt.dao.usr;

import com.irille.core.repository.EntityRepository;
import com.irille.omt.entity.usr.User;
import com.irille.omt.entity.usr.User.T;

public class UserDao extends EntityRepository<User>{

	public static User findByUsername(String username) {
		return selectFrom(User.class).where(T.USERNAME.eq(username)).query();
	}
	
}
