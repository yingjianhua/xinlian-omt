package com.irille.omt.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.irille.omt.view.usr.UserView;
import com.opensymphony.xwork2.ActionInvocation;

import irille.pub.tb.FldLanguage.Language;

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
	private static final String WW_TRANS_I18N_LOCALE = "WW_TRANS_I18N_LOCALE";
	private static final String[] mobile_device_array = new String[]{"android","windows phone","mobile","iphone"};
	
	private String loginName;
	private Language lang;
	private Boolean isMobile;
	private static Integer count = 0;
	
	public static SessionMsg build() {
		SessionMsg msg = new SessionMsg();
		msg.setIsMobile(false);
		return msg;
	}
	
	public static void update(SessionMsg sessionmsg, ActionInvocation actionInvocation) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
		
		//设置是否为移动设备
		String agent = request.getHeader("User-Agent").toLowerCase();
		Boolean isMobile = false;
		for(String device:mobile_device_array) {
			if(agent.indexOf(device)>0){
				isMobile = true;
				break;
			}
		}
		sessionmsg.setIsMobile(isMobile);
		
	}
	
	public UserView getUser() {
		UserView user = new UserView();
		return user;
	}
	public void setUser(UserView user) {
	}
	public Language getLang() {
		return lang;
	}
	public void setLang(Language lang) {
		this.lang = lang;
	}
	public Boolean getIsMobile() {
		return isMobile;
	}
	public void setIsMobile(Boolean isMobile) {
		this.isMobile = isMobile;
	}
	
}
