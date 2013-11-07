package com.jatools.web.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jatools.common.Global;
import com.jatools.manager.sys.UserManager;
import com.jatools.vo.sys.User;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
/**
 * 用户数据缓存
 * @author wanglj
 *
 */
public class UserCache implements CacheSingletonIntf {
	private static Logger logger = Logger.getLogger(UserCache.class);
	private static UserCache userCache = null;
	private static Map<String, User> userMap = null;
	private static Map<String, User> userMap2 = null;
	private static Date refreshTime = null;

	private UserCache() {
	}

	public synchronized static UserCache getInstance() {
		if (null == userCache) {
			init();
		}
		return UserCache.userCache;
	}
	public synchronized static void refresh(){
		userCache = null;
		userMap = null;
		userMap2 = null;
		init();
	}

	private synchronized static void init() {
		if (null == UserCache.userCache) {
			logger.debug("初始化用户缓存数据...");
			try {
				UserManager userManager = (UserManager) Global.springContext.getBean("userManager");
				if (null != userManager) {
					UserCache.userMap = new HashMap<String, User>();
					UserCache.userMap2 = new HashMap<String, User>();
					List<User> list = userManager.getAllUser();
					for(User user : list){
						UserCache.userMap.put(user.getUserid(), user);
						UserCache.userMap2.put(user.getUsercode(), user);
					}
				}
				UserCache.userCache = new UserCache();
			} catch (Exception e) {
				logger.error("初始化用户缓存数据失败");
				throw new RuntimeException("初始化用户缓存数据失败");
			}
		}
		refreshTime = new Date();
	}

	public synchronized void destroy() {
		if (null != UserCache.userCache) {
			UserCache.userCache = null;
		}
		if(null != UserCache.userMap){
			UserCache.userMap.clear();
		}
		if(null != UserCache.userMap2){
			UserCache.userMap2.clear();
		}
	}
	/**
	 * 根据用户id获取用户名称
	 * @param userId
	 * @return
	 */
	public String getUserName(String userId){
		User user = UserCache.userMap.get(userId);
		if(null != user){
			return user.getUsername();
		}
		return null;
	}
	public String getNameById(String userIds){
		if(StringUtil.isEmpty(userIds)){
			return null;
		}
		String[] useridArr = userIds.split(",");
		String nameStr = "$";
		for(String userid : useridArr){
			if(!StringUtil.isEmpty(userid)){
				String tmp = UserCache.getInstance().getUserName(userid);
				if(null != tmp){
					nameStr += "," + tmp;
				}
			}
		}
		if("$".equals(nameStr))
			return userIds;
		return nameStr.substring(2);
	}
	public String getUserIdByCode(String code){
		User user = UserCache.userMap2.get(code);
		if(null != user){
			return user.getUserid();
		}
		return null;
	}
	public String getRefreshTime() {
		return DateUtil.formatSdf1(refreshTime);
	}
}
