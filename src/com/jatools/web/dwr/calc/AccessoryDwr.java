package com.jatools.web.dwr.calc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.AccessoryManager;
import com.jatools.vo.calc.Accessory;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class AccessoryDwr  {
	private static Logger logger = Logger.getLogger(MoveSalecoefDwr.class);
	private AccessoryManager accessoryManager;
	public static void setLogger(Logger logger) {
		AccessoryDwr.logger = logger;
	}
	public void setAccessoryManager(AccessoryManager accessoryManager) {
		this.accessoryManager = accessoryManager;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateAccessory(Accessory head ,HttpServletRequest req){
		try {
			Accessory cka = accessoryManager.getAccessory(head);
			if(cka!=null)
				return "2_该辅料有维护系数";
			if(StringUtil.isEmpty(head.getId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateUserId(CommonUtil.getSessionUserId(req));
				accessoryManager.saveAccessory(head);
			}else{
				Accessory old = accessoryManager.getAccessoryById(head.getId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateUserId(old.getCreateUserId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateUserId(CommonUtil.getSessionUserId(req));
					accessoryManager.updateAccessory(head);
				}
			}
			return "1_保存成功";
		} catch (Exception e) {
			logger.error(e);
			return "2_保存出错";
		}
	}
	
	/**
	 * 
	 * @param sc
	 * @return
	 */
	public Accessory getAccessory(Accessory a){
		if(a==null)
			return null;
		else{
			return accessoryManager.getAccessory(a);
		}
	}
}
