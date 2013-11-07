package com.jatools.common;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.jatools.common.constant.ParameterConstant;
import com.jatools.web.cache.ParameterCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.sys.AuthToken;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
import com.mclon.sfqh.Yonghusf;

public class CommonUtil {
	private static Logger logger = Logger.getLogger(CommonUtil.class);
	private static final String AUTH_TOKEN_SSO			= "yonghusf";			//session中的用户登陆信息
	private static final String AUTH_TOKEN					= "auth_token";			//session中的用户登陆信息
	
	/**
	 * 从request中获取参数信息，未找到返回null
	 * @param req
	 * @param name
	 * @return
	 */
	public static String getParameterNull(HttpServletRequest req, String name){
		return req.getParameter(name);
	}
	/**
	 * 从request中获取参数信息，未找到返回defaultValue
	 * @param req
	 * @param name
	 * @return
	 */
	public static String getParameterNull(HttpServletRequest req, String name, String defaultValue){
		String tmp = getParameterNull(req, name);
		if(null == tmp)
			return defaultValue;
		return tmp;
	}
	public static Object getAttribute(HttpSession session, String name){
		return session.getAttribute(name);
	}
	public static Object getAttribute(HttpSession session, String name, Object defaultValue){
		Object tmp = getAttribute(session, name);
		logger.debug("从session取值：" +name+"："+tmp);
		if(null == tmp)
			return defaultValue;
		return tmp;
	}
	
	/**
	 * 从request中获取参数信息，未找到返回空字符串""
	 * @param req
	 * @param name
	 * @return
	 */
	public static String getParameterEmpty(HttpServletRequest req, String name){
		String val = req.getParameter(name);
		if(null == val)	return "";
		return val;
	}
	/**
	 * 获取登陆用户id
	 * @param req
	 * @return
	 */
	public static String getSessionUserId(HttpServletRequest req){
		if(null == req){
			return null;
		}
		HttpSession session = req.getSession();
		return getSessionUserId(session);
	}
    
    /**
     * 判断客户是否登陆
     * @param session
     * @return false : 未登录；true ： 已登录;
     */
    public static final boolean isLogined(HttpServletRequest request){
    	if(request!=null){
	    	HttpSession session = request.getSession();
	    	return isLogined(session);
	    }else{
	    	return false;
	    }
    }
    private static AuthToken getAuthToken(HttpSession session){
    	AuthToken authToken = null;
		if(Global.ENABLE_SSO_FLAG){
			authToken = getSsoAuthToken(session);
		}else{
			authToken = (AuthToken) session.getAttribute(AUTH_TOKEN);
		}
		return authToken;
    }
    private static AuthToken getSsoAuthToken(HttpSession session){
		Yonghusf yonghusf = (Yonghusf) session.getAttribute(AUTH_TOKEN_SSO);
		if(null == yonghusf){
			throw new RuntimeException("统一认证不能获取用户信息");
		}
    	String code = yonghusf.getQiehuanyubh();
		String userid = UserCache.getInstance().getUserIdByCode(code);
		String username = UserCache.getInstance().getUserName(userid);
		String orgId = yonghusf.getDangqiancwzzbh();
		
		AuthToken authToken = new AuthToken();
		authToken.setUserId(userid);
		authToken.setUserName(username);
		authToken.setOrgId(orgId);
		return authToken;
    }
    /**
     * 判断客户是否登陆
     * @param session
     * @return false : 未登录；true ： 已登录;
     */
    public static final boolean isLogined(HttpSession session){
		if (session != null) {
			return null != getAuthToken(session);
		} else {
			return false;
		}
    }
    
	public static void addSessionToken(HttpSession session, String userid, String username, String orgid) {
		if(null != session){
			AuthToken authToken = new AuthToken();
			authToken.setUserId(userid);
			authToken.setUserName(username);
			authToken.setOrgId(orgid);
			session.setAttribute(AUTH_TOKEN, authToken);
		}
	}
	
	public static void logoutClearSession(HttpSession session) {
		if(null != session){
			session.removeAttribute(AUTH_TOKEN);
			session.setAttribute(AUTH_TOKEN, null);
		}
	}
	
	/**
	 * 获取登陆用户id
	 * @param req
	 * @return
	 */
	public static String getSessionUserId(HttpSession session){
		if(null == session){
			return null;
		}
		AuthToken authToken = getAuthToken(session);
		if(null == authToken){
			throw new RuntimeException("session失效，请重新登录");
		}
		return authToken.getUserId();
	}
	/**
	 * 获取登陆用户name
	 * @param req
	 * @return
	 */
	public static String getSessionUserName(HttpSession session){
		if(null == session){
			return null;
		}
		AuthToken authToken = getAuthToken(session);
		if(null == authToken){
			throw new RuntimeException("session失效，请重新登录");
		}
		return authToken.getUserName();
	}
	/**
	 * 获取登录组织
	 * @param session
	 * @return
	 */
	public static String getSessionOrgId(HttpServletRequest req){
		if(null == req){
			return null;
		}
		HttpSession session = req.getSession();
		return getSessionOrgId(session);
	}
	/**
	 * 获取登录组织
	 * @param session
	 * @return
	 */
	public static String getSessionOrgId(HttpSession session) {
		if(null == session){
			return null;
		}
		AuthToken authToken = getAuthToken(session);
		if(null == authToken){
			throw new RuntimeException("session失效，请重新登录");
		}
		return authToken.getOrgId();
	}
	/**
	 * 根据Class的可写字段，从request中获取值，并生成对应的实例
	 * 注意此方法只会写String类型的字段
	 * @param req
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T> T request2Object(HttpServletRequest req, Class<T> cls){
		try {
			if(req == null || cls == null) {
				return null;
			}
			T rtnObj = cls.newInstance();
			Field[] feilds = cls.getDeclaredFields();
			for( Field field: feilds ) {
				if( StringUtil.isBlank(req.getParameter(field.getName()))) {
					continue;
				}
				if( PropertyUtils.isWriteable(rtnObj, field.getName())) {
					Class<?> fieldClazz = PropertyUtils.getPropertyType(rtnObj, field.getName());
					if(fieldClazz == java.lang.String.class){
						PropertyUtil.setProperty(rtnObj, field.getName(), getParameterNull(req, field.getName()));
					}
				}
			}
			return rtnObj;
		} catch (Exception e) {
			logger.error("根据request创建" + cls.getName() + "失败");
			throw new RuntimeException("根据request创建" + cls.getName() + "失败");
		}
	}
	
	/**
	 * 添加start和limit key
	 * @param keys
	 * @return
	 */
	private static String[] addStartLimitKey(String[] keys, boolean flag){
		if(null == keys){
			keys = new String[0];
		}
		String[] pageKeys = new String[flag?keys.length+3:keys.length + 2];
		for(int i=0;i<keys.length;i++){
			pageKeys[i] = keys[i];
		}
		pageKeys[keys.length] = "start";
		pageKeys[keys.length + 1] = "limit";
		if(flag){
			pageKeys[keys.length + 2] = "_q_sql";
		}
		return pageKeys;
	}
	
	/**
	 * 从request获取值
	 * @param req
	 * @param condition
	 * @param keys
	 */
	private static void getRequestParameter(HttpServletRequest req, Map<String, String> condition, String... keys) {
		if(null == keys||keys.length<1){
			return ;
		}
		try {
			for(String key : keys){
				String tmp = getParameterNull(req, key);
				if("_q_sql".equals(key) && StringUtil.isNotEmpty(tmp)){
					condition.put(key, tmp);
				}else if(null != tmp){
					condition.put(key, new String(tmp.getBytes("ISO-8859-1"),"utf-8"));
				}
			}
			condition.put("start", getParameterNull(req, "start"));
			condition.put("limit", getParameterNull(req, "limit"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
	}
	private static void setDefaultStartLimit(Map<String, String> condition, String startDefaultValue, String limitDefaultValue) {
		if(StringUtil.isEmpty(condition.get("start"))){
			condition.put("start", startDefaultValue);
		}
		if(StringUtil.isEmpty(condition.get("limit"))){
			condition.put("limit", limitDefaultValue);
		}
	}
	/**
	 * 从request获取查询条件，包含start（默认为0）、limit（默认15）参数
	 * @param req
	 * @param strings
	 * @return
	 */
	public static Map<String, String> getConditionForPage(HttpServletRequest req, String... keys) {
		keys = addStartLimitKey(keys, false);
		Map<String, String> condition = new HashMap<String, String>();
		getRequestParameter(req, condition, keys);
		setDefaultStartLimit(condition, Global.PAGE_DEFAULT_START, Global.PAGE_DEFAULT_LIMIT);
		return condition;
	}
	/**
	 * 从request获取查询条件，包含start（默认为0）、limit（默认15）参数
	 * @param req
	 * @param strings
	 * @return
	 */
	public static Map<String, String> getConditionForPageSession(BaseMultiActionController controller, HttpServletRequest req, String... keys) {
		keys = addStartLimitKey(keys, true);
		Map<String, String> condition = new HashMap<String, String>();
		getRequestParameter(req, condition, keys);
		QueryConditionUtil.getSessionParameter(controller, req, condition, keys);
		setDefaultStartLimit(condition, Global.PAGE_DEFAULT_START, Global.PAGE_DEFAULT_LIMIT);
		return condition;
	}
	
	/**
	 * 从request获取查询条件，包含start（默认为0）、limit（默认10）参数
	 * @param req
	 * @param strings
	 * @return
	 */
	public static Map<String, String> getConditionForPage2(HttpServletRequest req, String... keys) {
		keys = addStartLimitKey(keys, false);
		Map<String, String> condition = new HashMap<String, String>();
		getRequestParameter(req, condition, keys);
		setDefaultStartLimit(condition, Global.PAGE_DEFAULT_START, Global.PAGE_DEFAULT_LIMIT_WIN);
		return condition;
	}
	/**
	 * 获取图片根目录
	 * @return
	 */
	public static String getPicBasePath(){
		String basePath = ParameterCache.getInstance().getValue(ParameterConstant.UTIL_STYLE_PIC_BASE_PATH_OUT);
		if(StringUtil.isEmpty(basePath))
			basePath = "";
//		return "http://" + Global.LOCAL_ADDR_IP + ":" + Global.LOCAL_ADDR_PORT + Global.CONTEXT + basePath;
		return basePath;
	}
	/**
	 * 获取图片根目录(内网)
	 * @return
	 */
	public static String getPicBasePath2(){
		String basePath = ParameterCache.getInstance().getValue(ParameterConstant.UTIL_STYLE_PIC_BASE_PATH_IN);
		if(StringUtil.isEmpty(basePath))
			basePath = "";
//		return "http://" + Global.LOCAL_ADDR_IP + ":" + Global.LOCAL_ADDR_PORT + Global.CONTEXT + basePath;
		return basePath;
	}

    public static void resetCondition(BaseMultiActionController controller, HttpServletRequest req){
        QueryConditionUtil.resetCondition(controller, req);
    }
}