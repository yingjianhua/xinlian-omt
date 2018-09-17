package com.irille.omt.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.irille.core.controller.BaseAction;
import com.irille.omt.dao.auth.RoleOperationDao;
import com.irille.omt.dao.sys.AccessDao;
import com.irille.omt.dao.usr.UserDao;
import com.irille.omt.entity.usr.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ItpUserLogin extends AbstractInterceptor {

	private static final long serialVersionUID = -5308775862619271060L;

	private String autoLogin = "";
	
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		SessionMsg sessionmsg = ItpSessionmsg.getSessionmsg();
		if(!sessionmsg.haveUser() && !"".equals(autoLogin)) {
			User user = UserDao.findByUsername(autoLogin);
			sessionmsg.setCurUser(user);
		}
		if(!sessionmsg.haveUser()) {
			BaseAction.writeTimeout();
			return null;
		}
		
		ActionProxy proxy = actionInvocation.getProxy();
		
		final String actionName = actionInvocation.getProxy().getActionName();
		if(!AccessDao.listAccess().contains(actionName)) {
			//非法请求
			BaseAction.writeErr(0, "非法请求");
			return null;
		}
		if(!RoleOperationDao.listActionByUser(sessionmsg.getCurUser().getPkey()).contains(actionName)) {
			//没有权限
			BaseAction.writeErr(0, "没有权限");
			return null;
		}
		return actionInvocation.invoke();
	}
	
	/**
	 * 在需要登录后才能使用的方法上加上该注释</p>
	 * 在调用方法前会进行校验</p>
	 * 若该方法返回类型为String,即页面转发的action,会自动跳转到登录页面,登录完成后返回该页面</p>
	 * 若该方法的返回类型为void,即数据请求的action,会自动条用writeTimeout方法,返回{ret:-1}</p>
	 * 
	 * @author yingjianhua
	 *
	 */
	@Target({ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface NeedLogin {

	}
	
	public static void s(Object s) {
		System.out.println(s);
	}
	
	public String getAutoLogin() {
		return autoLogin;
	}
	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}
	public String getJumpUrl(HttpServletRequest request) {
		String params = getParameters(request);
		if("".equals(params)) {
			return getServletUrl(request);
		} else {
			return getServletUrl(request)+"?"+params;
		}
	}
	public String getParameters(HttpServletRequest request) {
		String params = "";
		Enumeration<?> names = request.getParameterNames();
		int i=0;
		while(names.hasMoreElements()) {
			Object element  = names.nextElement();
			if(i!=0)
				params += "&";
			params += (element+"="+request.getParameter(element.toString()));
			i++;
		}
		return params;
	}
	public String getServletUrl(HttpServletRequest request) {
		return request.getRequestURI();
	}
}
