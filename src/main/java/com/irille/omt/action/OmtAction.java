package com.irille.omt.action;

import java.io.Serializable;

import com.irille.core.controller.EntityAction;
import com.irille.core.controller.Writeable;
import com.irille.core.repository.orm.Entity;
import com.irille.omt.entity.usr.User;
import com.irille.omt.interceptor.ItpSessionmsg;

public abstract class OmtAction<T extends Entity, R extends Serializable> extends EntityAction<T, R> implements Writeable {

	protected final User curUser() {
		return ItpSessionmsg.getSessionmsg().getCurUser();
	}
	
}
