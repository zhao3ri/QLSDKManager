package com.item.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IpUtils {

	private static final Log log = LogFactory.getLog(IpUtils.class.getName());
	
	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("HTTP_CLIENT_IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getRemoteAddr();

		return ip;
	}
	
	public static String getIp(HttpServletRequest request) {
		
		String ip = request.getHeader("X-Forwarded-For"); 
		
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			log.info("request.getHeader(Proxy-Client-IP)="+request.getHeader("Proxy-Client-IP"));
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			log.info("request.getRemoteAddr()="+request.getRemoteAddr());
		}
		if( ip!=null && ip.length()>15){
			try{			
				log.warn(" ***  IP= "+ip);
				ip=ip.substring(2+ip.lastIndexOf(","));		
			}catch(Exception e){
				log.error(" ***获取IP地址异常:="+ip);
			}
		}
		
		return ip;

	}
	
}
