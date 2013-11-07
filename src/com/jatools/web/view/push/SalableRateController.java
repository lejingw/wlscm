package com.jatools.web.view.push;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.push.SalableRateManager;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.StyleMiddleClassCache;
import com.jatools.web.cache.StyleOrnaClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.BaseForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

public class SalableRateController extends BaseMultiActionController {
	private SalableRateManager salableRateManager;

	public void setSalableRateManager(SalableRateManager salableRateManager) {
		this.salableRateManager = salableRateManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = salableRateManager.getSalableRatePageData(condition);
		form.setPager(pager);
		return new ModelAndView("push/salableRate_list", "form", form);
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = salableRateManager.getSalableRatePageData(condition);
		ExcelData excelData = new ExcelData();
		excelData.setTitle("畅销款比例");
		excelData.setPager(pager);
		excelData.setColumnTitle(new String[]{"区域", "畅销款铺货量比例", "畅销款周转量比例", "非畅销款铺货量比例", "非畅销款周转量比例", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"regionName", "saleDisRate", "saleTurnRate", "unsaleDisRate", "unsaleTurnRate", "createId", "createDate", "updateId", "updateDate", "status"});
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "updateId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}
}
