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
		return selectFrom(Operation.class).leftJoin(RoleOperation.class, Operation.T.PKEY, T.OPERATION).where(T.ROLE.eq(role)).orderBy(Operation.T.SORT.asc()).queryList();
	}
	
	public static void clearAllIfRoleNoExists() {
		String sql = "DELETE FROM "+RoleOperation.table.name()+" WHERE "+T.ROLE+" IN (SELECT a."+T.ROLE+" FROM (SELECT a."+T.ROLE+" FROM "+RoleOperation.table.name()+" a LEFT JOIN "+Role.table.name()+" b ON a."+T.ROLE+" = b."+Role.T.PKEY+" WHERE b."+Role.T.PKEY+" IS NULL ) a)";
		sql(sql).execute();
	}
	
	public static void clearAllIfOperationNoExists() {
		Query2.sql(new EntitySQL() {{
			deleteFrom(RoleOperation.class);
			where(T.OPERATION.in(new EntitySQL() {{
				select(T.OPERATION);
				from(new EntitySQL() {{
					select(T.OPERATION);
					from(RoleOperation.class);
					leftJoin(Operation.class, T.OPERATION, Operation.T.PKEY);
					where(Operation.T.PKEY.isNull());
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
		sql("insert ignore into "+RoleOperation.table.name()+" ("+T.ROLE+", "+T.OPERATION+") select ?, "+Operation.T.PKEY+" from "+Operation.table.name(), role).executeUpdate();
	}
	public static List<String> listActionByAnonymous() {
		return select(Operation.T.ACTION)
				.FROM(Operation.class)
				.leftJoin(RoleOperation.class, Operation.T.PKEY, RoleOperation.T.OPERATION)
				.leftJoin(Role.class, RoleOperation.T.ROLE, Role.T.PKEY)
				.where(Role.T.NAME.eq(Role.anonymous))
				.queryList(String.class);
	}
	public static List<String> listActionByRole(Integer... roles) {
		return select(Operation.T.ACTION)
				.FROM(Operation.class)
				.leftJoin(RoleOperation.class, Operation.T.PKEY, RoleOperation.T.OPERATION)
				.where(roles.length>0, RoleOperation.T.ROLE, "in (?)", ()->Stream.of(roles).map(role->String.valueOf(role)).collect(Collectors.joining(",")))
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
