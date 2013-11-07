package com.jatools.web.dwr.basic;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.PtChargeManager;
import com.jatools.vo.basic.PtCharge;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class PtChargeDwr {
	private static Logger logger = Logger.getLogger(PtChargeDwr.class);
	
	private PtChargeManager ptChargeManger;
	
	/**
	 * 保存或修改POS对接数据
	 * @param orgGroup
	 */
	public String saveOrUpdatePtCharge(PtCharge charge, HttpServletRequest req){
		try {
			if(StringUtil.isEmpty(charge.getChargeId())){
				charge.setCreateDate(DateUtil.getCurrentDate18());
				charge.setCreateId(CommonUtil.getSessionUserId(req));
				charge.setStatus(DictConstant.BILL_STATUS_SAVE);
			}
			charge.setUpdateDate(DateUtil.getCurrentDate18());
			charge.setUpdateId(CommonUtil.getSessionUserId(req));
			ptChargeManger.saveOrUpdatePtCharge(charge);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "保存出错";
		}
	}
	
	public String deletePtCharge(String chargeId, HttpServletRequest req){
		try {
			if(StringUtil.isNotEmpty(chargeId)){
				this.ptChargeManger.deletePtCharge(chargeId);
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除失败";
		}
	}

	public void setPtChargeManager(PtChargeManager ptChargeManger) {
		this.ptChargeManger = ptChargeManger;
	}
	
	
	public boolean checkWeightStr(String id, String weightStr, HttpServletRequest req){
		int count = this.ptChargeManger.checkWeightStr(id, weightStr);
		if(count >0) return true;
		return false;
	}
	
	public boolean checkWeightEnd(String id, String weightEnd, HttpServletRequest req){
		int count = this.ptChargeManger.checkWeightEnd(id, weightEnd);
		if(count >0) return true;
		return false;
	}
}
