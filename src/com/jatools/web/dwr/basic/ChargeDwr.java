package com.jatools.web.dwr.basic;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.ChargeManager;
import com.jatools.vo.basic.Charge;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class ChargeDwr {
	private static Logger logger = Logger.getLogger(ChargeDwr.class);
	
	private ChargeManager chargeManager;
	
	/**
	 * 保存或修改POS对接数据
	 * @param orgGroup
	 */
	public String saveOrUpdateCharge(Charge charge, HttpServletRequest req){
		try {
			if(StringUtil.isEmpty(charge.getChargeId())){
				charge.setCreateDate(DateUtil.getCurrentDate18());
				charge.setCreateId(CommonUtil.getSessionUserId(req));
				charge.setStatus(DictConstant.BILL_STATUS_SAVE);
			}
			charge.setUpdateDate(DateUtil.getCurrentDate18());
			charge.setUpdateId(CommonUtil.getSessionUserId(req));
			chargeManager.saveOrUpdateCharge(charge);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "保存出错";
		}
	}
	
	public String deleteCharge(String chargeId, HttpServletRequest req){
		try {
			if(StringUtil.isNotEmpty(chargeId)){
				this.chargeManager.deleteCharge(chargeId);
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除失败";
		}
	}

	public void setChargeManager(ChargeManager chargeManager) {
		this.chargeManager = chargeManager;
	}
	
	
	public boolean checkWeightStr(String id, String weightStr, HttpServletRequest req){
		int count = this.chargeManager.checkWeightStr(id, weightStr);
		if(count >0) return true;
		return false;
	}
	
	public boolean checkWeightEnd(String id, String weightEnd, HttpServletRequest req){
		int count = this.chargeManager.checkWeightEnd(id, weightEnd);
		if(count >0) return true;
		return false;
	}
}
