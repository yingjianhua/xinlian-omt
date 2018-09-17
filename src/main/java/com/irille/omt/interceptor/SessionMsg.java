package com.irille.omt.interceptor;

import com.irille.omt.entity.usr.User;

/**
 * 存放在session中,用于统计记录该会话的用户消息
 * @author yingjianhua
 *
 */
public class SessionMsg {
	
	/**
	 * 存放于session中的键值
	 */
	public static final String session_key = "SESSION_MSG";
	
	private User curUser;
	
	public static SessionMsg build() {
		SessionMsg msg = new SessionMsg();
		return msg;
	}

	public User getCurUser() {
		return curUser;
	}
	public void setCurUser(User curUser) {
		this.curUser = curUser;
	}
	public boolean haveUser() {
		return curUser==null?false:true;
	}
	
}
