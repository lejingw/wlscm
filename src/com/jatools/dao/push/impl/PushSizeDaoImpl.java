package com.jatools.dao.push.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.PushSizeDao;
import com.jatools.vo.push.PushSize;

public class PushSizeDaoImpl extends BaseDao implements PushSizeDao{
	/**
	 * 获取分页数据
	 */
	public Pager getPushSizePageData(Map<String, String> condition){
		return executeQueryForPager("PushSize.getPushSizePageData", "PushSize.getPushSizeTotalCount", condition);
	}
	@Override
	public void savePushSize(PushSize dn) {
		executeInsert("PushSize.savePushSize", dn);
	}

	@Override
	public void updatePushSize(PushSize dn) {
		executeUpdate("PushSize.updatePushSize", dn);
	}
	
	public List<PushSize> checkPushSizeRepeat(PushSize dn) {
		return executeQueryForList("PushSize.checkPushSizeRepeat", dn);
	}

	/**
	 * 删除
	 * @param billId
	 */
	public void deletePushSize(List<String> billIdList){
		executeBatchDelete("PushSize.deletePushSize", billIdList);
	}
}
