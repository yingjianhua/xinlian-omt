package com.irille.omt.service.sys;

import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.irille.core.commons.annotation.Scanner;
import com.irille.omt.dao.sys.AccessDao;
import com.irille.omt.entity.sys.Access;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;

public class AccessService {
	
	private static final Logger log = Logger.getLogger(AccessService.class);
	
	private static final String action_root_package = "com.irille.omt.action";
	
	/**
	 * 根据注解初始化系统的访问权限表
	 * @author Jianhua Ying
	 */
	public static void initAccess() {
		Map<String, Access> map = AccessDao.list().stream().collect(Collectors.toMap(Access::getAction, bean->bean));
		Scanner.findClassByAnnotation(Controller.class, action_root_package)
		.forEach(controller->{
			Controller controllerAnnotation = controller.getAnnotation(Controller.class);
			String moduleShowName = controllerAnnotation.module();
			String controllerShowName = controllerAnnotation.name();
			Scanner
			.findMethodByAnnotation(RequestMapping.class, controller)
			.forEach(method->{
				RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
				String action = (method.getDeclaringClass().getName()+"."+method.getName()).substring(action_root_package.length()+1).replaceAll("\\.", "_");
				if(map.containsKey(action)) {
					Access bean = map.remove(action);
					bean.setModule(moduleShowName);
					bean.setController(controllerShowName);
					bean.setMethod(methodAnnotation.alias());
					bean.setSort(methodAnnotation.sort());
					bean.upd();
					log.info("更新访问控制:"+bean.getModule()+"_"+bean.getController()+"_"+bean.getMethod()+" "+bean.getAction());
				} else {
					Access bean = new Access();
					bean.setModule(moduleShowName);
					bean.setController(controllerShowName);
					bean.setMethod(methodAnnotation.alias());
					bean.setSort(methodAnnotation.sort());
					bean.setAction(action);
					bean.ins();
					log.info("新增访问控制:"+bean.getModule()+"_"+bean.getController()+"_"+bean.getMethod()+" "+bean.getAction());
				}
			});
		});
		map.values().forEach(bean->{
			bean.del();
			log.info("删除访问控制:"+bean.getModule()+"_"+bean.getController()+"_"+bean.getMethod()+" "+bean.getAction());
		});
	}

}
