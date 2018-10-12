package com.item.service;

import com.item.domain.Sysroleappauth;
import com.item.domain.authority.User;
import com.item.dao.SysroleappauthDao;

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
public class SysroleappauthService {

    @Autowired
    private SysroleappauthDao sysroleappauthDao;

    public void deleteByRoleId(Long roleId){
    	sysroleappauthDao.delete("Sysroleappauth.deleteByRoleId", roleId);
    }
    
    public List<Long> getAuthAppIdsByRoleId(){
    	User userInfo=(User)Struts2Utils.getRequest().getSession().getAttribute("sessionUserInfo");
    	return getAuthAppIdsByRoleId(userInfo.getRoleID());
    }
    
    public ArrayList<Long> getAuthAppIdsByRoleId(Long roleId){
    	ArrayList<Long> result = new ArrayList<Long>();
    	
    	MapBean mb = new MapBean();
    	mb.put("roleId", roleId);
    	List<Sysroleappauth> list = sysroleappauthDao.find("Sysroleappauth.list",mb);
    	for (Sysroleappauth sysroleappauth : list)
    		result.add(sysroleappauth.getAppId());
    	
    	if (CollectionUtils.isEmpty(result)) {
    		result.add(-1L);
		}
    	return result;
    }

	public void save(Long roleID, Long[] gameIds) {
		for (Long appId : gameIds) {
			Sysroleappauth auth = new Sysroleappauth();
			auth.setRoleId(roleID.intValue());
			auth.setAppId(appId);
			sysroleappauthDao.save("Sysroleappauth.save", auth);
		}
		
	}
}