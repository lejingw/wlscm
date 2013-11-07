package com.jatools.dao.report.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.report.UnsaleReportDao;
import com.jatools.vo.bd.Analysis;
import com.jatools.vo.bd.Style;
import com.jatools.vo.bd.StyleItemClass;
import com.jatools.vo.bd.StyleMiddleClass;
import com.jatools.vo.bd.StyleOrnaClass;
import com.jatools.vo.util.SelectorOption;
@SuppressWarnings("unchecked")
public class UnsaleReportDaoImpl extends BaseDao implements UnsaleReportDao {

	@Override
	public Pager getChargePageData(Map<String, String> condition) {
		return executeQueryForPager("UnsaleReport.getUnsaleReportPageData", "UnsaleReport.getUnsaleReportTotalCount", condition);
	}

	
	@Override
	public List<StyleItemClass> getStyleItemClass(String itemClassId, String ornaClassId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("itemClassId", itemClassId);
		params.put("ornaClassId", ornaClassId);
		return (List<StyleItemClass>)executeQueryForList("UnsaleReport.getStyleItemClass", params);
	}

	@Override
	public List<StyleMiddleClass> getStyleMiddleClass(String itemClassId, String ornaClassId, String styleItemClassId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("itemClassId", itemClassId);
		params.put("ornaClassId", ornaClassId);
		params.put("styleItemClassId", styleItemClassId);
		return (List<StyleMiddleClass>)executeQueryForList("UnsaleReport.getStyleMiddleClass", params);
	}

	@Override
	public List<StyleOrnaClass> getStyleOrnaClass(String itemClassId, String ornaClassId, String styleItemClassId, 	String styleMiddleClassId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("itemClassId", itemClassId);
		params.put("ornaClassId", ornaClassId);
		params.put("styleItemClassId", styleItemClassId);
		params.put("styleMiddleClassId", styleMiddleClassId);
		return (List<StyleOrnaClass>)executeQueryForList("UnsaleReport.getStyleOrnaClass", params);
	}

	@Override
	public List<Style> getStyle(String itemClassId, String ornaClassId, String styleItemClassId, String styleMiddleClassId,
			String styleOrnaClassId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("itemClassId", itemClassId);
		params.put("ornaClassId", ornaClassId);
		params.put("styleItemClassId", styleItemClassId);
		params.put("styleMiddleClassId", styleMiddleClassId);
		params.put("styleOrnaClassId", styleOrnaClassId);
		return (List<Style>)executeQueryForList("UnsaleReport.getStyle", params);
	}

	public List<Analysis> getAnalysis(String itemClassId, String ornaClassId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("itemClassId", itemClassId);
		params.put("ornaClassId", ornaClassId);
		return (List<Analysis>)executeQueryForList("UnsaleReport.getAnalysis", params);
	}
	
	public List<SelectorOption> getTheme(String styleId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("styleId", styleId);
		return (List<SelectorOption>)executeQueryForList("UnsaleReport.getTheme", params);
	}
}
