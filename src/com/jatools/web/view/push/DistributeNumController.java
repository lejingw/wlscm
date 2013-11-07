package com.jatools.web.view.push;

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
import com.jatools.manager.push.DistributeNumManager;
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

public class DistributeNumController extends BaseMultiActionController {
	private DistributeNumManager distributeNumManager;
	private ExcelUtilManager excelUtilManager;
	

	public void setExcelUtilManager(ExcelUtilManager excelUtilManager) {
		this.excelUtilManager = excelUtilManager;
	}

	public void setDistributeNumManager(DistributeNumManager distributeNumManager) {
		this.distributeNumManager = distributeNumManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = distributeNumManager.getDistributeNumPageData(condition);
		form.setPager(pager);
		return new ModelAndView("push/distributeNum_list", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = distributeNumManager.getDistributeNumPageData(condition);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("铺货量");
//		excelData.setDataList(dataList);//手工重新查找数据
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"组织", "大类", "小类", "分析范围", "款式大类", "铺货量", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "distributeNum", "createId", "createDate", "updateId", "updateDate", "status"});
//		excelData.addColumnWidth(new String[]{"startDate", "endDate", "orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "turnoverNum", "createId", "createDate", "updateId", "updateDate", "status"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "itemClassId", "ornaClassId", "styleItemClassId", "createId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), ItemClassCache.getInstance(), OrnaClassCache.getInstance(), StyleItemClassCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
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
				new ExcelCheckMode(0, 5, false, ExcelColumnEnum.NUMBER_COLUMN),
				new ExcelCheckMode(0, 0, new ExcelSelfDefinedCheck(){
					@Override
					public boolean check(int rowIndex, String value,
							ExcelRowData rowData, List<ExcelRowData> allData) {
						String itemClass = getNullString(rowData.getCol0(), "");
						String ornaClass = getNullString(rowData.getCol1(), "");
						String styleItemClass = getNullString(rowData.getCol2(), "");
						String analysis = getNullString(rowData.getCol3(), "");
						String unitType = getNullString(rowData.getCol4(), "");
						
						for (ExcelRowData data : allData){
							if(rowIndex != data.getRowIndex()){
								// 大类
								if(itemClass.equals(data.getCol0()) && ornaClass.equals(data.getCol1()) && styleItemClass.equals(data.getCol2()) 
										&& analysis.equals(data.getCol3()) && unitType.equals(data.getCol4())){
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
						String styleItemClass = getNullString(rowData.getCol2(), "");
						String analysis = getNullString(rowData.getCol3(), "");
						String unitType = getNullString(rowData.getCol4(), "");
						
						for (ExcelRowData data : allData){
							if(rowIndex != data.getRowIndex()){
								// 大类
								if(itemClass.equals(data.getCol0()) && ornaClass.equals(data.getCol1()) && styleItemClass.equals(data.getCol2()) 
										&& analysis.equals(data.getCol3()) && unitType.equals(data.getCol4())){
									return "第" + (rowIndex+1) + "行和第" + (data.getRowIndex()+1) + "数据重复";
								}
							}
						}
						return null;
					}
					
					private String getNullString(String str, String defaultStr){
						if(StringUtil.isNotBlank(str)) return str;
						return defaultStr;
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
                                " and b.archiveflag = 0"), true)
			});
		if(successFlag){
			excelUtil.convertDbrefrenceToId();
			String userId = CommonUtil.getSessionUserId(req);
			distributeNumManager.saveDistributeNumFromExcel(excelUtil.getSeqId(), userId);
			write(res, "{'isSuccess':'true'}");
		}
		return null;
	}
}
