package com.item.web.action.authority;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.item.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.application.StateContext;
import com.item.constants.Constants;
import com.item.constants.TypeConstant;
import com.item.domain.authority.Role;
import com.item.domain.authority.User;
import com.item.domain.authority.Dictionary;
import com.item.service.authority.RoleService;
import com.item.service.authority.UserService;
import com.item.service.authority.DictionaryService;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;

/**
 * 管理员账号控制类
 *
 * @author guojt
 * @since 2010-02-14
 */
@Action
public class UserAction extends Struts2Action {

	private static final long serialVersionUID = -4714087534313256399L;
	private static final String SUCCESS_MSG_FORMAT = "设置为/\"%s/\"状态成功!";

	@Autowired
	private UserService us;
	@Autowired
	private RoleService rs;
	@Autowired
	private DictionaryService ds;

	private User user;
	private User searchUser;    //用于保存搜索条件的User对象
	private Page<User> page = new Page<User>(10);
	private String checkedIds;
	private List<Role> roleList;
	private String audit;
	private Set<String> viewRechargeSet = new HashSet<String>();    //该管理员能查看哪几款游戏的充值
	private String[] games = null;
	private List<Dictionary> departmentList = new ArrayList<Dictionary>();
	private List<Dictionary> groupList = new ArrayList<Dictionary>();
	private int id;
	private String userName;
	private String oldPassword;

	private HttpServletResponse response = ServletActionContext.getResponse();

	/**
	 * 获取管理员列表
	 *
	 * @return
	 */
	public String list() {
		try {
			MapBean deparMb = new MapBean();
			deparMb.put("dtype", TypeConstant.department.name());
			deparMb.put("state", Constants.STATE_NORMAL);
			deparMb.put("orderby", "dsort desc");
			departmentList = ds.list(deparMb);

			MapBean groupMb = new MapBean();
			groupMb.put("dtype", TypeConstant.group.name());
			groupMb.put("dvalue", searchUser == null ? "" : searchUser.getDid());
			groupMb.put("state", Constants.STATE_NORMAL);
			groupMb.put("orderby", "dsort desc");
			groupList = ds.list(groupMb);

			MapBean mb = search();
			page = us.getUserPage(page, UserTools.getVisitationRightMapBean(mb));
			roleList = rs.getRoleList(null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取管理员列表出错：", e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * 获取查询条件
	 *
	 * @return
	 */
	private MapBean search() {
		MapBean mb = new MapBean();
		if (searchUser != null) {
			//添加搜索条件
			if (searchUser.getUserName() != null && !"".equals(searchUser.getUserName())) {
				mb.put("userName", searchUser.getUserName());
			}
			if (searchUser.getRealName() != null && !"".equals(searchUser.getRealName())) {
				mb.put("realName", searchUser.getRealName());
			}
			if (searchUser.getDid() != null && searchUser.getDid() > 0) {
				mb.put("did", searchUser.getDid());
			}
			if (searchUser.getGid() != null && searchUser.getGid() > 0) {
				mb.put("gid", searchUser.getGid());
			}
			if (searchUser.getRoleID() != null && searchUser.getRoleID() > 0) {
				mb.put("roleID", searchUser.getRoleID());
			}
			if (searchUser.getState() != null && searchUser.getState() > -1) {
				mb.put("state", searchUser.getState());
			}
		}
		mb.put("validstate", "-1");

		return mb;
	}

	/**
	 * 禁止/启用管理员
	 *
	 * @return
	 */
	public String disabled() {
		try {
			if (audit != null) {
				MapBean mb = new MapBean();
				mb.put("state", audit);
				addActionMessage(String.format(SUCCESS_MSG_FORMAT,StateContext.getStateConfigs().get("userState").get(audit + "").getName()));
//                addActionMessage("设置为\"" + StateContext.getStateConfigs().get("userState").get(audit + "").getName() + "\"状态成功");
				for (String checkedId : StringUtils.split(checkedIds, ",")) {
					mb.put("id", Long.valueOf(checkedId));
					us.disabled(mb);
				}
			}
		} catch (Exception e) {
			logger.error("修改管理员状态发生异常：" + e);
			addActionMessage("修改管理员状态发生异常：" + e);
		}
		return RELOAD;
	}

	/**
	 * 初始化添加管理员
	 *
	 * @return
	 */
	public String create() {
		MapBean mb = new MapBean();
		mb.put("dtype", TypeConstant.department.name());
		mb.put("state", Constants.STATE_NORMAL);
		mb.put("orderby", "dsort desc");
		departmentList = ds.list(mb);

		roleList = rs.getRoleList(null);
		return SUCCESS;
	}

	/**
	 * Ajax 查询某个大类的小类
	 *
	 * @return
	 */
	public void getGroupAjax() {
		try {
			if (id > 0) {
				MapBean mb = new MapBean();
				mb.put("dtype", TypeConstant.group.name());
				mb.put("dvalue", id);
				mb.put("state", Constants.STATE_NORMAL);
				mb.put("orderby", "dsort desc");
				groupList = ds.list(mb);
				List<Map<String, Object>> subDic = new ArrayList<>();
				for (Dictionary d : groupList) {
					Map<String, Object> map = new HashMap<>();
					map.put("value", d.getId());
					map.put("text", d.getDname());
					subDic.add(map);
				}
				String jsonStr = JsonUtil.toJsonString(subDic);
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().println(jsonStr);
				response.getWriter().flush();
				response.getWriter().close();
			}
		} catch (Exception e) {
			logger.error("" + e.getMessage(), e);
		}
	}

	/**
	 * 初始化修改管理员
	 *
	 * @return
	 */
	public String update() {
		user = us.getUserById(user.getId());

		MapBean mb = new MapBean();
		mb.put("dtype", TypeConstant.department.name());
		mb.put("state", Constants.STATE_NORMAL);
		mb.put("orderby", "dsort desc");
		departmentList = ds.list(mb);

		MapBean groupMb = new MapBean();
		groupMb.put("dtype", TypeConstant.group.name());
		groupMb.put("dvalue", user.getDid() == null ? 0 : user.getDid());
		groupMb.put("state", Constants.STATE_NORMAL);
		groupMb.put("orderby", "dsort desc");
		groupList = ds.list(groupMb);

		roleList = rs.getRoleList(null);
		if (user != null)
			viewRechargeSet = Tools.str2set(user.getViewRecharge());
		return SUCCESS;
	}


	/**
	 * 修改个人资料-页面
	 *
	 * @return
	 */
	public String updateMyInfo() {
		user = us.getUserById(user.getId());

		MapBean mb = new MapBean();
		mb.put("dtype", TypeConstant.department.name());
		mb.put("state", Constants.STATE_NORMAL);
		mb.put("orderby", "dsort desc");
		departmentList = ds.list(mb);

		MapBean groupMb = new MapBean();
		groupMb.put("dtype", TypeConstant.group.name());
		groupMb.put("dvalue", user.getDid() == null ? 0 : user.getDid());
		groupMb.put("state", Constants.STATE_NORMAL);
		groupMb.put("orderby", "dsort desc");
		groupList = ds.list(groupMb);

		roleList = rs.getRoleList(null);
		if (user != null)
			viewRechargeSet = Tools.str2set(user.getViewRecharge());
		return "myInfo";
	}

	/**
	 * 保存修改个人资料
	 *
	 * @return
	 */
	public String saveMyInfo() {
		try {
			user.setUpdateTime(new Date());
			us.updateMyInfo(user);
			addActionMessage("修改个人资料成功!");
		} catch (Exception e) {
			addActionMessage("修改个人资料出错：" + e);
			logger.error("修改个人资料出错：" + e);
		}
		return "myInfo";
	}

	/**
	 * 修改密码-页面
	 *
	 * @return
	 */
	public String updateMyPassword() {
		user = us.getUserById(user.getId());
		return "myPassword";
	}

	/**
	 * 保存修改密码
	 *
	 * @return
	 */
	public String saveMyPassword() {
		User u = us.getUserById(user.getId());
		if (oldPassword != null && !"".equals(oldPassword)) {
			Md5PwdEncoder encoder = new Md5PwdEncoder();
			String md5_oldPassword = encoder.encodePassword(oldPassword);
			if (md5_oldPassword.equals(u.getPassword())) {
				//如果用户提交了新密码，则加密后一同更新
				if (user.getPassword() != null && !"".equals(user.getPassword())) {
					String md5_password = encoder.encodePassword(user.getPassword());
					user.setPassword(md5_password);
					try {
						user.setUpdateTime(new Date());
						us.updateMyPassword(user);
						addActionMessage("修改密码成功!");
					} catch (Exception e) {
						addActionMessage("修改密码出错：" + e);
						logger.error("修改密码出错：" + e);
					}
				} else {
					addActionMessage("修改密码失败，新密码不能为空!");
				}
			} else {
				addActionMessage("修改密码失败，旧密码不正确!");
			}
		} else {
			addActionMessage("修改密码失败，旧密码不能为空!");
		}
		return "myPassword";
	}

	/**
	 * 清除密码
	 *
	 * @return
	 */
	public String clearPassword() {
		String sid = CookieUtils.getCookieValue(Struts2Utils.getRequest(), Constants.COOKIE_KEY);
		CookieUtils.setCookieValue(Struts2Utils.getResponse(), "autoLogin", "0");
		CookieUtils.setCookieValue(Struts2Utils.getResponse(), new Md5PwdEncoder().encodePassword(sid + "zdop_us"), "");
		CookieUtils.setCookieValue(Struts2Utils.getResponse(), new Md5PwdEncoder().encodePassword(sid + "zdop_pw"), "");
		return "toIndex";
	}

	/**
	 * 添加/更新用户
	 *
	 * @return
	 */
	public String save() {
		//用户id为空，则表示添加,否则为修改
		if (user.getId() == null) {
			//检测用户名是否已经存在
			MapBean mb = new MapBean();
			mb.put("userName", user.getUserName());
			mb.put("allState", "all");
			if (us.isUserNameExist(mb)) {
				addActionMessage("账号已经存在!");
			} else {
				try {
					Md5PwdEncoder encoder = new Md5PwdEncoder();
					String md5_password = encoder.encodePassword(user.getPassword());
					user.setPassword(md5_password);
					user.setViewRecharge("");
					us.saveUser(user);
					addActionMessage("新增管理员成功!");
				} catch (Exception e) {
					addActionMessage("新增管理员出错：" + e);
					logger.error("新增管理员出错：" + e);
				}
			}

		} else {
			//如果用户提交了新密码，则加密后一同更新
			if (user.getPassword() != null && !"".equals(user.getPassword())) {
				Md5PwdEncoder encoder = new Md5PwdEncoder();
				String md5_password = encoder.encodePassword(user.getPassword());
				user.setPassword(md5_password);
			}
			try {
				StringBuffer sb = new StringBuffer();
				if (games != null) {
					for (String g : games)
						if (!g.trim().isEmpty()) sb.append(g + ",");
					if (sb.length() >= 2) sb.deleteCharAt(sb.length() - 1);
					user.setViewRecharge(sb.toString());
				} else {
					user.setViewRecharge("");
				}
				us.updateUser(user);
				addActionMessage("修改管理员成功!");
			} catch (Exception e) {
				addActionMessage("修改管理员出错：" + e);
				logger.error("修改管理员出错：" + e);
			}
		}
		return RELOAD;

	}

	/**
	 * 检查帐号是否已经存在
	 *
	 * @return
	 */
	public String checkUserName() {
		try {
			MapBean mb = new MapBean();
			mb.put("userName", userName);
			mb.put("allState", "all");
			boolean isUnique = us.isUserNameExist(mb);
			Struts2Utils.renderText(isUnique + "");
		} catch (Exception e) {
			logger.error("检查帐号是否已经存在出错：" + e.getMessage(), e);
		}
		return null;
	}

	public Page<User> getPage() {
		return page;
	}

	public void setPage(Page<User> page) {
		this.page = page;
	}

	public String getCheckedIds() {
		return checkedIds;
	}

	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getSearchUser() {
		return searchUser;
	}

	public void setSearchUser(User searchUser) {
		this.searchUser = searchUser;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public String[] getGames() {
		return games;
	}

	public void setGames(String[] games) {
		this.games = games;
	}

	public Set<String> getViewRechargeSet() {
		return viewRechargeSet;
	}

	public void setViewRechargeSet(Set<String> viewRechargeSet) {
		this.viewRechargeSet = viewRechargeSet;
	}

	public List<Dictionary> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Dictionary> departmentList) {
		this.departmentList = departmentList;
	}

	public List<Dictionary> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Dictionary> groupList) {
		this.groupList = groupList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}
