package com.irille.omt.config;

import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException.IWebMessage;

public enum WebMessage implements IWebMessage {

	third_error("服务器连接异常", ReturnCode.tools_unknow),
	success("提交成功", ReturnCode.success),
	valid_error("{0}校验失败", ReturnCode.valid_unknow),
	timeout("登录超时,请重新登录", ReturnCode.service_timeout),
	user_login_wrongvcode("验证码错误", ReturnCode.service_verification_code),
	user_login_notexists("用户不存在", ReturnCode.service_wrong_data),
	user_login_wrongpassword("密码与用户不匹配", ReturnCode.service_wrong_data),
	
	rquest_gone("非法请求", ReturnCode.service_gone),//资源不可用
	rquest_unauthorized("没有权限", ReturnCode.service_unauthorized), //没有权限
	
	valid_notnull("{0}不能为空", ReturnCode.valid_notnull),
	valid_phone("手机格式不正确", ReturnCode.valid_regex),
	valid_email("邮箱格式不正确", ReturnCode.valid_regex),
	valid_param("{0} 参数异常", ReturnCode.valid_illegal),
	
	;
	private String value;
	private ReturnCode code;

	private WebMessage(String value, ReturnCode code) {
		this.value = value;
		this.code = code;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public ReturnCode getCode() {
		return this.code;
	}

}
