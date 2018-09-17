package com.irille.omt.dao.sys;

import java.util.List;

import com.irille.core.repository.BaseDao;
import com.irille.omt.entity.sys.Menu;

public class MenuDao extends BaseDao<Menu> {

	public static List<Menu> list() {
		return SELECT(Menu.class).queryList();
	}
	
}
