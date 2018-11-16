package com.item.service;

import com.item.domain.SysGameManager;
import com.item.domain.authority.User;
import com.item.dao.SysGameManagerDao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import core.module.orm.MapBean;
import core.module.utils.Struts2Utils;

@Service
@Transactional
public class SysGameManagerService {

    @Autowired
    private SysGameManagerDao sysGameManagerDao;

    public void deleteByIdentityId(Long roleId){
    	sysGameManagerDao.delete("SysGameManager.deleteByIdentityId", roleId);
    }
    
    public List<Long> getAppIdsByIdentityId(){
    	User userInfo=(User)Struts2Utils.getRequest().getSession().getAttribute("sessionUserInfo");
    	return getAppIdsByIdentityId(userInfo.getIdentityId());
    }
    
    public ArrayList<Long> getAppIdsByIdentityId(Long identityId){
    	ArrayList<Long> result = new ArrayList<Long>();
    	
    	MapBean mb = new MapBean();
    	mb.put("identityId", identityId);
    	List<SysGameManager> list = sysGameManagerDao.find("SysGameManager.list",mb);
    	for (SysGameManager sysroleappauth : list)
    		result.add(sysroleappauth.getAppId());
    	
    	if (CollectionUtils.isEmpty(result)) {
    		result.add(-1L);
		}
    	return result;
    }

	public void save(Long roleID, Long[] gameIds) {
		for (Long appId : gameIds) {
			SysGameManager auth = new SysGameManager();
			auth.setIdentityId(roleID.intValue());
			auth.setAppId(appId);
			sysGameManagerDao.save("SysGameManager.save", auth);
		}
		
	}
}