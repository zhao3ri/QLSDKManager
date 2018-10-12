package com.item.domain.authority;

import java.io.Serializable;
import java.util.Date;

import core.module.annotation.LogAttribute;
import core.module.annotation.LogModule;

/**
 * 管理员账户表实体类
 * @author guojt
 * @since 2010-02-14
 * 
 */
@LogModule(name="用户",key="user")
public class User implements Serializable{
	private static final long serialVersionUID = -4005191924490899121L;
	private Long id;
	private String userName;
	
	@LogAttribute(name="密码")
	private String password;
	
	@LogAttribute(name="真实姓名")
	private String realName;
	
	@LogAttribute(name="部门")
	private Long did;
	
	@LogAttribute(name="分组")
	private Long gid;
	
	@LogAttribute(name="状态")
	private Integer state;
	
	@LogAttribute(name="角色")
	private Long roleID;
	
	private String roleName;
	private String dname;
	private Long levelId;
	private Integer projectType;
	private Date createTime;
	private Date updateTime;
	private String viewRecharge;
	public User() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getRoleID() {
		return roleID;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getViewRecharge() {
		return viewRecharge;
	}

	public void setViewRecharge(String viewRecharge) {
		this.viewRecharge = viewRecharge;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}
	
}
