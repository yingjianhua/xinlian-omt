package com.irille.omt.dao.auth;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.irille.core.repository.EntityRepository;
import com.irille.core.repository.Query2;
import com.irille.core.repository.sql.EntitySQL;
import com.irille.omt.entity.auth.Role;
import com.irille.omt.entity.auth.RoleOperation;
import com.irille.omt.entity.auth.RoleOperation.T;
import com.irille.omt.entity.sys.Operation;

public class RoleOperationDao extends EntityRepository<Operation> {

	
	public static List<Operation> listOperationByRole(Integer role) {
		return selectFrom(Operation.class).leftJoin(RoleOperation.class, Operation.T.pkey, T.operation).where(T.role.eq(role)).orderBy(Operation.T.sort.asc()).queryList();
	}
	
	public static void clearAllIfRoleNoExists() {
		String sql = "DELETE FROM "+RoleOperation.table.name()+" WHERE "+T.role+" IN (SELECT a."+T.role+" FROM (SELECT a."+T.role+" FROM "+RoleOperation.table.name()+" a LEFT JOIN "+Role.table.name()+" b ON a."+T.role+" = b."+Role.T.pkey+" WHERE b."+Role.T.pkey+" IS NULL ) a)";
		sql(sql).execute();
	}
	
	public static void clearAllIfOperationNoExists() {
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
//		String sql = "DELETE FROM "+RoleOperation.table.name()+" WHERE "+T.OPERATION+" IN (SELECT a."+T.OPERATION+" FROM (SELECT a."+T.OPERATION+" FROM "+RoleOperation.table.name()+" a LEFT JOIN "+Operation.table.name()+" b ON a."+T.OPERATION+" = b."+Operation.T.PKEY+" WHERE b."+Operation.T.PKEY+" IS NULL ) a)";
//		sql(sql).execute();
	}
	
	/**
	 * 给指定的角色在角色操作关联表中添加所有的操作的关联
	 * @param role
	 */
	public static void addAllOperation4Role(Integer role) {
		sql("insert ignore into "+RoleOperation.table.name()+" ("+T.role+", "+T.operation+") select ?, "+Operation.T.pkey+" from "+Operation.table.name(), role).executeUpdate();
	}
	public static List<String> listActionByAnonymous() {
		return select(Operation.T.action)
				.FROM(Operation.class)
				.leftJoin(RoleOperation.class, Operation.T.pkey, RoleOperation.T.operation)
				.leftJoin(Role.class, RoleOperation.T.role, Role.T.pkey)
				.where(Role.T.name.eq(Role.anonymous))
				.queryList(String.class);
	}
	public static List<String> listActionByRole(Integer... roles) {
		return select(Operation.T.action)
				.FROM(Operation.class)
				.leftJoin(RoleOperation.class, Operation.T.pkey, RoleOperation.T.operation)
				.where(roles.length>0, RoleOperation.T.role, "in (?)", ()->Stream.of(roles).map(role->String.valueOf(role)).collect(Collectors.joining(",")))
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
