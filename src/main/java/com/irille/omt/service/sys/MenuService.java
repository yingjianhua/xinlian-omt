package com.irille.omt.service.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irille.omt.dao.sys.MenuDao;
import com.irille.omt.entity.sys.Menu;
import com.irille.omt.service.sys.OperationService.OperationBuilder;
import com.irille.omt.view.sys.MenuView;

public class MenuService {

	private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
	
	public static class MenuBuilder {
		
		Map<String, MenuView> new_map;
		HashMap<String, Menu> old_map;
		Map<String, Menu> del_map;
		OperationService.OperationBuilder operationBuilder = new OperationBuilder();
		
		private int sort = 0;
		public MenuBuilder(List<MenuView> menus) {
			new_map = viewList2Map(menus);
			old_map = beanList2Map(MenuDao.list());
			del_map = (HashMap<String, Menu>)old_map.clone();
		}
		public void init() {
			for(Entry<String, MenuView> entry:new_map.entrySet()) {
				if(old_map.containsKey(entry.getKey())) {
					Menu bean = del_map.remove(entry.getKey());
					bean.setLeaf(entry.getValue().getSubs()==null?true:false);
					bean.setRoute(entry.getValue().getRoute());
					bean.setComponent(entry.getValue().getComponent());
					bean.setSort(sort++);
					bean.upd();
					logger.info("更新菜单:{}", bean.getFullName());
					if(entry.getValue().getOps()!=null)
						operationBuilder.upd(bean.getPkey(), entry.getValue().getOps());
				} else {
					insWithUpIfNoExists(entry.getValue(), old_map);
				}
			}
			del_map.forEach((name, bean)->{
				bean.del();
				logger.info("删除菜单:{}", bean.getFullName());
				operationBuilder.delByMenu(bean.getPkey());
			});
			operationBuilder.init();
		}
		
		private void insWithUpIfNoExists(MenuView view, Map<String, Menu> all) {
			if(all.containsKey(view.getFullName()))
				return;
			if(view.getUp()!=null&&!all.containsKey(view.getUp().getFullName())) {
				insWithUpIfNoExists(view.getUp(), all);
			}
			Menu bean = new Menu();
			bean.setName(view.getName());
			bean.setRoute(view.getRoute());
			bean.setComponent(view.getComponent());
			bean.setFullName(view.getFullName());
			bean.setUp(view.getUp()==null?null:all.get(view.getUp().getFullName()).getPkey());
			bean.setLeaf(view.getSubs()==null?true:false);
			bean.setSort(sort++);
			bean.ins();
			logger.info("新增菜单:{}", bean.getFullName());
			if(view.getOps()!=null)
				operationBuilder.add(bean.getPkey(), view.getOps());
			all.put(bean.getFullName(), bean);
		}
		
		private static HashMap<String, Menu> beanList2Map(List<Menu> menus) {
			HashMap<String, Menu> map = new HashMap<>();
			menus.forEach(menu->map.put(menu.getFullName(), menu));
			return map;
		}
		private static Map<String, MenuView> viewList2Map(List<MenuView> menus) {
			Map<String, MenuView> map = new HashMap<>();
			viewList2Map(menus, null, map, 1);
			return map;
		}
		
		private static void viewList2Map(List<MenuView> menus, MenuView up, Map<String, MenuView> map, int deep) {
			if(deep>3) {
				return;
			}
			for(MenuView sub:menus) {
				sub.setUp(up);
				sub.setFullName(up==null?sub.getRoute():(up.getFullName()+"/"+sub.getRoute()));
				map.put(sub.getFullName(), sub);
				if(sub.getSubs()!=null) {
					viewList2Map(sub.getSubs(), sub, map, ++deep);
				}
			}
		}
	}
	
//	/**
//	 * 初始化平台的展示菜单
//	 */
//	/**
//	 * @author Jianhua Ying
//	 * @throws IOException 
//	 * @throws JsonMappingException 
//	 * @throws JsonParseException 
//	 */
//	public static void initMenu(List<MenuView> menus) throws JsonParseException, JsonMappingException, IOException {
//		Map<String, MenuView> new_map = viewList2Map(menus);
//		HashMap<String, Menu> old_map = beanList2Map(MenuDao.list());
//		Map<String, Menu> del_map = (HashMap<String, Menu>)old_map.clone();
//		int sort = 0;
//		for(Entry<String, MenuView> entry:new_map.entrySet()) {
//			if(old_map.containsKey(entry.getKey())) {
//				Menu bean = del_map.remove(entry.getKey());
//				bean.stLeaf(entry.getValue().getSubs()==null?true:false);
//				bean.setSort(sort++);
//				bean.upd();
//				log.info("更新菜单:"+bean.getFullName());
//				if(entry.getValue().isLeaf())
//					OperationService.updOperation(bean.getPkey(), entry.getValue().getOps());
//			} else {
//				insWithUpIfNoExists(entry.getValue(), old_map);
//			}
//		}
//		del_map.forEach((name, bean)->{
//			bean.del();
//			log.info("删除菜单:"+bean.getFullName());
//			OperationService.delOperationByMenu(bean.getPkey());
//		});
//	}
//	private static void insWithUpIfNoExists(MenuView view, Map<String, Menu> all) {
//		if(all.containsKey(view.getFullName()))
//			return;
//		if(view.getUp()!=null&&!all.containsKey(view.getUp().getFullName())) {
//			insWithUpIfNoExists(view.getUp(), all);
//		}
//		Menu bean = new Menu();
//		bean.setName(view.getName());
//		bean.setFullName(view.getFullName());
//		bean.setUp(view.getUp()==null?null:all.get(view.getUp().getFullName()).getPkey());
//		bean.stLeaf(view.isLeaf());
//		bean.setSort(view.getSort());
//		bean.ins();
//		log.info("新增菜单:"+bean.getFullName());
//		if(view.isLeaf())
//			OperationService.addOperation(bean.getPkey(), view.getOps());
//		all.put(bean.getFullName(), bean);
//	}
//	
//	private static HashMap<String, Menu> beanList2Map(List<Menu> menus) {
//		HashMap<String, Menu> map = new HashMap<>();
//		menus.forEach(menu->map.put(menu.getFullName(), menu));
//		return map;
//	}
//	private static Map<String, MenuView> viewList2Map(List<MenuView> menus) {
//		Map<String, MenuView> map = new HashMap<>();
//		viewList2Map(menus, null, map, 1);
//		return map;
//	}
//	
//	private static void viewList2Map(List<MenuView> menus, MenuView up, Map<String, MenuView> map, int deep) {
//		if(deep>3) {
//			return;
//		}
//		for(MenuView sub:menus) {
//			sub.setUp(up);
//			sub.setFullName(up==null?sub.getName():(up.getFullName()+"_"+sub.getName()));
//			map.put(sub.getFullName(), sub);
//			if(!sub.isLeaf()) {
//				viewList2Map(sub.getSubs(), sub, map, ++deep);
//			}
//		}
//	}
//	
//	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
//		MenusView list = AppConfig.objectMapper.readValue(MenuService.class.getResourceAsStream("/menu.json"), MenusView.class);
//		MenuService.initMenu(list.getMenus());
//	}
	
}
