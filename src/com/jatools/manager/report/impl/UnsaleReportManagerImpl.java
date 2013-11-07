package com.jatools.manager.report.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.report.UnsaleReportDao;
import com.jatools.manager.report.UnsaleReportManager;
import com.jatools.vo.bd.Analysis;
import com.jatools.vo.bd.Style;
import com.jatools.vo.bd.StyleItemClass;
import com.jatools.vo.bd.StyleMiddleClass;
import com.jatools.vo.bd.StyleOrnaClass;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;

public class UnsaleReportManagerImpl implements UnsaleReportManager {

	@Override
	public Pager getChargePageData(Map<String, String> condition) {
		condition.put("curDate", DateUtil.getCurrentDate10());
		return this.unsaleReportDao.getChargePageData(condition);
	}

	private UnsaleReportDao unsaleReportDao;

	public void setUnsaleReportDao(UnsaleReportDao unsaleReportDao) {
		this.unsaleReportDao = unsaleReportDao;
	}

	@Override
	public List<StyleItemClass> getStyleItemClass(String itemClassId,
			String ornaClassId) {
		return this.unsaleReportDao.getStyleItemClass(itemClassId, ornaClassId);
	}

	@Override
	public List<StyleMiddleClass> getStyleMiddleClass(String itemClassId,
			String ornaClassId, String styleItemClassId) {
		return this.unsaleReportDao.getStyleMiddleClass(itemClassId, ornaClassId, styleItemClassId);
	}

	@Override
	public List<StyleOrnaClass> getStyleOrnaClass(String itemClassId,
			String ornaClassId, String styleItemClassId,
			String styleMiddleClassId) {
		return this.unsaleReportDao.getStyleOrnaClass(itemClassId, ornaClassId, styleItemClassId, styleMiddleClassId);
	}

	@Override
	public List<Style> getStyle(String itemClassId, String ornaClassId,
			String styleItemClassId, String styleMiddleClassId,
			String styleOrnaClassId) {
		return this.unsaleReportDao.getStyle(itemClassId, ornaClassId, styleItemClassId, styleMiddleClassId, styleOrnaClassId);
	}
	
	public List<Analysis> getAnalysis(String itemClassId, String ornaClassId){
		return this.unsaleReportDao.getAnalysis(itemClassId, ornaClassId);
	}
	
	public List<SelectorOption> getTheme(String styleId){
		return this.unsaleReportDao.getTheme(styleId);
	}
}
