package com.jatools.web.dwr.basic;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.MaterialItemManager;
import com.jatools.vo.basic.MaterialItem;
import com.jatools.web.util.StringUtil;

public class MaterialItemDwr {
	private static Logger logger = Logger.getLogger(MaterialItemDwr.class);
	
	private MaterialItemManager materialItemManager;
	
	public String saveOrUpdateMItem(MaterialItem materialItem, HttpServletRequest req){
		try {
			materialItemManager.saveOrUpdateMaterialItem(materialItem, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}

	public String deleteMItem(String materialItemId, HttpServletRequest req){
		try {
			if(StringUtil.isNotBlank(materialItemId)){
				this.materialItemManager.deleteMaterialItem(materialItemId);
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "保存出错";
		}
	}
	
	public void setMaterialItemManager(MaterialItemManager materialItemManager) {
		this.materialItemManager = materialItemManager;
	}
	
	
}
