package com.jatools.dao.out.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.out.OutVendorDao;
import com.jatools.vo.out.OutOrna;
import com.jatools.vo.out.OutVendorHead;
import com.jatools.vo.out.OutVendorLine;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.MaterNoActive;
import com.jatools.web.util.DateUtil;

@SuppressWarnings("unchecked")
public class OutVendorDaoImpl extends BaseDao implements OutVendorDao,
		ReviewActionIntf {

	public Pager getVendorHeadPage(Map<String, String> condition) {
		if(null==condition.get("showAll")){
			condition.put("showAll","0");
		}
		return executeQueryForPager("OutVendor.getVendorHeadPage",
				"OutVendor.getTotalCount", condition);
	}
	public Pager getReportSaleVendorHeadPage(Map<String, String> condition) {
		return executeQueryForPager("OutVendor.getReportSaleVendorHeadPage","OutVendor.getReportSaleVendorHeadTotalCount", condition);
	}
	public Pager getReportOutVendorHeadPage(Map<String, String> condition) {
		return executeQueryForPager("OutVendor.getReportOutVendorHeadPage","OutVendor.getReportOutVendorHeadTotalCount", condition);
	}
	public Pager getReportSendVendorHeadPage(Map<String, String> condition) {
		return executeQueryForPager("OutVendor.getReportSendVendorHeadPage","OutVendor.getReportSendVendorHeadTotalCount", condition);
	}

	@Override
	public OutOrna getOrna(String code, boolean ornaFlag) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("code", code);
		condition.put("ornaFlag", ornaFlag ? "1" : "0");
		return (OutOrna) executeQueryForObject("OutVendor.getOutOrna", condition);
	}

	@Override
	public void deleteLines(String delIds) {
		// executeUpdate("OutVendor.delLineByIds", delIds);
		try {
			Connection conn = getSqlMapClientTemplate().getDataSource()
					.getConnection();
			Statement sta = conn.createStatement();
			sta.execute("update ic_mater_active a set a.state=900 where exists(\n"
					+ "     select 1 from jat_out_vendor_line l where l.orna_code=a.orna_code and l.lineid in(0,"
					+ delIds + ",0)\n" + ")");
			sta.execute("delete jat_out_vendor_line l where l.lineid in(0,"
					+ delIds + ",0)");
			sta.close();
		} catch (SQLException e) {
			throw new RuntimeException("OutVendorDaoImpl.deleteLines eror:"
					+ e.getMessage());
		}

	}

	@Override
	public void updateHead(OutVendorHead head) {
		executeUpdate("OutVendor.updateHead", head);
	}

	@Override
	public String insertHead(OutVendorHead head) {
		return (String) executeInsert("OutVendor.insertHead", head);
	}

	@Override
	public void updateLine(OutVendorLine line) {
		executeUpdate("OutVendor.updateLine", line);

	}

	@Override
	public void insertLine(OutVendorLine line) {
		executeInsert("OutVendor.insertLine", line);
	}

	@Override
	public List<OutVendorLine> getVendorLines(String headId) {
		return executeQueryForList("OutVendor.getVendorLines", headId);
	}

	@Override
	public OutVendorHead getVendorHead(String headId) {
		return (OutVendorHead) executeQueryForObject("OutVendor.getVendorHead",
				headId);
	}

	@Override
	public void deleteHead(String headId) {
		executeUpdate("OutVendor.deleteHead", headId);
	}

	@Override
	public void deleteLinesByHeadId(String headId) {
		executeUpdate("OutVendor.deleteLinesByHeadId", headId);
	}

	@Override
	public void changeHeadStatus(OutVendorHead head) {
		executeUpdate("OutVendor.changeHeadStatus", head);
	}

	@Override
	public void moveOrna(String code, boolean noActive) {
		if (noActive) {
			executeUpdate("OutVendor.copyOrnaNoToActive", code);
		} else
			executeUpdate("OutVendor.copyOrnaToNo", code);

	}

	@Override
	public void deleteOrna(String code) {
		executeUpdate("OutVendor.deleteOrna", code);
	}

	@Override
	public void deleteOrnaNo(String code) {
		executeUpdate("OutVendor.deleteOrnaNo", code);
	}

	@Override
	public void saveMaterTrans(OutVendorLine line) {
		executeUpdate("OutVendor.saveMaterTrans", line);
	}

	@Override
	public void saveCashMoneyAccount(OutVendorHead head) {
		executeUpdate("OutVendor.saveCashMoneyAccount", head);
	}

	@Override
	public void saveCashProdAccount(OutVendorHead head, OutVendorLine line) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("billType", head.getBillType());
		condition.put("billNo", head.getBillNo());
		condition.put("doDate", head.getDoDate());
		condition.put("orgId", head.getOrgId());
		condition.put("vendorId", head.getVendorId());
		condition.put("materialType", head.getMaterialType());
		condition.put("itemClassId", line.getItemClassId());
		condition.put("sumWeight", line.getAllQty());
		condition.put("updateDate", head.getUpdateDate());
		condition.put("updateId", head.getUpdateId());
		condition.put("createDate", head.getUpdateDate());
		condition.put("createId", head.getUpdateId());
		condition.put("ornaCode", line.getOrnaCode());
		condition.put("cashUnit", line.getCashUnit());
		executeUpdate("OutVendor.saveCashProdAccount", condition);
	}

	@Override
	public void changeMaterStatus(String ornaCode, boolean noActive,
			String billType, String billNo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ornaCode", ornaCode);
		map.put("state", noActive ? "900" : "901");
		map.put("billType", "");
		map.put("billNo", "");
		if (!noActive) {
			map.put("billType", billType);
			map.put("billNo", billNo);
		}
		executeUpdate("OutVendor.changeMaterStatus", map);
	}

	@Override
	public String getFinaceType(String billType) {
		return (String) executeQueryForObject("OutVendor.getFinaceType",
				billType);
	}

	@Override
	public String getItemClassByMaterial(String materialType) {
		return (String) executeQueryForObject(
				"OutVendor.getItemClassByMaterial", materialType);
	}

	@Override
	public void changeProdChecked(String billNo) {
		executeUpdate("OutVendor.changeProdChecked", billNo);
	}

	@Override
	public void reviewSuccess(String billid, String userid) {
		updateBillStatus(billid, userid, DictConstant.BILL_STATUS_REVIEWED, true);
	}

	@Override
	public void reviewFail(String billid, String userid) {
		updateBillStatus(billid, userid, DictConstant.BILL_STATUS_SAVE, true);
	}
	
	/**
	 * 修改单据状态
	 * 
	 * @param billid
	 * @param userid
	 * @param status
	 */
	public void updateBillStatus(String billid, String userid, String status,
			boolean isReview) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", billid);
		condition.put("status", status);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		condition.put("reviewFlag", isReview ? "1" : "0");
		executeUpdate("OutVendor.updateBillHeadStatus", condition);
	}

	@Override
	public String getLossMoney(String venderId, String payMoney) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("voderId", venderId);
		condition.put("payMoney", payMoney);
		return (String) executeQueryForObject("OutVendor.getLossMoney", condition);
	}

	@Override
	public String getUnitFromMaterial(String materialType, String itemClass) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("materialType", materialType);
		condition.put("itemClass", itemClass);
		return (String) executeQueryForObject("OutVendor.getUnitFromMaterial", condition);
	}

	@Override
	public List<PurchaseHead> getPurchaseByVendorId(String vendorId) {
		return executeQueryForList("OutVendor.getPurchaseByVendorId", vendorId);
	}

	@Override
	public List<MaterNoActive> getPrintLineByNoMater(String id) {
		return executeQueryForList("OutVendor.getPrintLineByNoMater", id);
	}

	@Override
	public List<MaterActive> getPrintLineByMater(String id) {
		return executeQueryForList("OutVendor.getPrintLineByMater", id);
	}

	public void cashCharge(OutVendorHead head){
		this.executeUpdate("OutVendor.cashCharge", head);
	}

    @Override
    public OutVendorHead getVendorHeadByBillno(String billno) {
        return (OutVendorHead) this.executeQueryForObject("OutVendor.getVendorHeadByBillno", billno);
    }
}
