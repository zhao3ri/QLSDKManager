package core.module.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.UrlEscapers;
import com.item.utils.PropertyUtils;
import com.opensymphony.xwork2.ActionSupport;

public class Struts2Action extends ActionSupport {

	private static final long serialVersionUID = 6758024434424958754L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public static final String RELOAD = "reload";
	
	
	 protected String encodeUrlStr(String str){
	    	try {
				return UrlEscapers.urlFragmentEscaper().escape(str);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return "undefined";

	    }
	
	
	/**
	 * 配置网站路径
	 * @return
	 */
	public String getCtx(){
//		return PropertyUtils.get("base.url");
		return PropertyUtils.getPath();
	}
	
	/**
	 * 获得session用户ID
	 * @author liuxh 2011-02-23
	 * @return 用户ID
	 */
//	public Long sessionAid(){
//		return ((User)Struts2Utils.getSession().getAttribute("sessionUser")).getId();
//	}
//	
//	public User loginUser(){
//		return ((User)Struts2Utils.getSession().getAttribute("sessionUser"));
//	}

}
