package com.irille.omt.service.auth;

import java.util.List;
import java.util.stream.Collectors;

import com.irille.omt.dao.auth.RoleOperationDao;
import com.irille.omt.view.sys.OperationView;

public class RoleOperationService {
	
	public static List<OperationView> listViewByRole(Integer role) {
		 return RoleOperationDao
		.listOperationByRole(role)
		.stream()
		.map(bean->{
			return new OperationView() {{
				setName(bean.getName());
				setRoute(bean.getRoute());
				setAction(bean.getAction());
				setSort(bean.getSort());
			}};
		})
		.collect(Collectors.toList());
	}
	
	public static void clearAllIfRoleNoExists() {
		RoleOperationDao.clearAllIfRoleNoExists();
	}
	
	public static void giveAllRoleOperation(Integer role) {
		RoleOperationDao.addAllOperation4Role(role);
	}

}
