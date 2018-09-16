package com.irille.omt.dao.sys;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.irille.omt.dao.BaseDao;
import com.irille.omt.entity.sys.Access;

public class AccessDao extends BaseDao {

	public static List<Access> list() {
		return SELECT(Access.class).queryList();
	}
	
	public static void main(String[] args) {
		SELECT(Access.class).queryList().forEach(System.out::println);
		Map<String, Access> map = AccessDao.list().stream().collect(Collectors.toMap(Access::getAction, bean->bean));
		System.out.println(map);
	}
}
