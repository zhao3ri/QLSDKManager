package com.item.domain.authority;

public class RoleAuth {
	private Long id;
	private Long roleID;
	private Long authID;
	
	public RoleAuth(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleID() {
		return roleID;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}

	public Long getAuthID() {
		return authID;
	}

	public void setAuthID(Long authID) {
		this.authID = authID;
	}
	
	
}
