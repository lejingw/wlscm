package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.PlSaleDao;
import com.jatools.vo.stock.PlSaleHead;
import com.jatools.vo.stock.PlSaleLine;
import com.jatools.web.util.DateUtil;

public class PlSaleDaoImpl extends BaseDao implements PlSaleDao, ReviewActionIntf {

	@SuppressWarnings("rawtypes")
	@Override
	public Pager getPlSaleData(Map<String, String> condition) {
		return executeQueryForPager("PlSale.getPlSalePageData", "PlSale.getPlSaleTotalCount", condition);
	}

	@Override
	public void savePlSaleHead(PlSaleHead PlSaleHead) {
		this.executeInsert("PlSale.savePlSale", PlSaleHead);
	}

	@Override
	public PlSaleHead getPlSaleHead(String billid) {
		return (PlSaleHead)executeQueryForObject("PlSale.getPlSale", billid);
	}

	@Override
	public void updatePlSaleHead(PlSaleHead PlSaleHead) {
		this.executeUpdate("PlSale.updatePlSale", PlSaleHead);
	}

	@Override
	public void modifyPlSaleHeadStatus(String billid, String status,
			String userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("PlSale.updatePlSaleStatus", params);
	}

	@Override
	public void deletePlSaleHead(String billid) {
		executeUpdate("PlSale.deletePlSale", billid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlSaleLine> getLines(String billid) {
		return executeQueryForList("PlSale.getLines", billid);
	}

	@Override
	public void insertLine(PlSaleLine line) {
		this.executeInsert("PlSale.insertLine", line);
	}

	@Override
	public void updateLine(PlSaleLine line) {
		this.executeUpdate("PlSale.updateLine", line);
	}

	@Override
	public void deleteLineById(String lineid) {
		executeUpdate("PlSale.deleteLineById", lineid);
	}

	@Override
	public void deleteLineByBillid(String billid) {
		executeUpdate("PlSale.deleteLineByBillid", billid);
	}

	@Override
	public void reviewSuccess(String billid, String userid) {
		this.modifyPlSaleHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWED, userid);
	}

	@Override
	public void reviewFail(String billid, String userid) {
		this.modifyPlSaleHeadStatus(billid, DictConstant.BILL_STATUS_SAVE, userid);
	}


    public boolean isExistsOrnaCode(String billid, String ornaCode) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("billid", billid);
        params.put("ornaCode", ornaCode);
        Integer res = (Integer)executeQueryForObject("PlSale.isExistsOrnaCode", params);
        if(res != null && res.intValue() > 0) {
            return true;
        }
        return false;
    }
    public boolean isExistsInOtherBill(String billid, String ornaCode) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("billid", billid);
        params.put("ornaCode", ornaCode);
        Integer res = (Integer)executeQueryForObject("PlSale.isExistsInOtherBill", params);
        if(res != null && res.intValue() > 0) {
            return true;
        }
        return false;
    }
}
