package com.item.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.item.constants.Constants;
import com.item.domain.authority.User;
import com.item.service.authority.AuthCacheManage;
import com.item.utils.CookieUtils;
import com.item.utils.Md5PwdEncoder;
import com.item.utils.PropertyUtils;

/**
 * 后台登陆权限过滤
 *
 * @author liuxh
 * @since 2012-01-13
 */
public class LoginFilter implements Filter {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

    FilterConfig config = null;
    //	private User userInfo;
//    private String targetURL = null;	//请求地址
    String[] unFilterExts = {".bmp", ".gif", ".jpg", ".png", ".ico", ".js", ".css", ".eot", ".svg", ".ttf", ".woff", ".dwr"};    //不参与过滤的文件后缀

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        //防止页面出现乱码
        res.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");

        //取session中的用户
        String user = (String) req.getSession().getAttribute("sessionUser");
        User userInfo = (User) req.getSession().getAttribute("sessionUserInfo");

        String sid = CookieUtils.getCookieValue(req, Constants.COOKIE_KEY);
        if (StringUtils.isEmpty(sid))
            sid = java.util.UUID.randomUUID().toString();
        CookieUtils.setCookieValue(res, Constants.COOKIE_KEY, sid);

        //带命名空间的请求路径
        String currentURL = req.getRequestURI();
        //截取不带命名空间的请求路径
        String targetURL = currentURL.substring(currentURL.lastIndexOf("/") + 1);

        //如果用户没登陆，且访问的不是登陆界面，或者访问的不是图片，则跳转至登录界面
        if ((StringUtils.isEmpty(user) || userInfo == null)
                && targetURL.indexOf(".shtml") > 0
                && (targetURL.indexOf("login.shtml") < 0)
                && (targetURL.indexOf("logview.shtml") < 0)
                && (targetURL.indexOf("logout.shtml") < 0)
                && !isVisitUnFilterResource(targetURL)) {

            String md5_password = CookieUtils.getCookieValue(req, new Md5PwdEncoder().encodePassword(sid + "zdop_pw"));
            String userName = CookieUtils.getCookieValue(req, new Md5PwdEncoder().encodePassword(sid + "zdop_us"));

            if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(md5_password)) {
                String refererUrl = req.getContextPath() + req.getRequestURI();
                res.sendRedirect(req.getContextPath() + "/login.shtml?refererUrl=" + refererUrl);
            } else {
                res.sendRedirect(req.getContextPath() + "/logview.shtml");
            }

        } else {
            if ("true".equals(PropertyUtils.get("authority.control"))) {
                //判断是否是受保护资源
                if (isProtectedResource(targetURL)) {
                    //继续判断是否有权限访问
                    if (!checkAuth(targetURL, userInfo)) {
                        noAuthTip(req, res);//提示无权访问
                    }
                }
            }
            chain.doFilter(req, res);
        }
    }

    public void destroy() {
        this.config = null;
    }

    /**
     * 检查访问的是否是图片等不需要过滤的资源
     *
     * @return
     */
    public boolean isVisitUnFilterResource(String targetURL) {
        boolean result = false;
        for (int i = 0; i < unFilterExts.length; i++) {
            if (targetURL.toLowerCase().endsWith(unFilterExts[i])) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 判断是不是受保护资源，未受保护资源是只要登录就可以访问的资源,受保护的资源只有赋予用户相应权限才能访问
     *
     * @return
     */
    public boolean isProtectedResource(String targetURL) {
        //如果请求的url存在权限Map中，则为受保护资源
        if (AuthCacheManage.allAuthMap.containsKey(targetURL)) {
            return true;
        }
        return false;
    }

    /**
     * 检查访问权限
     */
    public boolean checkAuth(String targetURL, User userInfo) {
        //取出当前登录用户所属角色拥有的权限
        Map<String, String> map = AuthCacheManage.roleAuthMap.get(userInfo.getRoleID());
        if (map == null || map.isEmpty()) {
            return false;
        }
        //如果该角色的权限包含所请求的URL，则表示有权限访问
        if (map.containsKey(targetURL)) {
            return true;
        }
        return false;

    }

    /**
     * 没有权限时，跳转至提示页面
     *
     * @param req
     * @param res
     * @throws IOException
     */
    public void noAuthTip(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + "/common/403.jsp");
        return;
    }
}

