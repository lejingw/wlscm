package com.jatools.dao.out.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.out.OutBackDao;
import com.jatools.vo.out.OutBackHead;
import com.jatools.vo.out.OutBackLine;
import com.jatools.vo.out.OutOrna;
import com.jatools.web.util.DateUtil;

public class OutBackDaoImpl extends BaseDao implements OutBackDao ,ReviewActionIntf{

	public Pager getBackHeadPage(Map<String, String> condition) {
		return executeQueryForPager("OutBack.getBackHeadPage","OutBack.getTotalCount", condition);
	}

	@Override
	public OutOrna getOrna(String code, boolean ornaFlag) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("code", code);
		condition.put("ornaFlag", ornaFlag?"1":"0");
		return (OutOrna) executeQueryForObject("OutBack.getOutOrna", condition);
	}

	@Override
	public void deleteLines(String delIds) {
		try {
			Connection conn=getSqlMapClientTemplate().getDataSource().getConnection();
			Statement sta=conn.createStatement();
			sta.execute("update ic_mater_active a set a.state=900 where exists(\n" +
				"     select 1 from jat_out_Back_line l where l.orna_code=a.orna_code and l.lineid in(0,"+delIds+",0)\n" + 
				")");
			sta.execute("delete jat_out_Back_line l where l.lineid in(0,"+delIds+",0)");
			sta.close();
		} catch (SQLException e) {
			throw new RuntimeException("OutBackDaoImpl.deleteLines eror:"+e.getMessage());
		}
		
	}

	@Override
	public void updateHead(OutBackHead head) {
		executeUpdate("OutBack.updateHead", head);
	}

	@Override
	public String insertHead(OutBackHead head){ 
		return (String) executeInsert("OutBack.insertHead", head);
	}

	@Override
	public void updateLine(OutBackLine line) {
		executeUpdate("OutBack.updateLine", line);
		
	}

	@Override
	public void insertLine(OutBackLine line) {
		executeInsert("OutBack.insertLine", line);
	}

	@Override
	public List<OutBackLine> getBackLines(String headId) {
		return executeQueryForList("OutBack.getBackLines", headId);
	}

	@Override
	public OutBackHead getBackHead(String headId) {
		return (OutBackHead) executeQueryForObject("OutBack.getBackHead", headId);
	}

	@Override
	public void deleteHead(String headId) {
		executeUpdate("OutBack.deleteHead", headId);
	}

	@Override
	public void deleteLinesByHeadId(String headId) {
		executeUpdate("OutBack.deleteLinesByHeadId", headId);
	}

	@Override
	public void changeHeadStatus(OutBackHead head) {
		executeUpdate("OutBack.changeHeadStatus", head);
	}

	@Override
	public void saveMaterTrans(OutBackLine line) {
		executeUpdate("OutBack.saveMaterTrans", line);
	}

	@Override
	public OutBackLine getBackLine(String headId, String ornaCode,
			boolean ornaFlag) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("code", ornaCode);
		condition.put("headId", headId);
		condition.put("ornaFlag", ornaFlag?"1":"0");
		return (OutBackLine) executeQueryForObject("OutBack.getBackLine", condition);
	}

	@Override
	public void backLines(String ids, String backDate, String backBody) {
		try {
			Connection conn=getSqlMapClientTemplate().getDataSource().getConnection();
			Statement sta=conn.createStatement();
			sta.execute("update jat_out_Back_line a set a.is_back=1 , a.BACK_DATE='"+backDate+"' , a.BACK_BODY='"+backBody+"' where a.lineid in (0,"+ids+",0)");
			sta.close();
		} catch (SQLException e) {
			throw new RuntimeException("OutBackDaoImpl.backLines eror:"+e.getMessage());
		}
		
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
		executeUpdate("OutBack.updateBillHeadStatus", condition);
	}
	
}
