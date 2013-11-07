package com.jatools.web.view.report;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Global;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.report.UnsaleReportManager;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.report.UnsaleReportForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class UnsaleReportController extends BaseMultiActionController {
	
	private Logger logger = Logger.getLogger(UnsaleReportController.class);
	private static final String PARAMS[] ={"orgId", "orgName", "ornaCode", "stockId", "itemClassId", "ornaClassId",
		"styleName", "styleId", "styleItemClass", "styleMiddleClass", "styleOrnaClass", 
		"qualityId", "status", "themeId", "analysisId", "analysisName"};
	private static final String LIST_VM = "report/unsaleReport_list";
	
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		UnsaleReportForm form = new UnsaleReportForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPage(req, PARAMS);
			if(StringUtil.isBlank(condition.get("status"))){
				condition.put("status", "0");
			}
			if("".equals(CommonUtil.getParameterEmpty(req, "firstFlag"))){
				form.setPager(form.getPager());
			}else{
				Pager pager = this.unsaleReportManager.getChargePageData(condition);
				form.setPager(pager);
			}
			form.setCondition(condition);
		}catch(Exception e){
			logger.error(e);
		}
		return new ModelAndView(LIST_VM, "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		if(StringUtil.isBlank(condition.get("status"))){
			condition.put("status", "0");
		}
		Pager pager = this.unsaleReportManager.getChargePageData(condition);
		String fix = "";
		if("1".equals(condition.get("status"))){
			fix = "淘汰处理的";
		} else if("0".equals(condition.get("status"))){
			fix = "流转处理的";
		}
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle(fix + "商品清单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "toshopdate", "storagedate"}, new Integer[]{4, 3, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"组织", "饰品名称", "饰品编码", "大类", "小类", "款式", "分析范围", "材质", "仓库", "计量单位", "原料类型", "柜组", "基础价", "单位成本", "总成本", "销售金额", "特殊工费", "现有量", "总重量", "粒数", "主石重量", "配石重量", "色级", "净度", "入库时间", "到店时间", "在司库龄", "在店库龄", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"orgId", "ornaDsc", "ornaCode", "itemClassId", "ornaClassId", "stylename", "alaysisName", "qualityId", "stockId", "saleUnitId", "materialType", "groups", "basicPrice", "realUnitCost", "realTotalCost", "posAmount", "specialWorkPrice", "nowQty", "allQty", "stoneNowNum", "mainWeight", "partWeight", "mainGradeName", "cleanName", "storagedate", "toshopdate", "comAge", "shopAge", "state"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createuserid", "itemClassId", "ornaClassId", "qualityId", "saleUnitId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), ItemClassCache.getInstance(), OrnaClassCache.getInstance(), QualityCache.getInstance(), UnitCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"state", "stockId", "materialType"},
					new String[]{DictConstant.BILL_STATUS, DictConstant.INVCODE, DictConstant.MATERIAL_TYPE});
		excelData.addColumnType(new String[]{"basicPrice", "posAmount", "specialWorkPrice", "stoneNowNum", "partWeight", "mainWeight"}, 
				new Class[]{Double.class, Double.class, Double.class, Double.class, Double.class, Double.class});
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
	private UnsaleReportManager unsaleReportManager;
	public void setUnsaleReportManager(UnsaleReportManager unsaleReportManager) {
		this.unsaleReportManager = unsaleReportManager;
	}
}
