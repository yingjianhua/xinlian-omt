package com.irille.omt.dao.usr;

import com.irille.core.repository.BaseDao;
import com.irille.omt.entity.usr.User;
import com.irille.omt.entity.usr.User.T;

public class UserDao extends BaseDao<User>{

	public static User findByUsername(String username) {
		return SELECT(User.class).WHERE(T.USERNAME, "=?", username).query();
	}
}
