package com.jatools.dao.basic.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.PtChargeDao;
import com.jatools.vo.basic.PtCharge;

public class PtChargeDaoImpl extends BaseDao implements PtChargeDao{

	public Pager getPtChargePageData(Map<String, String> condition){
		Pager pager = executeQueryForPager("PtCharge.getPtChargePageData", "PtCharge.getPtChargeTotalCount", condition);
		return pager;
	}
	
	
	public void savePtCharge(PtCharge charge){
		executeInsert("PtCharge.savePtCharge", charge);
	}
	
	
	public PtCharge getPtCharge(String chargeId){
		PtCharge rule = (PtCharge) executeQueryForObject("PtCharge.getPtCharge", chargeId);
		return rule;
	}
	
	
	public void updatePtCharge(PtCharge charge){
		executeUpdate("PtCharge.updatePtCharge", charge);
	}
	
	
	public void deletePtCharge(String chargeId){
		executeUpdate("PtCharge.deletePtCharge", chargeId);
	}


	@Override
	public int checkWeightStr(String id, String weightStr) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chargeId", id);
		params.put("weightStr", weightStr);
		Integer result = (Integer)executeQueryForObject("PtCharge.checkWeightStr", params);
		return result==null?0:result.intValue();
	}


	@Override
	public int checkWeightEnd(String id, String weightEnd) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chargeId", id);
		params.put("weightEnd", weightEnd);
		Integer result = (Integer)executeQueryForObject("PtCharge.checkWeightEnd", params);
		return result==null?0:result.intValue();
	}
}
