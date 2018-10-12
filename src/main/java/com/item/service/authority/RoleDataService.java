package com.item.service.authority;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.authority.RoleDataDao;
import com.item.domain.authority.RoleData;

/**
 * 角色数据集权限业务处理类
 * @author 
 * @since 2013-10-12
 */
@Service
@Transactional
public class RoleDataService {
	@Autowired
	private RoleDataDao roleDataDao;
	
	/**
	 * 获取全部的角色数据集权限信息
	 * @return
	 */
	public List<RoleData> getAllRoleDataList(){
		return roleDataDao.find("RoleData.listAll", null);
	}
	
	/**
	 * 通过角色ID获取角色对应的数据集权限信息
	 * @param roleId
	 * @return
	 */
	public List<RoleData> getRoleDataListByRoleId(Long roleId){
		List<RoleData> roleDataList = new ArrayList<RoleData>();
		if(roleId == null){
			return roleDataList;
		}
		for(RoleData rd : AuthCacheManage.roleDataCacheList){
			if(roleId.equals(rd.getRoleID())){
				roleDataList.add(rd);
			}
		}
		return roleDataList;
	}
	
	/**
	 * 通过角色ID和数据集ID获取角色数据集信息
	 * @param roleId
	 * @param datasetId
	 * @return
	 */
	public RoleData getRoleDataByRoleAndData(Long roleId, Long datasetId){
		RoleData roleData = null;
		if(roleId == null || datasetId == null){
			return roleData;
		}
		for(RoleData rd : AuthCacheManage.roleDataCacheList){
			if(roleId.equals(rd.getRoleID()) && datasetId.equals(rd.getDatasetID())){
				roleData = rd;
				break;
			}
		}
		return roleData;
	}
	
	/**
	 * 通过角色ID删除角色数据集权限信息
	 * @param roleId
	 */
	public void deleteRoleDataByRoleId(Long roleId){
		List<RoleData> delList = new ArrayList<RoleData>();
		if(AuthCacheManage.roleDataCacheList != null && !AuthCacheManage.roleDataCacheList.isEmpty()){
			for(RoleData rd : AuthCacheManage.roleDataCacheList){
				if(roleId != null && roleId.equals(rd.getRoleID())){
					delList.add(rd);
				}
			}
		}
		AuthCacheManage.roleDataCacheList.removeAll(delList);
		roleDataDao.delete("RoleData.deleteByRoleId", roleId);
	}
	
	/**
	 * 保存角色与数据集的关联信息到数据库和缓存
	 * @param roleData
	 * @return
	 */
	public Long saveRoleData(RoleData roleData){
		Long id = (Long)roleDataDao.save("RoleData.save", roleData);
		roleData.setId(id);//ibatis不像hibernate那样在保存完成后自动更新对象
		if(!AuthCacheManage.roleDataCacheList.contains(roleData)){
			AuthCacheManage.roleDataCacheList.add(roleData);
		}
		return id;
	}
}
