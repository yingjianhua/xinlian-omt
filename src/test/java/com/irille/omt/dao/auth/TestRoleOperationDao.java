package com.irille.omt.dao.auth;

import org.junit.BeforeClass;
import org.junit.Test;

import com.irille.core.repository.Query2;
import com.irille.core.repository.sql.EntitySQL;
import com.irille.omt.entity.auth.RoleOperation;
import com.irille.omt.entity.auth.RoleOperation.T;
import com.irille.omt.entity.sys.Operation;

public class TestRoleOperationDao {

	@BeforeClass
	public static void init() {
		RoleOperation.table.getClass();
		Operation.table.getClass();
	}
	@Test
	public void testClearAllIfOperationNoExists() {
		Query2.sql(new EntitySQL() {{
			deleteFrom(RoleOperation.class);
			where(T.operation.in(new EntitySQL() {{
				select(T.operation);
				from(new EntitySQL() {{
					select(T.operation);
					from(RoleOperation.class);
					leftJoin(Operation.class, T.operation, Operation.T.pkey);
					where(Operation.T.pkey.isNull());
				}}, RoleOperation.class.getSimpleName());
			}}));
		}}).execute();
	}
}
