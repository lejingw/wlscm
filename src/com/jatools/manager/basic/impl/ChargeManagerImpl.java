package com.jatools.manager.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.ChargeDao;
import com.jatools.manager.basic.ChargeManager;
import com.jatools.vo.basic.Charge;
import com.jatools.web.util.StringUtil;

/**
 * @author  ren.ming
 * @Created 2011.11.16
 * <br>
 *  与pos接口数据manager实现类
 */
public class ChargeManagerImpl implements ChargeManager {
	private ChargeDao chargeDao;

	public void setChargeDao(ChargeDao chargeDao) {
		this.chargeDao = chargeDao;
	}

	
	public void saveOrUpdateCharge(Charge charge){
		String chargeId = charge.getChargeId();
		if(StringUtil.isEmpty(chargeId)){
			chargeDao.saveCharge(charge);
		}else{
			chargeDao.updateCharge(charge);
		}
		
	}
	

	@Override
	public Pager getChargePageData(Map<String, String> condition) {
		return chargeDao.getChargePageData(condition);
	}


	@Override
	public void saveCharge(Charge charge) {
		chargeDao.saveCharge(charge);
	}


	@Override
	public Charge getCharge(String chargeId) {
		return chargeDao.getCharge(chargeId);
	}


	@Override
	public void updateCharge(Charge charge) {
		chargeDao.updateCharge(charge);
	}


	@Override
	public void deleteCharge(String chargeIds) {
		if(StringUtil.isNotBlank(chargeIds)){
			String chargeIdArr [] = chargeIds.split(";");
			for(String chargeId : chargeIdArr){
				if(StringUtil.isNotBlank(chargeId)){
					chargeDao.deleteCharge(chargeId);
				}
			}
		}
	}


	@Override
	public int checkWeightStr(String id, String weightStr) {
		return this.chargeDao.checkWeightStr(id, weightStr);
	}


	@Override
	public int checkWeightEnd(String id, String weightEnd) {
		return this.chargeDao.checkWeightEnd(id, weightEnd);
	}
}
