package com.jatools.dao.stock.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.ProcChangeHeadDao;
import com.jatools.vo.stock.ProcChangeHead;
import com.jatools.web.util.DateUtil;

/**
 * 形态转换单头表Dao 实现类
 * @author ren.ming
 * Created 2011-11-29
 */
public class ProcChangeHeadDaoImpl extends BaseDao implements ProcChangeHeadDao, ReviewActionIntf {

	@Override
	public Pager getProcChangeHeadData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("ProcChangeHead.getProcChangeHeadPageData", "ProcChangeHead.getProcChangeHeadTotalCount", condition);
		return pager;
	}

	@Override
	public void saveProcChangeHead(ProcChangeHead procChangeHead) {
		executeInsert("ProcChangeHead.saveProcChangeHead", procChangeHead);

	}

	@Override
	public ProcChangeHead getProcChangeHead(String billid) {
		ProcChangeHead head = (ProcChangeHead)executeQueryForObject("ProcChangeHead.getProcChangeHead", billid);
		return head;
	}

	@Override
	public void updateProcChangeHead(ProcChangeHead procChangeHead) {
		executeUpdate("ProcChangeHead.updateProcChangeHead", procChangeHead);
	}
	@SuppressWarnings("rawtypes")
	public void modifyProcChangeHeadStatus(Map params) {
		executeUpdate("ProcChangeHead.updateProcChangeHeadStatus", params);
	}

	@Override
	public void deleteProcChangeHead(String billid) {
		executeUpdate("ProcChangeHead.deleteProcChangeHead", billid);
	}

	public void modifyProcChangeHeadStatus(String billid, String status, String userId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", status);
		params.put("updateId", userId);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("ProcChangeHead.updateProcChangeHeadStatus", params);
	}
	
	/**
	 * 审批完成
	 * 状态设置为审批完成
	 */
	@Override
	public void reviewSuccess(String billid, String userid) {
		modifyProcChangeHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWED, userid);
	}

	@Override
	public void reviewFail(String billid, String userid) {
		modifyProcChangeHeadStatus(billid, DictConstant.BILL_STATUS_SAVE, userid);
	}

	@Override
	public double getSumMoneyByOrnaCodes(String ornaCodes) {
		Object res = executeQueryForObject("ProcChangeHead.getSumMoney", ornaCodes);
		if(res != null) return (Double)res;
		return 0;
	}

	/**
	 * 装箱单回写快递单号
	 * @param headid 装箱单id
	 * @param expressNo null 表示清除快递单号
	 */
	public void updatePackNoFromMovePack(String headid, String billNo, String expressNo){
		Map<String, String> params = new HashMap<String, String>();
		params.put("headid", headid);
		params.put("billNo", billNo);
		params.put("expressNo", expressNo);
		executeUpdate("ProcChangeHead.updatePackNoFromMovePack", params);
	}
}
