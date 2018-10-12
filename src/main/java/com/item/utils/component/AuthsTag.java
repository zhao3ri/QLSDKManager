package com.item.utils.component;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.item.service.authority.AuthCacheManage;

public class AuthsTag extends BodyTagSupport {
	private static final long serialVersionUID = -5578741559818893674L;
	private String authUrls;
	
	public int doStartTag() throws JspException {
		boolean isAllowAccess = false;
		if(authUrls.indexOf(",") == -1){
			isAllowAccess = isAllowAccess(authUrls);
		}
		else{
			String[] authUrlArr = authUrls.split(",");
			for(String authUrl : authUrlArr){
				isAllowAccess = isAllowAccess(authUrl);
				//只要有一个资源可访问，就能够进入
				if(isAllowAccess){
					break;
				}
			}
		}
		return isAllowAccess?EVAL_BODY_INCLUDE:SKIP_BODY;
	}
	
	/**
	 * 判断资源是否允许访问
	 * @param authUrl
	 * @return
	 */
	private boolean isAllowAccess(String authUrl){
		boolean isAllowAccess = false;
		//判断是否是受保护资源
		if(AuthCacheManage.allAuthMap.containsKey(authUrl)){
			String roleAuthStr = (String) pageContext.getSession().getAttribute("roleAuthStr");
			// 权限不为空
			if(roleAuthStr != null && !"".equals(roleAuthStr)){
				// 该角色的权限包含所请求的URL，则表示有权限访问
				if(roleAuthStr.indexOf(authUrl) !=-1){
					isAllowAccess = true;
				}
			}
     	}else{
     		isAllowAccess = true;
     	}
		return isAllowAccess;
	}
	
	public String getAuthUrls() {
		return authUrls;
	}
	public void setAuthUrls(String authUrls) {
		this.authUrls = authUrls;
	}

}
