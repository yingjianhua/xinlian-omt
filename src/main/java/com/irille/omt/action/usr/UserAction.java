package com.irille.omt.action.usr;

import java.io.IOException;

import com.irille.omt.action.OmtAction;
import com.irille.omt.entity.usr.User;
import com.irille.omt.service.usr.UserService;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller(module="用户模块", name="用户管理")
public class UserAction extends OmtAction<User, Integer>{
	
	private String username;
	private String password;
	
	@RequestMapping(alias="登录", description="用户登录", sort=1)
	public void signIn(String user) throws IOException {
		UserService.signIn(username, password);
		write();
	}
	
}
