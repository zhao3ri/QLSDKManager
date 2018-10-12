package com.item.utils;

import core.module.orm.Page;
import core.module.utils.Struts2Utils;

public class InitSearchCondition {
	
	@SuppressWarnings("unchecked")
	public static <T> T initEntity(T t,Integer keepSearchCondition,String key){
    	if (keepSearchCondition != null && keepSearchCondition==1)
    		t = (T)Struts2Utils.getSession().getAttribute("searchCondition_entity_"+key);
    	else
			Struts2Utils.getSession().setAttribute("searchCondition_entity_"+key, t);
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Page<T> initPage(Page<T> page,Integer keepSearchRecord,String key){
    	if (keepSearchRecord != null && keepSearchRecord==1)
    		page = (Page<T>)Struts2Utils.getSession().getAttribute("searchCondition_page_"+key);
    	else
			Struts2Utils.getSession().setAttribute("searchCondition_page_"+key, page);
		return page;
	}
}
