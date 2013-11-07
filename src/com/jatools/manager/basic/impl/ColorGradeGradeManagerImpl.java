package com.jatools.manager.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.ColorGradeGradeDao;
import com.jatools.manager.basic.ColorGradeGradeManager;
import com.jatools.vo.basic.ColorGradeGrade;

public class ColorGradeGradeManagerImpl implements ColorGradeGradeManager{

	private ColorGradeGradeDao colorGradeGradeDao;
	
	
	public ColorGradeGradeDao getColorGradeGradeDao() {
		return colorGradeGradeDao;
	}

	public void setColorGradeGradeDao(ColorGradeGradeDao colorGradeGradeDao) {
		this.colorGradeGradeDao = colorGradeGradeDao;
	}

	@Override
	public Pager getColorGradeGradePageData(Map<String, String> condition) {
		return colorGradeGradeDao.getColorGradeGradePageData(condition);
	}

	@Override
	public void saveColorGradeGrade(ColorGradeGrade colorGradeGrade) {
		colorGradeGradeDao.saveColorGradeGrade(colorGradeGrade);
	}

	@Override
	public ColorGradeGrade getColorGradeGradeById(String id) {
		return colorGradeGradeDao.getColorGradeGradeById(id);
	}

	@Override
	public void updateColorGradeGrade(ColorGradeGrade colorGradeGrade) {
		colorGradeGradeDao.updateColorGradeGrade(colorGradeGrade);
	}

	@Override
	public void deleteColorGradeGrade(String id) {
		colorGradeGradeDao.deleteColorGradeGrade(id);
	}

	@Override
	public ColorGradeGrade getColorGradeGrade(ColorGradeGrade a) {
		return colorGradeGradeDao.getColorGradeGrade(a);
	}

}
