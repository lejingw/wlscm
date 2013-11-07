package com.jatools.web.view.push;

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
import com.jatools.manager.push.GatherOrderManager;
import com.jatools.manager.push.PushDispatchManager;
import com.jatools.manager.stock.DispatchOrnaManager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.push.GatherOrderHead;
import com.jatools.vo.stock.DispatchCondition;
import com.jatools.vo.stock.DispatchMoveBill;
import com.jatools.vo.stock.DispatchOrnaInfo;
import com.jatools.vo.stock.DispatchOrnaLog;
import com.jatools.vo.stock.DispatchRealCost;
import com.jatools.vo.stock.DispatchStatistics;
import com.jatools.vo.stock.PushDispatchTemp;
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
import com.jatools.web.form.BaseForm;
import com.jatools.web.form.push.PushDispatchForm;
import com.jatools.web.form.stock.DispatchOrnaForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
import com.jatools.web.view.pur.ExportPurchaseBillToExcelUtil;

public class PushDispatchController extends BaseMultiActionController {
	private PushDispatchManager pushDispatchManager;
	public DispatchOrnaManager dispatchOrnaManager;
	public GatherOrderManager gatherOrderManager;
	public void setPushDispatchManager(PushDispatchManager pushDispatchManager) {
		this.pushDispatchManager = pushDispatchManager;
	}
	public void setGatherOrderManager(GatherOrderManager gatherOrderManager) {
		this.gatherOrderManager = gatherOrderManager;
	}

	public void setDispatchOrnaManager(DispatchOrnaManager dispatchOrnaManager) {
		this.dispatchOrnaManager = dispatchOrnaManager;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> conditon = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		Pager pager = gatherOrderManager.getGatherOrderPageData(conditon);
		BaseForm form = new BaseForm();
		form.setPager(pager);
		form.setCondition(conditon);
		return new ModelAndView("push/pushDispatch_list", "form", form);
	
	}
	public ModelAndView dispatch(HttpServletRequest req, HttpServletResponse res) {
		String conditionId = CommonUtil.getParameterEmpty(req, "conditionId");
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		String gstatus = CommonUtil.getParameterEmpty(req, "gstatus");
		PushDispatchForm form = new PushDispatchForm();
		form.setGheadid(gheadid);
		form.setGstatus(gstatus);
		form.setConditionId(conditionId);
		GatherOrderHead ghead = gatherOrderManager.getGatherOrderHead(gheadid);
		form.setGhead(ghead);
		DispatchCondition disCondition = null;
		if(!"-1".equals(conditionId)){
			disCondition = dispatchOrnaManager.getDispatchConditionById(conditionId);
		}
		form.setDisCondition(disCondition);
		return new ModelAndView("push/pushDispatch_edit", "form", form);
	}
	/**
	 * 选择分配店弹出框
	 */
	public ModelAndView selectDispatchOrg(HttpServletRequest req, HttpServletResponse res) {
		String conditionId = CommonUtil.getParameterEmpty(req, "conditionId");
		String inOrgId = CommonUtil.getParameterEmpty(req, "inOrgId");
		//获取配货参数下的所有分配店
		List<Org> dispatchOrgList = pushDispatchManager.getDispatchOrg(conditionId);
		DispatchOrnaForm form = new DispatchOrnaForm();
		form.setDispatchOrgList(dispatchOrgList);
		form.setInOrgId(inOrgId);
		return new ModelAndView("stock/win/dispatchOrna_selectOrg", "form", form);
	}
	/**
	 * 根据配货参数id,获取正配货临时分页数据,并显示
	 */
	public ModelAndView dispatching(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "conditionId", "orgId");
		DispatchOrnaForm form = new DispatchOrnaForm();
		Pager pager = null;
		if(StringUtil.isNotEmpty(condition.get("conditionId"))){
			pager = pushDispatchManager.getDispatchingPageData(condition);
		}else{
			pager = new Pager();
		}
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("push/pushDispatch_dispatching", "form", form);
	}
	/**
	 * 显示已配货
	 */
	public ModelAndView dispatched(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "gheadid", "orgId", "itemClassId", "ornaClassId");
		DispatchOrnaForm form = new DispatchOrnaForm();
		Pager pager = null;
		if(StringUtil.isNotEmpty(condition.get("gheadid"))){
			pager = pushDispatchManager.getDispatchedPageData(condition);
		}else{
			pager = new Pager();
		}
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("push/pushDispatch_dispatched", "form", form);
	}
	/**
	 * 显示总部总单
	 */
	public ModelAndView hqline(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "gheadid", "itemClassId", "ornaClassId", "showUnfullOnly", "showUnperfectMatch");
		DispatchOrnaForm form = new DispatchOrnaForm();
		Pager pager = null;
		if(StringUtil.isNotEmpty(condition.get("gheadid"))){
			pager = pushDispatchManager.getHqlinePageData2(condition);
		}else{
			pager = new Pager();
		}
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("push/pushDispatch_hqline", "form", form);
	}

    /**
     * 显示已分配饰品
     */
    public ModelAndView showDispatched(HttpServletRequest req, HttpServletResponse res) {
        String params[] = new String[]{ "headid", "itemClassId", "ornaClassId", "analysisId", "styleItemClassId", "styleId"};
        Map<String, String> condition = CommonUtil.getConditionForPage2(req, params);
        DispatchOrnaForm form = new DispatchOrnaForm();
        Pager pager = pushDispatchManager.getShowDispatchedPageData(condition);
        form.setPager(pager);
        form.setCondition(condition);
        return new ModelAndView("push/win/dispatchOrna_showDispatched", "form", form);
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
			pager = pushDispatchManager.getDispatchSubTempOrnaPageData(condition);
		}else{
			pager = new Pager();
		}
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("push/win/pushDispatch_showSubOrna", "form", form);
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
		Pager pager = pushDispatchManager.getDispatchSubTempOrnaPageData(condition);
		condition.put("start", startTmp);
		condition.put("limit", limitTmp);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("推式减库饰品");
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
	 * 导出总部总单行数据
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView exportHqline(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "gheadid", "itemClassId", "ornaClassId", "showUnfullOnly", "showUnperfectMatch");
		List<Map> dataList = pushDispatchManager.getHqlineForExcel(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("总部总单");
		excelData.setDataList(dataList);//手工重新查找数据
		excelData.addColumnWidth(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "analysisName", "qualityId", "bracketColorId", "unitId", "sizeName", "gradeName", "numOrder", "dispatchedNum", "smallGraph"}
		, new Integer[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 3, 2, 2, 4});

		//添加列标题
		excelData.setColumnTitle(new String[]{"大类", "小类", "款式大类", "款式中类", "款式小类", "款式", "分析范围", "商品材质", "托架颜色", "单位", "尺寸", "品质", "数量", "已配数", "图片"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "analysisName", "qualityId", "bracketcolorId", "unitId", "sizeName", "gradeName", "numOrder",  "dispatchedNum","smallGraph"});
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
	/**
	 * 显示可变更列表
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView changeDispatching(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> map = CommonUtil.getConditionForPage2(req, "conditionId", "ornaLogId", "orgId", "cancelFlag");
		DispatchOrnaForm form = new DispatchOrnaForm();
		Pager pager = new Pager();
		if(StringUtil.isNotEmpty(map.get("conditionId")) && StringUtil.isNotEmpty(map.get("ornaLogId"))){
			DispatchOrnaLog dispatchOrnaLog = dispatchOrnaManager.getDispatchOrnaLogById(map.get("ornaLogId"));
			DispatchOrnaInfo info = dispatchOrnaManager.getDispatchOrnaInfo(dispatchOrnaLog.getOrnaCode(), true);
			String inOrgId = "-1";
			List<PushDispatchTemp> dispatchTempList = pushDispatchManager.getMatchDispatchTempList(info, inOrgId, map.get("conditionId"));
			
			pager.setLimit(Integer.valueOf(StringUtil.isNotEmpty(map.get("limit"))?map.get("limit"):Global.PAGE_DEFAULT_LIMIT_WIN));
			pager.setStart(Integer.valueOf(StringUtil.isNotEmpty(map.get("start"))?map.get("start"):Global.PAGE_DEFAULT_START));
			pager.setTotalCount(dispatchTempList.size());
			
			List<Object> pageData = new ArrayList<Object>();
			for(int i = pager.getStart() -1;i<pager.getLimit() + pager.getStart() -1 && i<pager.getTotalCount();i++){
				pageData.add(dispatchTempList.get(i));
			}
			pager.setPageData(pageData);
		}
		form.setPager(pager);
		form.setCondition(map);
		return new ModelAndView("push/pushDispatch_changeDispatching", "form", form);
	}
	/**
	 * 未生成调拨单查询
	 */
	public ModelAndView unmovebill(HttpServletRequest req, HttpServletResponse res) {
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		List<DispatchMoveBill> list = pushDispatchManager.getUnmovebill(gheadid);
		DispatchOrnaForm form = new DispatchOrnaForm();
		form.setMoveBillList(list);
		return new ModelAndView("push/pushDispatch_unmovebill", "form", form);
	}
	/**
	 * 生成调拨单查询
	 */
	public ModelAndView movebill(HttpServletRequest req, HttpServletResponse res) {
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		List<DispatchMoveBill> list = pushDispatchManager.getMovebill(gheadid);
		DispatchOrnaForm form = new DispatchOrnaForm();
		form.setMoveBillList(list);
		return new ModelAndView("push/pushDispatch_movebill", "form", form);
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
			List<DispatchRealCost> list = dispatchOrnaManager.getDispatchRealCost(gheadid, orgIds, itemClassIds, ornaClassIds, GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);
			form.setRealCostList(list);
		}
		return new ModelAndView("push/pushDispatch_realCost", "form", form);
	}
	/**
	 * 查看真实总成本
	 */
	public ModelAndView viewStatistics(HttpServletRequest req, HttpServletResponse res) {
		String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
		String dispatchingFlag = CommonUtil.getParameterEmpty(req, "dispatchingFlag");
		DispatchOrnaForm form = new DispatchOrnaForm();
		if(StringUtil.isNotEmpty(gheadid)){
			List<DispatchStatistics> list = pushDispatchManager.getDispatchStatistics(gheadid, dispatchingFlag);
			form.setStatisticsList(list);
		}
		return new ModelAndView("push/pushDispatch_statistics", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> conditon = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		Pager pager = gatherOrderManager.getGatherOrderPageData(conditon);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("推式配货列表");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"总单编号", "区域", "周期类型", "商品类别", "购物类型", "创建人", "创建时间", "状态", "执行功能", "已配货"});
		excelData.setColumnName(new String[]{"billNo", "regionName", "cycleTypeName", "articleTypeNames", "ordertype", "createId", "createDate", "status", "dotype", "dispatchFlag"});
//		excelData.addColumnWidth(new String[]{"startDate", "endDate", "orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "turnoverNum", "createId", "createDate", "updateId", "updateDate", "status"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"createId"}, new CacheSingletonIntf[]{UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "ordertype", "dotype", "dispatchFlag"}, new String[]{DictConstant.BILL_STATUS, DictConstant.ORDER_TYPE, DictConstant.DISPATCH_DO_TYPE, DictConstant.YES_OR_NO});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}

    public ModelAndView dispatchingExcel(HttpServletRequest req, HttpServletResponse res) {
        Map<String, String> condition = CommonUtil.getConditionForPage2(req, "conditionId", "orgId");
        Pager pager = new Pager();
        if(StringUtil.isNotEmpty(condition.get("conditionId"))){
            pager = pushDispatchManager.getDispatchingPageData(condition);
        }
        ExcelData excelData = new ExcelData();//excel数据对象
        excelData.setTitle("推式配货-正配货");
        excelData.addColumnWidth(new String[]{"orgId",  "ornaCode", "ornaDsc", "itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "analysisName", "qualityId", "bracketColorId", "unitId", "sizeName", "gradeName", "numOrder", "smallGraph"}
                , new Integer[]{4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 3, 2, 4});
        excelData.setPager(pager);//直接利用分页pager对象
        excelData.setColumnTitle(new String[]{"分配店", "饰品编码", "饰品名称", "大类", "小类", "分析范围", "款式大类", "款式中类", "款式小类",
                "款式", "色级", "净度", "品质", "尺寸", "商品材质", "托架颜色", "计量单位", "图片"});
        excelData.setColumnName(new String[]{"orgId", "ornaCode", "ornaDsc", "itemClassId", "ornaClassId", "analysisName", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId",
                "styleName", "colorGradeId", "cleanId", "colorGradeGradeId", "sizeName", "qualityId", "bracketcolorId", "unitId", "smallGraph"});
        //设置对应的缓存数据
        excelData.addSingletonDisplayColumns(new String[]{"createId", "orgId", "itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId",
                "qualityId", "unitId"},
                new CacheSingletonIntf[]{UserCache.getInstance(), OrgCache.getInstance(), ItemClassCache.getInstance(), OrnaClassCache.getInstance(), StyleItemClassCache.getInstance(), StyleMiddleClassCache.getInstance(), StyleOrnaClassCache.getInstance(),
                QualityCache.getInstance(), UnitCache.getInstance()});
        //设置对应的数据字典
        excelData.addDictDisplayColumns(new String[]{"status", "ordertype", "dotype", "dispatchFlag",
                "colorGradeId", "cleanId" , "colorGradeGradeId", "bracketcolorId"},
                new String[]{DictConstant.BILL_STATUS, DictConstant.ORDER_TYPE, DictConstant.DISPATCH_DO_TYPE, DictConstant.YES_OR_NO,
                DictConstant.DIA_COLORGRADE, DictConstant.DIA_CLEAN, DictConstant.GRADE_LEVEL, DictConstant.BRACKET_COLOR});

        ExportPurchaseBillToExcelUtil util = new ExportPurchaseBillToExcelUtil();
        util.setPicBasePath(CommonUtil.getPicBasePath2());
        util.setExcelData(excelData);
        util.export(res);
        return null;
    }


    public ModelAndView dispatchedExcel(HttpServletRequest req, HttpServletResponse res) {
        Map<String, String> condition = CommonUtil.getConditionForPage2(req, "gheadid", "orgId", "itemClassId", "ornaClassId");
        DispatchOrnaForm form = new DispatchOrnaForm();
        Pager pager = new Pager();
        if(StringUtil.isNotEmpty(condition.get("gheadid"))){
            pager = pushDispatchManager.getDispatchedPageData(condition);
        }
        ExcelData excelData = new ExcelData();//excel数据对象
        excelData.setTitle("推式配货-已配货");
        excelData.addColumnWidth(new String[]{"orgId",  "ornaCode", "ornaDsc", "itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "analysisName", "qualityId", "bracketColorId", "unitId", "sizeName", "gradeName", "numOrder", "smallGraph"}
                , new Integer[]{4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 3, 2, 4});
        excelData.setPager(pager);//直接利用分页pager对象
        excelData.setColumnTitle(new String[]{"分配店", "饰品编码", "饰品名称", "大类", "小类", "分析范围", "款式大类", "款式中类", "款式小类",
                "款式", "色级", "净度", "品质", "尺寸", "商品材质", "托架颜色", "计量单位", "图片"});
        excelData.setColumnName(new String[]{"orgId", "ornaCode", "ornaDsc", "itemClassId", "ornaClassId", "analysisName", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId",
                "styleName", "colorGradeId", "cleanId", "colorGradeGradeId", "sizeName", "qualityId", "bracketcolorId", "unitId", "smallGraph"});
        //设置对应的缓存数据
        excelData.addSingletonDisplayColumns(new String[]{"createId", "orgId", "itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId",
                "qualityId", "unitId"},
                new CacheSingletonIntf[]{UserCache.getInstance(), OrgCache.getInstance(), ItemClassCache.getInstance(), OrnaClassCache.getInstance(), StyleItemClassCache.getInstance(), StyleMiddleClassCache.getInstance(), StyleOrnaClassCache.getInstance(),
                        QualityCache.getInstance(), UnitCache.getInstance()});
        //设置对应的数据字典
        excelData.addDictDisplayColumns(new String[]{"status", "ordertype", "dotype", "dispatchFlag",
                "colorGradeId", "cleanId" , "colorGradeGradeId", "bracketcolorId"},
                new String[]{DictConstant.BILL_STATUS, DictConstant.ORDER_TYPE, DictConstant.DISPATCH_DO_TYPE, DictConstant.YES_OR_NO,
                        DictConstant.DIA_COLORGRADE, DictConstant.DIA_CLEAN, DictConstant.GRADE_LEVEL, DictConstant.BRACKET_COLOR});

        ExportPurchaseBillToExcelUtil util = new ExportPurchaseBillToExcelUtil();
        util.setPicBasePath(CommonUtil.getPicBasePath2());
        util.setExcelData(excelData);
        util.export(res);
        return null;
    }

    /**
     * 未生成调拨单查询
     */
    public ModelAndView unmovebillExcel(HttpServletRequest req, HttpServletResponse res) {
        String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
        Pager pager = new Pager();
        List<DispatchMoveBill> list = pushDispatchManager.getUnmovebill(gheadid);
        pager.setPageData(list);

        ExcelData excelData = new ExcelData();//excel数据对象
        excelData.setTitle("推式配货-未生成调拨单");
        excelData.addColumnWidth(new String[]{"orgId","itemClassId", "ornaClassId"}  , new Integer[]{4, 3, 3});
        excelData.setPager(pager);//直接利用分页pager对象
        excelData.setColumnTitle(new String[]{"分配店", "大类", "小类", "件数"});
        excelData.setColumnName(new String[]{"orgId",  "itemClassId", "ornaClassId", "sumCount"});
        //设置对应的缓存数据
        excelData.addSingletonDisplayColumns(new String[]{"orgId", "itemClassId", "ornaClassId"},
                new CacheSingletonIntf[]{OrgCache.getInstance(), ItemClassCache.getInstance(), OrnaClassCache.getInstance()});
        //设置对应的数据字典
        excelData.addDictDisplayColumns(new String[]{}, new String[]{});
        ExportExcelUtil util = new ExportExcelUtil();
        util.setExcelData(excelData);
        util.export(res);
        return null;
    }
    /**
     * 生成调拨单查询
     */
    public ModelAndView movebillExcel(HttpServletRequest req, HttpServletResponse res) {
        String gheadid = CommonUtil.getParameterEmpty(req, "gheadid");
        Pager pager = new Pager();
        List<DispatchMoveBill> list = pushDispatchManager.getMovebill(gheadid);
        pager.setPageData(list);

        ExcelData excelData = new ExcelData();//excel数据对象
        excelData.setTitle("推式配货-调拨单查询");
        excelData.addColumnWidth(new String[]{"orgId","moveNo", "sumCount", "createId", "createDate", "status"}  , new Integer[]{4, 3, 2, 2, 3, 2});
        excelData.setPager(pager);//直接利用分页pager对象
        excelData.setColumnTitle(new String[]{"分配店", "调拨单号", "件数", "创建人", "创建日期", "状态"});
        excelData.setColumnName(new String[]{"orgId","moveNo", "sumCount", "createId", "createDate", "status"});
        //设置对应的缓存数据
        excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId"},
                new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance()});
        //设置对应的数据字典
        excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
        ExportExcelUtil util = new ExportExcelUtil();
        util.setExcelData(excelData);
        util.export(res);
        return null;
    }
}
