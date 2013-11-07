package com.jatools.web.view.push;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.push.WearSizeManager;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.BaseForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

public class WearSizeController  extends BaseMultiActionController {
	private WearSizeManager wearSizeManager;

	public void setWearSizeManager(WearSizeManager wearSizeManager) {
		this.wearSizeManager = wearSizeManager;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = wearSizeManager.getWearSizePageData(condition);
		form.setPager(pager);
		return new ModelAndView("push/wearSize_list", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = wearSizeManager.getWearSizePageData(condition);
		ExcelData excelData = new ExcelData();
		excelData.setTitle("佩戴对象尺寸比例");
		excelData.setPager(pager);
		excelData.setColumnTitle(new String[]{"大类", "小类", "款式大类", "佩戴对象", "尺寸起", "尺寸止", "尺寸名称", "比例", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "wearName","startSizeName", "endSizeName", "sizeName", "rate", "createId", "createDate", "updateId", "updateDate", "status"});
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "createId", "updateId"},
					new CacheSingletonIntf[]{ItemClassCache.getInstance(), OrnaClassCache.getInstance(), StyleItemClassCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}
}
