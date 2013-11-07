package com.jatools.dao.out.impl;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.out.OutStockDao;
import com.jatools.vo.out.OutOrna;
import com.jatools.vo.out.OutStockHead;
import com.jatools.vo.out.OutStockLine;
import com.jatools.web.util.DateUtil;
import com.jatools.dao.BaseDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutStockDaoImpl extends BaseDao implements OutStockDao , ReviewActionIntf{
	public Pager getStockHeadPage(Map<String, String> condition) {
		return executeQueryForPager("OutStock.getStockHeadPage","OutStock.getTotalCount", condition);
	}

	@Override
	public OutOrna getOrna(String code, boolean ornaFlag) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("code", code);
		condition.put("ornaFlag", ornaFlag ? "1" : "0");
		return (OutOrna) executeQueryForObject("OutStock.getOutOrna", condition);
	}

	@Override
	public void deleteLines(String delIds) {
		// executeUpdate("OutStock.delLineByIds", delIds);
		try {
			Connection conn = getSqlMapClientTemplate().getDataSource()
					.getConnection();
			Statement sta = conn.createStatement();
			sta.execute("delete jat_out_line l where l.lineid in(0,"
					+ delIds + ",0)");
			sta.close();
		} catch (SQLException e) {
			throw new RuntimeException("OutStockDaoImpl.deleteLines eror:"
					+ e.getMessage());
		}

	}

	@Override
	public void updateHead(OutStockHead head) {
		executeUpdate("OutStock.updateHead", head);
	}

	@Override
	public String insertHead(OutStockHead head) {
		return (String) executeInsert("OutStock.insertHead", head);
	}

	@Override
	public void updateLine(OutStockLine line) {
		executeUpdate("OutStock.updateLine", line);

	}

	@Override
	public void insertLine(OutStockLine line) {
		executeInsert("OutStock.insertLine", line);
	}

	@Override
	public List<OutStockLine> getStockLines(String headId) {
		return executeQueryForList("OutStock.getStockLines", headId);
	}

	@Override
	public OutStockHead getStockHead(String headId) {
		return (OutStockHead) executeQueryForObject("OutStock.getStockHead",
				headId);
	}

	@Override
	public void deleteHead(String billid) {
		executeUpdate("OutStock.deleteHead",billid);
	}

	@Override
	public void deleteLinesByHeadId(String billid) {
		executeUpdate("OutStock.deleteLinesByBillId",billid);
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
	 * @param billid
	 * @param userid
	 * @param status
	 */
	public void updateBillStatus(String billid, String userid, String status, boolean isReview) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("headid", billid);
		condition.put("status", status);
		condition.put("userid", userid);
		condition.put("date", DateUtil.getCurrentDate18());
		condition.put("reviewFlag", isReview?"1":"0");
		executeUpdate("OutStock.updateBillHeadStatus", condition);
	}
}