package com.jatools.common;

import org.springframework.web.context.WebApplicationContext;

/**
 * 应用全局配置文件
 */
public class Global {
	public static WebApplicationContext springContext = null;
	public static String				CONTEXT												= "";//应用上下文/wlscm
	public static final String 			COOKIE_USER_NAME									= "zs_user_name";//账号密码 cookie名称
	public static final String			COOKIE_USER_PASSWORD								= "zs_user_password";//账号密码 cookie名称
	public static final int				COOKIE_MAX_AGE										= 36288000;//单位秒
	public static final String			COOKIE_CLIENT_NAME									= "client_name";
	public static final String			COOKIE_IS_LOGIN										= "is_login";

	public static final String			PAGE_DEFAULT_START									= "1";//列表默认从1开始
	public static final String			PAGE_DEFAULT_LIMIT									= "15";//列表默认每页15行
	public static final String			PAGE_DEFAULT_LIMIT_WIN								= "10";//弹出框列表默认每页10行
	
	public static boolean				ENABLE_SSO_FLAG										= false;//是否开启单点登录
	public static boolean				ENABLE_REMOTE_REVIEW_FLAG							= false;//是否开启远程审批
	public static String				LOCAL_ADDR_IP										= null;//本地IP
	public static String				LOCAL_ADDR_PORT										= null;//本地端口
}
