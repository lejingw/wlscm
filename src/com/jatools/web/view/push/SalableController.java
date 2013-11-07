package com.jatools.web.view.push;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.push.SalableManager;
import com.jatools.vo.push.Salable;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.*;
import com.jatools.web.form.BaseForm;
import com.jatools.web.form.push.SalableForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class SalableController extends BaseMultiActionController {
	private SalableManager salableManager;

	public void setSalableManager(SalableManager SalableManager) {
		this.salableManager = SalableManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		SalableForm form = new SalableForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = salableManager.getSalablePageData(condition);
		form.setPager(pager);
		return new ModelAndView("push/salable_list", "form", form);
	}

	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		SalableForm form = new SalableForm();
        String id = CommonUtil.getParameterEmpty(req, "id");
        if(StringUtil.isNotBlank(id)) {
            Salable salable = this.salableManager.getSalableById(id);
            form.setSalable(salable);
        }
        return new ModelAndView("push/salable_edit", "form", form);
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = salableManager.getSalablePageData(condition);
		ExcelData excelData = new ExcelData();
		excelData.setTitle("畅销款基础表");
		excelData.setPager(pager);
		excelData.setColumnTitle(new String[]{"大类", "小类", "分析范围", "款式大类", "款式", "款式比例",  "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "analysisName", "styleItemClassId", "styleName", "styleRate", "createId", "createDate", "updateId", "updateDate", "status"});
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "updateId", "itemClassId", "ornaClassId","styleItemClassId" },
					new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance(), ItemClassCache.getInstance(), OrnaClassCache.getInstance(), StyleItemClassCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}
}
