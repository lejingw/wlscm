package com.jatools.web.view.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.manager.report.UnsaleReportManager;
import com.jatools.vo.bd.Analysis;
import com.jatools.vo.bd.StyleItemClass;
import com.jatools.vo.bd.StyleMiddleClass;
import com.jatools.vo.bd.StyleOrnaClass;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleClassCache;
import com.jatools.web.form.common.SelectorForm;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class SelectStyleClassController extends BaseMultiActionController {
	
	private BdCommonManager bdCommonManager;
	private UnsaleReportManager unsaleReportManager;
	
	public void setBdCommonManager(BdCommonManager bdCommonManager) {
		this.bdCommonManager = bdCommonManager;
	}

	public void setUnsaleReportManager(UnsaleReportManager unsaleReportManager) {
		this.unsaleReportManager = unsaleReportManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}

	/**
	 * 获取所有款式大类
	 */
	public ModelAndView getAllStyleItemClass(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		Map<String, String> dataMap = StyleClassCache.getInstance().getAllStyleItemClass();
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectStyleClass", "form", form);
	}
	/**
	 * 根据大类、小类获取款式大类
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView getStyleItemClassByItemClassOrnaClass(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		String itemClassId = CommonUtil.getParameterEmpty(req, "itemClassId");
		String ornaClassId = CommonUtil.getParameterEmpty(req, "ornaClassId");
		List<StyleItemClass> styleItemList = bdCommonManager.getStyleItemClass(itemClassId, ornaClassId);
		Map<String, String> dataMap = new HashMap<String, String>();
		for(StyleItemClass styleItem : styleItemList){
			dataMap.put(styleItem.getItemClassId(), styleItem.getItemClassDesc());
		}
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectStyleClass", "form", form);
	}
	
	// -----------------------------------------------------------------------------------------------------
	/**
	 * 取所有大类
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView getAllItemClass(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		Map<String, String> dataMap = ItemClassCache.getInstance().getAllItemClass2();
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectStyleClass", "form", form);
	}
	
	/**
	 * 取所有小类
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView getAllOrnaClass(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		Map<String, String> dataMap = OrnaClassCache.getInstance().getAllOrnaClass();
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectStyleClass", "form", form);
	}
	
	/**
	 * 获取所有款式大类
	 */
	public ModelAndView getAllStyleItemClass2(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		String itemClassId = CommonUtil.getParameterEmpty(req, "itemClassId");
		String ornaClassId = CommonUtil.getParameterEmpty(req, "ornaClassId");
		List<StyleItemClass> styleItemList = this.unsaleReportManager.getStyleItemClass(itemClassId, ornaClassId);
		Map<String, String> dataMap = new HashMap<String, String>();
		for(StyleItemClass styleItem : styleItemList){
			dataMap.put(styleItem.getItemClassId(), styleItem.getItemClassDesc());
		}
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectStyleClass", "form", form);
	}
	
	/**
	 * 获取所有款式中类
	 */
	public ModelAndView getAllStyleMiddleClass(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		String itemClassId = CommonUtil.getParameterEmpty(req, "itemClassId");
		String ornaClassId = CommonUtil.getParameterEmpty(req, "ornaClassId");
		String styleItemClassId = CommonUtil.getParameterEmpty(req, "styleItemClassId");
		List<StyleMiddleClass> styleMiddleList = this.unsaleReportManager.getStyleMiddleClass(itemClassId, ornaClassId, styleItemClassId);
		Map<String, String> dataMap = new HashMap<String, String>();
		for(StyleMiddleClass styleMiddle : styleMiddleList){
			dataMap.put(styleMiddle.getMiddleClassId(), styleMiddle.getMiddleClassDesc());
		}
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectStyleClass", "form", form);
	}
	
	/**
	 * 获取所有款式小类
	 */
	public ModelAndView getAllStyleOrnaClass(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		String itemClassId = CommonUtil.getParameterEmpty(req, "itemClassId");
		String ornaClassId = CommonUtil.getParameterEmpty(req, "ornaClassId");
		String styleItemClassId = CommonUtil.getParameterEmpty(req, "styleItemClassId");
		String styleMiddleClassId = CommonUtil.getParameterEmpty(req, "styleMiddleClassId");
		List<StyleOrnaClass> styleOrnaList = this.unsaleReportManager.getStyleOrnaClass(itemClassId, ornaClassId, styleItemClassId, styleMiddleClassId);
		Map<String, String> dataMap = new HashMap<String, String>();
		for(StyleOrnaClass styleOrna : styleOrnaList){
			dataMap.put(styleOrna.getOrnaClassId(), styleOrna.getOrnaClassDesc());
		}
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectStyleClass", "form", form);
	}
	
	/**
	 * 获取所有分析范围
	 */
	public ModelAndView getAllAnalysis(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		String itemClassId = CommonUtil.getParameterEmpty(req, "itemClassId");
		String ornaClassId = CommonUtil.getParameterEmpty(req, "ornaClassId");
		Map<String, String> dataMap = new HashMap<String, String>();
		if(StringUtil.isNotBlank(itemClassId) && StringUtil.isNotBlank(ornaClassId)){
			List<Analysis> analysisList = this.unsaleReportManager.getAnalysis(itemClassId, ornaClassId);
			for(Analysis analysis : analysisList){
				dataMap.put(analysis.getAnalysisId(), analysis.getAnalysisDesc());
			}
		}
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectStyleClass", "form", form);
	}
	
	/**
	 * 获取所有主题名称
	 */
	public ModelAndView getAllTheme(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		String styleId = CommonUtil.getParameterEmpty(req, "styleId");
		Map<String, String> dataMap = new HashMap<String, String>();
		List<SelectorOption> themeList = this.unsaleReportManager.getTheme(styleId);
		for(SelectorOption theme : themeList){
			dataMap.put(theme.getValue(), theme.getText());
		}
		SelectorForm form = new SelectorForm("true".equals(multiFlag), selectedValues);
		form.setDataMap(dataMap);
		return new ModelAndView("common/SelectStyleClass", "form", form);
	}
}
