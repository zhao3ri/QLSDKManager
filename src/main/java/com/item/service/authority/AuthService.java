package com.item.service.authority;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.authority.AuthDao;
import com.item.domain.authority.Auth;
/**
 * 权限处理类
 * @author 郭建填
 * @since 2011-02-20
 */
@Service
@Transactional
public class AuthService{
	@Autowired
	private AuthDao authDao;
	@Autowired
	private RoleAuthService ras;
	@Autowired
	private FunctionService fs;

	/**
	 * 取所有权限
	 * @return
	 */
	public List<Auth> getAuthList(){
		List<Auth> authList = authDao.find("Auth.listAll", null);
		this.list2map(authList);
		return authList;
	}
	
	/**
	 * 将list转为map,使用map取单条记录，速度更快
	 */
	public void list2map(List<Auth> authList){
		if(authList!=null && !authList.isEmpty()){
			for (Auth auth:authList) {
				AuthCacheManage.authCacheMap.put(auth.getId(), auth);
			}
		}
	}
	
	/**
	 * 根据模块ID取权限列表
	 * @param moduleId
	 * @return
	 */
	public List<Auth> getAuthListByModuleId(Long moduleId){
		List<Auth> authList = new ArrayList<Auth>();
		if(AuthCacheManage.authCacheList!=null && !AuthCacheManage.authCacheList.isEmpty()){
			for(Auth auth:AuthCacheManage.authCacheList){
				if(auth.getModuleID().equals(moduleId)){
					authList.add(auth);
				}
			}
		}
		return authList;
	}
	
	/**
	 *根据ID取某个权限
	 * @param id
	 * @return
	 */
	@Transactional (readOnly=true)
	public Auth getAuthById(Long id){
		if(AuthCacheManage.authCacheMap.isEmpty()){
			return null;
		}
		return AuthCacheManage.authCacheMap.get(id);
	}
	
	/**
	 * 根据模块id取出该模块的所有操作权限,并拼成带复选框的html字符串,新建角色的时候使用它
	 * @param moduleId
	 * @return
	 */
	public String getAuthHtml(Long moduleId){
		int count = 0;
		StringBuffer sb = new StringBuffer();
		List<Auth> authList =this.getAuthListByModuleId(moduleId);
		for(Auth auth:authList){
			if (fs.getFunctionByID(auth.getFunctionID()) == null) {
				continue;
			}
			sb.append("<input type=\"checkbox\" name=\"authIds\" value=\""+auth.getId()+"\" lang=\""+moduleId+"\"/>");
			sb.append(fs.getFunctionByID(auth.getFunctionID()).getDescription()+"\n");	
			count++;
			if(7 == count){
				sb.append("<br/>");	//每7个一行
				count=0;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 取出角色已经拥有的权限。更新角色时使用
	 * @param moduleId
	 * @param role
	 * @return
	 */
	public String getUpdateAuthHtml(Long moduleId,Long roleID){
		int count = 0;
		StringBuffer sb = new StringBuffer();
		List<Auth> authList =this.getAuthListByModuleId(moduleId);
		for(Auth auth:authList){
			if (fs.getFunctionByID(auth.getFunctionID()) == null) {
				continue;
			}
			String str = " ";
			//判断是否有该权限,如果有,checkbox设置为勾选
			if(ras.getRoleauthByRoleAndAuth(roleID, auth.getId())!=null)
				str = " checked ";
			sb.append("<input type=\"checkbox\" "+str+" name=\"authIds\" value=\""+auth.getId()+"\" lang=\""+moduleId+"\"/>");
			sb.append(fs.getFunctionByID(auth.getFunctionID()).getDescription()+"\n");
			count++;
			if(7 == count){
				sb.append("<br/>");	//每7个一行
				count=0;
			}			
		}
		return sb.toString();
	}
	
	public void save(Auth entity){
		authDao.save("Auth.save", entity);
	}
	
	public void delete(Long fid){
		authDao.delete("Auth.delete", fid);
	}
	
	/**
	 * 以FunctionID为条件，查找auth
	 * @param fid
	 * @return
	 */
	public Auth getAuthByFunctionID(Long fid){
		return authDao.get("Auth.getAuthByFunctionID", fid);
	}
}
