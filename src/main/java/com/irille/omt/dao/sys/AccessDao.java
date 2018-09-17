package com.irille.omt.dao.sys;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.irille.core.repository.BaseDao;
import com.irille.omt.entity.sys.Access;
import com.irille.omt.entity.sys.Access.T;

public class AccessDao extends BaseDao<Access> {

	public static List<Access> list() {
		return SELECT(Access.class).queryList();
	}
	
	public static void main(String[] args) {
		SELECT(Access.class).queryList().forEach(System.out::println);
		Map<String, Access> map = AccessDao.list().stream().collect(Collectors.toMap(Access::getAction, bean->bean));
		System.out.println(map);
	}
	public static List<String> listAccess() {
		return SELECT(T.ACTION).FROM(Access.class).queryList(String.class);
	}
	
}
