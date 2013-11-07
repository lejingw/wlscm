package com.jatools.web.view.basic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.SafeStockManager;
import com.jatools.vo.basic.SafeStock;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.StyleMiddleClassCache;
import com.jatools.web.cache.StyleOrnaClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.basic.SafeStockForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class SafeStockController extends BaseMultiActionController {
	private SafeStockManager safeStockManager;

	public void setSafeStockManager(SafeStockManager safeStockManager) {
		this.safeStockManager = safeStockManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = safeStockManager.getSafeStockPageData(condition);
		SafeStockForm form = new SafeStockForm();
		form.setPager(pager);
		return new ModelAndView("basic/safeStock_list", "form", form);
	}

	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String safeId = CommonUtil.getParameterEmpty(req, "safeId");
		SafeStockForm form = new SafeStockForm();
		if(StringUtil.isNotEmpty(safeId)){
			SafeStock stock = safeStockManager.getSafeStock(safeId);
			form.setSafeStock(stock);
		}
		return new ModelAndView("basic/safeStock_edit", "form", form);
	}
	
	public ModelAndView batchCreate(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "itemClassId", "ornaClassId", "analysisId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleId", "startOrderNum1", "startOrderNum2", "safeNum");
		List<SafeStock> list = null;
		if(StringUtils.isNotEmpty(condition.get("itemClassId")) && StringUtils.isNotEmpty(condition.get("ornaClassId"))){
			list = safeStockManager.queryBatchCreateSafeStock(condition);
		}
		SafeStockForm form = new SafeStockForm();
		form.setList(list);
		form.setCondition(condition);
		return new ModelAndView("basic/safeStock_batchCreate", "form", form);
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = safeStockManager.getSafeStockPageData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("总部安全库存");
//		excelData.setDataList(dataList);//手工重新查找数据
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate"}, new Integer[]{4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"大类", "小类", "分析范围", "款式大类", "款式中类", "款式小类", "款式名称", "库存标准", "创建人", "创建时间", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "analysisName", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "safeNum", "createId", "createDate", "status"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"itemClassId", "ornaClassId", "createId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId"},
					new CacheSingletonIntf[]{ItemClassCache.getInstance(), OrnaClassCache.getInstance(), UserCache.getInstance(), StyleItemClassCache.getInstance(), StyleMiddleClassCache.getInstance(), StyleOrnaClassCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
}
