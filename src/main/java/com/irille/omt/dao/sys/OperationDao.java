package com.irille.omt.dao.sys;

import java.util.List;

import com.irille.core.repository.BaseDao;
import com.irille.omt.entity.sys.Operation;
import com.irille.omt.entity.sys.Operation.T;

public class OperationDao extends BaseDao<Operation> {

	public static int delByMenu(Integer menu) {
		return DELETE(Operation.class).WHERE(T.MENU, "=?", menu).executeUpdate();
	}
	public static List<Operation> listByMenu(Integer menu) {
		return SELECT(Operation.class).WHERE(T.MENU, "=?", menu).queryList();
	}
	public static int del(Integer pkey) {
		return DELETE(Operation.class).WHERE(T.PKEY, "=?", pkey).executeUpdate();
	}
}
