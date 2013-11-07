package com.jatools.web.view.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.jatools.common.CommonUtil;
import com.jatools.common.Global;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.stock.DispatchOrnaManager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.stock.DispatchCondition;
import com.jatools.vo.stock.DispatchMoveBill;
import com.jatools.vo.stock.DispatchOrnaInfo;
import com.jatools.vo.stock.DispatchOrnaLog;
import com.jatools.vo.stock.DispatchRealCost;
import com.jatools.vo.stock.DispatchTemp;
import com.jatools.vo.stock.GatherHead;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.ArticleTypeCache;
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
import com.jatools.web.form.stock.DispatchConditonForm;
import com.jatools.web.form.stock.DispatchOrnaForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
import com.jatools.web.view.pur.ExportPurchaseBillToExcelUtil;

public class DispatchOrnaController extends BaseMultiActionController {
	private DispatchOrnaManager dispatchOrnaManager;

	public void setDispatchOrnaManager(DispatchOrnaManager dispatchOrnaManager) {
		this.dispatchOrnaManager = dispatchOrnaManager;
	}
	
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> conditon = CommonUtil.getConditionForPageSession(this, req, "gatherNo", "cycleTypeId", "orderType", "orderKind2", "gatherDate", "status", "dotype", "dispatchFlag2", "showAllFlag");
		Pager pager = dispatchOrnaManager.getGatherHeadPageData(conditon);
		DispatchOrnaForm form = new DispatchOrnaForm();
		form.setPager(pager);
		form.setCondition(conditon);
		return new ModelAndView("stock/dispatchOrna_list", "form", form);
	}
	/**
	 * 选择配货参数
	 */
	public ModelAndView condition(HttpServletRequest req, HttpServletResponse res) {
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		String gstatus = CommonUtil.getParameterEmpty(req, "gstatus");
		String userid = CommonUtil.getSessionUserId(req);
		//获取配货参数
		List<DispatchCondition> list = dispatchOrnaManager.getDispatchCondition(gheadid);
		DispatchConditonForm form = new DispatchConditonForm();
		form.setGstatus(gstatus);
		form.setGheadid(gheadid);
		form.setList(list);
		return new ModelAndView("stock/win/dispatchOrna_condition", "form", form);
	}
	/**
	 * 进入订单配货页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView dispatch(HttpServletRequest req, HttpServletResponse res) {
		String conditionId = CommonUtil.getParameterEmpty(req, "conditionId");
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		String gstatus = CommonUtil.getParameterEmpty(req, "gstatus");
		DispatchOrnaForm form = new DispatchOrnaForm();
		form.setGheadid(gheadid);
		form.setGstatus(gstatus);
		form.setConditionId(conditionId);
		GatherHead ghead = dispatchOrnaManager.getDispatchGatherHead(gheadid);
		form.setGhead(ghead);
		DispatchCondition disCondition = null;
		if(!"-1".equals(conditionId)){
			disCondition = dispatchOrnaManager.getDispatchConditionById(conditionId);
		}
		form.setDisCondition(disCondition);
		return new ModelAndView("stock/dispatchOrna", "form", form);
	}
	/**
	 * 选择分配店弹出框
	 */
	public ModelAndView selectDispatchOrg(HttpServletRequest req, HttpServletResponse res) {
		String conditionId = CommonUtil.getParameterEmpty(req, "conditionId");
		String inOrgId = CommonUtil.getParameterEmpty(req, "inOrgId");
		//获取配货参数下的所有分配店
		List<Org> dispatchOrgList = dispatchOrnaManager.getDispatchOrg(conditionId);
		DispatchOrnaForm form = new DispatchOrnaForm();
		form.setDispatchOrgList(dispatchOrgList);
		form.setInOrgId(inOrgId);
		return new ModelAndView("stock/win/dispatchOrna_selectOrg", "form", form);
	}
	/**
	 * 根据配货参数id,获取正配货临时分页数据,并显示
	 */
	public ModelAndView dispatching(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "conditionId", "orgId", "showUnfullOnly");
		DispatchOrnaForm form = new DispatchOrnaForm();
		Pager pager = null;
		if(StringUtil.isNotEmpty(condition.get("conditionId"))){
			pager = dispatchOrnaManager.getDispatchingPageData(condition);
		}else{
			pager = new Pager();
		}
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("stock/dispatchOrna_dispatching", "form", form);
	}
	/**
	 * 显示已配货
	 */
	public ModelAndView dispatched(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "gheadid", "orgId", "itemClassId", "ornaClassId", "showUnfullOnly", "showUnperfectMatch");
		DispatchOrnaForm form = new DispatchOrnaForm();
		Pager pager = null;
		if(StringUtil.isNotEmpty(condition.get("gheadid"))){
			pager = dispatchOrnaManager.getDispatchedPageData(condition);
		}else{
			pager = new Pager();
		}
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("stock/dispatchOrna_dispatched", "form", form);
	}
	/**
	 * 显示总部总单
	 */
	public ModelAndView hqline(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "gheadid", "itemClassId", "ornaClassId", "showUnfullOnly", "showUnperfectMatch");
		DispatchOrnaForm form = new DispatchOrnaForm();
		Pager pager = null;
		if(StringUtil.isNotEmpty(condition.get("gheadid"))){
			pager = dispatchOrnaManager.getHqlinePageData(condition);
		}else{
			pager = new Pager();
		}
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("stock/dispatchOrna_hqline", "form", form);
	}
	/**
	 * 获取减库饰品
	 */
	public ModelAndView viewSubOrna(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "gheadid");
		DispatchOrnaForm form = new DispatchOrnaForm();
		Pager pager = null;
		if(StringUtil.isNotEmpty(condition.get("gheadid"))){
			//获取正分配饰品信息
			pager = dispatchOrnaManager.getDispatchSubTempOrnaPageData(condition);
		}else{
			pager = new Pager();
		}
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("stock/win/dispatchOrna_showSubOrna", "form", form);
	}
	/**
	 * 导出减库饰品信息
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView exportSubOrnaExcel(HttpServletRequest req, HttpServletResponse res){
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "gheadid");
		String startTmp = condition.get("start");
		String limitTmp = condition.get("limit");
		condition.put("start", "1");
		condition.put("limit", "10000000");
		Pager pager = dispatchOrnaManager.getDispatchSubTempOrnaPageData(condition);
		condition.put("start", startTmp);
		condition.put("limit", limitTmp);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("减库饰品");
		excelData.setPager(pager);//手工重新查找数据
//		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"ornaDsc", "unitId"}, new Integer[]{4, 1});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"饰品编码", "饰品名称", "大类", "小类", "款式大类", "款式中类", "款式小类", "分析范围", "计量单位", "款式", "色级", "净度",  "总重量", "主石重", "配石重", "尺寸", "基础价", "是否已配货", "状态", "仓库", "是否备货"});
		//添加列name
		excelData.setColumnName(new String[]{"ornaCode", "ornaDsc", "itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "analysisName", "unitId", "styleName", "colorGradeId", "cleanId", "allQty", "mainWeight", "partWeight", "sizeName", "basicPrice", "dispatchFlag", "status", "stockId", "isMaterial"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"unitId"}, new CacheSingletonIntf[]{UnitCache.getInstance()});
		excelData.addDictDisplayColumns(new String[]{"colorGradeId","cleanId", "dispatchFlag", "status", "stockId", "isMaterial"},
				new String[]{DictConstant.DIA_COLORGRADE, DictConstant.DIA_CLEAN, DictConstant.YES_OR_NO, DictConstant.BILL_STATUS, DictConstant.INVCODE, DictConstant.YES_OR_NO});
		excelData.addSingletonDisplayColumns(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId"},
				new CacheSingletonIntf[]{ItemClassCache.getInstance(), OrnaClassCache.getInstance(), StyleItemClassCache.getInstance(), StyleMiddleClassCache.getInstance(), StyleOrnaClassCache.getInstance()});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
	/**
	 * 显示正分配饰品
	 */
	public ModelAndView showDispatching(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "orderLineId");
		DispatchOrnaForm form = new DispatchOrnaForm();
		Pager pager = null;
		if(StringUtil.isNotEmpty(condition.get("orderLineId"))){
			//获取正分配饰品信息
			pager = dispatchOrnaManager.getShowDispatchingPageData(condition);
		}else{
			pager = new Pager();
		}
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("stock/win/dispatchOrna_showDispatching", "form", form);
	}
	/**
	 * 显示已分配饰品
	 */
	public ModelAndView showDispatched(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "useOrderLineIdFlag", "lineId", "srcBillCode");
		DispatchOrnaForm form = new DispatchOrnaForm();
		Pager pager = null;
		if(StringUtil.isNotEmpty(condition.get("useOrderLineIdFlag")) && StringUtil.isNotEmpty(condition.get("lineId"))){
			//获取已分配饰品信息
			pager = dispatchOrnaManager.getShowDispatchedPageData(condition);
		}else{
			pager = new Pager();
		}
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("stock/win/dispatchOrna_showDispatched", "form", form);
	}
	/**
	 * 显示可变更列表
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView changeDispatching(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "conditionId", "ornaLogId","orderLineId","styleFalg","styleFalg2","colorGradeFlag","cleanFlag", "openflag");
		DispatchOrnaForm form = new DispatchOrnaForm();
		Pager pager = null;
		if(StringUtil.isNotEmpty(condition.get("conditionId")) && StringUtil.isNotEmpty(condition.get("ornaLogId"))){
//			pager = dispatchOrnaManager.getDispatchingPageData(condition);
			String ornaLogId = condition.get("ornaLogId");
			DispatchOrnaLog dispatchOrnaLog = dispatchOrnaManager.getDispatchOrnaLogById(ornaLogId);
			DispatchOrnaInfo info = dispatchOrnaManager.getDispatchOrnaInfo(dispatchOrnaLog.getOrnaCode(), true);
			boolean styleFlag = "1".equals(condition.get("styleFalg")), colorGradeFlag = "1".equals(condition.get("colorGradeFlag")), cleanFlag = "1".equals(condition.get("cleanFlag"));
			String styleFalg2 = condition.get("styleFalg2");
			String inOrgId = "-1";
			String conditionId = condition.get("conditionId");
			List<DispatchTemp> dispatchTempList = dispatchOrnaManager.getMatchDispatchTempList(info, styleFlag, styleFalg2, colorGradeFlag, cleanFlag, inOrgId, conditionId);
			pager = new Pager();
			pager.setLimit(Integer.valueOf(StringUtil.isNotEmpty(condition.get("limit"))?condition.get("limit"):Global.PAGE_DEFAULT_LIMIT_WIN));
			pager.setStart(Integer.valueOf(StringUtil.isNotEmpty(condition.get("start"))?condition.get("start"):Global.PAGE_DEFAULT_START));
			pager.setTotalCount(dispatchTempList.size());
			List<Object> pageData = new ArrayList<Object>();
			for(int i = pager.getStart() -1;i<pager.getLimit() + pager.getStart() -1 && i<pager.getTotalCount();i++){
				pageData.add(dispatchTempList.get(i));
			}
			pager.setPageData(pageData);
		}else{
			pager = new Pager();
		}
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("stock/win/dispatchOrna_changeDispatching", "form", form);
	}
	/**
	 * 未生成调拨单查询
	 */
	public ModelAndView unmovebill(HttpServletRequest req, HttpServletResponse res) {
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		List<DispatchMoveBill> list = dispatchOrnaManager.getUnmovebill(gheadid);
		DispatchOrnaForm form = new DispatchOrnaForm();
		form.setMoveBillList(list);
		return new ModelAndView("stock/dispatchOrna_unmovebill", "form", form);
	}
	/**
	 * 生成调拨单查询
	 */
	public ModelAndView movebill(HttpServletRequest req, HttpServletResponse res) {
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		List<DispatchMoveBill> list = dispatchOrnaManager.getMovebill(gheadid);
		DispatchOrnaForm form = new DispatchOrnaForm();
		form.setMoveBillList(list);
		return new ModelAndView("stock/dispatchOrna_movebill", "form", form);
	}
	/**
	 * 查看真实总成本
	 */
	public ModelAndView viewRealCost(HttpServletRequest req, HttpServletResponse res) {
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		String orgIds = CommonUtil.getParameterEmpty(req, "orgIds");
		String itemClassIds = CommonUtil.getParameterEmpty(req, "itemClassIds");
		String ornaClassIds = CommonUtil.getParameterEmpty(req, "ornaClassIds");
		DispatchOrnaForm form = new DispatchOrnaForm();
		if(StringUtil.isNotEmpty(gheadid) && StringUtil.isNotEmpty(orgIds) && StringUtil.isNotEmpty(itemClassIds) && StringUtil.isNotEmpty(ornaClassIds)){			
			List<DispatchRealCost> list = dispatchOrnaManager.getDispatchRealCost(gheadid, orgIds, itemClassIds, ornaClassIds, GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA);
			form.setRealCostList(list);
		}
		return new ModelAndView("stock/win/dispatchOrna_realCost", "form", form);
	}
	/**
	 * 导出总部总单行数据
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView exportHqline(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "gheadid", "itemClassId", "ornaClassId", "showUnfullOnly", "showUnperfectMatch");
		List<Map> dataList = dispatchOrnaManager.getHqlineForExcel(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("总部总单");
		excelData.setDataList(dataList);//手工重新查找数据
		excelData.addColumnWidth(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "analysisName", "qualityId", "bracketcolorId", "unitId", "sizeName", "gradeName", "numOrder", "smallGraph"}
		, new Integer[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 3, 2, 4});

		//添加列标题
		excelData.setColumnTitle(new String[]{"大类", "小类", "款式大类", "款式中类", "款式小类", "款式", "分析范围", "商品材质", "托架颜色", "单位", "尺寸", "品质", "数量", "图片"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "analysisName", "qualityId", "bracketcolorId", "unitId", "sizeName", "gradeName", "numOrder", "smallGraph"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(
				new String[] { "itemClassId", "ornaClassId",
						"styleItemClassId", "styleMiddleClassId", "styleOrnaClassId",
						"qualityId", "unitId" },
				new CacheSingletonIntf[] { ItemClassCache.getInstance(), OrnaClassCache.getInstance(),
						StyleItemClassCache.getInstance(), StyleMiddleClassCache.getInstance(), StyleOrnaClassCache.getInstance(),
						QualityCache.getInstance(), UnitCache.getInstance() });
		excelData.addDictDisplayColumns(new String[]{"bracketcolorId"}, new String[]{DictConstant.BRACKET_COLOR});
		
		ExportPurchaseBillToExcelUtil util = new ExportPurchaseBillToExcelUtil();
		util.setPicBasePath(CommonUtil.getPicBasePath2());
//		util.setHead(head);
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
	

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> conditon = CommonUtil.getConditionForPageSession(this, req, "gatherNo", "cycleTypeId", "orderType", "orderKind2", "gatherDate", "status", "dotype", "dispatchFlag2", "showAllFlag");
		Pager pager = dispatchOrnaManager.getGatherHeadPageData(conditon);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("订单配货列表");
//		excelData.setDataList(dataList);//手工重新查找数据
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate", "updateDate", "orgId"}, new Integer[]{3, 3, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"总单编号", "区域", "周期类型", "商品类别", "购物类型", "要货分类", "汇总人", "汇总日期", "状态", "执行功能", "已配货"});
		//添加列name
		excelData.setColumnName(new String[]{"gatherNo", "regionName", "cycleTypeName", "articleTypeIds", "orderType", "orderKind", "gatherUser", "gatherDate", "status", "dotype", "dispatchFlag"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"articleTypeIds", "gatherUser"},
				new CacheSingletonIntf[]{ArticleTypeCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"orderType", "orderKind", "status", "dotype", "dispatchFlag"}, new String[]{DictConstant.ORDER_TYPE, DictConstant.ORDER_KIND, DictConstant.PLAN_BILL_STATUS
				, DictConstant.DISPATCH_DO_TYPE, DictConstant.YES_OR_NO});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
}
