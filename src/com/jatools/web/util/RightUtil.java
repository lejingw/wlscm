package com.jatools.web.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import com.jatools.common.CommonUtil;
import com.jatools.common.Global;
import com.jatools.dao.util.CommonDao;
import com.jatools.vo.sys.RightMapping;

public class RightUtil {
	private static final String RIGHT_SESSION_KEY = "right_session_key";
	private static String SESSION_USER_ID = null;
	private static String SESSION_ORG_ID = null;
	/**
	 * 判断是否有权限
	 * @param session
	 * @param toolbarCode
	 * @param itemCode
	 * @return
	 */
	public static boolean isAble(HttpSession session, String toolbarCode, String itemCode){
		if(null == session)
			return false;
		if(!Global.ENABLE_SSO_FLAG){
			return true;
		}
		String flag = hasRight(session, toolbarCode, itemCode);
		if("-1".equals(flag)){
			CommonDao commonDao =  (CommonDao)Global.springContext.getBean("commonDao");
			queryToolbarRight(session, toolbarCode, itemCode, commonDao);
			flag = hasRight(session, toolbarCode, itemCode);
		}
		return "1".equals(flag);
	}

	/**
	 * 判断是否有权限，直接输出隐藏button样式
	 * @param session
	 * @param toolbarCode
	 * @param itemCode
	 * @return
	 */
	public static String able(HttpSession session, String toolbarCode, String itemCode){
		if(null == session){
			return "style='display:none;'";
		}
		return getRight(isAble(session, toolbarCode, itemCode));
	}
	/**
	 * 是否有权限的样式
	 * @param flag
	 * @return
	 */
	private static String getRight(boolean flag) {
		if(Global.ENABLE_SSO_FLAG){
			return flag?"":"style='display:none;'";
//			return flag?"":"style='color: red'";
		}else{
			return flag?"":"style='color: blue'";
		}
	}
	/**
	 * 查询数据库获取权限
	 * @param session
	 * @param toolbarCode
	 * @param itemCode
	 * @param commonDao
	 */
	private static void queryToolbarRight(HttpSession session, String toolbarCode, String itemCode, CommonDao commonDao) {
		try {
			List<RightMapping> rightList = commonDao.getToolbarRight(toolbarCode, CommonUtil.getSessionOrgId(session), CommonUtil.getSessionUserId(session));
			Map<String, String> map = getRightSessionMap(session);
			for(RightMapping rm : rightList){
				map.put(rm.getToolbarCode() + "$" + rm.getItemCode(), rm.getHasRight());
			}
			//标识此工具条权限已经查询过
			map.put(toolbarCode, "1");
		} catch (Exception e) {
		}
	}
	/**
	 * 从session中获取权限
	 * @param session
	 * @param toolbarCode
	 * @param itemCode
	 * @return
	 */
	private static String hasRight(HttpSession session, String toolbarCode, String itemCode){
		if(!Global.ENABLE_SSO_FLAG){
			return "1";
		}
		Map<String, String> map = getRightSessionMap(session);
		String ableFlag = map.get(toolbarCode + "$" + itemCode);
		if(null == ableFlag){
			//如果此工具条权限已经查询过
			if(null != map.get(toolbarCode)){
				return "0";
			}else{
				return "-1";
			}
		}
		return ableFlag;
	}
	
	private static Map<String, String> getRightSessionMap(HttpSession session){
		Object obj = null;
		if(StringUtil.equalsIgnoreNull(SESSION_USER_ID, CommonUtil.getSessionUserId(session)) && StringUtil.equalsIgnoreNull(SESSION_ORG_ID, CommonUtil.getSessionOrgId(session))){			
			obj = session.getAttribute(RIGHT_SESSION_KEY);
		}else{
			//如果是切换组织、用户，则需要重新获取权限
			SESSION_USER_ID = CommonUtil.getSessionUserId(session);
			SESSION_ORG_ID = CommonUtil.getSessionOrgId(session);
		}
		Map<String, String> map = null;
		if(null == obj || !(obj instanceof Map)){
			map = new HashMap<String, String>();
			session.setAttribute(RIGHT_SESSION_KEY, map); 
		}else{
			map = (Map<String, String>)obj;
		}
		return map;
	}
}
