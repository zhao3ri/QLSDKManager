package com.item.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.item.constants.Constants;
import com.item.exception.ApplicationException;
import com.item.service.authority.AuthCacheManager;
import com.item.utils.PropertyUtils;

import core.module.utils.SpringUtils;

import static com.item.constants.Constants.CLASS_PATH;

/**
 * 系统应用初始化.
 *
 * @author liuxh
 * @since 2011-07-08
 */
public class ApplicationListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

    public void contextInitialized(ServletContextEvent event) {
        try {
            logger.info("=============== 开始加载平台系统应用 ===============");
            // 加载配置信息
            String appFile = event.getServletContext().getRealPath("") + CLASS_PATH + "application.properties";
            PropertyUtils.loadFile(appFile);


            Constants.CONTEXT_PATH.append(PropertyUtils.getPath());

            logger.info("平台 context_path: {}", Constants.CONTEXT_PATH);

            WebApplicationContext ctx = SpringUtils.getCtx(event.getServletContext());

            //加载日志注解类
            LogAnnotation.init();

            //初始化话状态内容
            StateContext.initStateConfigs(event.getServletContext().getRealPath("") + CLASS_PATH + "state.xml");

            if ("true".equals(PropertyUtils.get("authority.control"))) {
                //缓存权限
                AuthCacheManager acm = (AuthCacheManager) ctx.getBean("authCacheManager");
                AuthCacheManager.create(acm).init();
            }
            logger.info("=============== 加载平台系统应用完毕 ===============");
        } catch (ApplicationException e) {
            logger.error("加载平台系统应用失败: {}", e.getMessage());
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
    }
}
