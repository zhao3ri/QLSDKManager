package com.item.utils.component;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.item.service.authority.AuthCacheManager;



/**
 * 权限自定义标签，用于控制页面链接或者按钮的显示与隐藏
 * @author wqb
 *
 */
public class AuthTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String authUrl;	//请求地址

	public int doStartTag() throws JspException {
		boolean isAllowAccess = false;
		//判断是否是受保护资源
		if(AuthCacheManager.getInstance().getAllPermissions().containsKey(authUrl)){
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
		return isAllowAccess?EVAL_BODY_INCLUDE:SKIP_BODY;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
}
