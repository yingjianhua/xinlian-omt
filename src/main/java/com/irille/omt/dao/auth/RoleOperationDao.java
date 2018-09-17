package com.irille.omt.dao.auth;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.irille.core.repository.BaseDao;
import com.irille.omt.entity.auth.Role;
import com.irille.omt.entity.auth.RoleOperation;
import com.irille.omt.entity.auth.RoleOperation.T;
import com.irille.omt.entity.sys.Operation;

public class RoleOperationDao extends BaseDao<Operation> {

	
	public static List<Operation> listOperationByRole(Integer role) {
		return SELECT(Operation.class).LEFT_JOIN(RoleOperation.class, Operation.T.PKEY, T.OPERATION).WHERE(T.ROLE, "=?", role).queryList();
	}
	
	public static void clearAllIfRoleNoExists() {
		String sql = "DELETE FROM "+RoleOperation.TB.getCodeSqlTb()+" WHERE "+T.ROLE+" IN (SELECT a."+T.ROLE+" FROM (SELECT a."+T.ROLE+" FROM "+RoleOperation.TB.getCodeSqlTb()+" a LEFT JOIN "+Role.TB.getCodeSqlTb()+" b ON a."+T.ROLE+" = b."+Role.T.PKEY+" WHERE b."+Role.T.PKEY+" IS NULL ) a)";
		sql(sql).executeUpdate();
	}
	
	/**
	 * 给指定的角色在角色操作关联表中添加所有的操作的关联
	 * @param role
	 */
	public static void addAllOperation4Role(Integer role) {
		sql("insert ignore into "+RoleOperation.TB.getCodeSqlTb()+" ("+T.ROLE+", "+T.OPERATION+") select ?, "+Operation.T.PKEY+" from "+Operation.TB.getCodeSqlTb(), role).executeUpdate();
	}
	
	public static List<String> listActionByRole(Integer... roles) {
		return SELECT(Operation.T.ACTION)
				.FROM(Operation.class)
				.LEFT_JOIN(RoleOperation.class, Operation.T.PKEY, RoleOperation.T.OPERATION)
				.WHERE(roles.length>0, RoleOperation.T.ROLE, "in (?)", ()->Stream.of(roles).map(role->String.valueOf(role)).collect(Collectors.joining(",")))
				.queryList(String.class);
	}
	public static List<String> listActionByUser(Integer user) {
		List<Integer> roles = UserRoleDao.listRoleByUser(user);
		return listActionByRole(roles.toArray(new Integer[roles.size()]));
	}
	public static void main(String[] args) {
		System.out.println(listActionByUser(1));
	}
}
