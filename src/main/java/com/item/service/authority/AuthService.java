package com.item.service.authority;

import java.util.ArrayList;
import java.util.List;

import com.item.constants.Constants;
import com.item.domain.BPlatform;
import com.item.service.BPlatformAppService;
import core.module.orm.MapBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.authority.AuthDao;
import com.item.domain.authority.Auth;

/**
 * 权限处理类
 *
 * @author 郭建填
 * @since 2011-02-20
 */
@Service
@Transactional
public class AuthService {
    @Autowired
    private AuthDao authDao;
    @Autowired
    private IdentityPermissionService ips;
    @Autowired
    private FunctionService fs;
    @Autowired
    private BPlatformAppService channelService;

    /**
     * 取所有权限
     *
     * @return
     */
    public List<Auth> getAuthList() {
        List<Auth> authList = authDao.find("Auth.listAll", null);
        this.list2map(authList);
        return authList;
    }

    /**
     * 将list转为map,使用map取单条记录，速度更快
     */
    public void list2map(List<Auth> authList) {
        if (authList != null && !authList.isEmpty()) {
            for (Auth auth : authList) {
                AuthCacheManager.getInstance().putPermission2Cache(auth);
            }
        }
    }

    /**
     * 根据模块ID取权限列表
     *
     * @param moduleId
     * @return
     */
    public List<Auth> getAuthListByModuleId(Long moduleId) {
        return AuthCacheManager.getInstance().getPermissionsByModuleId(moduleId);
    }

    /**
     * 根据ID取某个权限
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Auth getAuthById(Long id) {
        return AuthCacheManager.getInstance().getPermissionByCache(id);
    }

    public String getFunctionHtml(Long moduleId, Long identityId) {
        int count = 0;
        StringBuffer sb = new StringBuffer();
        List<Auth> authList = this.getAuthListByModuleId(moduleId);
        for (Auth auth : authList) {
            if (fs.getFunctionByID(auth.getFunctionID()) == null) {
                continue;
            }
            String str = " ";
            //判断是否有该权限,如果有,checkbox设置为勾选
            boolean hasPermission = false;
            if (identityId != null && ips.getIdentityPermissionByIdentityAndAuth(identityId, auth.getFunctionID()) != null) {
                hasPermission = true;
            }
            if (hasPermission)
                str = " checked ";
            sb.append("<input type=\"checkbox\" " + str + " name=\"authIds\" value=\"" + auth.getFunctionID() + "\" lang=\"" + moduleId + "\"/>");
            sb.append(fs.getFunctionByID(auth.getFunctionID()).getDescription() + "\n");
            if (moduleId == Constants.MODULE_ID_PERMISSION && auth.getFunctionID() == Constants.FUNCTION_ID_CHANNEL_MANAGER) {
                sb.append(getChannelHtml(moduleId, identityId));
            }
            count++;
            if (7 == count) {
                sb.append("<br/>");    //每7个一行
                count = 0;
            }
        }
        return sb.toString();
    }

    public String getChannelHtml(Long moduleId, Long identityId) {
        int count = 0;
        StringBuffer sb = new StringBuffer();
        List<BPlatform> allChannel = channelService.getAllPlatform();
        List<BPlatform> identityChannel = null;
        if (identityId != null)
            identityChannel = channelService.getIdentityChannelList(identityId);
        sb.append("<fieldset>");
        sb.append("<legend> <input type=\"checkbox\" onclick=\"selectAll(this,'channel')\"/>全选 </legend>");
        for (BPlatform channel : allChannel) {
            String check = " ";
            if (identityChannel != null && identityChannel.contains(channel)) {
                check = " checked ";
            }
            sb.append("<input type=\"checkbox\" " + check + " name=\"channelIds\" value=\"" + channel.getId() + "\" lang=\"channel\"/>");
            sb.append(channel.getPlatformName() + "\n");
            count++;
            if (10 == count) {
                sb.append("<br/>");
                count = 0;
            }
        }
        sb.append("</fieldset>");
        return sb.toString();
    }

    public void save(Auth entity) {
        authDao.save("Auth.save", entity);

    }

    public void delete(Long fid) {
        authDao.delete("Auth.delete", fid);
    }

    /**
     * 以FunctionID为条件，查找auth
     *
     * @param fid
     * @return
     */
    public Auth getAuthByFunctionID(Long fid) {
        return authDao.get("Auth.getAuthByFunctionID", fid);
    }

}
