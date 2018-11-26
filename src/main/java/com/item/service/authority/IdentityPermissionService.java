package com.item.service.authority;

import java.util.ArrayList;
import java.util.List;

import com.item.domain.authority.IdentityPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.authority.IdentityPermissionDao;

/**
 * 身份权限业务逻辑处理类
 *
 * @author 郭建填
 * @since 2011-02-20
 */
@Service
@Transactional
public class IdentityPermissionService {
    @Autowired
    private IdentityPermissionDao identityPermissionDao;

    /**
     * 取出所有身份权限
     *
     * @return
     */
    public List<IdentityPermission> getIdentityPermissionList() {
        return identityPermissionDao.find("IdentityPermission.listAll", null);
    }

    /**
     * 通过身份获取身份ID权限列表
     *
     * @param id
     * @return
     */
    public List<IdentityPermission> getIdentityPermissionListById(Long id) {
        List<IdentityPermission> identityPermissionList = new ArrayList<IdentityPermission>();
        if (AuthCacheManager.getInstance().getIdentityPermissionCache() != null && !AuthCacheManager.getInstance().getIdentityPermissionCache().isEmpty()) {
            for (IdentityPermission ip : AuthCacheManager.getInstance().getIdentityPermissionCache()) {
                if (ip.getIdentityId().equals(id)) {
                    identityPermissionList.add(ip);
                }
            }
        }
        return identityPermissionList;
    }

    /**
     * 通过身份和权限获取一条权限记录
     *
     * @param id
     * @param authID
     * @return
     */
    @Transactional(readOnly = true)
    public IdentityPermission getIdentityPermissionByIdentityAndAuth(Long id, Long authID) {
        if (AuthCacheManager.getInstance().getIdentityPermissionCache() != null && !AuthCacheManager.getInstance().getIdentityPermissionCache().isEmpty()) {
            for (IdentityPermission ra : AuthCacheManager.getInstance().getIdentityPermissionCache()) {
                if (ra.getIdentityId().equals(id) && ra.getAuthId().equals(authID)) {
                    return ra;
                }
            }
        }
        return null;
    }

    /**
     * 删除身份权限记录
     *
     * @param id
     */
    public void deleteIdentityPermission(Long id) {
        List<IdentityPermission> identityPermissionList = this.getIdentityPermissionListById(id);

        //循环删除身份对应的权限
        for (IdentityPermission identityPermission : identityPermissionList) {
            identityPermissionDao.delete("IdentityPermission.delete", identityPermission.getId());
        }
    }

    /**
     * 删除身份权限记录，以authID为标记
     *
     * @param authID
     */
    public void deleteByAuthID(Long authID) {
        identityPermissionDao.delete("IdentityPermission.deleteByAuthID", authID);
    }
}
