package com.item.utils;

import org.apache.struts2.dispatcher.mapper.ActionMapping;

import com.item.domain.authority.Data;
import com.item.domain.authority.IdentityData;
import com.item.domain.authority.User;
import com.item.service.authority.AuthCacheManager;

import core.module.orm.MapBean;
import core.module.utils.Struts2Utils;

/**
 * 用户工具类
 *
 * @author ljqiang
 * @since 2012-02-18
 */
public class UserTools {

    /**
     * 返回该用户所有信息
     *
     * @return
     */
    public static User getUserClass() {
        return (User) Struts2Utils.getSession().getAttribute("sessionUserInfo");
    }

    /**
     * 返回该用户ID
     *
     * @return
     */
    public static Long getUserId() {
        User u = getUserClass();
        return u != null ? u.getId() : null;
    }

    /**
     * 返回该用户的身份ID
     *
     * @return
     */
    public static Long getRoleId() {
        User u = getUserClass();
        return u != null ? u.getIdentityId() : null;
    }

    /**
     * 返回该用户的部门ID
     *
     * @return
     */
    public static Long getDid() {
        User u = getUserClass();
        return u != null ? u.getDid() : null;
    }

    /**
     * 返回该用户的小组ID
     *
     * @return
     */
    public static Long getGid() {
        User u = getUserClass();
        return u != null ? u.getGid() : null;
    }

    /**
     * 通过身份和数据集获取身份对该数据集的查看范围
     *
     * @param identityId
     * @param datasetId
     * @return
     */
    public static String getLevelByDatasetId(Long identityId, Long datasetId) {
        return String.valueOf(AuthCacheManager.getInstance().getLevelByDatasetId(identityId, datasetId));
    }

    public static String getDatasetKeyFromRequest() {
        ActionMapping mapping = Struts2Utils.getActionMapping();
        StringBuffer sb = new StringBuffer();
        String nameSpace = mapping.getNamespace();
        String name = mapping.getName();
        String extension = mapping.getExtension();
        sb.append(nameSpace != null ? nameSpace : "")
                .append("/").append(name != null ? name : "")
                .append(".").append(extension != null ? extension : "");
        return sb.toString();
    }

    /**
     * 通过数据集键获取数据集ID
     *
     * @param datasetKey
     * @return
     */
    public static Long getDatasetIdByDatasetKey(String datasetKey) {
        return AuthCacheManager.getInstance().getDatasetIdByKey(datasetKey);
    }

    /**
     * 返回该用户对某个数据集的查看范围
     *
     * @return
     */
    public static MapBean getVisitationRightMapBean(MapBean mb) {
        String level = getLevelByDatasetId(getRoleId(), getDatasetIdByDatasetKey(getDatasetKeyFromRequest()));
        if (level != null) {
//			System.out.println("level(:"+level);
            switch (Integer.parseInt(level)) {
                case 1://个人
                    mb.put("UserId", getUserId());
                case 2://小组
                    mb.put("Gid", getGid());
                    mb.put("Did", getDid());
                case 3://部门
                    mb.put("Did", getDid());
//				case 4://总经理
//					return "";
//				case 5://超级管理员
//					return "";
            }
        }
        return mb;
    }

}
