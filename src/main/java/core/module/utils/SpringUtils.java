package core.module.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringUtils {

	public static WebApplicationContext getCtx(ServletContext context) {
		return WebApplicationContextUtils.getWebApplicationContext(context);
	}
	
	public static Object getBeanByName(String beanName){
		 WebApplicationContext ctx = getCtx(Struts2Utils.getSession().getServletContext());
         return ctx.getBean(beanName);		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBeanByClass(Class<T> clazz){
		WebApplicationContext ctx = getCtx(Struts2Utils.getSession().getServletContext());
		String name = clazz.getSimpleName();
		Character secondWord = name.charAt(1);
		if (Character.isLowerCase(secondWord))
			name = name.substring(0,1).toLowerCase() + name.substring(1);
		return (T)ctx.getBean(name);
	}
	
	public static Object getBeanByName(String beanName,HttpSession httpSession){
		WebApplicationContext ctx = getCtx(httpSession.getServletContext());
		return ctx.getBean(beanName);
	}
}
