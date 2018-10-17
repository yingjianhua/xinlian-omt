package com.irille.omt.dao.sys;

import java.util.List;

import com.irille.core.repository.EntityRepository;
import com.irille.omt.entity.sys.Operation;
import com.irille.omt.entity.sys.Operation.T;

public class OperationDao extends EntityRepository<Operation> {

	public static int delByMenu(Integer menu) {
		return delete(Operation.class).where(T.MENU.eq(menu)).execute();
	}
	public static List<Operation> listByMenu(Integer menu) {
		return selectFrom(Operation.class).where(T.MENU.eq(menu)).orderBy(T.SORT.asc()).queryList();
	}
	public static int del(Integer pkey) {
		return delete(Operation.class).where(T.PKEY.eq(pkey)).execute();
	}
}
