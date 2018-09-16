package com.irille.omt.service.sys;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.irille.omt.dao.sys.OperationDao;
import com.irille.omt.entity.sys.Operation;
import com.irille.omt.view.sys.OperationView;

public class OperationService {
	
	private static final Logger log = Logger.getLogger(OperationService.class);

	public static void delOperationByMenu(Integer menu) {
		OperationDao.delByMenu(menu);
	}
	public static void addOperation(Integer menu, List<OperationView> views) {
		if(views==null||views.size()==0)
			return;
		views.forEach(view->{
			Operation op = new Operation();
			op.setName(view.getName());
			op.setMenu(menu);
			op.setRoute(view.getRoute());
			op.setAction("".equals(view.getAction())?null:view.getAction());
			op.setSort(view.getSort());
			op.ins();
			log.info("新增操作:"+op.getName());
		});
	}
	public static void updOperation(Integer menu, List<OperationView> views) {
		if(views==null||views.size()==0)
			return;
		List<OperationView> views2 = views.stream().collect(Collectors.toList());
		List<Operation> list = OperationDao.listByMenu(menu);
		List<Operation> list2 = list.stream().collect(Collectors.toList());
		list.forEach(bean->{
			views.forEach(view->{
				if(view.getName().equals(bean.getName())) {
					if((bean.getAction()!=null&&bean.getAction().equals(view.getAction()))||(bean.getAction()==null&&"".equals(view.getAction()))) {
						//名字不变,action不变,判断为同一个operation,执行更新操作
						bean.setRoute(view.getRoute());
						bean.setSort(view.getSort());
						bean.upd();
						log.info("更新操作:"+bean.getName());
						list2.remove(bean);
						views2.remove(view);
					}
				}
			});
		});
		list2.forEach(bean->{
			OperationDao.del(bean.getPkey());
			log.info("删除操作:"+bean.getName());
		});
		addOperation(menu, views2);
	}
}
