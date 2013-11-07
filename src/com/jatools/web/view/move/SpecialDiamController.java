package com.jatools.web.view.move;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jatools.common.excel.*;
import org.apache.log4j.Logger;
import org.springframework.util.NumberUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.ExcelUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.move.SpecialDiamManager;
import com.jatools.manager.util.ExcelUtilManager;
import com.jatools.vo.util.ExcelData;
import com.jatools.vo.util.ExcelRowData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.BaseForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class SpecialDiamController extends BaseMultiActionController {
	
	private SpecialDiamManager specialDiamManager;
	private ExcelUtilManager excelUtilManager;
	private String sessionKey;
	
	public String getSessionKey(){
		return this.getClass().getName()+sessionKey;
	}

	public void setExcelUtilManager(ExcelUtilManager excelUtilManager) {
		this.excelUtilManager = excelUtilManager;
	}
	

	public void setSpecialDiamManager(SpecialDiamManager specialDiamManager) {
		this.specialDiamManager = specialDiamManager;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		sessionKey = "1";
		BaseForm form = new BaseForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = specialDiamManager.getSpecialDiamPageData(condition);
		form.setPager(pager);
		return new ModelAndView("move/specialDiam_list", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		sessionKey = "1";
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = specialDiamManager.getSpecialDiamPageData(condition);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("特价钻石价格");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"大类", "小类", "分析范围", "基础价起始", "基础价截止", "商场店价格", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "analysisName","startBasicPrice", "endBasicPrice", "storePrice", "createId", "createDate", "updateId", "updateDate", "status"});
//		excelData.addColumnWidth(new String[]{"startDate", "endDate", "orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "turnoverNum", "createId", "createDate", "updateId", "updateDate", "status"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"itemClassId", "ornaClassId", "createId", "updateId"},
					new CacheSingletonIntf[]{ItemClassCache.getInstance(), OrnaClassCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}

	public ModelAndView importExcel(HttpServletRequest req, HttpServletResponse res) {
		String fieldName = "excel_file";
		ExcelUtil excelUtil = new ExcelUtil(req, res, excelUtilManager);
		excelUtil.saveExcelData(fieldName);
		boolean successFlag = excelUtil.checkExcelData(new ExcelCheckMode[] {
				new ExcelCheckMode(0, 3, false, ExcelColumnEnum.NUMBER_COLUMN),
				new ExcelCheckMode(0, 4, false, ExcelColumnEnum.NUMBER_COLUMN),
				new ExcelCheckMode(0, 5, false, ExcelColumnEnum.NUMBER_COLUMN),
				new ExcelCheckMode(0, 0, new ExcelSelfDefinedCheck(){
					@Override
					public boolean check(int rowIndex, String value,
							ExcelRowData rowData, List<ExcelRowData> allData) {
						String itemClass = getNullString(rowData.getCol0(), "");
						String ornaClass = getNullString(rowData.getCol1(), "");
						String analysis = getNullString(rowData.getCol2(), "");
						
						for (ExcelRowData data : allData){
							if(rowIndex != data.getRowIndex()){
								if(itemClass.equals(data.getCol0()) && ornaClass.equals(data.getCol1()) && analysis.equals(data.getCol2())){
									return false;
								}
							}
						}
						return true;
					}

					@Override
					public String getErrorMsg(int rowIndex, String value,
							ExcelRowData rowData, List<ExcelRowData> allData) {
						String itemClass = getNullString(rowData.getCol0(), "");
						String ornaClass = getNullString(rowData.getCol1(), "");
						String analysis = getNullString(rowData.getCol2(), "");
						
						for (ExcelRowData data : allData){
							if(rowIndex != data.getRowIndex()){
								// 大类
								if(itemClass.equals(data.getCol0()) && ornaClass.equals(data.getCol1()) && analysis.equals(data.getCol2())){
									return "第" + (rowIndex+1) + "行和第" + (data.getRowIndex()+1) + "行 数据重复";
								}
							}
						}
						return null;
					}
					
					private String getNullString(String str, String defaultStr){
						if(StringUtil.isNotBlank(str)) return str;
						return defaultStr;
					}}),
					new ExcelCheckMode(0, 3, new ExcelSelfDefinedCheck(){
						public boolean check(int rowIndex, String value, ExcelRowData rowData, List<ExcelRowData> allData) {
							try {
								if(Double.parseDouble(rowData.getCol3())>Double.parseDouble(rowData.getCol4())){
									return false;
								}
							} catch (Exception e) {
								return false;
							}
							return true;
						}

						@Override
						public String getErrorMsg(int rowIndex, String value, ExcelRowData rowData, List<ExcelRowData> allData) {
							try{
								if(Double.parseDouble(rowData.getCol3())>Double.parseDouble(rowData.getCol4())){
								return "础价起始 不能大于 基础价截止,且必须为数据类型";
								}
							} catch (Exception e) {
								return "础价起始 不能大于 基础价截止,且必须为数据类型";
							}
							return null;
						}}),
				new ExcelCheckMode(0, 0, false, new ExcelDbrefrenceCheck("jas_bd_item_class", "item_class_id", "item_class_dsc"), true),
				new ExcelCheckMode(0, 1, false, new ExcelDbrefrenceCheck("jas_bd_orna_class", "orna_class_id", "orna_class_dsc"), true),
				new ExcelCheckMode(0, 2, false, new ExcelDbrefrenceCheck2("jas_bd_analysis_arange", "analysis_arange_id", "summarydescription",
                                " exists (select 1 from jas_bd_item_class ic where ic.ITEM_CLASS_ID=b.ITEM_CLASS_ID and ic.ITEM_CLASS_DSC =a.col0 )" +
                                " and exists (select 1 from jas_bd_orna_class oc where oc.ORNA_CLASS_ID=b.ORNA_CLASS_ID and oc.ORNA_CLASS_DSC =a.col1 )" +
                                " and b.archiveflag = 0",
                                " b.item_class_id = a.col0" +
                                " and b.orna_class_id = a.col1" +
                                " and b.archiveflag = 0"), true),
			});
		if(successFlag){
			excelUtil.convertDbrefrenceToId();
			String userId = CommonUtil.getSessionUserId(req);
			specialDiamManager.saveSpecialDiamFromExcel(excelUtil.getSeqId(), userId);
			write(res, "{'isSuccess':'true'}");
		}
		return null;
	}
	
	public ModelAndView orgTypeList(HttpServletRequest req, HttpServletResponse res) {
		sessionKey = "2";
		BaseForm form = new BaseForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = specialDiamManager.getSpecialDiamOrgPageData(condition);
		form.setPager(pager);
		return new ModelAndView("move/specialDiamOrg_list", "form", form);
	}

	public ModelAndView exportExcelOrg(HttpServletRequest req, HttpServletResponse res) {
		sessionKey = "2";
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = specialDiamManager.getSpecialDiamOrgPageData(condition);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("专柜专卖店");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"组织", "类型", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"orgName", "orgType", "createId", "createDate", "updateId", "updateDate", "status"});
//		excelData.addColumnWidth(new String[]{"startDate", "endDate", "orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "turnoverNum", "createId", "createDate", "updateId", "updateDate", "status"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "updateId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "orgType"}, new String[]{DictConstant.BILL_STATUS, DictConstant.SPECIAL_DIAM_ORG_TYPE});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}

}
