package com.item.domain.authority;

/**
 * 角色数据集关联表实体类
 * @author Administrator
 * @since 2013-10-10
 *
 */
public class RoleData {
	
	public static Long LEVEL_PERSONAL = 1L;
	public static Long LEVEL_GROUP = 2L;
	public static Long LEVEL_DEPARTMENT = 3L;
	public static Long LEVEL_ALL = 4L;

	private Long id;
	private Long datasetID;
	private Long roleID;
	private Long level;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDatasetID() {
		return datasetID;
	}
	public void setDatasetID(Long datasetID) {
		this.datasetID = datasetID;
	}
	public Long getRoleID() {
		return roleID;
	}
	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	
}
