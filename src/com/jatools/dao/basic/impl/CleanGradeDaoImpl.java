package com.jatools.dao.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.CleanGradeDao;
import com.jatools.vo.basic.CleanGrade;

public class CleanGradeDaoImpl extends BaseDao implements CleanGradeDao {

	@Override
	public Pager getCleanGradePageData(Map<String, String> condition) {
		return executeQueryForPager("CleanGrade.getCleanGradeAllPageData", "CleanGrade.getCleanGradeToalCount", condition);
	}

	@Override
	public void saveCleanGrade(CleanGrade cleanGrade) {
		executeInsert("CleanGrade.saveCleanGrade", cleanGrade);	
	}

	@Override
	public CleanGrade getCleanGradeById(String id) {
		CleanGrade cleanGrade = (CleanGrade)executeQueryForObject("CleanGrade.getCleanGradeById", id);
		return cleanGrade;
	}

	@Override
	public void updateCleanGrade(CleanGrade cleanGrade) {
		executeUpdate("CleanGrade.updateCleanGrade", cleanGrade);
	}

	@Override
	public void deleteCleanGrade(String id) {
		executeUpdate("CleanGrade.deleteCleanGrade", id);
	}

	/**
	 * 获取颜色品质
	 * @param sc
	 * @return
	 */
	public CleanGrade getCleanGrade(CleanGrade a){
		return (CleanGrade) executeQueryForObject("CleanGrade.getCleanGrade", a);
	}
}
