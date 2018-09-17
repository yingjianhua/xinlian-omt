package com.irille.omt.action;

import java.io.Serializable;

import com.irille.core.controller.BeanAction;
import com.irille.omt.entity.usr.User;

import irille.pub.bean.BeanMain;

public abstract class OmtAction<T extends BeanMain<?, ?>, R extends Serializable> extends BeanAction<T, R> {

	private static final long serialVersionUID = 1L;
	

	protected User curUser() {
		return null;
		//TODO
	}
}
