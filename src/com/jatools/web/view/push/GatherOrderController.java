package com.jatools.web.view.push;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.push.GatherOrderManager;
import com.jatools.manager.push.GatherSetManager;
import com.jatools.vo.push.GatherSetHead;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.StyleMiddleClassCache;
import com.jatools.web.cache.StyleOrnaClassCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.push.GatherSetForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
import com.jatools.web.view.pur.ExportPurchaseBillToExcelUtil;

public class GatherOrderController extends BaseMultiActionController {
	private GatherSetManager gatherSetManager;
	private GatherOrderManager gatherOrderManager;

	public void setGatherSetManager(GatherSetManager gatherSetManager) {
		this.gatherSetManager = gatherSetManager;
	}

	public void setGatherOrderManager(GatherOrderManager gatherOrderManager) {
		this.gatherOrderManager = gatherOrderManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 总单列表
	 */
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res) {
		GatherSetForm form = new GatherSetForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		Pager pager = gatherOrderManager.getGatherOrderPageData(condition);
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("push/gatherOrder_list", "form", form);
	}
	
	/**
	 * 查看总单明细
	 */
	public ModelAndView view(HttpServletRequest req, HttpServletResponse res) {
		GatherSetForm form = new GatherSetForm();
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "headid", "setLineId", "salableFlag", "itemClassId", "ornaClassId", "analysisId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleId");
		//如果salableFlag不为空，则表示配置页面弹出显示明细
		if(StringUtil.isNotEmpty(condition.get("headid"))){
			String fromSetList = CommonUtil.getParameterEmpty(req, "fromSetList");
			form.setBackUrl("1".equals(fromSetList)?"gatherSet.vm?user_action=setList":"gatherOrder.vm?user_action=list");
			GatherSetHead head = gatherSetManager.getGatherSetHead(condition.get("headid"));
			form.setHead(head);
		}
		form.setCondition(condition);
		Pager pager = gatherOrderManager.getGatherOrderLinePageData(condition.get("headid"), condition.get("setLineId"), condition.get("salableFlag"), condition);
		form.setPager(pager);
		return new ModelAndView("push/gatherOrder_view", "form", form);
	}
	
	/**
	 * 修改总单明细
	 */
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res) {
		GatherSetForm form = new GatherSetForm();
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "headid", "salableFlag", "itemClassId", "ornaClassId", "analysisId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleId");
		GatherSetHead head = gatherSetManager.getGatherSetHead(condition.get("headid"));
		form.setHead(head);
		form.setCondition(condition);
		Pager pager = gatherOrderManager.getGatherOrderLinePageData(condition.get("headid"), null, null, condition);
		form.setPager(pager);
		return new ModelAndView("push/gatherOrder_edit", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "showAllFlag");
		Pager pager = gatherOrderManager.getGatherOrderPageData(condition);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("下单设置");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"状态", "总单编号", "区域", "周期类型", "商品类别", "购物开始日期", "购物结束日期", "销售开始日期", "销售结束日期", "最后到货日期", "组织", "创建人", "创建时间", "修改人", "修改时间"});
		excelData.setColumnName(new String[]{"status", "billNo", "regionName", "cycleTypeName","articleTypeNames", "purDateStart", "purDateEnd", "saleDateStart", "saleDateEnd", "purArriveDateEnd", "orgId", "createId", "createDate", "updateId", "updateDate"});
//		excelData.addColumnWidth(new String[]{"startDate", "endDate", "orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "turnoverNum", "createId", "createDate", "updateId", "updateDate", "status"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}
	public ModelAndView exportExcel2(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "headid", "setLineId", "salableFlag", "itemClassId", "ornaClassId", "analysisId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleId");
		Pager pager = gatherOrderManager.getGatherOrderLinePageData(condition.get("headid"), condition.get("setLineId"), condition.get("salableFlag"), condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("总部总单");
//		excelData.setDataList(dataList);//手工重新查找数据
		excelData.setPager(pager);
		excelData.addColumnWidth(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "styleNo", "analysisName", "qualityId", "bracketColorId", "unitId", "sizeName", "gradeName", "orderNum", "smallGraph"}
		, new Integer[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 3, 2, 4});

		//添加列标题
		excelData.setColumnTitle(new String[]{"大类", "小类", "款式大类", "款式中类", "款式小类", "款式", "分析范围", "商品材质", "托架颜色", "单位", "尺寸", "品质", "采购数量", "图片"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "analysisName", "qualityId", "bracketColorId", "unitId", "sizeName", "gradeName", "orderNum", "smallGraph"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(
				new String[] { "itemClassId", "ornaClassId",
						"styleItemClassId", "styleMiddleClassId", "styleOrnaClassId",
						"qualityId", "unitId" },
				new CacheSingletonIntf[] { ItemClassCache.getInstance(), OrnaClassCache.getInstance(),
						StyleItemClassCache.getInstance(), StyleMiddleClassCache.getInstance(), StyleOrnaClassCache.getInstance(),
						QualityCache.getInstance(), UnitCache.getInstance() });
		excelData.addDictDisplayColumns(new String[]{"bracketColorId"}, new String[]{DictConstant.BRACKET_COLOR});
		
		ExportPurchaseBillToExcelUtil util = new ExportPurchaseBillToExcelUtil();
		util.setPicBasePath(CommonUtil.getPicBasePath2());
//		util.setHead(head);
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
}
