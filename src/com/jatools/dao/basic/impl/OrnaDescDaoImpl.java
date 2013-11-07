package com.jatools.dao.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.OrnaDescDao;
import com.jatools.vo.basic.OrnaDesc;

public class OrnaDescDaoImpl extends BaseDao implements OrnaDescDao {

	@Override
	public Pager getOrnaDescPageData(Map<String, String> condition) {
		return executeQueryForPager("OrnaDesc.getOrnaDescPageData", "OrnaDesc.getOrnaDescTotalCount", condition);
	}

	@Override
	public void saveOrnaDesc(OrnaDesc ornaDesc) {
		executeInsert("OrnaDesc.saveOrnaDesc", ornaDesc);
	}

	@Override
	public OrnaDesc getOrnaDesc(String ornadscId) {
		return (OrnaDesc)executeQueryForObject("OrnaDesc.getOrnaDesc", ornadscId);
	}

	@Override
	public void updateOrnaDesc(OrnaDesc ornaDesc) {
		executeUpdate("OrnaDesc.updateOrnaDesc", ornaDesc);
	}

	@Override
	public void deleteOrnaDesc(String ornadscId) {
		executeUpdate("OrnaDesc.deleteOrnaDesc", ornadscId);
	}

	@Override
	public int selectOrnaDescCount(OrnaDesc ornaDesc) {
		Integer res = (Integer)executeQueryForObject("OrnaDesc.selectOrnaDescCount", ornaDesc);
		if(null != res){
			return res.intValue();
		}
		return 0;
	}

}
