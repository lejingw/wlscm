package com.jatools.dao.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.UnsaleOrnaDao;
import com.jatools.vo.basic.UnsaleOrna;

public class UnsaleOrnaDaoImpl extends BaseDao implements UnsaleOrnaDao {

	@Override
	public Pager getUnsaleOrnaPageData(Map<String, String> condition) {
		return executeQueryForPager("UnsaleOrna.getUnsaleOrnaPageData", "UnsaleOrna.getUnsaleOrnaTotalCount", condition);
	}

	@Override
	public void saveUnsaleOrna(UnsaleOrna unsaleOrna) {
		executeInsert("UnsaleOrna.saveUnsaleOrna", unsaleOrna);
	}

	@Override
	public UnsaleOrna getUnsaleOrna(String unsaleId) {
		return (UnsaleOrna)executeQueryForObject("UnsaleOrna.getUnsaleOrna", unsaleId);
	}

	@Override
	public void updateUnsaleOrna(UnsaleOrna unsaleOrna) {
		executeUpdate("UnsaleOrna.updateUnsaleOrna", unsaleOrna);
	}

	@Override
	public void deleteUnsaleOrna(String unsaleId) {
		executeUpdate("UnsaleOrna.deleteUnsaleOrna", unsaleId);
	}

	@Override
	public int isExists(UnsaleOrna unsaleOrna) {
		Integer res = (Integer)executeQueryForObject("UnsaleOrna.isExistsUnsaleOrna", unsaleOrna);
		if(res != null){
			return res.intValue();
		}
		return 0;
	}

}
