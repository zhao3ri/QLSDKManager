package com.item.service.authority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.item.domain.authority.Identity;
import com.item.domain.authority.IdentityData;
import com.item.domain.authority.IdentityPermission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.item.dao.authority.IdentityPermissionDao;
import com.item.dao.authority.IdentityDao;
import com.item.domain.authority.User;
import core.module.orm.MapBean;
import core.module.orm.Page;

/**
 * 
 * 身份管理逻辑类
 * @author guojt
 * @since 2011-02-20
 *
 */
@Service
@Transactional
public class IdentityService {
	@Autowired
	private IdentityDao identityDao;
	@Autowired
	private IdentityPermissionDao identityPermissionDao;
	@Autowired
	private IdentityPermissionService ips;
	@Autowired
	private IdentityDataService ids;
	@Autowired
	private UserService us;
	
	
	/**
	 * 添加身份
	 * @author guojt
	 * @param identity
	 * @param authIds
	 */
	public Long createIdentity(Identity identity, Long[] authIds, List<String> datasetLevels){
		Long identityId = (Long) identityDao.save("Identity.save", identity);
		
		if(authIds!=null && authIds.length>0){
			//保存身份所拥有的权限
			for(int i=0;i<authIds.length;i++){
				IdentityPermission identityPermission =new IdentityPermission();
				identityPermission.setIdentityId(identityId);
				identityPermission.setAuthId(authIds[i]);
				identityPermissionDao.save("IdentityPermission.save", identityPermission);
			}
		}
		//约定了datasetLevel格式为【datasetID&level】
		if(datasetLevels!=null){
			for(String datasetLevel : datasetLevels){
				if(datasetLevel != null && datasetLevel.indexOf("&") > -1){
					String[] strArr = datasetLevel.split("&");
					IdentityData identityData = new IdentityData();
					identityData.setDatasetId(Long.parseLong(strArr[0]));
					identityData.setIdentityId(identityId);
					identityData.setLevel(Long.parseLong(strArr[1]));
					ids.saveIdentityIdData(identityData);
				}
			}
		}
		return identityId;
	}
	
	/**
	 * 从ID数组2中取出ID数组1不包含的
	 * @param t1
	 * @param t2
	 * @return
	 */
	public <T> List<T> compareLongs(List<T> t1, List<T> t2){
		List<T> list = new ArrayList<T>();
		for(T t : t2){
			if(!t1.contains(t)){
				list.add(t);
			}
		}
		return list;
	}
	
	/**
	 * 更新身份功能权限
	 * @param id
	 * @param authIds
	 */
	public void updateIdentityPermission(Long id
			, Long[] authIds){
		List<Long> oldAuthList = new ArrayList<Long>();
		List<IdentityPermission> identityPermissionList = ips.getIdentityPermissionListById(id);
		for(IdentityPermission ra : identityPermissionList){
			oldAuthList.add(ra.getAuthId());
		}
		
		if (authIds == null || authIds.length ==0) {//一个都没有选择的情况下
			if (oldAuthList.size() > 0) {//如果原有的权限不为空，代表要吧所有得原有权限去掉
				for(Long authId : oldAuthList){
					ips.deleteByAuthID(authId);
				}
			}
		} else {//表示有选择的情况下
			List<Long> newAuthList = Arrays.asList(authIds);
			List<Long> auth2AddList = compareLongs(oldAuthList,newAuthList);
			List<Long> auth2DelList = compareLongs(newAuthList, oldAuthList);
			for(Long authId : auth2DelList){
				ips.deleteByAuthID(authId);
			}
			for(Long authId : auth2AddList){
				IdentityPermission identityPermission = new IdentityPermission();
				identityPermission.setIdentityId(id);
				identityPermission.setAuthId(authId);
				identityPermissionDao.save("IdentityPermission.save", identityPermission);
			}
		}
		
	}
	
	/**
	 * 修改身份信息
	 * @author guojt
	 * @param identity
	 * @param authIds
	 */
	public void updateIdentity(Identity identity, Long[] authIds, List<String> datasetLevels){
		
		//第一步：更新身份信息，包括身份名，身份描述
		identityDao.update("Identity.update", identity);
		
		//第二步：更新身份权限
		updateIdentityPermission(identity.getId(), authIds);
		
		//第三步：更新身份数据集权限
		ids.deleteIdentityDataById(identity.getId());
		//约定了datasetLevel格式为【datasetID&level】
		if(datasetLevels!=null && datasetLevels.size() > 0){
			for(String datasetLevel : datasetLevels){
				if(datasetLevel != null && datasetLevel.indexOf("&") > -1){
					String[] strArr = datasetLevel.split("&");
					IdentityData identityData = new IdentityData();
					identityData.setDatasetId(Long.parseLong(strArr[0]));
					identityData.setIdentityId(identity.getId());
					identityData.setLevel(Long.parseLong(strArr[1]));
					ids.saveIdentityIdData(identityData);
				}
			}
		}
	}
	
	/**
	 * 通过id批量删除
	 * @param checkedIds id字符串，形如：1,2,3
	 */
	public void deleteIdentities(String checkedIds){
		for (String checkedId : StringUtils.split(checkedIds, ",")){
			this.deleteIdentityById(Long.valueOf(checkedId));
		}
	}
	
	/**
	 * 删除身份
	 * @author guojt
	 * @param role
	 */
	public void deleteIdentityById(Long id){
		//第一步：删除身份的权限
		ips.deleteIdentityPermission(id);
		this.ids.deleteIdentityDataById(id);
		
		//第二步：删除身份下的所有用户
		List<User> userList = us.getUserListByIdentityId(id);
		for(User user:userList){
			us.deleteUserById(user.getId());
		}

		//第三步：删除身份
		identityDao.delete("Identity.delete", id);
	}
	
	/**
	 * 得到身份列表
	 * @author guojt
	 * @return
	 */
	@Transactional( readOnly = true )
	public List<Identity> getIdentityList(MapBean mb){
		return identityDao.find("Identity.listAll",mb);
	}
	
	/**
	 * 取身份分页列表
	 * @param page
	 * @param mb
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page<Identity> getIdentityPage(Page<Identity> page, MapBean mb){
		return identityDao.find(page, mb, "Identity.count", "Identity.list");
	}

	/**
	 * 通过身份名查找身份
	 * @author guojt
	 * @param name
	 * @return
	 */
	@Transactional( readOnly = true )
	public Identity getIdentityByName(String name){
		return identityDao.get("Identity.selectUniqueIdentityByName", name);
	}
	
	/**
	 * 通过ID查找身份
	 * @author guojt
	 * @param id
	 * @return
	 */
	@Transactional( readOnly = true )
	public Identity getIdentityById(Long id){
		return identityDao.get("Identity.selectUniqueIdentityById", id);
	}
	
	/**
	 *
	 * 检查身份名是否已经存在
	 * @author guojt
	 * @param role
	 * @return
	 */
	@Transactional( readOnly = true )
	public Identity isNameExist(String roleName){
		Identity tempIdentity =this.getIdentityByName(roleName);
		return tempIdentity;
	}
	
	public Identity getEntity(Long id){
        return identityDao.get("Identity.getEntity",id);
    }
}
