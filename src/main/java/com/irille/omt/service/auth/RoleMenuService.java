package com.irille.omt.service.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.irille.core.controller.JsonWriter;
import com.irille.core.web.config.AppConfig;
import com.irille.omt.dao.auth.RoleMenuDao;
import com.irille.omt.dao.auth.RoleOperationDao;
import com.irille.omt.entity.auth.RoleMenu;
import com.irille.omt.entity.sys.Menu;
import com.irille.omt.entity.sys.Operation;
import com.irille.omt.view.sys.MenuView;
import com.irille.omt.view.sys.MenusView;
import com.irille.omt.view.sys.OperationView;

public class RoleMenuService {

	public static void main(String[] args) throws JsonProcessingException {
		System.out.println(AppConfig.objectMapper.writeValueAsString(listViewByRole(1)));
	}

	@BeforeClass
	public static void init() {
		Operation.table.getClass();
	}

	@Test
	public void testListViewByRole() throws JsonProcessingException {
		JsonWriter.toConsole(RoleMenuService.listViewByRole(1));
	}

	public static List<MenuView> listViewByRole(Integer role) throws JsonProcessingException {
		List<Operation> ops = RoleOperationDao.listOperationByRole(role);
		Map<Integer, List<OperationView>> menu_ops_map = new HashMap<>();
		ops.forEach(bean -> {
			if (!menu_ops_map.containsKey(bean.getMenu()))
				menu_ops_map.put(bean.getMenu(), new ArrayList<>());
			menu_ops_map.get(bean.getMenu()).add(new OperationView() {
				{
					setName(bean.getName());
					setAction(bean.getAction());
				}
			});
		});
		List<Menu> menus = RoleMenuDao.listMenuByRole(role);
		Map<Integer, Menu> pkey_menu_map = menus.stream().collect(Collectors.toMap(Menu::getPkey, bean -> bean));
		Map<String, MenuView> name_menu_map = menus.stream().map(bean -> {
			return new MenuView() {
				{
					setName(bean.getName());
					setFullName(bean.getFullName());
					setRoute(bean.getRoute());
					setComponent(bean.getComponent());
					setLeaf(bean.getLeaf());
					if (isLeaf()) {
						setOps(new ArrayList<>());
					} else {
						setSubs(new ArrayList<>());
					}
				}
			};
		}).collect(Collectors.toMap(MenuView::getFullName, view -> view));
		MenusView views = new MenusView();
		views.setMenus(new ArrayList<>());
		for (Menu bean : menus) {
			if (bean.getUp() == null) {
				views.getMenus().add(name_menu_map.get(bean.getFullName()));
			} else {
				MenuView up = name_menu_map.get(pkey_menu_map.get(bean.getUp()).getFullName());
				if (!up.isLeaf()) {
					MenuView view = name_menu_map.get(bean.getFullName());
					if (view.isLeaf()) {
						view.setOps(menu_ops_map.get(bean.getPkey()));
					}
					up.getSubs().add(view);
				}
			}
		}
		return views.getMenus();
	}

	public static void giveAllRoleMenu(Integer role) {
		RoleMenuDao.addAllMenu4Role(role);
	}

	public static void clearAllIfRoleOrMenuNoExists() {
		RoleMenuDao.clearAllIfRoleNoExists();
		RoleMenuDao.clearAllIfMenuNoExists();
	}

	public static List<RoleMenu> list() {
		return RoleMenuDao.list();
	}
}
