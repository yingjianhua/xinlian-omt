package com.irille.omt.service.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irille.omt.dao.sys.OperationDao;
import com.irille.omt.entity.sys.Operation;
import com.irille.omt.view.sys.OperationView;

public class OperationService {
	
	private static final Logger logger = LoggerFactory.getLogger(OperationService.class);
	
	public static class OperationBuilder {
		private List<Integer> needDel = new ArrayList<>();
		private Map<Integer, List<OperationView>> needAdd = new HashMap<>();
		private Map<Integer, List<OperationView>> needAddOrUpd = new HashMap<>();
		
		public void init() {
			needAddOrUpd.forEach((menu, views)->{
				List<OperationView> views2 = views.stream().collect(Collectors.toList());
				List<Operation> list = OperationDao.listByMenu(menu);
				List<Operation> list2 = list.stream().collect(Collectors.toList());
				int sort = 0;
				for(OperationView view:views) {
					for(Operation bean:list) {
						if(view.getName().equals(bean.getName())) {
							if((bean.getAction()!=null&&bean.getAction().equals(view.getAction()))||(bean.getAction()==null&&"".equals(view.getAction()))) {
								//名字不变,action不变,判断为同一个operation,执行更新操作
								bean.setSort(sort++);
								bean.upd();
								logger.info("更新操作:{}[{}]", bean.getName(), bean.getPkey());
								list2.remove(bean);
								views2.remove(view);
							}
						}
					}
				}
				list2.forEach(bean->{
					OperationDao.del(bean.getPkey());
					logger.info("删除操作:{}[{}]", bean.getName(), bean.getPkey());
				});
				for(OperationView view:views2) {
					Operation op = new Operation();
					op.setName(view.getName());
					op.setMenu(menu);
					op.setAction("".equals(view.getAction())?null:view.getAction());
					op.setSort(sort++);
					op.ins();
					logger.info("新增操作:{}[{}]", op.getName(), op.getPkey());
				}
			});
			needAdd.forEach((menu, views)->{
				int sort = 0;
				for(OperationView view:views) {
					Operation op = new Operation();
					op.setName(view.getName());
					op.setMenu(menu);
					op.setAction("".equals(view.getAction())?null:view.getAction());
					op.setSort(sort++);
					op.ins();
					logger.info("新增操作:{}[{}]", op.getName(), op.getPkey());
				}
			});
			needDel.forEach(menu->{
				OperationDao.delByMenu(menu);
			});
		}
		public static void delByMenu(Integer menu) {
			OperationDao.delByMenu(menu);
		}
		public void add(Integer menu, List<OperationView> views) {
			if(views==null||views.size()==0)
				return;
			else
				needAdd.put(menu, views);
		}
		public void upd(Integer menu, List<OperationView> views) {
			if(views==null||views.size()==0)
				return;
			else
				needAddOrUpd.put(menu, views);
		}
	}

//	public static void delOperationByMenu(Integer menu) {
//		OperationDao.delByMenu(menu);
//	}
//	public static void addOperation(Integer menu, List<OperationView> views) {
//		if(views==null||views.size()==0)
//			return;
//		views.forEach(view->{
//			Operation op = new Operation();
//			op.setName(view.getName());
//			op.setMenu(menu);
//			op.setAction("".equals(view.getAction())?null:view.getAction());
//			op.setSort(view.getSort());
//			op.ins();
//			logger.info("新增操作:"+op.getName());
//		});
//	}
//	public static void updOperation(Integer menu, List<OperationView> views) {
//		if(views==null||views.size()==0)
//			return;
//		List<OperationView> views2 = views.stream().collect(Collectors.toList());
//		List<Operation> list = OperationDao.listByMenu(menu);
//		List<Operation> list2 = list.stream().collect(Collectors.toList());
//		list.forEach(bean->{
//			views.forEach(view->{
//				if(view.getName().equals(bean.getName())) {
//					if((bean.getAction()!=null&&bean.getAction().equals(view.getAction()))||(bean.getAction()==null&&"".equals(view.getAction()))) {
//						//名字不变,action不变,判断为同一个operation,执行更新操作
//						bean.setSort(view.getSort());
//						bean.upd();
//						logger.info("更新操作:"+bean.getName());
//						list2.remove(bean);
//						views2.remove(view);
//					}
//				}
//			});
//		});
//		list2.forEach(bean->{
//			OperationDao.del(bean.getPkey());
//			logger.info("删除操作:"+bean.getName());
//		});
//		addOperation(menu, views2);
//	}
}
