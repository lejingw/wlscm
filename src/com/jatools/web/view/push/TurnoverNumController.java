package com.jatools.web.view.push;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jatools.common.excel.ExcelDbrefrenceCheck2;
import org.springframework.web.servlet.ModelAndView;


import com.jatools.common.CommonUtil;
import com.jatools.common.ExcelUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.excel.ExcelCheckMode;
import com.jatools.common.excel.ExcelColumnEnum;
import com.jatools.common.excel.ExcelDbrefrenceCheck;
import com.jatools.manager.push.TurnoverNumManager;
import com.jatools.manager.util.ExcelUtilManager;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.BaseForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

public class TurnoverNumController extends BaseMultiActionController {
	private TurnoverNumManager turnoverNumManager;
	private ExcelUtilManager excelUtilManager;

	public void setExcelUtilManager(ExcelUtilManager excelUtilManager) {
		this.excelUtilManager = excelUtilManager;
	}

	public void setTurnoverNumManager(TurnoverNumManager turnoverNumManager) {
		this.turnoverNumManager = turnoverNumManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = turnoverNumManager.getTurnoverNumPageData(condition);
		form.setPager(pager);
		return new ModelAndView("push/turnoverNum_list", "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = turnoverNumManager.getTurnoverNumPageData(condition);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("周转量");
//		excelData.setDataList(dataList);//手工重新查找数据
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"起始日期", "截止日期", "组织", "大类", "小类", "分析范围", "款式大类", "周转量", "创建人", "创建时间", "修改人", "修改时间", "状态"});
		excelData.setColumnName(new String[]{"startDate", "endDate", "orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "turnoverNum", "createId", "createDate", "updateId", "updateDate", "status"});
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
				new ExcelCheckMode(0, 0, false, ExcelColumnEnum.DATE_COLUMN),
				new ExcelCheckMode(0, 1, false, ExcelColumnEnum.DATE_COLUMN),
				new ExcelCheckMode(0, 7, false, ExcelColumnEnum.FLOAT_COLUMN),
				new ExcelCheckMode(0, 2, false, new ExcelDbrefrenceCheck("jas_sm_org", "org_id", "org_name", "org_type = 1"), true),
				new ExcelCheckMode(0, 3, false, new ExcelDbrefrenceCheck("jas_bd_item_class", "item_class_id", "item_class_dsc"), true),
				new ExcelCheckMode(0, 4, false, new ExcelDbrefrenceCheck("jas_bd_orna_class", "orna_class_id", "orna_class_dsc"), true),
				new ExcelCheckMode(0, 5, false, new ExcelDbrefrenceCheck2("jas_bd_analysis_arange", "analysis_arange_id", "summarydescription",
                                " exists (select 1 from jas_bd_item_class ic where ic.ITEM_CLASS_ID=b.ITEM_CLASS_ID and ic.ITEM_CLASS_DSC =a.col3 )" +
                                " and exists (select 1 from jas_bd_orna_class oc where oc.ORNA_CLASS_ID=b.ORNA_CLASS_ID and oc.ORNA_CLASS_DSC =a.col4 )" +
                                " and b.archiveflag = 0",
                                " b.item_class_id = a.col3" +
                                " and b.orna_class_id = a.col4" +
                                " and b.archiveflag = 0"), true),
				new ExcelCheckMode(0, 6, false, new ExcelDbrefrenceCheck2("jas_bd_style_item_class", "item_class_id", "item_class_name",
                        " exists (select 1 from jas_bd_styleitemclass_itemorna d, jas_bd_itemclass_ornaclass c where d.archiveflag = 0 " +
                        " and c.archiveflag = 0 and c.id = d.itemornaid and b.item_class_id = d.styleitemclassid " +
                        " and exists (select 1 from jas_bd_item_class ic where ic.ITEM_CLASS_ID=c.ITEM_CLASS_ID and ic.ITEM_CLASS_DSC =a.col3 )" +
                        " and exists (select 1 from jas_bd_orna_class oc where oc.ORNA_CLASS_ID=c.ORNA_CLASS_ID and oc.ORNA_CLASS_DSC =a.col4 ))" +
                        " and b.archiveflag = 0",
                        " exists (select 1 from jas_bd_styleitemclass_itemorna d, jas_bd_itemclass_ornaclass c where d.archiveflag = 0 " +
                        " and c.archiveflag = 0 and c.id = d.itemornaid and b.item_class_id = d.styleitemclassid " +
                        " and c.item_class_id = a.col3" +
                        " and c.orna_class_id = a.col4)" +
                        " and b.archiveflag = 0"), true)
			});
		if(successFlag){
			excelUtil.convertDbrefrenceToId();
			String userId = CommonUtil.getSessionUserId(req);
			turnoverNumManager.saveTurnoverNumFromExcel(excelUtil.getSeqId(), userId);
			write(res, "{'isSuccess':'true'}");
		}
		return null;
	}
}
