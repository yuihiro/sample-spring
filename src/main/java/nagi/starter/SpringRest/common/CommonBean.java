package nagi.starter.SpringRest.common;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import nagi.starter.SpringRest.Application;

@Component
public class CommonBean implements ServletContextAware {

	@Autowired
	Environment env;

	public static String app_name;
	public static Boolean debug_mode;
	public static String main_page;

	public static Log log;

	static {
		log = LogFactory.getLog(Application.class);
	}

	public CommonBean() {
		System.out.println("초기화");
		System.out.println("app_name");
		//log = LogFactory.getLog(Application.class);

		//app_name = env.getProperty("app_name");
		//debug_mode = Boolean.parseBoolean(env.getProperty("debug_mode"));
		//main_page = env.getProperty("main_page");

		//System.out.println(app_name);

		//LogBean.log.info(MethodHandles.lookup().lookupClass());
	}

	@PostConstruct
	public void init() {
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		//servletContext.setAttribute("myKey", value);
	}
}
