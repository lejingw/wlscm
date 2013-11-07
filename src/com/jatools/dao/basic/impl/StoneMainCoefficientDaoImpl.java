package com.jatools.dao.basic.impl;

import java.util.HashMap;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.StoneMainCoefficientDao;
import com.jatools.vo.basic.StoneMainCoefficient;

public class StoneMainCoefficientDaoImpl extends BaseDao implements StoneMainCoefficientDao {

	public Pager getStoneMainCoefficientPageData(Map<String, String> condition){
		Pager pager = executeQueryForPager("StoneMainCoefficient.getStoneMainCoefficientPageData", "StoneMainCoefficient.getStoneMainCoefficientTotalCount", condition);
		return pager;
	}
	
	
	public void saveStoneMainCoefficient(StoneMainCoefficient StoneMainCoefficient){
		executeInsert("StoneMainCoefficient.saveStoneMainCoefficient", StoneMainCoefficient);
	}
	
	
	public StoneMainCoefficient getStoneMainCoefficient(String StoneMainCoefficientId){
		StoneMainCoefficient rule = (StoneMainCoefficient) executeQueryForObject("StoneMainCoefficient.getStoneMainCoefficient", StoneMainCoefficientId);
		return rule;
	}
	
	
	public void updateStoneMainCoefficient(StoneMainCoefficient StoneMainCoefficient){
		executeUpdate("StoneMainCoefficient.updateStoneMainCoefficient", StoneMainCoefficient);
	}
	
	
	public void deleteStoneMainCoefficient(String StoneMainCoefficientId){
		executeUpdate("StoneMainCoefficient.deleteStoneMainCoefficient", StoneMainCoefficientId);
	}
	
	@Override
	public int checkWeightStr(StoneMainCoefficient StoneMainCoefficient) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", StoneMainCoefficient.getId());
		params.put("weightStr", StoneMainCoefficient.getWeightStr());
		params.put("itemClassId", StoneMainCoefficient.getItemClassId());
		Integer result = (Integer)executeQueryForObject("StoneMainCoefficient.checkWeightStr", params);
		return result==null?0:result.intValue();
	}


	@Override
	public int checkWeightEnd(StoneMainCoefficient StoneMainCoefficient) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", StoneMainCoefficient.getId());
		params.put("weightStr", StoneMainCoefficient.getWeightStr());
		params.put("itemClassId", StoneMainCoefficient.getItemClassId());
		Integer result = (Integer)executeQueryForObject("StoneMainCoefficient.checkWeightEnd", params);
		return result==null?0:result.intValue();
	}
}
