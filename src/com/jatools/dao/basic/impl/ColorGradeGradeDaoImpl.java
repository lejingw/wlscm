package com.jatools.dao.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.basic.ColorGradeGradeDao;
import com.jatools.vo.basic.ColorGradeGrade;

public class ColorGradeGradeDaoImpl extends BaseDao implements ColorGradeGradeDao {

	@Override
	public Pager getColorGradeGradePageData(Map<String, String> condition) {
		return executeQueryForPager("ColorGradeGrade.getColorGradeGradeAllPageData", "ColorGradeGrade.getColorGradeGradeToalCount", condition);
	}

	@Override
	public void saveColorGradeGrade(ColorGradeGrade colorGradeGrade) {
		executeInsert("ColorGradeGrade.saveColorGradeGrade", colorGradeGrade);	
	}

	@Override
	public ColorGradeGrade getColorGradeGradeById(String id) {
		ColorGradeGrade colorGradeGrade = (ColorGradeGrade)executeQueryForObject("ColorGradeGrade.getColorGradeGradeById", id);
		return colorGradeGrade;
	}

	@Override
	public void updateColorGradeGrade(ColorGradeGrade colorGradeGrade) {
		executeUpdate("ColorGradeGrade.updateColorGradeGrade", colorGradeGrade);
	}

	@Override
	public void deleteColorGradeGrade(String id) {
		executeUpdate("ColorGradeGrade.deleteColorGradeGrade", id);
	}

	/**
	 * 获取颜色品质
	 * @param sc
	 * @return
	 */
	public ColorGradeGrade getColorGradeGrade(ColorGradeGrade a){
		return (ColorGradeGrade) executeQueryForObject("ColorGradeGrade.getColorGradeGrade", a);
	}
}
