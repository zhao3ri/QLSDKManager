package com.item.constants;

public class Constants {

    public final static String COOKIE_KEY = "sms_manage_sid";

    public static final String CLASS_PATH = "/WEB-INF/classes/";
    /**
     * cookie的路径.
     */
    public static final String COOKIE_PATH = "/";

    /**
     * Cookie的域名.
     */
    public static String COOKIE_DOMAIN = "y6.cn";

    /**
     * 域名.
     */
    public static String CONTEXT_PATH = "";

    /**
     * 过期时间.
     */
    public static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 14;

    /**
     * 状态：删除
     */
    public static final int STATE_DELETE = 0;
    /**
     * 状态：正常
     */
    public static final int STATE_NORMAL = 1;
    /**
     * 状态：暂停/锁定
     */
    public static final int STATE_SUSPEND = 2;
    /**
     * WAP业务
     */
    public static final String ADV_WAP = "ADV_WAP";
    /**
     * APP业务
     */
    public static final String ADV_APP = "ADV_APP";
    /**
     * 线下业务
     */
    public static final String OFFLINE_APP = "OFFLINE_APP";

    public static final String COOKIE_PAGESIZE = "cookie_pageSize";


    public static final Integer CLIENT_ANDROID = 1;
    public static final Integer CLIENT_IOS = 2;

    /**
     * 超级管理员身份id
     */
    public static final int ADMIN_IDENTITY_ID = 3;

    public static final int FUNCTION_ID_CHANNEL_MANAGER = 228;

    /**
     * 权限管理模块id
     * */
    public static final int MODULE_ID_PERMISSION = 2;
}
