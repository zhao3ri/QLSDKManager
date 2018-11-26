package com.item.web.action.login;


import com.item.domain.Dashbord;
import com.item.service.DashbordService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.constants.Constants;
import com.item.domain.authority.User;
import com.item.service.authority.AuthCacheManager;
import com.item.service.authority.UserService;
import com.item.utils.CookieUtils;
import com.item.utils.DES;
import com.item.utils.Md5PwdEncoder;
import com.item.utils.PropertyUtils;

import core.module.orm.MapBean;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;

/**
 * @author liuxh
 * @version 创建时间：2011-7-11 上午01:45:12
 * <p>
 * 类说明：平台登陆Action
 */
@Action
public class LoginAction extends Struts2Action {

    private static final long serialVersionUID = 605955384879187074L;

    @Autowired
    private UserService userService;

    private String userName;
    private String password;
    private String refererUrl;
    @Autowired
    private DashbordService dashbordService;

    private Dashbord dashbord;

    /**
     * 加载登陆页面
     *
     * @return
     */
    public String view() {
        return "login";
    }

    /**
     * 登陆方法
     *
     * @return
     */
    public String in() {
        try {
            if ("true".equals(PropertyUtils.get("authority.control"))) {
                Md5PwdEncoder encoder = new Md5PwdEncoder();
                String md5_password = "";

                String sid = CookieUtils.getCookieValue(Struts2Utils.getRequest(), Constants.COOKIE_KEY);
                //如果用户名、密码为空则判断cookies是否保存了用户名、密码
                if (userName == null && password == null) {
                    md5_password = DES.decryptBase64(CookieUtils.getCookieValue(Struts2Utils.getRequest(), encoder.encodePassword(sid + "zdop_pw")), null);
                    userName = DES.decryptBase64(CookieUtils.getCookieValue(Struts2Utils.getRequest(), encoder.encodePassword(sid + "zdop_us")), null);
                } else {
                    md5_password = encoder.encodePassword(password);
                }

                MapBean mb = new MapBean();
                mb.put("userName", userName);
                mb.put("state", 0);
                mb.put("projectType", 1);
                User user = userService.getUserByUserName(mb);
                if (user != null) {
                    if (md5_password.equals(user.getPassword())) {
                        Struts2Utils.getSession().setAttribute("sessionUser", user.getUserName());

                        Struts2Utils.getSession().setAttribute("sessionUserInfo", user);

                        //从缓存中取出该用户所属角色的权限，并拼凑成字符串放到session中，由页面解析
                        String permission = AuthCacheManager.getInstance().getPermissionText(user.getIdentityId());
                        Struts2Utils.getSession().setAttribute("roleAuthStr", permission);
                        System.err.println("roleAuthStr===" + permission);

                        CookieUtils.setCookieValue(Struts2Utils.getResponse(), encoder.encodePassword(sid + "zdop_us"), DES.encryptAndBase64(user.getUserName(), null));
                        CookieUtils.setCookieValue(Struts2Utils.getResponse(), encoder.encodePassword(sid + "zdop_pw"), DES.encryptAndBase64(user.getPassword(), null));

                        if (!StringUtils.isEmpty(refererUrl) && refererUrl.indexOf(".shtml") > 0) {
                            Struts2Utils.getResponse().sendRedirect(refererUrl);
                            return null;
                        }

                        return RELOAD;
                    } else {
                        addActionMessage("密码错误");
                        return "in";
                    }
                } else {
                    addActionMessage("用户名不存在");
                    return "in";
                }
            } else {
                if ("admin".equals(userName) && "admin".equals(password)) {
                    Struts2Utils.getSession().setAttribute("sessionUser", "admin");

                    System.out.println("========用户登录========");
                    return RELOAD;
                } else {
                    addActionMessage("用户名或者密码错误");
                    System.out.println("========登陆失败========");
                    return "in";
                }
            }
        } catch (Exception e) {
            logger.error("登录出错：" + e.getMessage());
            addActionMessage("登录出错:" + e);
            return "in";
        }
    }

    /**
     * 退出方法
     *
     * @return
     */
    public String out() {
        String sid = CookieUtils.getCookieValue(Struts2Utils.getRequest(), Constants.COOKIE_KEY);
        try {
            Struts2Utils.getSession().removeAttribute("sessionUser");
            Struts2Utils.getSession().removeAttribute("sessionUserInfo");
            CookieUtils.setCookieValue(Struts2Utils.getResponse(), "autoLogin", "0");
            CookieUtils.setCookieValue(Struts2Utils.getResponse(), new Md5PwdEncoder().encodePassword(sid + "zdop_us"), "");
            CookieUtils.setCookieValue(Struts2Utils.getResponse(), new Md5PwdEncoder().encodePassword(sid + "zdop_pw"), "");
            dashbordService.reset();
            if ("true".equals(PropertyUtils.get("authority.control"))) {
                Struts2Utils.getSession().removeAttribute("roleAuthStr");
                AuthCacheManager.getInstance().cleanIdentityPermissionGameList();
            }
        } catch (Exception e) {
            logger.error("退出错误：" + e.getMessage());
            addActionMessage("退出错误:" + e);
        }
        return "out";
    }

    public String index() {
        dashbord = dashbordService.loadDashbord();
        return "index";
    }

    public String list() {
        return "getGameList";
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

    public Dashbord getDashbord() {
        return dashbord;
    }

    public void setDashbord(Dashbord dashbord) {
        this.dashbord = dashbord;
    }

    public String getRefererUrl() {
        return refererUrl;
    }

    public void setRefererUrl(String refererUrl) {
        this.refererUrl = refererUrl;
    }
}
