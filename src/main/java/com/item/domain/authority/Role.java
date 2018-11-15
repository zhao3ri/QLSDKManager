package com.item.domain.authority;

import java.util.List;

import com.item.domain.Game;

import core.module.annotation.LogAttribute;
import core.module.annotation.LogModule;

/**
 * 
 * 角色表实体类
 * @author guojt
 * @since 2010-02-14
 *
 */
@LogModule(name="身份",key="role")
public class Role {

	private Long id;
	
	@LogAttribute(name="身份名称")
	private String roleName;
	
	@LogAttribute(name="身份描述")
	private String description;
	
	private Integer projectType;
	
	private List<Game> authGames;

	public Role() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public List<Game> getAuthGames() {
		return authGames;
	}

	public void setAuthGames(List<Game> authGames) {
		this.authGames = authGames;
	}
}
