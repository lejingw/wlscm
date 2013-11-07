package com.jatools.dao.stock.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.BaseDao;
import com.jatools.dao.stock.ProcPackageHeadDao;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.stock.ProcPackageHead;
import com.jatools.web.util.DateUtil;

/**
 * 拆包混包单头表Dao 实现类
 * @author ren.ming
 * Created 2011-11-20
 */
public class ProcPackageHeadDaoImpl extends BaseDao implements ProcPackageHeadDao, ReviewActionIntf {

	@Override
	public Pager getProcPackageHeadData(Map<String, String> condition) {
		Pager pager = executeQueryForPager("ProcPackageHead.getProcPackageHeadPageData", "ProcPackageHead.getProcPackageHeadTotalCount", condition);
		return pager;
	}

	@Override
	public void saveProcPackageHead(ProcPackageHead procPackageHead) {
		executeInsert("ProcPackageHead.saveProcPackageHead", procPackageHead);

	}

	@Override
	public ProcPackageHead getProcPackageHead(String billid) {
		ProcPackageHead head = (ProcPackageHead)executeQueryForObject("ProcPackageHead.getProcPackageHead", billid);
		return head;
	}

	@Override
	public void updateProcPackageHead(ProcPackageHead procPackageHead) {
		executeUpdate("ProcPackageHead.updateProcPackageHead", procPackageHead);
	}
	
	public void modifyProcPackageHeadStatus(String billid, String status, String userid) {
		Map<String , String> params = new HashMap<String, String>();
		params.put("billid", billid);
		params.put("status", status);
		params.put("updateId", userid);
		params.put("updateDate", DateUtil.getCurrentDate18());
		executeUpdate("ProcPackageHead.updateProcPackageHeadStatus", params);
	}

	@Override
	public void deleteProcPackageHead(String billid) {
		executeUpdate("ProcPackageHead.deleteProcPackageHead", billid);
	}

	/**
	 * 审批完成
	 * 状态设置为审批完成
	 */
	@Override
	public void reviewSuccess(String billid, String userid) {
		modifyProcPackageHeadStatus(billid, DictConstant.BILL_STATUS_REVIEWED, userid);
	}

	@Override
	public void reviewFail(String billid, String userid) {
		modifyProcPackageHeadStatus(billid, DictConstant.BILL_STATUS_SAVE, userid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> getPrintData(String billid) {
		List<Tag> result = new ArrayList<Tag>();
		List<Tag> mater = (List<Tag>)executeQueryForList("ProcPackageHead.getPrintDataByMater", billid);
		if(CollectionUtils.isNotEmpty(mater)){
			result.addAll(mater);
		}
		List<Tag> nomater = (List<Tag>)executeQueryForList("ProcPackageHead.getPrintDataByNoMater", billid);
		if(CollectionUtils.isNotEmpty(nomater)){
			result.addAll(nomater);
		}
		return result; 
	}

	public int selectAnalysCount(String oldAnalysId, String newAnalysId) {
		Map<String , String> params = new HashMap<String, String>();
		params.put("oldAnalysId", oldAnalysId);
		params.put("newAnalysId", newAnalysId);
		Integer res = (Integer)executeQueryForObject("ProcPackageHead.selectAnalysCount", params);
		if(res != null){
			return res.intValue();
		}
		return 0;
	}
}
