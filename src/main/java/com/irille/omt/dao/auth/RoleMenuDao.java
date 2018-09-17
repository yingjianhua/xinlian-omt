package com.irille.omt.dao.auth;

import java.util.List;

import com.irille.core.repository.BaseDao;
import com.irille.omt.entity.auth.Role;
import com.irille.omt.entity.auth.RoleMenu;
import com.irille.omt.entity.auth.RoleMenu.T;
import com.irille.omt.entity.sys.Menu;

public class RoleMenuDao extends BaseDao<RoleMenu> {
	
	public static List<Menu> listMenuByRole(Integer role) {
		return SELECT(Menu.class).LEFT_JOIN(RoleMenu.class, Menu.T.PKEY, T.MENU).WHERE(T.ROLE, "=?", role).queryList();
	}

	public static List<RoleMenu> list() {
		return SELECT(RoleMenu.class).queryList();
	}
	
	public static void clearAllIfRoleNoExists() {
		String sql = "DELETE FROM "+RoleMenu.TB.getCodeSqlTb()+" WHERE "+T.ROLE+" IN (SELECT a."+T.ROLE+" FROM (SELECT a."+T.ROLE+" FROM "+RoleMenu.TB.getCodeSqlTb()+" a LEFT JOIN "+Role.TB.getCodeSqlTb()+" b ON a."+T.ROLE+" = b."+Role.T.PKEY+" WHERE b."+Role.T.PKEY+" IS NULL ) a)";
		sql(sql).executeUpdate();
	}
	
	/**
	 * 给指定的角色在角色菜单关联表中添加所有的菜单的关联
	 * @param role
	 */
	public static void addAllMenu4Role(Integer role) {
		sql("insert ignore into "+RoleMenu.TB.getCodeSqlTb()+" ("+T.ROLE+", "+T.MENU+") select ?, "+Menu.T.PKEY+" from "+Menu.TB.getCodeSqlTb(), role).executeUpdate();
	}
	
	public static void main(String[] args) {
		String sql = "insert ignore into "+RoleMenu.TB.getCodeSqlTb()+" ("+T.ROLE+", "+T.MENU+") select ?, "+Menu.T.PKEY+" from "+Menu.TB.getCodeSqlTb();
		System.out.println(sql);
	}
}
