package com.item.service.authority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.item.dao.authority.RoleAuthDao;
import com.item.dao.authority.RoleDao;
import com.item.domain.authority.Role;
import com.item.domain.authority.RoleAuth;
import com.item.domain.authority.RoleData;
import com.item.domain.authority.User;
import core.module.orm.MapBean;
import core.module.orm.Page;

/**
 * 
 * 角色管理逻辑类
 * @author guojt
 * @since 2011-02-20
 *
 */
@Service
@Transactional
public class RoleService{
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleAuthDao roleAuthDao;
	@Autowired
	private RoleAuthService ras;
	@Autowired
	private RoleDataService rds;
	@Autowired
	private UserService us;
	
	
	/**
	 * 添加角色
	 * @author guojt
	 * @param role
	 * @param authIds
	 */
	public Long createRole(Role role,Long[] authIds, List<String> datasetLevels){
		Long roleID = (Long)roleDao.save("Role.save", role);
		
		if(authIds!=null && authIds.length>0){
			//保存角色所拥有的权限
			for(int i=0;i<authIds.length;i++){
				RoleAuth roleAuth=new RoleAuth();
				roleAuth.setRoleID(roleID);
				roleAuth.setAuthID(authIds[i]);
				roleAuthDao.save("RoleAuth.save", roleAuth);
			}
		}
		//约定了datasetLevel格式为【datasetID&level】
		if(datasetLevels!=null){
			for(String datasetLevel : datasetLevels){
				if(datasetLevel != null && datasetLevel.indexOf("&") > -1){
					String[] strArr = datasetLevel.split("&");
					RoleData roleData = new RoleData();
					roleData.setDatasetID(Long.parseLong(strArr[0]));
					roleData.setRoleID(roleID);
					roleData.setLevel(Long.parseLong(strArr[1]));
					rds.saveRoleData(roleData);
				}
			}
		}
		return roleID;
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
	 * 更新角色功能权限
	 * @param roleID
	 * @param authIds
	 */
	public void updateRoleAuth(Long roleID, Long[] authIds){
		List<Long> oldAuthList = new ArrayList<Long>();
		List<RoleAuth> roleAuthList = ras.getRoleAuthListByRoleID(roleID);
		for(RoleAuth ra : roleAuthList){
			oldAuthList.add(ra.getAuthID());
		}
		
		if (authIds == null || authIds.length ==0) {//一个都没有选择的情况下
			if (oldAuthList.size() > 0) {//如果原有的权限不为空，代表要吧所有得原有权限去掉
				for(Long authId : oldAuthList){
					ras.deleteByAuthID(authId);
				}
			}
		} else {//表示有选择的情况下
			List<Long> newAuthList = Arrays.asList(authIds);
			List<Long> auth2AddList = compareLongs(oldAuthList,newAuthList);
			List<Long> auth2DelList = compareLongs(newAuthList, oldAuthList);
			for(Long authId : auth2DelList){
				ras.deleteByAuthID(authId);
			}
			for(Long authId : auth2AddList){
				RoleAuth roleAuth = new RoleAuth();
				roleAuth.setRoleID(roleID);
				roleAuth.setAuthID(authId);
				roleAuthDao.save("RoleAuth.save", roleAuth);
			}
		}
		
	}
	
	/**
	 * 修改角色信息
	 * @author guojt
	 * @param role
	 * @param authId
	 */
	public void updateRole(Role role,Long[] authIds, List<String> datasetLevels){
		
		//第一步：更新角色信息，包括角色名，角色描述
		roleDao.update("Role.update", role);
		
		//第二步：更新角色权限
		updateRoleAuth(role.getId(), authIds);
		
		//第三步：更新角色数据集权限
		rds.deleteRoleDataByRoleId(role.getId());
		//约定了datasetLevel格式为【datasetID&level】
		if(datasetLevels!=null && datasetLevels.size() > 0){
			for(String datasetLevel : datasetLevels){
				if(datasetLevel != null && datasetLevel.indexOf("&") > -1){
					String[] strArr = datasetLevel.split("&");
					RoleData roleData = new RoleData();
					roleData.setDatasetID(Long.parseLong(strArr[0]));
					roleData.setRoleID(role.getId());
					roleData.setLevel(Long.parseLong(strArr[1]));
					rds.saveRoleData(roleData);
				}
			}
		}
	}
	
	/**
	 * 通过id批量删除
	 * @param checkedIds id字符串，形如：1,2,3
	 */
	public void deleteRoles(String checkedIds){
		for (String checkedId : StringUtils.split(checkedIds, ",")){
			this.deleteRoleById(Long.valueOf(checkedId));
		}
	}
	
	/**
	 * 删除角色
	 * @author guojt
	 * @param role
	 */
	public void deleteRoleById(Long roleID){
		//第一步：删除角色的权限
		ras.deleteRoleAuth(roleID);
		rds.deleteRoleDataByRoleId(roleID);
		
		//第二步：删除角色下的所有用户
		List<User> userList = us.getUserListByRoleID(roleID);
		for(User user:userList){
			us.deleteUserById(user.getId());
		}

		//第三步：删除角色
		roleDao.delete("Role.delete", roleID);
	}
	
	/**
	 * 得到角色列表
	 * @author guojt
	 * @return
	 */
	@Transactional( readOnly = true )
	public List<Role> getRoleList(MapBean mb){
		return roleDao.find("Role.listAll",mb);
	}
	
	/**
	 * 取角色分页列表
	 * @param page
	 * @param mb
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page<Role> getRolePage(Page<Role> page,MapBean mb){
		return roleDao.find(page, mb, "Role.count", "Role.list");
	}

	/**
	 * 通过角色名查找角色
	 * @author guojt
	 * @param roleName
	 * @return
	 */
	@Transactional( readOnly = true )
	public Role getRoleByName(String roleName){
		return roleDao.get("Role.selectUniqueRoleByName", roleName);
	}
	
	/**
	 * 通过ID查找角色
	 * @author guojt
	 * @param roleId
	 * @return
	 */
	@Transactional( readOnly = true )
	public Role getRoleById(Long roleId){
		return roleDao.get("Role.selectUniqueRoleById", roleId);
	}
	
	/**
	 *
	 * 检查角色名是否已经存在
	 * @author guojt
	 * @param role
	 * @return
	 */
	@Transactional( readOnly = true )
	public Role isRoleNameExist(String roleName){
		Role tempRole=this.getRoleByName(roleName);
		return tempRole;
	}
	
	public Role getEntity(Long id){
        return roleDao.get("Role.getEntity",id);
    }
}
