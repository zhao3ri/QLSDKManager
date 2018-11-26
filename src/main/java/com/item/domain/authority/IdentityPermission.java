package com.item.domain.authority;

public class IdentityPermission extends SystemModule {
	private Long identityId;
	private Long authId;
	
	public IdentityPermission(){
		
	}

	public Long getIdentityId() {
		return identityId;
	}

	public void setIdentityId(Long identityId) {
		this.identityId = identityId;
	}

	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}
	
	
}
