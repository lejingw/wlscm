package com.jatools.manager.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.PtChargeDao;
import com.jatools.manager.basic.PtChargeManager;
import com.jatools.vo.basic.PtCharge;
import com.jatools.web.util.StringUtil;

public class PtChargeManagerImpl implements PtChargeManager{
	private PtChargeDao ptChargeDao;

	public void setPtChargeDao(PtChargeDao ptChargeDao) {
		this.ptChargeDao = ptChargeDao;
	}

	
	public void saveOrUpdatePtCharge(PtCharge charge){
		String chargeId = charge.getChargeId();
		if(StringUtil.isEmpty(chargeId)){
			ptChargeDao.savePtCharge(charge);
		}else{
			ptChargeDao.updatePtCharge(charge);
		}
		
	}
	

	@Override
	public Pager getPtChargePageData(Map<String, String> condition) {
		return ptChargeDao.getPtChargePageData(condition);
	}


	@Override
	public void savePtCharge(PtCharge charge) {
		ptChargeDao.savePtCharge(charge);
	}


	@Override
	public PtCharge getPtCharge(String chargeId) {
		return ptChargeDao.getPtCharge(chargeId);
	}


	@Override
	public void updatePtCharge(PtCharge charge) {
		ptChargeDao.updatePtCharge(charge);
	}


	@Override
	public void deletePtCharge(String chargeIds) {
		if(StringUtil.isNotBlank(chargeIds)){
			String chargeIdArr [] = chargeIds.split(";");
			for(String chargeId : chargeIdArr){
				if(StringUtil.isNotBlank(chargeId)){
					ptChargeDao.deletePtCharge(chargeId);
				}
			}
		}
	}


	@Override
	public int checkWeightStr(String id, String weightStr) {
		return this.ptChargeDao.checkWeightStr(id, weightStr);
	}


	@Override
	public int checkWeightEnd(String id, String weightEnd) {
		return this.ptChargeDao.checkWeightEnd(id, weightEnd);
	}
}
