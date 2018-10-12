package com.item.service.authority;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.authority.RoleAuthDao;
import com.item.domain.authority.RoleAuth;
/**
 * 角色权限业务逻辑处理类
 * @author 郭建填
 * @since 2011-02-20
 */
@Service
@Transactional
public class RoleAuthService{
	@Autowired
	private RoleAuthDao roleAuthDao;
	
	/**
	 * 取出所有角色权限
	 * @return
	 */
	public List<RoleAuth> getRoleAuthList(){
		return roleAuthDao.find("RoleAuth.listAll", null);
	}
	
	/**
	 * 通过角色获取角色ID权限列表
	 * @param roleID
	 * @return
	 */
	public List<RoleAuth> getRoleAuthListByRoleID(Long roleID){
		List<RoleAuth> roleAuthList = new ArrayList<RoleAuth>();
		if(AuthCacheManage.roleAuthCaCheList!=null && !AuthCacheManage.roleAuthCaCheList.isEmpty()){
			for(RoleAuth ra:AuthCacheManage.roleAuthCaCheList){
				if(ra.getRoleID().equals(roleID)){
					roleAuthList.add(ra);
				}
			}
		}
		return roleAuthList;
	}
	
	/**
	 * 通过角色和权限获取一条权限记录
	 * @param roleID
	 * @param authID
	 * @return
	 */
	@Transactional( readOnly = true )
	public RoleAuth getRoleauthByRoleAndAuth(Long roleID,Long authID){
		if(AuthCacheManage.roleAuthCaCheList!=null && !AuthCacheManage.roleAuthCaCheList.isEmpty()){
			for(RoleAuth ra:AuthCacheManage.roleAuthCaCheList){
				if(ra.getRoleID().equals(roleID) && ra.getAuthID().equals(authID)){
					return ra;
				}
			}
		}
		return null;
	}
	
	/**
	 * 删除角色权限记录
	 * @param role
	 */
	public void deleteRoleAuth(Long roleID){
		List<RoleAuth> roleAuthList=this.getRoleAuthListByRoleID(roleID);
		
		//循环删除角色对应的权限
		for(RoleAuth roleAuth:roleAuthList){
			roleAuthDao.delete("RoleAuth.delete", roleAuth.getId());
		}
	}
	
	/**
	 * 删除角色权限记录，以authID为标记
	 * @param roleID
	 */
	public void deleteByAuthID(Long authID){
		roleAuthDao.delete("RoleAuth.deleteByAuthID", authID);
	}
}
