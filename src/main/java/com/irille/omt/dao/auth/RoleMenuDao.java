package com.irille.omt.dao.auth;

import java.util.List;

import com.irille.core.repository.EntityRepository;
import com.irille.omt.entity.auth.Role;
import com.irille.omt.entity.auth.RoleMenu;
import com.irille.omt.entity.auth.RoleMenu.T;
import com.irille.omt.entity.sys.Menu;

public class RoleMenuDao extends EntityRepository<RoleMenu> {
	
	public static List<Menu> listMenuByRole(Integer role) {
		return selectFrom(Menu.class).leftJoin(RoleMenu.class, Menu.T.pkey, T.menu).where(T.role.eq(role)).orderBy(Menu.T.sort.asc()).queryList();
	}

	public static List<RoleMenu> list() {
		return selectFrom(RoleMenu.class).queryList();
	}
	
	public static void clearAllIfRoleNoExists() {
		String sql = "DELETE FROM "+RoleMenu.table.name()+" WHERE "+T.role+" IN (SELECT a."+T.role+" FROM (SELECT a."+T.role+" FROM "+RoleMenu.table.name()+" a LEFT JOIN "+Role.table.name()+" b ON a."+T.role+" = b."+Role.T.pkey+" WHERE b."+Role.T.pkey+" IS NULL ) a)";
		sql(sql).executeUpdate();
	}
	
	public static void clearAllIfMenuNoExists() {
		String sql = "DELETE FROM "+RoleMenu.table.name()+" WHERE "+T.menu+" IN (SELECT a."+T.menu+" FROM (SELECT a."+T.menu+" FROM "+RoleMenu.table.name()+" a LEFT JOIN "+Menu.table.name()+" b ON a."+T.menu+" = b."+Menu.T.pkey+" WHERE b."+Menu.T.pkey+" IS NULL ) a)";
		sql(sql).executeUpdate();
	}
	
	/**
	 * 给指定的角色在角色菜单关联表中添加所有的菜单的关联
	 * @param role
	 */
	public static void addAllMenu4Role(Integer role) {
		sql("insert ignore into "+RoleMenu.table.name()+" ("+T.role+", "+T.menu+") select ?, "+Menu.T.pkey+" from "+Menu.table.name(), role).executeUpdate();
	}
	
	public static void main(String[] args) {
		String sql = "insert ignore into "+RoleMenu.table.name()+" ("+T.role+", "+T.menu+") select ?, "+Menu.T.pkey+" from "+Menu.table.name();
		System.out.println(sql);
	}
}
