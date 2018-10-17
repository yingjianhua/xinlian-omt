package com.irille.omt.service.usr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.irille.omt.dao.usr.UserDao;
import com.irille.omt.entity.usr.User;

import irille.pub.DateTools;

public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public static User signIn(String username, String password) {
		User bean = UserDao.findByUsername(username);
		if(bean==null) {
			logger.info("用户不存在:{}", username);
			throw new WebMessageException(ReturnCode.failure, "用户不存在");
		} else if(!bean.getPassword().equals(DateTools.getDigest(bean.getPkey()+password))) {
			logger.info("密码和用户不匹配:{}|{}", username, password);
			throw new WebMessageException(ReturnCode.failure, "密码和用户不匹配");
		}
		return bean;
	}
}
