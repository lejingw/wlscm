package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.MaterInivDao;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.web.util.DateUtil;

public class MaterInivDaoImpl extends BaseDao implements MaterInivDao {

	public int getSumMaters(String srcBillId, String srcBillCode){
		Map<String, String> params = new HashMap<String, String>();
		params.put("srcBillId", srcBillId);
		params.put("srcBillCode", srcBillCode);
		Object res= executeQueryForObject("MaterIniv.getSumMaters", params);
		if(null != res) {
			return (Integer)res;
		} 
		return 0;
	}

	@Override
	public void updateBillStatusBySourceId(String fromStatus, String toStatus, String userId, String srcBillId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("fromStatus", fromStatus);
		params.put("toStatus", toStatus);
		params.put("updateId", userId);
		params.put("srcBillId", srcBillId);
		params.put("srcBillCode", GlobalConstant.BILL_CODE_JIAOJIEDAN);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("MaterIniv.updateBillStatusBySourceId", params);
	}

	@Override
	public Pager getMaterInivPageData(Map<String, String> condition) {
		//if("on".equals(condition.get("stateId")))
		//	condition.put("stateId", "1");
//		System.out.println("on".equals(condition.get("stateId"))+"_____________________123____");
		return executeQueryForPager("MaterIniv.getPageData", "MaterIniv.getTotalCount", condition);
	}

	@Override
	public String saveMaterIniv(MaterIniv materIniv) {
		return (String) executeInsert("MaterIniv.saveMaterIniv", materIniv);
	}

	@Override
	public MaterIniv getMaterInivById(String id) {
		return (MaterIniv) executeQueryForObject("MaterIniv.getMaterInivById", id);
	}

	@Override
	public void updateMaterIniv(MaterIniv materIniv) {
		executeUpdate("MaterIniv.updateMaterIniv", materIniv);
	}

	@Override
	public void deleteMaterIniv(String id) {
		executeUpdate("MaterIniv.deleteMaterIniv", id);
	}

	@Override
	public MaterIniv getMaterIniv(MaterIniv a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHangPriceByIniv(String handoverBillId, String itemClassId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("handoverBillId", handoverBillId);
		params.put("itemClassId", itemClassId);
		return (String)executeQueryForObject("MaterIniv.getHangPriceByIniv", params);
	}

	@Override
	public void updatePriceStateByIniv(String state,String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("state", state);
		params.put("id", id);
		executeUpdate("MaterIniv.updatePriceStateByIniv", params);
	}

    @Override
    public Double getFashionGoldPrice(String itemClassId) {
        return (Double)executeQueryForObject("MaterIniv.getFashionGoldPrice", itemClassId);
    }
}
