package com.jatools.dao.push.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.WearSizeDao;
import com.jatools.vo.push.WearSize;

public class WearSizeDaoImpl extends BaseDao implements WearSizeDao{
	/**
	 * 获取分页数据
	 */
	public Pager getWearSizePageData(Map<String, String> condition){
		return executeQueryForPager("WearSize.getWearSizePageData", "WearSize.getWearSizeTotalCount", condition);
	}
	@Override
	public void saveWearSize(WearSize dn) {
		executeInsert("WearSize.saveWearSize", dn);
	}

	@Override
	public void updateWearSize(WearSize dn) {
		executeUpdate("WearSize.updateWearSize", dn);
	}
	
	public List<WearSize> checkWearSizeRepeat(WearSize dn) {
		return executeQueryForList("WearSize.checkWearSizeRepeat", dn);
	}

	/**
	 * 删除
	 * @param billId
	 */
	public void deleteWearSize(List<String> billIdList){
		executeBatchDelete("WearSize.deleteWearSize", billIdList);
	}
}
