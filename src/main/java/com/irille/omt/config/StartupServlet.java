package com.irille.omt.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.irille.omt.pub.annotation.Scanner;

import irille.pub.svr.Controller;

public class StartupServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 启动时运行
	 * <p>必须要在<code>@Configuration</code>注解的类下面才会生效
	 * 
	 * @see Configuration
	 * @author Jianhua Ying
	 *
	 */
	@Target({ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface runWithStartup {
		
	}

	@Override
	public void init() throws ServletException {
		super.init();
		Long l1 = System.currentTimeMillis();
		Scanner
		.findClassByAnnotation(Controller.class, "com.irille.omt.config")
		.forEach(clazz->Scanner.findMethodByAnnotation(runWithStartup.class, clazz));
		System.out.println(System.currentTimeMillis()-l1);
	}
}
