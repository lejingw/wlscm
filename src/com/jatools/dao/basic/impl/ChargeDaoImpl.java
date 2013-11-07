package com.jatools.dao.basic.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.ChargeDao;
import com.jatools.vo.basic.Charge;
/**
 * @author  ren.ming
 * @Created 2011.11.16
 * <br>
 *  工费核算标准
 */
public class ChargeDaoImpl extends BaseDao implements ChargeDao{
	
	public Pager getChargePageData(Map<String, String> condition){
		Pager pager = executeQueryForPager("Charge.getChargePageData", "Charge.getChargeTotalCount", condition);
		return pager;
	}
	
	
	public void saveCharge(Charge charge){
		executeInsert("Charge.saveCharge", charge);
	}
	
	
	public Charge getCharge(String chargeId){
		Charge rule = (Charge) executeQueryForObject("Charge.getCharge", chargeId);
		return rule;
	}
	
	
	public void updateCharge(Charge charge){
		executeUpdate("Charge.updateCharge", charge);
	}
	
	
	public void deleteCharge(String chargeId){
		executeUpdate("Charge.deleteCharge", chargeId);
	}


	@Override
	public int checkWeightStr(String id, String weightStr) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chargeId", id);
		params.put("weightStr", weightStr);
		Integer result = (Integer)executeQueryForObject("Charge.checkWeightStr", params);
		return result==null?0:result.intValue();
	}


	@Override
	public int checkWeightEnd(String id, String weightEnd) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chargeId", id);
		params.put("weightEnd", weightEnd);
		Integer result = (Integer)executeQueryForObject("Charge.checkWeightEnd", params);
		return result==null?0:result.intValue();
	}
}
