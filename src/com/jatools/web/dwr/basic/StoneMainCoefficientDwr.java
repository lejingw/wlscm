package com.jatools.web.dwr.basic;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.StoneMainCoefficientManager;
import com.jatools.vo.basic.StoneMainCoefficient;
import com.jatools.web.util.StringUtil;

public class StoneMainCoefficientDwr {
	private static Logger logger = Logger.getLogger(StoneMainCoefficientDwr.class);
	
	private StoneMainCoefficientManager stoneMainCoefficientManager;
	
	public String saveOrUpdate(StoneMainCoefficient stoneMainCoefficient, HttpServletRequest req){
		try {
			stoneMainCoefficientManager.saveOrUpdateStoneMainCoefficient(stoneMainCoefficient, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	
	public String deleteStoneMainCoefficient(String smcId, HttpServletRequest req){
		try {
			if(StringUtil.isNotBlank(smcId)){
				String[] ids = smcId.split(",");
				for (int i = 0; i < ids.length; i++) {
					this.stoneMainCoefficientManager.deleteStoneMainCoefficient(ids[i]);
				}
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "删除失败";
		}
	}

	public void setStoneMainCoefficientManager(
			StoneMainCoefficientManager stoneMainCoefficientManager) {
		this.stoneMainCoefficientManager = stoneMainCoefficientManager;
	}

	
}
