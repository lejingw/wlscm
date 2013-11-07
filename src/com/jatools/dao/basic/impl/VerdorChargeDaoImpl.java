package com.jatools.dao.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.VerdorChargeDao;
import com.jatools.vo.basic.VerdorCharge;

public class VerdorChargeDaoImpl extends BaseDao implements VerdorChargeDao{

	@Override
	public Pager getVerdorChargePageData(Map<String, String> condition) {
		return executeQueryForPager("VerdorCharge.getVerdorChargeAllPageData", "VerdorCharge.getVerdorChargeToalCount", condition);
	}

	@Override
	public void saveVerdorCharge(VerdorCharge VerdorCharge) {
		executeInsert("VerdorCharge.saveVerdorCharge", VerdorCharge);	
	}

	@Override
	public VerdorCharge getVerdorChargeById(String id) {
		VerdorCharge VerdorCharge = (VerdorCharge)executeQueryForObject("VerdorCharge.getVerdorChargeById", id);
		return VerdorCharge;
	}

	@Override
	public void updateVerdorCharge(VerdorCharge VerdorCharge) {
		executeUpdate("VerdorCharge.updateVerdorCharge", VerdorCharge);
	}

	@Override
	public void deleteVerdorCharge(String id) {
		executeUpdate("VerdorCharge.deleteVerdorCharge", id);
	}

	/**
	 * 获取颜色品质
	 * @param sc
	 * @return
	 */
	public VerdorCharge getVerdorCharge(VerdorCharge a){
		return (VerdorCharge) executeQueryForObject("VerdorCharge.getVerdorCharge", a);
	}
}
