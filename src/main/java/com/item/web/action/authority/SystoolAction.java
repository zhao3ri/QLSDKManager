package com.item.web.action.authority;


import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.item.service.authority.AuthCacheManager;
import com.item.utils.EnumUtil;
import com.item.utils.PropertyUtils;

import core.module.utils.SpringUtils;
import core.module.web.Struts2Action;

/**
 * 系统工具
 *
 * @author ljqiang
 * 2013-03-08
 */
public class SystoolAction extends Struts2Action {

    private static final long serialVersionUID = -4705963720187503963L;

    private Map<String, String> cacheIDMap = EnumUtil.getCacheIDMap();

    private String checkedIds;

    /**
     * 清除缓存页面
     *
     * @return
     */
    public String cleanCache() {
        return SUCCESS;
    }

    /**
     * 执行清除数据库缓存
     *
     * @return
     */
    public String doCleanCache() {
        try {
            SqlMapClient client = (SqlMapClient) SpringUtils.getBeanByName("sqlMapClient");
            for (String checkedId : StringUtils.split(checkedIds, ",")) {
                client.flushDataCache(checkedId);
                logger.info("清除数据库缓存，缓存ID：" + checkedId);
            }
//			client.flushDataCache("Mediator.Mediator-cache");
            addActionMessage("清除数据库缓存成功！");
        } catch (Exception e) {
            addActionMessage("清除数据库缓存出错：" + e);
            logger.error("清除数据库缓存出错：" + e);
        }
        return RELOAD;
    }

    /**
     * 执行清除权限缓存
     *
     * @return
     */
    public String doCleanAuthCache() {
        try {
            if ("true".equals(PropertyUtils.get("authority.control"))) {
                //缓存权限
//				AuthCacheManager acm = (AuthCacheManager)SpringUtils.getBeanByName("authCacheManage");
                AuthCacheManager acm = AuthCacheManager.getInstance();
                acm.clear();
                acm.init();
            }
            addActionMessage("清除权限缓存成功！");
        } catch (Exception e) {
            addActionMessage("清除权限缓存出错：" + e);
            logger.error("清除权限缓存出错：" + e);
        }
        return RELOAD;
    }

    public Map<String, String> getCacheIDMap() {
        return cacheIDMap;
    }

    public void setCacheIDMap(Map<String, String> cacheIDMap) {
        this.cacheIDMap = cacheIDMap;
    }

    public String getCheckedIds() {
        return checkedIds;
    }

    public void setCheckedIds(String checkedIds) {
        this.checkedIds = checkedIds;
    }


}
