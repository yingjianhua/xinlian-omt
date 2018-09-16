package com.irille.omt.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.irille.omt.pub.annotation.Scanner;

public class StartupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(StartupServlet.class);
	
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
	@interface RunWithStartup {

	}
	
	private static final Map<String, Object> config_cache = new ConcurrentHashMap<>();
	
	private static final Object findConfigInstance(String className) throws Exception {
		if(!config_cache.containsKey(className)||config_cache.get(className)==null) {
			try {
				config_cache.put(className, Class.forName(className).newInstance());
			} catch (Exception e) {
				log.error("can't init "+className, e);
				throw e;
			}
		}
		return config_cache.get(className);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		Scanner
		.findClassByAnnotation(Configuration.class, "com.irille.omt.config")
		.forEach(clazz->{
			List<Method> methods = Scanner.findMethodByAnnotation(RunWithStartup.class, clazz);
			methods.forEach(method->{
				try {
					method.invoke(findConfigInstance(clazz.getName()));
				} catch (Exception e) {
					log.error("init error", e);
					System.exit(1);
				}
			});
		});
	}
	
}
