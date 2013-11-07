package com.jatools.web.view.pur;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.manager.pur.PurchaseCustManager;
import com.jatools.vo.pur.PurchaseCust;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.ParameterCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.pur.PurchaseCustForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class PurchaseCustController extends BaseMultiActionController {
	private String listKey = null;
	private PurchaseCustManager purchaseCustManager;

	public void setPurchaseCustManager(PurchaseCustManager purchaseCustManager) {
		this.purchaseCustManager = purchaseCustManager;
	}

	public String getSessionKey(){
		return super.getSessionKey() + listKey;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		String jmFlag = CommonUtil.getParameterEmpty(req, "jmFlag");
		if(StringUtil.isEmpty(jmFlag)){
			jmFlag = DictConstant.YES_OR_NO_NO;
		}
		listKey = "1"+jmFlag;
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		PurchaseCustForm form = new PurchaseCustForm();
		form.setCondition(condition);
		form.setJmFlag(jmFlag);
		Pager pager = purchaseCustManager.getPurchaseCustPageData(condition, jmFlag);
		form.setPager(pager);
		return new ModelAndView("pur/purchaseCust_list", "form", form);
	}
	/**
	 * 接收定做单列表
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView receiveList(HttpServletRequest req, HttpServletResponse res) {
		String jmFlag = CommonUtil.getParameterEmpty(req, "jmFlag");
		if(StringUtil.isEmpty(jmFlag)){
			jmFlag = DictConstant.YES_OR_NO_NO;
		}
		listKey = "2"+jmFlag;
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		PurchaseCustForm form = new PurchaseCustForm();
		form.setCondition(condition);
		form.setJmFlag(jmFlag);
		Pager pager = purchaseCustManager.getCustReceiveListPageData(condition, jmFlag, CommonUtil.getSessionUserId(req));
		form.setPager(pager);
		return new ModelAndView("pur/purchaseCust_receiveList", "form", form);
	}


	public ModelAndView toReview(HttpServletRequest req, HttpServletResponse res) {
		String custid = CommonUtil.getParameterNull(req, "custid");
		String jmFlag = CommonUtil.getParameterEmpty(req, "jmFlag");
		if(StringUtil.isEmpty(jmFlag)){
			jmFlag = DictConstant.YES_OR_NO_NO;
		}
		PurchaseCustForm form = new PurchaseCustForm();
		form.setJmFlag(jmFlag);
		PurchaseCust cust = purchaseCustManager.getPurchaseCust(custid, jmFlag);
		form.setCust(cust);
		List<PurchaseHead> vendorList = purchaseCustManager.getPurchaseCustVendorList(custid, jmFlag);
		form.setVendorList(vendorList);
		String basePath = ParameterCache.getInstance().getValue(ParameterConstant.UTIL_PURCHASE_CUST_STYLE_PIC_BASE_PATH);
		if(StringUtil.isEmpty(basePath))
			basePath = "";
		if(!StringUtil.isEmpty(cust.getBigGraph())){
			String tmp = cust.getBigGraph().split(",")[0];
			form.setStylePicPath(basePath + tmp);
		}
		return new ModelAndView("pur/purchaseCust_receiveView", "form", form);
	}
	
	public ModelAndView toView(HttpServletRequest req, HttpServletResponse res) {
		String custid = CommonUtil.getParameterNull(req, "custid");
		String jmFlag = CommonUtil.getParameterEmpty(req, "jmFlag");
		if(StringUtil.isEmpty(jmFlag)){
			jmFlag = DictConstant.YES_OR_NO_NO;
		}
		PurchaseCustForm form = new PurchaseCustForm();
		form.setJmFlag(jmFlag);
		PurchaseCust cust = purchaseCustManager.getPurchaseCust(custid, jmFlag);
		form.setCust(cust);
		List<PurchaseHead> vendorList = purchaseCustManager.getPurchaseCustVendorList(custid, jmFlag);
		form.setVendorList(vendorList);
		String basePath = ParameterCache.getInstance().getValue(ParameterConstant.UTIL_PURCHASE_CUST_STYLE_PIC_BASE_PATH);
		if(StringUtil.isEmpty(basePath))
			basePath = "";
		if(!StringUtil.isEmpty(cust.getBigGraph())){
			String tmp = cust.getBigGraph().split(",")[0];
			form.setStylePicPath(basePath + tmp);
		}
		return new ModelAndView("pur/purchaseCust_view", "form", form);
	}
	
	/**
	 * 订做单选择款式
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView selectStyleForCust(HttpServletRequest req, HttpServletResponse res) {
		String custid = CommonUtil.getParameterEmpty(req, "custid");
		String jmFlag = CommonUtil.getParameterEmpty(req, "jmFlag");
		if(StringUtil.isEmpty(jmFlag)){
			jmFlag = DictConstant.YES_OR_NO_NO;
		}
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		PurchaseCust cust = purchaseCustManager.getPurchaseCust(custid, jmFlag);
		PurchaseCustForm form = new PurchaseCustForm();
		form.setSelectedStyleId(selectedValues);
		form.setCust(cust);
		return new ModelAndView("pur/win/purchaseCust_SelectStyle", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		String jmFlag = CommonUtil.getParameterEmpty(req, "jmFlag");
		if(StringUtil.isEmpty(jmFlag)){
			jmFlag = DictConstant.YES_OR_NO_NO;
		}
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		Pager pager = purchaseCustManager.getPurchaseCustPageData(condition, jmFlag);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("定做生成采购单列表");
//		excelData.setDataList(dataList);//手工重新查找数据
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate", "updateDate", "orgId"}, new Integer[]{3, 3, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"定做单号", "组织", "大类", "小类", "预订数量", "要求到货时间", "创建人", "创建时间", "更新人", "更新时间", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "orgId", "itemClassId", "ornaClassId", "doomNum", "requestDate", "createId", "createDate", "updateId", "updateDate", "status"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "itemClassId", "ornaClassId", "createId", "updateId"},
				new CacheSingletonIntf[]{OrgCache.getInstance(), ItemClassCache.getInstance(), OrnaClassCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.PLAN_BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
}
