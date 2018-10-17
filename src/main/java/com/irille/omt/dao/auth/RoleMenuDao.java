package com.irille.omt.dao.auth;

import java.util.List;

import com.irille.core.repository.EntityRepository;
import com.irille.omt.entity.auth.Role;
import com.irille.omt.entity.auth.RoleMenu;
import com.irille.omt.entity.auth.RoleMenu.T;
import com.irille.omt.entity.sys.Menu;

public class RoleMenuDao extends EntityRepository<RoleMenu> {
	
	public static List<Menu> listMenuByRole(Integer role) {
		return selectFrom(Menu.class).leftJoin(RoleMenu.class, Menu.T.PKEY, T.MENU).where(T.ROLE.eq(role)).orderBy(Menu.T.SORT.asc()).queryList();
	}

	public static List<RoleMenu> list() {
		return selectFrom(RoleMenu.class).queryList();
	}
	
	public static void clearAllIfRoleNoExists() {
		String sql = "DELETE FROM "+RoleMenu.table.name()+" WHERE "+T.ROLE+" IN (SELECT a."+T.ROLE+" FROM (SELECT a."+T.ROLE+" FROM "+RoleMenu.table.name()+" a LEFT JOIN "+Role.table.name()+" b ON a."+T.ROLE+" = b."+Role.T.PKEY+" WHERE b."+Role.T.PKEY+" IS NULL ) a)";
		sql(sql).executeUpdate();
	}
	
	public static void clearAllIfMenuNoExists() {
		String sql = "DELETE FROM "+RoleMenu.table.name()+" WHERE "+T.MENU+" IN (SELECT a."+T.MENU+" FROM (SELECT a."+T.MENU+" FROM "+RoleMenu.table.name()+" a LEFT JOIN "+Menu.table.name()+" b ON a."+T.MENU+" = b."+Menu.T.PKEY+" WHERE b."+Menu.T.PKEY+" IS NULL ) a)";
		sql(sql).executeUpdate();
	}
	
	/**
	 * 给指定的角色在角色菜单关联表中添加所有的菜单的关联
	 * @param role
	 */
	public static void addAllMenu4Role(Integer role) {
		sql("insert ignore into "+RoleMenu.table.name()+" ("+T.ROLE+", "+T.MENU+") select ?, "+Menu.T.PKEY+" from "+Menu.table.name(), role).executeUpdate();
	}
	
	public static void main(String[] args) {
		String sql = "insert ignore into "+RoleMenu.table.name()+" ("+T.ROLE+", "+T.MENU+") select ?, "+Menu.T.PKEY+" from "+Menu.table.name();
		System.out.println(sql);
	}
}
