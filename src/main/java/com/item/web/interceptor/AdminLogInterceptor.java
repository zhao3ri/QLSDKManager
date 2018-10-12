package com.item.web.interceptor;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.item.utils.DateUtils;
import com.item.utils.IpUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * 用户操作日志符拦截器
 * @author liuxh
 * @since 2012-03-01
 *
 */
public class AdminLogInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -6136383560927216463L;
	private static final Logger logger = Logger.getLogger("operlog");
	
	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
		String userName=(String)request.getSession().getAttribute("sessionUser");
		try {
			Enumeration<String> e=request.getParameterNames();
		    String parameterName, parameterValue,servletParam="";
	        while(e.hasMoreElements()){
	            parameterName = e.nextElement();
	            parameterValue = request.getParameter(parameterName);
	            servletParam+=parameterName + "=" + parameterValue + "&";
	        }
	        if(servletParam.endsWith("&")){
	        	servletParam=servletParam.substring(0, servletParam.length()-1);
	        }
        	logger.info(userName+"("+IpUtils.getClientIpAddr(request)+")["+DateUtils.format(new Date())+"] 操作："+request.getRequestURI()+(servletParam.equals("")?"":"?")+servletParam);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(userName+"("+IpUtils.getClientIpAddr(request)+")["+DateUtils.format(new Date())+"] 操作："+"保存管理员操作日志出错：" + e);
			e.printStackTrace();
		}
		
		return invocation.invoke();
	}
}
