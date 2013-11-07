package com.jatools.web.dwr.calc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.SpecialChargeManager;
import com.jatools.vo.calc.SpecialCharge;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class SpecialChargeDwr {
	private static Logger logger = Logger.getLogger(SpecialChargeDwr.class);
	private SpecialChargeManager sbraCoefficientManager;
	public static void setLogger(Logger logger) {
		SpecialChargeDwr.logger = logger;
	}
	public void setSpecialChargeManager(
			SpecialChargeManager sbraCoefficientManager) {
		this.sbraCoefficientManager = sbraCoefficientManager;
	}
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateSpecialCharge(SpecialCharge head ,HttpServletRequest req){
		try {
			String str = head.getWeightStr();
			String sb1 = sbraCoefficientManager.getSpecialCharge(head);
			head.setWeightStr(head.getWeightEnd());
			String sb2 = sbraCoefficientManager.getSpecialCharge(head);
			head.setWeightStr(str);
			
			if(!"0".equals(sb1)||!"0".equals(sb2)){
				return "2_保存出错,不能交叉、重复维护。";
			}
			if(StringUtil.isEmpty(head.getChargeId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateId(CommonUtil.getSessionUserId(req));
				sbraCoefficientManager.saveSpecialCharge(head);
			}else{
				SpecialCharge old = sbraCoefficientManager.getSpecialChargeById(head.getChargeId());
				if(old!=null){
					head.setCreateDate(old.getCreateDate());
					head.setCreateId(old.getCreateId());
					head.setUpdateDate(DateUtil.getCurrentDate18());
					head.setUpdateId(CommonUtil.getSessionUserId(req));
					sbraCoefficientManager.updateSpecialCharge(head);
				}
			}
			return "1_保存成功";
		} catch (Exception e) {
			logger.error(e);
			return "2_保存出错";
		}
	}
	
	public String deleteSpecialCharge(String smcId, HttpServletRequest req){
		try {
			if(StringUtil.isNotBlank(smcId)){
				String[] ids = smcId.split(",");
				for (int i = 0; i < ids.length; i++) {
					this.sbraCoefficientManager.deleteSpecialCharge(ids[i]);
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
}
