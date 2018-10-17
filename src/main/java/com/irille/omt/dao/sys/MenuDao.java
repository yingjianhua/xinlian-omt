package com.irille.omt.dao.sys;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.irille.core.controller.JsonWriter;
import com.irille.core.repository.EntityRepository;
import com.irille.omt.entity.sys.Menu;
import com.irille.omt.entity.sys.Menu.T;

public class MenuDao extends EntityRepository<Menu> {

	public static List<Menu> list() {
		return selectFrom(Menu.class).orderBy(T.SORT.asc()).queryList();
	}
	
	@BeforeClass
	public static void init() {
		Menu.table.getClass();
	}
	
	@Test
	public void testList() {
		JsonWriter.toConsole(list());;
	}
	
}
