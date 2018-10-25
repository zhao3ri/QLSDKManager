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
 * ����Ա�˺ſ�����
 *
 * @author guojt
 * @since 2010-02-14
 */
@Action
public class UserAction extends Struts2Action {

	private static final long serialVersionUID = -4714087534313256399L;
	private static final String SUCCESS_MSG_FORMAT = "����Ϊ\"%s\"״̬�ɹ�!";

	@Autowired
	private UserService us;
	@Autowired
	private RoleService rs;
	@Autowired
	private DictionaryService ds;

	private User user;
	private User searchUser;    //���ڱ�������������User����
	private Page<User> page = new Page<User>(10);
	private String checkedIds;
	private List<Role> roleList;
	private String audit;
	private Set<String> viewRechargeSet = new HashSet<String>();    //�ù���Ա�ܲ鿴�ļ�����Ϸ�ĳ�ֵ
	private String[] games = null;
	private List<Dictionary> departmentList = new ArrayList<Dictionary>();
	private List<Dictionary> groupList = new ArrayList<Dictionary>();
	private int id;
	private String userName;
	private String oldPassword;

	private HttpServletResponse response = ServletActionContext.getResponse();

	/**
	 * ��ȡ����Ա�б�
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
			logger.error("��ȡ����Ա�б����", e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * ��ȡ��ѯ����
	 *
	 * @return
	 */
	private MapBean search() {
		MapBean mb = new MapBean();
		if (searchUser != null) {
			//�����������
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
	 * ��ֹ/���ù���Ա
	 *
	 * @return
	 */
	public String disabled() {
		try {
			if (audit != null) {
				MapBean mb = new MapBean();
				mb.put("state", audit);
				addActionMessage(String.format(SUCCESS_MSG_FORMAT,StateContext.getStateConfigs().get("userState").get(audit + "").getName()));
//                addActionMessage("����Ϊ\"" + StateContext.getStateConfigs().get("userState").get(audit + "").getName() + "\"״̬�ɹ�");
				for (String checkedId : StringUtils.split(checkedIds, ",")) {
					mb.put("id", Long.valueOf(checkedId));
					us.disabled(mb);
				}
			}
		} catch (Exception e) {
			logger.error("�޸Ĺ���Ա״̬�����쳣��" + e);
			addActionMessage("�޸Ĺ���Ա״̬�����쳣��" + e);
		}
		return RELOAD;
	}

	/**
	 * ��ʼ����ӹ���Ա
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
	 * Ajax ��ѯĳ�������С��
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
	 * ��ʼ���޸Ĺ���Ա
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
	 * �޸ĸ�������-ҳ��
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
	 * �����޸ĸ�������
	 *
	 * @return
	 */
	public String saveMyInfo() {
		try {
			user.setUpdateTime(new Date());
			us.updateMyInfo(user);
			addActionMessage("�޸ĸ������ϳɹ�!");
		} catch (Exception e) {
			addActionMessage("�޸ĸ������ϳ���" + e);
			logger.error("�޸ĸ������ϳ���" + e);
		}
		return "myInfo";
	}

	/**
	 * �޸�����-ҳ��
	 *
	 * @return
	 */
	public String updateMyPassword() {
		user = us.getUserById(user.getId());
		return "myPassword";
	}

	/**
	 * �����޸�����
	 *
	 * @return
	 */
	public String saveMyPassword() {
		User u = us.getUserById(user.getId());
		if (oldPassword != null && !"".equals(oldPassword)) {
			Md5PwdEncoder encoder = new Md5PwdEncoder();
			String md5_oldPassword = encoder.encodePassword(oldPassword);
			if (md5_oldPassword.equals(u.getPassword())) {
				//����û��ύ�������룬����ܺ�һͬ����
				if (user.getPassword() != null && !"".equals(user.getPassword())) {
					String md5_password = encoder.encodePassword(user.getPassword());
					user.setPassword(md5_password);
					try {
						user.setUpdateTime(new Date());
						us.updateMyPassword(user);
						addActionMessage("�޸�����ɹ�!");
					} catch (Exception e) {
						addActionMessage("�޸��������" + e);
						logger.error("�޸��������" + e);
					}
				} else {
					addActionMessage("�޸�����ʧ�ܣ������벻��Ϊ��!");
				}
			} else {
				addActionMessage("�޸�����ʧ�ܣ������벻��ȷ!");
			}
		} else {
			addActionMessage("�޸�����ʧ�ܣ������벻��Ϊ��!");
		}
		return "myPassword";
	}

	/**
	 * �������
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
	 * ���/�����û�
	 *
	 * @return
	 */
	public String save() {
		//�û�idΪ�գ����ʾ���,����Ϊ�޸�
		if (user.getId() == null) {
			//����û����Ƿ��Ѿ�����
			MapBean mb = new MapBean();
			mb.put("userName", user.getUserName());
			mb.put("allState", "all");
			if (us.isUserNameExist(mb)) {
				addActionMessage("�˺��Ѿ�����!");
			} else {
				try {
					Md5PwdEncoder encoder = new Md5PwdEncoder();
					String md5_password = encoder.encodePassword(user.getPassword());
					user.setPassword(md5_password);
					user.setViewRecharge("");
					us.saveUser(user);
					addActionMessage("��������Ա�ɹ�!");
				} catch (Exception e) {
					addActionMessage("��������Ա����" + e);
					logger.error("��������Ա����" + e);
				}
			}

		} else {
			//����û��ύ�������룬����ܺ�һͬ����
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
				addActionMessage("�޸Ĺ���Ա�ɹ�!");
			} catch (Exception e) {
				addActionMessage("�޸Ĺ���Ա����" + e);
				logger.error("�޸Ĺ���Ա����" + e);
			}
		}
		return RELOAD;

	}

	/**
	 * ����ʺ��Ƿ��Ѿ�����
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
			logger.error("����ʺ��Ƿ��Ѿ����ڳ���" + e.getMessage(), e);
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
