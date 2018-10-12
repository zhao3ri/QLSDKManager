package com.item.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.item.constants.Constants;

import core.module.orm.Page;
import core.module.utils.Struts2Utils;

/**
 * 
 * @author ljqiang
 *
 */
public class CookieUtils {
	
	/**
	 * 设置cookie 
	 * @param response
	 * @param key
	 * @param value
	 */
	public static void setCookieValue(HttpServletResponse response,String key, String value){
		Cookie stationCookie = new Cookie(key,value);
		stationCookie.setDomain(Constants.COOKIE_DOMAIN);
		stationCookie.setPath(Constants.COOKIE_PATH);
		response.addCookie(stationCookie);
	}
	
	/**
	 * 读取用户的cookie，得到默认用户名和密码
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request,String key){
		String temp = null;
		Cookie[] cookies = null;
		try{
			cookies = request.getCookies();
			if(cookies!=null){
			    for (int i = 0; i < cookies.length; i++) {
			       Cookie c = cookies[i];  
			       if(c.getName().equalsIgnoreCase(key)){
			    	   temp = c.getValue();
			       }
//			       System.out.println("-----------cookie key:"+c.getName()+""+" value:"+c.getValue());
			    }
			}
		}catch(Exception e){
			return null;
		}
		return temp;
	}
	
	public static <T> Page<T> setCookiePageSize(Page<T> page) {
		if (page == null)
			return new Page<T>(10);
		HttpServletRequest request = Struts2Utils.getRequest();
		String cookie_pageSize = getCookieValue(request,Constants.COOKIE_PAGESIZE);
		if (cookie_pageSize != null && !cookie_pageSize.isEmpty())
			page.setPageSize(Integer.valueOf(cookie_pageSize));
		return page;
	}
}
