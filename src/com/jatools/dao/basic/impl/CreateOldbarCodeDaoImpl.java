package com.jatools.dao.basic.impl;

import java.util.HashMap;
import java.util.Map;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.CreateOldbarCodeDao;
import com.jatools.web.util.DateUtil;

public class CreateOldbarCodeDaoImpl extends BaseDao implements CreateOldbarCodeDao {

	public Long getStartIndex(String codeType, String year, String month, String userid){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("codeType", codeType);
		condition.put("year", year);
		condition.put("month", month);
		Long startIndex = (Long) executeQueryForObject("CreateOldbarCode.getStartIndex", condition);
		if(null == startIndex){
			condition.put("userid", userid);
			condition.put("date", DateUtil.getCurrentDate18());
			executeInsert("CreateOldbarCode.insertStrip", condition);
			return 1L;
		}
		return startIndex;
	}
	public void updateStartIndex(String codeType, String year, String month, String userid, Long serial){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("codeType", codeType);
		condition.put("year", year);
		condition.put("month", month);
		condition.put("serial",  ""+serial);
		executeUpdate("CreateOldbarCode.updateStartIndex", condition);
	}
}
