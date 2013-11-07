package com.jatools.dao.calc.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.calc.MaterAttrUpdateDao;
import com.jatools.vo.calc.MaterAttrUpdate;
import com.jatools.vo.stock.MaterActive;
import com.jatools.web.util.DateUtil;

public class MaterAttrUpdateDaoImpl extends BaseDao implements MaterAttrUpdateDao,ReviewActionIntf {

	@Override
	public Pager getMaterAttrUpdatePageData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("MaterAttrUpdate.getMaterAttrUpdateAllPageData", "MaterAttrUpdate.getMaterAttrUpdateToalCount", condition);
		return pager;
	}

	@Override
	public void saveMaterAttrUpdate(MaterAttrUpdate MaterAttrUpdate) {
		executeInsert("MaterAttrUpdate.saveMaterAttrUpdate", MaterAttrUpdate);
	}

	@Override
	public MaterAttrUpdate getMaterAttrUpdateById(String id) {
		return (MaterAttrUpdate)executeQueryForObject("MaterAttrUpdate.getMaterAttrUpdateById", id);
	}

	@Override
	public void updateMaterAttrUpdate(MaterAttrUpdate MaterAttrUpdate) {
		executeUpdate("MaterAttrUpdate.updateMaterAttrUpdate", MaterAttrUpdate);
	}

	@Override
	public void deleteMaterAttrUpdate(String id) {
		executeUpdate("MaterAttrUpdate.deleteMaterAttrUpdate", id);
	}

	@Override
	public MaterActive getMaterByCode(Map<String,String> conditionMap) {
		return (MaterActive) executeQueryForObject("MaterAttrUpdate.getMaterByCode", conditionMap);
	}
	/**
	 * 审批完成
	 * 状态设置为审批完成
	 */
	@Override
	public void reviewSuccess(String billid, String userid) {
		modifyMaterStatus(billid, DictConstant.BILL_STATUS_REVIEWED, userid);
	}

	@Override
	public void reviewFail(String billid, String userid) {
		modifyMaterStatus(billid, DictConstant.BILL_STATUS_SAVE, userid);
	}
	
	public void modifyMaterStatus(String billid, String status, String userid) {
		Map<String , String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", status);
		params.put("updateid", userid);
		params.put("updatedate", DateUtil.getCurrentDate18());
		executeUpdate("MaterAttrUpdate.updateMaterStatus", params);
	}

	@Override
	public void updateMaterActionByMB(MaterAttrUpdate MaterAttrUpdate) {
		executeUpdate("MaterAttrUpdate.updateMaterActionByMB", MaterAttrUpdate);
	}

	@Override
	public void updateMaterInivByMB(MaterAttrUpdate MaterAttrUpdate) {
		executeUpdate("MaterAttrUpdate.updateMaterInivByMB", MaterAttrUpdate);
	}

	@Override
	public void updatePriceByMB(MaterAttrUpdate MaterAttrUpdate) {
		executeUpdate("MaterAttrUpdate.updatePriceByMB", MaterAttrUpdate);
	}

}
