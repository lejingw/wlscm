package com.jatools.dao.push.impl;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.PushStyleDao;
import com.jatools.vo.push.PushStyle;

import java.util.List;
import java.util.Map;

public class PushStyleDaoImpl extends BaseDao implements PushStyleDao{
	/**
	 * 获取分页数据
	 */
	public Pager getPushStylePageData(Map<String, String> condition){
		return executeQueryForPager("PushStyle.getPushStylePageData", "PushStyle.getPushStyleTotalCount", condition);
	}

	@Override
	public void savePushStyle(PushStyle dn) {
		executeInsert("PushStyle.savePushStyle", dn);
	}

	@Override
	public void updatePushStyle(PushStyle dn) {
		executeUpdate("PushStyle.updatePushStyle", dn);
	}
	
	public boolean checkPushStyleExists(PushStyle dn) {
		Integer count = (Integer)executeQueryForObject("PushStyle.checkPushStyleExists", dn);
		return count >0;
	}

	/**
	 * 删除
	 * @param billIdList
	 */
	public void deletePushStyle(List<String> billIdList){
		executeBatchDelete("PushStyle.deletePushStyle", billIdList);
	}

	public PushStyle getPushStyleById(String id){
		return (PushStyle)executeQueryForObject("PushStyle.getPushStyleById", id);
	}
}
