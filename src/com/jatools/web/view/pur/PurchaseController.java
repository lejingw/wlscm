package com.jatools.web.view.pur;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.pur.PurchaseManager;
import com.jatools.vo.pur.PurchaseHead;
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
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.pur.PurchaseForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class PurchaseController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(PurchaseController.class);
	private String orderList;
	private PurchaseManager purchaseManager;

	public void setPurchaseManager(PurchaseManager purchaseManager) {
		this.purchaseManager = purchaseManager;
	}

	public String getSessionKey(){
		return super.getSessionKey() + orderList;
	}
 	
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		if(StringUtil.isNotEmpty(CommonUtil.getParameterNull(req, "purGatherHeadId"))){
			orderList = "2";
		}else{
			orderList = "1";
		}
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "purGatherHeadId");
		PurchaseForm form = new PurchaseForm();
		form.setCondition(condition);
		Pager pager = purchaseManager.getPurchasePageData(condition);
		form.setPager(pager);
		return new ModelAndView("pur/purchase_list", "form", form);
	}
	
	public ModelAndView toView(HttpServletRequest req, HttpServletResponse res) {
		String headid = CommonUtil.getParameterNull(req, "headid");
		if(StringUtil.isEmpty(headid)){
			logger.error("不能获取采购单id");
			return new ModelAndView(new RedirectView("pur/purchase_list"));
		}
		PurchaseForm form = new PurchaseForm();
		PurchaseHead head = purchaseManager.getPurchaseHead(headid);
		form.setHead(head);
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "itemClassId", "ornaClassId", "analysisId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleId", "purGatherHeadId");
		Pager pager = purchaseManager.getPurchaseLinePageData(headid, condition);
//		setNumCancelFromSession(pager, req.getSession());
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("pur/purchase_view", "form", form);
	}
//	private void setNumCancelFromSession(Pager pager, HttpSession session) {
//		Object obj = session.getAttribute(PurConstant.NUM_CANCEL_SESSION_KEY);
//		if(null != obj && obj instanceof List){
//			List<PurchaseLineSession> set = (List<PurchaseLineSession>) obj;
//			List<Object> list = pager.getPageData();
//			for(Object data : list){
//				PurGatherLine line = (PurGatherLine)data;
//				for(PurchaseLineSession pls : set){
//					if(line.getLineid().equals(pls.getGlineid())){
//						line.setNumCurrentDispatch(pls.getNumCurrentDispatch());
//					}
//				}
//			}
//		}
//	}
	
	/**
	 * 到货数量登记、不合格数量登记
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView arriveEnter(HttpServletRequest req, HttpServletResponse res) {
		String headid = CommonUtil.getParameterNull(req, "headid");
		String lineid = CommonUtil.getParameterNull(req, "lineid");
		String enterType = CommonUtil.getParameterNull(req, "enterType");
		Map<String, String> condition = CommonUtil.getConditionForPage2(req, "headid", "lineid", "enterType");
		
		PurchaseForm form = new PurchaseForm();
		PurchaseHead head = purchaseManager.getPurchaseHead(headid);
		form.setHead(head);
		Pager pager = purchaseManager.getPurchaseLineEntryByTypePageData(lineid, enterType, condition);
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("pur/win/purchase_enter", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		if(StringUtil.isNotEmpty(CommonUtil.getParameterNull(req, "purGatherHeadId"))){
			orderList = "2";
		}else{
			orderList = "1";
		}
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "purGatherHeadId");
		Pager pager = purchaseManager.getPurchasePageData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("采购单");
//		excelData.setDataList(dataList);//手工重新查找数据
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate", "purEmpids", "vendorId", "orgId"}, new Integer[]{4, 4, 4, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"采购单编号", "来源单据", "来源单据号", "单据类型", "业务类型", "供应商名称", "采购组织", "采购员", "是否保留", "采购总数", "创建人", "创建时间", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "srcBillCode", "srcBillNo", "billType", "bizType", "vendorId", "orgId", "purEmpids", "usedFlag", "sumNum", "createId", "createDate", "status"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"vendorId", "purEmpids", "orgId", "createId"},
				new CacheSingletonIntf[]{VendorCache.getInstance(), UserCache.getInstance(), OrgCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"srcBillCode", "billType", "bizType", "status", "usedFlag"},
				new String[]{DictConstant.BILL_CODE, DictConstant.PUR_BILL_TYPE, DictConstant.PUR_BIZ_TYPE, DictConstant.BILL_STATUS, DictConstant.YES_OR_NO});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
	public ModelAndView contentToExcel(HttpServletRequest req, HttpServletResponse res) {
		String headid = CommonUtil.getParameterEmpty(req, "headid");
		PurchaseHead head = purchaseManager.getPurchaseHead(headid);
		List<Map> dataList = purchaseManager.getPurchaseLineForExcel(headid);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("采购单");
		excelData.setDataList(dataList);//手工重新查找数据
		excelData.addColumnWidth(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "styleNo", "analysisName", "qualityId", "bracketcolorId", "unitId", "sizeName", "gradeName", "numOrder", "usedFlag", "smallGraph"}
		, new Integer[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 3, 2, 2, 4});

		//添加列标题
		excelData.setColumnTitle(new String[]{"大类", "小类", "款式大类", "款式中类", "款式小类", "款式", "工厂款号", "分析范围", "商品材质", "托架颜色", "单位", "尺寸", "品质", "采购数量", "是否保留", "图片"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "styleItemClassId", "styleMiddleClassId", "styleOrnaClassId", "styleName", "styleNo", "analysisName", "qualityId", "bracketcolorId", "unitId", "sizeName", "gradeName", "numOrder", "usedFlag", "smallGraph"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(
				new String[] { "itemClassId", "ornaClassId",
						"styleItemClassId", "styleMiddleClassId", "styleOrnaClassId",
						"qualityId", "unitId" },
				new CacheSingletonIntf[] { ItemClassCache.getInstance(), OrnaClassCache.getInstance(),
						StyleItemClassCache.getInstance(), StyleMiddleClassCache.getInstance(), StyleOrnaClassCache.getInstance(),
						QualityCache.getInstance(), UnitCache.getInstance() });
		excelData.addDictDisplayColumns(new String[]{"bracketcolorId", "usedFlag"}, new String[]{DictConstant.BRACKET_COLOR, DictConstant.YES_OR_NO});
		
		ExportPurchaseBillToExcelUtil util = new ExportPurchaseBillToExcelUtil();
		util.setPicBasePath(CommonUtil.getPicBasePath2());
		util.setHead(head);
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
}