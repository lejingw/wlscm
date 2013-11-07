package com.jatools.web.view.calc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jatools.vo.calc.PriceHead;
import com.jatools.web.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.calc.PriceHeadManager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.calc.PriceStoneLine;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.util.ExcelData;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.form.pur.HandoverForm;
import com.jatools.web.form.pur.PurchaseForm;
import com.jatools.web.form.stock.MaterInivForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

public class PriceHeadController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(PriceHeadController.class);
	private PriceHeadManager priceHeadManager;
	private static final String PRINT_VM = "common/printLabelJson";
	
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public void setPriceHeadManager(PriceHeadManager priceHeadManager) {
		this.priceHeadManager = priceHeadManager;
	}
	
	public ModelAndView toSelectStoneNum(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		PriceHeadForm form = new PriceHeadForm();
		String ornaCode = req.getParameter("ornaCode");
		String verdorId = req.getParameter("verdorId");
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		condition.put("ornaCode", ornaCode);
		condition.put("verdorId", verdorId);
		
		List<CashProdAccount> listc = null;
		List<PriceStoneLine> lists = null;
		if(ornaCode!=null&&!"".equals(ornaCode)){
			listc = priceHeadManager.getStoneNum(condition);
			lists = priceHeadManager.getPriceStoneNum(condition);
		}
		form.setCondition(condition);
		form.setStoneLine(lists);
		form.setCashLine(listc);
		return new ModelAndView("calc/priceStoneNum_list","form",form);
	}
	/**
	 * 加载分页数据
	 */
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		PriceHeadForm form = new PriceHeadForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"wlCode","caclType","handoverNo","stateId",
				"itemClassId","ornaClassId","verdorName","calcpriceNo","beginTime","endTime","selectLabel","createId","basicPrice");
		condition.put("orgId", CommonUtil.getSessionOrgId(session));
		form.setCondition(condition);
		
		Pager pager = priceHeadManager.getPriceHeadPageData(condition);
		form.setPager(pager);
		return new ModelAndView("calc/priceHead_list", "form", form);
	}
	/**
	 * 新建单据
	 * 
	 */
	public ModelAndView toAddPriceHead(HttpServletRequest req, HttpServletResponse res) {
		PriceHeadForm form = new PriceHeadForm();
		String caclType = CommonUtil.getParameterEmpty(req, "caclType");
		form.setCaclType(caclType);//单据类型
		if("1".equals(caclType))
			return new ModelAndView("calc/priceHeadGold_edit", "form", form);
		else if("2".equals(caclType))
			return new ModelAndView("calc/priceHeadDiamond_edit", "form", form);
		else if("3".equals(caclType))
			return new ModelAndView("calc/priceHeadOther_edit", "form", form);
		else if("4".equals(caclType))
			return new ModelAndView("calc/priceHeadSpecial_edit", "form", form);
		else 
			return new ModelAndView("", "form", form);
	}
	/**
	 * 编辑方法
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditPriceHead(HttpServletRequest req, HttpServletResponse res){
		String headid = CommonUtil.getParameterEmpty(req, "headid");
		String billno = CommonUtil.getParameterEmpty(req, "billno");
		String caclType = CommonUtil.getParameterEmpty(req, "caclType");
		PriceHeadForm form = new PriceHeadForm();
		if(StringUtil.isNotBlank(headid)){
			form.setHead(priceHeadManager.getPriceHead(headid));
			form.setStoneLine(priceHeadManager.getPriceStoneLineList(headid));
			form.setSbraLine(priceHeadManager.getPriceSbraLineList(headid));
			form.setAcsLine(priceHeadManager.getPriceAcsLineList(headid));
			form.setAccLine(priceHeadManager.getPriceAccLineList(headid));
			
			form.setCaclType(caclType);
		} else if(StringUtil.isNotBlank(billno)) {
            PriceHead head = priceHeadManager.getPriceHeadByNo(billno);
            form.setHead(head);
            form.setStoneLine(priceHeadManager.getPriceStoneLineList(head.getId()));
            form.setSbraLine(priceHeadManager.getPriceSbraLineList(head.getId()));
            form.setAcsLine(priceHeadManager.getPriceAcsLineList(head.getId()));
            form.setAccLine(priceHeadManager.getPriceAccLineList(head.getId()));

            form.setCaclType(head.getCaclType());
            caclType = head.getCaclType();
        }
		if("1".equals(caclType))
			return new ModelAndView("calc/priceHeadGold_edit", "form", form);
		else if("2".equals(caclType))
			return new ModelAndView("calc/priceHeadDiamond_edit", "form", form);
		else if("3".equals(caclType))
			return new ModelAndView("calc/priceHeadOther_edit", "form", form);
		else if("4".equals(caclType))
			return new ModelAndView("calc/priceHeadSpecial_edit", "form", form);
		else 
			return new ModelAndView("", "form", form);
	}
	/**
	 * 核价交接单列表
	 * @param ininvTypeId 交接单行表下大类的入库类型，状态为接收
	 * @return
	 */
	public ModelAndView getHandoerCalcWindow(HttpServletRequest req, HttpServletResponse res){
		HandoverForm form = new HandoverForm();
		Map<String, String> condition = CommonUtil.getConditionForPage(req,"ininvTypeId","handoverBillId","type",
				"verdorName","beginTime","endTime");
		condition.put("orgId", CommonUtil.getSessionOrgId(req.getSession()));
		Pager pager = priceHeadManager.getHandoerCalcListPage(condition);
		form.setPager(pager);
		form.setCondition(condition);
		
		return new ModelAndView("stock/handoverInivCalcWindow", "form", form);
	}
	/**
	 * 入库选核价分页窗
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView loadPriceByInivPage(HttpServletRequest req, HttpServletResponse res){
		PriceHeadForm form = new PriceHeadForm();
		Map<String, String> condition = CommonUtil.getConditionForPage(req,"handoverBillId","inivType","itemClassId","ornaClassId",
				"id","no","beginTime","endTime","wlCode","basicPrice");

		Pager pager = priceHeadManager.loadPriceByInivPage(condition);
		form.setPager(pager);
		form.setCondition(condition);
		
		return new ModelAndView("stock/priceHeadInivCalcWindow", "form", form);
	}
	
	/**
	 * 定做单列表
	 * @param ininvTypeId 交接单行表下大类的入库类型，状态为接收
	 * @return
	 */
	public ModelAndView getPurchaseHeadInivWindow(HttpServletRequest req, HttpServletResponse res){
		Map<String, String> condition = CommonUtil.getConditionForPage(req,"billid","billno");
		List<PurchaseHead> list = priceHeadManager.getDZcode(condition);
		PriceHeadForm form = new PriceHeadForm();
		form.setPur(list);
		condition.put("length", list.size()+"");
		form.setCondition(condition);
		return new ModelAndView("stock/purchaseHeadInivWind", "form", form);
	}
	public ModelAndView getDZWindow(HttpServletRequest req, HttpServletResponse res){
		
		return new ModelAndView("calc/dzWind", "form", null);
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		PriceHeadForm form = new PriceHeadForm();
		HttpSession session = req.getSession();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"wlCode","caclType","handoverNo","stateId",
				"itemClassId","ornaClassId","verdorName","calcpriceNo","beginTime","endTime","selectLabel","createId","basicPrice");
		condition.put("orgId", CommonUtil.getSessionOrgId(session));
		form.setCondition(condition);
		
		Pager pager = priceHeadManager.getPriceHeadPageData(condition);
		form.setPager(pager);

		ExcelData excelData = new ExcelData();
		excelData.setTitle("核价单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createdate", "updatedate","wlCode","handoverName","no"}, new Integer[]{4, 4, 3, 3,3});//宽度默认值为2
		
		//添加列标题  
		excelData.setColumnTitle(new String[]{"饰品编码", "交接单号","核价单号","单据类型", "创建时间","创建人","修改时间",
				"大类", "小类", "基础价","材质", "状态","供应商", "饰品总重", "是否双标签"});
		//添加列name
		excelData.setColumnName(new String[]{"wlCode", "handoverName", "no","caclType", "createDate", "createUserId", "updateDate",
				"itemClassId", "ornaClassId","basicPrice","qualityId", "state", "vender","allWeight","isDoubleLabel"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createUserId","itemClassId","ornaClassId","vender","qualityId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), ItemClassCache.getInstance(),OrnaClassCache.getInstance(),VendorCache.getInstance(),QualityCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"caclType", "state","isDoubleLabel"},
					new String[]{"cacltype", DictConstant.BILL_STATUS,"yesorno"});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	/**
	 * 标签打印
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView printLabels(HttpServletRequest req,
			HttpServletResponse res){
		String id = CommonUtil.getParameterEmpty(req, "billid");
		String pt = CommonUtil.getParameterEmpty(req, "pt");
		String params = CommonUtil.getParameterEmpty(req, "params");
		Map<String, String> condition = new HashMap<String, String>();
		if(null==id)
			return null;
		String ids [] = id.split(",");
		List<Tag> tag = priceHeadManager.printLabels(ids);
		JsonConfig config = new JsonConfig();
		
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		for (int i = 0; i < ids.length; i++) {
			String str = JSONSerializer.toJSON(tag.get(i), config).toString();
			array.add(str);
		}
		obj.put("lines", array.toString());
		MaterInivForm form = new MaterInivForm();
		form.setPrintData(obj.toString());
		condition.put("pt", pt);
		condition.put("params", params);
		form.setCondition(condition);

		return new ModelAndView(PRINT_VM, "form", form);
	}
} 
