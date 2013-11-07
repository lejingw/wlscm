package com.jatools.dao.report;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Analysis;
import com.jatools.vo.bd.Style;
import com.jatools.vo.bd.StyleItemClass;
import com.jatools.vo.bd.StyleMiddleClass;
import com.jatools.vo.bd.StyleOrnaClass;
import com.jatools.vo.util.SelectorOption;

public interface UnsaleReportDao {

	Pager getChargePageData(Map<String, String> condition);
	
	/**
	 * 根据大类、小类获取款式大类
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	List<StyleItemClass> getStyleItemClass(String itemClassId, String ornaClassId);

	/**
	 * 根据大类小类款式大类获取款式中类
	 * @param styleItemClassId
	 * @return
	 */
	List<StyleMiddleClass> getStyleMiddleClass(String itemClassId, String ornaClassId, String styleItemClassId);

	/**
	 * 根据大类小类款式大类款式中类获取款式小类
	 * @param styleMiddleClassId
	 * @return
	 */
	List<StyleOrnaClass> getStyleOrnaClass(String itemClassId, String ornaClassId, String styleItemClassId, String styleMiddleClassId);
	/**
	 * 根据大类小类款式大类、款式中类、款式小类获取款式
	 * @param styleItemClassId
	 * @param styleMiddleClassId
	 * @param styleOrnaClassId
	 * @return
	 */
	List<Style> getStyle(String itemClassId, String ornaClassId, String styleItemClassId, String styleMiddleClassId, String styleOrnaClassId);
	
	/**
	 * 根据大类小类去分析范围
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	List<Analysis> getAnalysis(String itemClassId, String ornaClassId);
	
	List<SelectorOption> getTheme(String styleId);
}
