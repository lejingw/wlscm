package com.jatools.manager.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.CleanGradeDao;
import com.jatools.manager.basic.CleanGradeManager;
import com.jatools.vo.basic.CleanGrade;

public class CleanGradeManagerImpl implements CleanGradeManager{

	private CleanGradeDao CleanGradeDao;
	
	
	public CleanGradeDao getCleanGradeDao() {
		return CleanGradeDao;
	}

	public void setCleanGradeDao(CleanGradeDao CleanGradeDao) {
		this.CleanGradeDao = CleanGradeDao;
	}

	@Override
	public Pager getCleanGradePageData(Map<String, String> condition) {
		return CleanGradeDao.getCleanGradePageData(condition);
	}

	@Override
	public void saveCleanGrade(CleanGrade CleanGrade) {
		CleanGradeDao.saveCleanGrade(CleanGrade);
	}

	@Override
	public CleanGrade getCleanGradeById(String id) {
		return CleanGradeDao.getCleanGradeById(id);
	}

	@Override
	public void updateCleanGrade(CleanGrade CleanGrade) {
		CleanGradeDao.updateCleanGrade(CleanGrade);
	}

	@Override
	public void deleteCleanGrade(String id) {
		CleanGradeDao.deleteCleanGrade(id);
	}

	@Override
	public CleanGrade getCleanGrade(CleanGrade a) {
		return CleanGradeDao.getCleanGrade(a);
	}

}
