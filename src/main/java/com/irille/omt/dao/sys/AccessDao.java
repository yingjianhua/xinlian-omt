package com.irille.omt.dao.sys;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.irille.core.repository.EntityRepository;
import com.irille.omt.entity.sys.Access;
import com.irille.omt.entity.sys.Access.T;

public class AccessDao extends EntityRepository<Access> {

	public static List<Access> list() {
		return selectFrom(Access.class).queryList();
	}
	
	public static void main(String[] args) {
		selectFrom(Access.class).queryList().forEach(System.out::println);
		Map<String, Access> map = AccessDao.list().stream().collect(Collectors.toMap(Access::getAction, bean->bean));
		System.out.println(map);
	}
	public static List<String> listAccess() {
		return select(T.action).FROM(Access.class).queryList(String.class);
	}
	
}
