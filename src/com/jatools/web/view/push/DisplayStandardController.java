package com.jatools.web.view.push;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.push.DisplayStandardManager;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.StyleMiddleClassCache;
import com.jatools.web.cache.StyleOrnaClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.BaseForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

public class DisplayStandardController extends BaseMultiActionController {
	private DisplayStandardManager displayStandardManager;
	
	public void setDisplayStandardManager(
			DisplayStandardManager displayStandardManager) {
		this.displayStandardManager = displayStandardManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = displayStandardManager.getDisplayOrgTypePageData(condition);
		form.setPager(pager);
		return new ModelAndView("push/displayStandard_list", "form", form);
	}
	
	public ModelAndView list2(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = displayStandardManager.getDisplayOrgTypePageData(condition);
		form.setPager(pager);
		return new ModelAndView("push/displayStandard_list2", "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		List<Map> dataList = displayStandardManager.getAllDisplayStandard();
		ExcelData excelData = new ExcelData();
		excelData.setTitle("畅销款陈列标准");
		excelData.setDataList(dataList);
		excelData.setColumnTitle(new String[]{"组织类别", "大类", "小类", "分析范围", "款式大类", "款式中类", "款式小类", "畅销款", "最小量", "最大量", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"orgtypeName", "itemClassId", "ornaClassId", "analysisName", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "minCount", "maxCount", "createId", "createDate", "updateId", "updateDate", "status"});
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"itemClassId", "ornaClassId", "createId", "updateId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId"},
					new CacheSingletonIntf[]{ItemClassCache.getInstance(), OrnaClassCache.getInstance(), UserCache.getInstance(), UserCache.getInstance(), StyleItemClassCache.getInstance(), StyleMiddleClassCache.getInstance(), StyleOrnaClassCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}
	
	public ModelAndView exportExcel2(HttpServletRequest req, HttpServletResponse res) {
		List<Map> dataList = displayStandardManager.getAllDisplayOrgtype();
		ExcelData excelData = new ExcelData();
		excelData.setTitle("畅销款组织类别");
		excelData.setDataList(dataList);
		excelData.setColumnTitle(new String[]{"组织类别", "组织", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"orgtypeName", "orgId", "createId", "createDate", "updateId", "updateDate", "status"});
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "updateId", "orgId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance(), OrgCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}
	
	public ModelAndView orgtype(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = displayStandardManager.getDisplayOrgTypePageData(condition);
		form.setPager(pager);
		return new ModelAndView("push/displayOrgType_list", "form", form);
	}
	
	public ModelAndView region(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		Pager pager = new Pager();
		List<Map<String,String>> list = displayStandardManager.getRegionOrgs();
		pager.setPageData(list);
		form.setPager(pager);
		return new ModelAndView("push/regionOrg_list", "form", form);
	}
}
