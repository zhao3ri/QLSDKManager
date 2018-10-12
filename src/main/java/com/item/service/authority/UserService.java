package com.item.service.authority;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.authority.UserDao;
import com.item.domain.authority.User;

import core.module.orm.MapBean;
import core.module.orm.Page;

/**
 * 
 * 管理员账号管理逻辑类
 * @author guojt
 * @since 2011-02-14
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * 根据用户提交的信息获取用户列表
	 * @param user 用户对象
	 * @return 用户列表
	 */
	@Transactional(readOnly=true)
	public Page<User> getUserPage(Page<User> page,MapBean mb){
		return userDao.find(page, mb, "User.count", "User.list");
	}
	
	/**
	 * 通过角色ID取用户
	 * @param roleID
	 * @return
	 */
	public List<User> getUserListByRoleID(Long roleID){
		return userDao.find("User.listByRoleID", roleID);
	}
	
	/**
	 * 通过返回的值取用户
	 * @param roleID
	 * @return
	 */
	public List<User> getUserListByReturnID(MapBean mb){
		return userDao.find("User.listByReturnID", mb);
	}
	
	/**
	 * 获取所有用户
	 * @param roleID
	 * @return
	 */
	public List<User> list(MapBean mb){
		return userDao.find("User.lists", mb);
	}
	
	/**
	 * 根据用户名取用户信息
	 * @param userName
	 * @return User对象
	 */
	@Transactional( readOnly = true )
	public User getUserByUserName(MapBean mb){
		return userDao.get("User.selectUniqueUserByName", mb);
	}
	
	/**
	 * 根据指定的id取用户信息
	 * @param id 用户id
	 * @return User对象
	 */
	@Transactional( readOnly = true)
	public User getUserById(Long id){
		return userDao.findUnique("User.selectUniqueUserById", id);
	}
	
	/**
	 * 根据指定的id取用户信息
	 * @param id 用户id
	 * @return User对象
	 */
	@Transactional( readOnly = true)
	public User get(Long id){
		return userDao.findUnique("User.selectUniqueUserById", id);
	}
	
	/**
	 * 通过id批量删除
	 * @param checkedIds id字符串，形如：1,2,3
	 */
	public void deleteUsers(String checkedIds){
		for (String checkedId : StringUtils.split(checkedIds, ",")){
			this.deleteUserById(Long.valueOf(checkedId));
		}
	}
	
	/**
	 * 通过指定的id删除用户信息
	 * @param id 用户id
	 */
	public void deleteUserById(Long id){
		userDao.delete("User.delete", id);
	}
	
	/**
	 * 检查用户名是否已经存在
	 * @param userName
	 * @return
	 */
	@Transactional( readOnly = true )
	public boolean isUserNameExist(MapBean mb){
		User user = this.getUserByUserName(mb);
		if(user!=null){
			return true;
		}
		return false;
	}
	
	/**
	 * 保存用户
	 * @param user
	 */
	public void saveUser(User user){
		userDao.save("User.save", user);
	}
	
	/**
	 * 更新用户
	 * @param user
	 */
	public void updateUser(User user){
		userDao.update("User.update", user);
	}
	
	/**
	 * 更新密码
	 * @param user
	 */
	public void updateMyPassword(User user){
		userDao.update("User.updateMyPassword", user);
	}
	
	/**
	 * 更新个人资料
	 * @param user
	 */
	public void updateMyInfo(User user){
		userDao.update("User.updateMyInfo", user);
	}

	/**
	 * 禁止管理员
	 * @param mb
	 */
	public void disabled(MapBean mb){
		userDao.update("User.disabled", mb);
	}
	
	 public User getEntity(Long id){
	        return userDao.get("User.getEntity",id);
	  }
}
