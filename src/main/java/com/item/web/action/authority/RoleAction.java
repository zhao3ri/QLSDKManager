package com.item.web.action.authority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.item.domain.Game;
import com.item.domain.authority.Module;
import com.item.domain.authority.Role;
import com.item.service.BGameService;
import com.item.service.SysroleappauthService;
import com.item.service.authority.AuthCacheManage;
import com.item.service.authority.ModuleService;
import com.item.service.authority.RoleService;
import com.item.service.authority.DictionaryService;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;
/**
 * 
 * 角色管理控制类
 * @author guojt
 * @since 2011-02-20
 *
 */
@Action
public class RoleAction extends Struts2Action {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RoleService rs;
	@Autowired
	private ModuleService ms;
	@Autowired
	private AuthCacheManage acm;
	@Autowired
	private DictionaryService ds;
	@Resource
	private SysroleappauthService roleAppAuthService;
	@Resource
	private BGameService gameService;

	private Role role;
	private Role searchRole;
	private Long[] authIds;
	private Long[] gameIds;
	private List<Role> roleList;
	private List<Module> moduleList;
	private List<Game> games;
	private Page<Role> page = new Page<Role>(10);
	private String checkedIds;
	private String roleName;
	private Long id;
	private List<String> datasetLevels;

	/**
	 * 获取角色列表
	 */
	public String list() {
		try {
			MapBean mb = this.putSearchConditions();
			page = rs.getRolePage(page, mb);
			for (Role item : page.getResult()) {
				item.setAuthGames(gameService.listByRoleId(item.getId()));
			}
		} catch (Exception e) {
			logger.error("获取角色列表出错：", e);
		}
		return SUCCESS;
	}

	/**
	 * 设置搜索条件
	 * 
	 * @return
	 */
	private MapBean putSearchConditions() {
		MapBean mb = new MapBean();
		if (searchRole != null) {
			if (searchRole.getRoleName() != null && !"".equals(searchRole.getRoleName())) {
				mb.put("roleName", searchRole.getRoleName());
			}
		}
		return mb;
	}

	/**
	 * 初始化新建角色页面
	 */
	public String create() {
		moduleList = ms.getModuleListForCreate();
		games = gameService.list(null);
		return SUCCESS;
	}

	/**
	 * 添加/更新角色
	 * 
	 * @return
	 */
	public String save() {
		// id为空，则表示添加,否则为修改
		if (role.getId() == null) {
			try {
				Long roleID = rs.createRole(role, authIds, datasetLevels);
				acm.refreshRoleAuth();	//刷新之前的角色权限缓存数据，保持同步
				acm.refreshRoleAuthCache(roleID);	//添加新的角色后，刷新该角色权限缓存
				
				roleAppAuthService.save(roleID, gameIds);
				
				addActionMessage("新增角色成功");
			} catch (Exception e) {
				addActionMessage("新增角色出错：" + e);
				logger.error("新增角色出错：" + e);
			}
		} else {
			try {
				rs.updateRole(role, authIds, datasetLevels);
				acm.refreshRoleAuth();	//刷新之前的角色权限缓存数据，保持同步
				acm.refreshRoleAuthCache(role.getId());	//刷新该角色的权限缓存
				
				if (gameIds != null && gameIds.length > 0) {
					roleAppAuthService.deleteByRoleId(role.getId());
					roleAppAuthService.save(role.getId(), gameIds);
				}
				
				addActionMessage("角色修改成功");
			} catch (Exception e) {
				e.printStackTrace();
				addActionMessage("角色修改出错：" + e);
				logger.error("角色修改出错：" + e);
			}
		}
		return RELOAD;

	}

	/**
	 * 初始化更新角色页面
	 * 
	 * @return
	 */
	public String update() {
		role = rs.getRoleById(role.getId());
		moduleList = ms.getModuleListForUpdate(role.getId());
		
		games = gameService.list(null);
		
		List<Long> authAppIds = roleAppAuthService.getAuthAppIdsByRoleId(role.getId());
		Map<String, String> map = new HashMap<String, String>();
		for (Long appId : authAppIds) {
			map.put(appId.toString(), "");
		}
		if (!CollectionUtils.isEmpty(games)) {
			for (Game game : games) {
				if (null != map.get(game.getId().toString())) {
					game.setIsAuth(1);
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 批量删除角色
	 * 
	 * @return
	 */
	public String delete() {
		try {
			rs.deleteRoles(checkedIds);
			addActionMessage("删除角色成功");
		} catch (Exception e) {
			logger.error("删除角色出错：" + e);
			addActionMessage("删除角色出错：" + e);
		}
		return RELOAD;
	}
	
	/**
	 * 检查角色名是否存在
	 * 
	 * @return
	 */
	public String checkRoleName() {
		boolean flag = false;
		try {
			Role tempRole = rs.isRoleNameExist(roleName);
			if(id != null){
				if(tempRole!=null && id.longValue()!=tempRole.getId().longValue()){
					flag = true;
				}else{
					flag = false;
				}
			}else{
				if(tempRole!=null){
					flag = true;
				}else{
					flag = false;
				}
			}
			Struts2Utils.renderText(flag + "");
		} catch (Exception e) {
			logger.error("检查角色名是否存在出错：" + e.getMessage(), e);
		}
		return null;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Role getSearchRole() {
		return searchRole;
	}

	public void setSearchRole(Role searchRole) {
		this.searchRole = searchRole;
	}

	public Long[] getAuthIds() {
		return authIds;
	}

	public void setAuthIds(Long[] authIds) {
		this.authIds = authIds;
	}

	public Page<Role> getPage() {
		return page;
	}

	public void setPage(Page<Role> page) {
		this.page = page;
	}

	public String getCheckedIds() {
		return checkedIds;
	}

	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<String> getDatasetLevels() {
		return datasetLevels;
	}
	
	public void setDatasetLevels(List<String> datasetLevels) {
		this.datasetLevels = datasetLevels;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public Long[] getGameIds() {
		return gameIds;
	}

	public void setGameIds(Long[] gameIds) {
		this.gameIds = gameIds;
	}
}
