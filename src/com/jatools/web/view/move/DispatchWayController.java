package com.jatools.web.view.move;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jatools.common.excel.*;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.ExcelUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.move.DispatchWayManager;
import com.jatools.manager.util.ExcelUtilManager;
import com.jatools.vo.util.ExcelData;
import com.jatools.vo.util.ExcelRowData;
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
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class DispatchWayController extends BaseMultiActionController {

	private DispatchWayManager dispatchWayManager;
	private ExcelUtilManager excelUtilManager;

	private String sessionKey;
	
	public String getSessionKey(){
		return this.getClass().getName()+sessionKey;
	}
	public void setExcelUtilManager(ExcelUtilManager excelUtilManager) {
		this.excelUtilManager = excelUtilManager;
	}

	public void setDispatchWayManager(DispatchWayManager dispatchWayManager) {
		this.dispatchWayManager = dispatchWayManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}
	public ModelAndView styleList(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		sessionKey = "1";
		Map<String, String> condition = CommonUtil.getConditionForPageSession( this, req);
		Pager pager = dispatchWayManager.getDispatchStylePageData(condition);
		form.setPager(pager);
		return new ModelAndView("move/dispatchWay_styleList", "form", form);
	}
	public ModelAndView priceList(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		sessionKey = "2";
		Map<String, String> condition = CommonUtil.getConditionForPageSession( this, req);
		Pager pager = dispatchWayManager.getDispatchPricePageData(condition);
		form.setPager(pager);
		return new ModelAndView("move/dispatchWay_priceList", "form", form);
	}

	public ModelAndView exportExcelStyle(HttpServletRequest req, HttpServletResponse res) {
		sessionKey = "1";
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = dispatchWayManager.getDispatchStylePageData(condition);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("款式配货基础数据");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"组织", "大类", "小类", "分析范围", "款式大类", "款式中类", "款式小类", "款式", "铺货量最小值", "铺货量最大值", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "dispMin", "dispMax", "createId", "createDate", "updateId", "updateDate", "status"});
//		excelData.addColumnWidth(new String[]{"startDate", "endDate", "orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "turnoverNum", "createId", "createDate", "updateId", "updateDate", "status"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(
				new String[] { "orgId", "itemClassId", "ornaClassId",
						"styleItemClassId", "styleMiddleClassId",
						"styleOrnaClassId", "createId", "updateId" },
				new CacheSingletonIntf[] { OrgCache.getInstance(),
						ItemClassCache.getInstance(),
						OrnaClassCache.getInstance(),
						StyleItemClassCache.getInstance(),
						StyleMiddleClassCache.getInstance(),
						StyleOrnaClassCache.getInstance(),
						UserCache.getInstance(), UserCache.getInstance() });
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}

	public ModelAndView importExcelStyle(HttpServletRequest req, HttpServletResponse res) {
		String fieldName = "excel_file";
		ExcelUtil excelUtil = new ExcelUtil(req, res, excelUtilManager);
		excelUtil.saveExcelData(fieldName);
		boolean successFlag = excelUtil.checkExcelData(new ExcelCheckMode[] {
				new ExcelCheckMode(0, 0, new ExcelSelfDefinedCheck(){
					@Override
					public boolean check(int rowIndex, String value,
							ExcelRowData rowData, List<ExcelRowData> allData) {
						String orgId = getNullString(rowData.getCol0(), "");
						String analysisId = getNullString(rowData.getCol3(), "");
						String styleId = getNullString(rowData.getCol7(), "");
						
						for (ExcelRowData data : allData){
							if(rowIndex != data.getRowIndex()){
								if(orgId.equals(data.getCol0()) && analysisId.equals(data.getCol3()) && styleId.equals(data.getCol7())){
									return false;
								}
							}
						}
						return true;
					}

					@Override
					public String getErrorMsg(int rowIndex, String value,
							ExcelRowData rowData, List<ExcelRowData> allData) {
						String orgId = getNullString(rowData.getCol0(), "");
						String analysisId = getNullString(rowData.getCol3(), "");
						String styleId = getNullString(rowData.getCol7(), "");
						
						for (ExcelRowData data : allData){
							if(rowIndex != data.getRowIndex()){
								if(orgId.equals(data.getCol0()) && analysisId.equals(data.getCol3()) && styleId.equals(data.getCol7())){
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
					new ExcelCheckMode(0, 8, new ExcelSelfDefinedCheck(){
						public boolean check(int rowIndex, String value, ExcelRowData rowData, List<ExcelRowData> allData) {
							try {
								if(Double.parseDouble(rowData.getCol8())>Double.parseDouble(rowData.getCol9())){
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
								if(Double.parseDouble(rowData.getCol8())>Double.parseDouble(rowData.getCol9())){
								return "铺货量最小值 不能大于 铺货量最大值,且必须为数据类型";
								}
							} catch (Exception e) {
								return "铺货量最小值 不能大于 铺货量最大值,且必须为数据类型";
							}
							return null;
						}}),
				new ExcelCheckMode(0, 0, false, new ExcelDbrefrenceCheck("jas_sm_org", "org_id", "org_name", "org_type = 1"), true),
				new ExcelCheckMode(0, 1, false, new ExcelDbrefrenceCheck("jas_bd_item_class", "item_class_id", "item_class_dsc"), true),
				new ExcelCheckMode(0, 2, false, new ExcelDbrefrenceCheck("jas_bd_orna_class", "orna_class_id", "orna_class_dsc"), true),
				new ExcelCheckMode(0, 3, false, new ExcelDbrefrenceCheck2("jas_bd_analysis_arange", "analysis_arange_id", "summarydescription",
                                " exists (select 1 from jas_bd_item_class ic where ic.ITEM_CLASS_ID=b.ITEM_CLASS_ID and ic.ITEM_CLASS_DSC =a.col1 )" +
                                " and exists (select 1 from jas_bd_orna_class oc where oc.ORNA_CLASS_ID=b.ORNA_CLASS_ID and oc.ORNA_CLASS_DSC =a.col2 )" +
                                " and b.archiveflag = 0",
                                " b.item_class_id = a.col1" +
                                " and b.orna_class_id = a.col2" +
                                " and b.archiveflag = 0"), true),
				new ExcelCheckMode(0, 4, false, new ExcelDbrefrenceCheck2("jas_bd_style_item_class", "item_class_id", "item_class_name",
                                " exists (select 1 from jas_bd_styleitemclass_itemorna d, jas_bd_itemclass_ornaclass c where d.archiveflag = 0 " +
                                " and c.archiveflag = 0 and c.id = d.itemornaid and b.item_class_id = d.styleitemclassid " +
                                " and exists (select 1 from jas_bd_item_class ic where ic.ITEM_CLASS_ID=c.ITEM_CLASS_ID and ic.ITEM_CLASS_DSC =a.col1 )" +
                                " and exists (select 1 from jas_bd_orna_class oc where oc.ORNA_CLASS_ID=c.ORNA_CLASS_ID and oc.ORNA_CLASS_DSC =a.col2 ))" +
                                " and b.archiveflag = 0",
                                " exists (select 1 from jas_bd_styleitemclass_itemorna d, jas_bd_itemclass_ornaclass c where d.archiveflag = 0 " +
                                " and c.archiveflag = 0 and c.id = d.itemornaid and b.item_class_id = d.styleitemclassid " +
                                " and c.item_class_id = a.col1" +
                                " and c.orna_class_id = a.col2)" +
                                " and b.archiveflag = 0"), true),
				new ExcelCheckMode(0, 5, false, new ExcelDbrefrenceCheck2("jas_bd_style_middle_class", "middle_class_id", "middle_class_name",
                                " exists (select 1 from jas_bd_itemclass_middleclass t where b.middle_class_id = t.middle_class_id and t.archiveflag = '0' " +
                                " and exists (select 1 from jas_bd_item_class ic where ic.ITEM_CLASS_ID=t.ITEM_CLASS_ID and ic.ITEM_CLASS_DSC =a.col1 )" +
                                " and exists (select 1 from jas_bd_orna_class oc where oc.ORNA_CLASS_ID=t.ORNA_CLASS_ID and oc.ORNA_CLASS_DSC =a.col2 )" +
                                " and exists (select 1 from jas_bd_style_item_class sic where sic.ITEM_CLASS_ID=t.STYLE_ITEM_CLASS_ID and sic.ITEM_CLASS_NAME =a.col4 ))" +
                                " and b.archiveflag = '0'",
                                " exists (select 1 from jas_bd_itemclass_middleclass t where b.middle_class_id = t.middle_class_id and t.archiveflag = '0' " +
                                " and t.item_class_id = a.col1" +
                                " and t.orna_class_id = a.col2" +
                                " and t.style_item_class_id = a.col4)" +
                                " and b.archiveflag = '0'"), true),
				new ExcelCheckMode(0, 6, false, new ExcelDbrefrenceCheck2("jas_bd_style_orna_class", "orna_class_id", "orna_class_name",
                                " exists (select 1 from jas_bd_middleclass_ornaclass t where b.orna_class_id = t.style_orna_class_id and t.archiveflag = '0' " +
                                " and exists (select 1 from jas_bd_item_class ic where ic.ITEM_CLASS_ID=t.ITEM_CLASS_ID and ic.ITEM_CLASS_DSC =a.col1 )" +
                                " and exists (select 1 from jas_bd_orna_class oc where oc.ORNA_CLASS_ID=t.ORNA_CLASS_ID and oc.ORNA_CLASS_DSC =a.col2 )" +
                                " and exists (select 1 from jas_bd_style_middle_class smc where t.middle_class_id = smc.MIDDLE_CLASS_ID and smc.MIDDLE_CLASS_NAME = a.col5)) " +
                                " and b.archiveflag = '0'",
                                " exists (select 1 from jas_bd_middleclass_ornaclass t where b.orna_class_id = t.style_orna_class_id and t.archiveflag = '0' " +
                                " and t.item_class_id = a.col1" +
                                " and t.orna_class_id = a.col2" +
                                " and t.middle_class_id = a.col5) " +
                                " and b.archiveflag = '0'"), true),
				new ExcelCheckMode(0, 7, false, new ExcelDbrefrenceCheck2("jas_bd_style", "styleid", "stylename",
                                " exists (select 1 from jas_bd_item_class ic where ic.ITEM_CLASS_ID=b.itemclassid and ic.ITEM_CLASS_DSC =a.col1 )" +
                                " and exists (select 1 from jas_bd_orna_class oc where oc.ORNA_CLASS_ID=b.ornaclassid and oc.ORNA_CLASS_DSC =a.col2 )" +
                                " and exists (select 1 from jas_bd_style_item_class sic where sic.ITEM_CLASS_ID=b.styleitemclass and sic.ITEM_CLASS_NAME =a.col4 )" +
                                " and exists (select 1 from jas_bd_style_middle_class smc where b.stylemiddleclass = smc.MIDDLE_CLASS_ID and smc.MIDDLE_CLASS_NAME = a.col5)" +
                                " and exists (select 1 from jas_bd_style_orna_class soc where b.STYLEORNACLASS = soc.ORNA_CLASS_ID and soc.ORNA_CLASS_NAME = a.col6)" +
                                " and b.isarchive = '0'",
                                " b.itemclassid = a.col1" +
                                " and b.ornaclassid = a.col2" +
                                " and b.styleitemclass = a.col4" +
                                " and b.stylemiddleclass = a.col5" +
                                " and b.styleornaclass = a.col6" +
                                " and b.isarchive = '0'"), true),
				new ExcelCheckMode(0, 8, false, ExcelColumnEnum.FLOAT_COLUMN),
				new ExcelCheckMode(0, 9, false, ExcelColumnEnum.FLOAT_COLUMN)
			});
		if(successFlag){
			excelUtil.convertDbrefrenceToId();
			String userId = CommonUtil.getSessionUserId(req);
			dispatchWayManager.saveDispatchStyleFromExcel(excelUtil.getSeqId(), userId);
			write(res, "{'isSuccess':'true'}");
		}
		return null;
	}
	//----------------------------------------------------
	public ModelAndView exportExcelPrice(HttpServletRequest req, HttpServletResponse res) {
		sessionKey = "2";
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = dispatchWayManager.getDispatchPricePageData(condition);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("价位带配货基础数据");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"组织", "大类", "小类", "分析范围", "价位带起始", "价位带截止", "铺货量最小值", "铺货量最大值", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"orgId", "itemClassId", "ornaClassId", "analysisName","priceStart", "priceEnd", "dispMin", "dispMax", "createId", "createDate", "updateId", "updateDate", "status"});
//		excelData.addColumnWidth(new String[]{"startDate", "endDate", "orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "turnoverNum", "createId", "createDate", "updateId", "updateDate", "status"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(
				new String[] { "orgId", "itemClassId", "ornaClassId",
						"createId", "updateId" },
				new CacheSingletonIntf[] { OrgCache.getInstance(),
						ItemClassCache.getInstance(),
						OrnaClassCache.getInstance(), UserCache.getInstance(),
						UserCache.getInstance() });
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}

	public ModelAndView importExcelPrice(HttpServletRequest req, HttpServletResponse res) {
		String fieldName = "excel_file";
		ExcelUtil excelUtil = new ExcelUtil(req, res, excelUtilManager);
		excelUtil.saveExcelData(fieldName);
		boolean successFlag = excelUtil.checkExcelData(new ExcelCheckMode[] {
//				new ExcelCheckMode(0, 0, new ExcelSelfDefinedCheck(){
//					@Override
//					public boolean check(int rowIndex, String value,
//							ExcelRowData rowData, List<ExcelRowData> allData) {
//						String orgId = getNullString(rowData.getCol0(), "");
//						String analysisId = getNullString(rowData.getCol3(), "");
//						String styleId = getNullString(rowData.getCol7(), "");
//						
//						for (ExcelRowData data : allData){
//							if(rowIndex != data.getRowIndex()){
//								if(orgId.equals(data.getCol0()) && analysisId.equals(data.getCol3()) && styleId.equals(data.getCol7())){
//									return false;
//								}
//							}
//						}
//						return true;
//					}
//
//					@Override
//					public String getErrorMsg(int rowIndex, String value,
//							ExcelRowData rowData, List<ExcelRowData> allData) {
//						String orgId = getNullString(rowData.getCol0(), "");
//						String analysisId = getNullString(rowData.getCol3(), "");
//						String styleId = getNullString(rowData.getCol7(), "");
//						
//						for (ExcelRowData data : allData){
//							if(rowIndex != data.getRowIndex()){
//								if(orgId.equals(data.getCol0()) && analysisId.equals(data.getCol3()) && styleId.equals(data.getCol7())){
//									return "第" + (rowIndex+1) + "行和第" + (data.getRowIndex()+1) + "行 数据重复";
//								}
//							}
//						}
//						return null;
//					}
//					
//					private String getNullString(String str, String defaultStr){
//						if(StringUtil.isNotBlank(str)) return str;
//						return defaultStr;
//					}}),
				new ExcelCheckMode(0, 4, new ExcelSelfDefinedCheck(){
					public boolean check(int rowIndex, String value, ExcelRowData rowData, List<ExcelRowData> allData) {
						try {
							if(Double.parseDouble(rowData.getCol4())>Double.parseDouble(rowData.getCol5())){
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
							if(Double.parseDouble(rowData.getCol4())>Double.parseDouble(rowData.getCol5())){
								return "价位带起始 不能大于 价位带截止,且必须为数据类型";
							}
						} catch (Exception e) {
							return "价位带起始 不能大于 价位带截止,且必须为数据类型";
						}
						return null;
					}}),
					new ExcelCheckMode(0, 6, new ExcelSelfDefinedCheck(){
						public boolean check(int rowIndex, String value, ExcelRowData rowData, List<ExcelRowData> allData) {
							try {
								if(Double.parseDouble(rowData.getCol6())>Double.parseDouble(rowData.getCol7())){
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
								if(Double.parseDouble(rowData.getCol6())>Double.parseDouble(rowData.getCol7())){
								return "铺货量最小值 不能大于 铺货量最大值,且必须为数据类型";
								}
							} catch (Exception e) {
								return "铺货量最小值 不能大于 铺货量最大值,且必须为数据类型";
							}
							return null;
						}}),
				new ExcelCheckMode(0, 0, false, new ExcelDbrefrenceCheck("jas_sm_org", "org_id", "org_name", "org_type = 1"), true),
				new ExcelCheckMode(0, 1, false, new ExcelDbrefrenceCheck("jas_bd_item_class", "item_class_id", "item_class_dsc"), true),
				new ExcelCheckMode(0, 2, false, new ExcelDbrefrenceCheck("jas_bd_orna_class", "orna_class_id", "orna_class_dsc"), true),
				new ExcelCheckMode(0, 3, false, new ExcelDbrefrenceCheck2("jas_bd_analysis_arange", "analysis_arange_id", "summarydescription",
                                " exists (select 1 from jas_bd_item_class ic where ic.ITEM_CLASS_ID=b.ITEM_CLASS_ID and ic.ITEM_CLASS_DSC =a.col1 )" +
                                " and exists (select 1 from jas_bd_orna_class oc where oc.ORNA_CLASS_ID=b.ORNA_CLASS_ID and oc.ORNA_CLASS_DSC =a.col2 )" +
                                " and b.archiveflag = 0",
                                " b.item_class_id = a.col1" +
                                " and b.orna_class_id = a.col2" +
                                " and b.archiveflag = 0"), true),
				new ExcelCheckMode(0, 4, false, ExcelColumnEnum.FLOAT_COLUMN),
				new ExcelCheckMode(0, 5, false, ExcelColumnEnum.FLOAT_COLUMN),
				new ExcelCheckMode(0, 6, false, ExcelColumnEnum.FLOAT_COLUMN),
				new ExcelCheckMode(0, 7, false, ExcelColumnEnum.FLOAT_COLUMN)
			});
		if(successFlag){
			excelUtil.convertDbrefrenceToId();
			String userId = CommonUtil.getSessionUserId(req);
			dispatchWayManager.saveDispatchPriceFromExcel(excelUtil.getSeqId(), userId);
			write(res, "{'isSuccess':'true'}");
		}
		return null;
	}

    public ModelAndView styleDispatchReport(HttpServletRequest req, HttpServletResponse res){
        BaseForm form = new BaseForm();
        sessionKey = "3";
        Map<String, String> condition = CommonUtil.getConditionForPageSession( this, req);
        Pager pager = dispatchWayManager.getDispatchStyleReportPageData(condition);
        form.setPager(pager);
        return new ModelAndView("move/win/dispatchStyleList", "form", form);
    }

    public ModelAndView styleDispatchReportExcel(HttpServletRequest req, HttpServletResponse res) {
        sessionKey = "3";
        Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
        Pager pager = dispatchWayManager.getDispatchStyleReportPageData(condition);
        ExcelData excelData = new ExcelData();//excel数据对象
        excelData.setTitle("款式配货表");
        excelData.setPager(pager);//直接利用分页pager对象
        excelData.setColumnTitle(new String[]{"组织", "大类", "小类", "分析范围", "款式大类", "款式中类", "款式小类", "款式", "铺货量最小值", "铺货量最大值", "有效库存", "最小值与库存差异", "最大值与库存差异"});
        excelData.setColumnName(new String[]{"orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "dispMin", "dispMax", "stockNum", "dispMinDiff", "dispMaxDiff"});
        //设置对应的缓存数据
        excelData.addSingletonDisplayColumns(
                new String[] { "orgId", "itemClassId", "ornaClassId",
                        "styleItemClassId", "styleMiddleClassId",
                        "styleOrnaClassId", "createId", "updateId" },
                new CacheSingletonIntf[] { OrgCache.getInstance(),
                        ItemClassCache.getInstance(),
                        OrnaClassCache.getInstance(),
                        StyleItemClassCache.getInstance(),
                        StyleMiddleClassCache.getInstance(),
                        StyleOrnaClassCache.getInstance(),
                        UserCache.getInstance(), UserCache.getInstance() });
        //设置对应的数据字典
        excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});

        ExportExcelUtil util = new ExportExcelUtil();
        util.setExcelData(excelData);
        util.export(res);
        return null;
    }

    public ModelAndView priceDispatchReport(HttpServletRequest req, HttpServletResponse res){
        BaseForm form = new BaseForm();
        sessionKey = "4";
        Map<String, String> condition = CommonUtil.getConditionForPageSession( this, req);
        Pager pager = dispatchWayManager.getDispatchPriceReportPageData(condition);
        form.setPager(pager);
        return new ModelAndView("move/win/dispatchPriceList", "form", form);
    }

    public ModelAndView priceDispatchReportExcel(HttpServletRequest req, HttpServletResponse res) {
        sessionKey = "4";
        Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
        Pager pager = dispatchWayManager.getDispatchPriceReportPageData(condition);
        ExcelData excelData = new ExcelData();//excel数据对象
        excelData.setTitle("价位带配货表");
        excelData.setPager(pager);//直接利用分页pager对象
        excelData.setColumnTitle(new String[]{"组织", "大类", "小类", "分析范围", "价位带起始", "价位带截止", "铺货量最小值", "铺货量最大值", "有效库存", "最小值与库存差异", "最大值与库存差异"});
        excelData.setColumnName(new String[]{"orgId", "itemClassId", "ornaClassId", "analysisName","priceStart", "priceEnd", "dispMin", "dispMax", "stockNum", "dispMinDiff", "dispMaxDiff"});
        //设置对应的缓存数据
        excelData.addSingletonDisplayColumns(
                new String[] { "orgId", "itemClassId", "ornaClassId",
                        "createId", "updateId" },
                new CacheSingletonIntf[] { OrgCache.getInstance(),
                        ItemClassCache.getInstance(),
                        OrnaClassCache.getInstance(), UserCache.getInstance(),
                        UserCache.getInstance() });
        //设置对应的数据字典
        excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});

        ExportExcelUtil util = new ExportExcelUtil();
        util.setExcelData(excelData);
        util.export(res);
        return null;
    }
}
