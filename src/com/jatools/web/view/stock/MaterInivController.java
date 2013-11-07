package com.jatools.web.view.stock;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.calc.PriceHeadManager;
import com.jatools.manager.stock.MaterInivManager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.form.pur.SelectOrnaCodeForm;
import com.jatools.web.form.stock.MaterInivForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

public class MaterInivController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(MaterInivController.class);
	private MaterInivManager materInivManager;
	private static final String PRINT_VM = "common/printLabelJson";
	
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setMaterInivManager(MaterInivManager materInivManager) {
		this.materInivManager = materInivManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		HttpSession session = req.getSession();
		PriceHeadForm form = new PriceHeadForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"wlCode","caclType","selectLabel","handoverBillId","stateId",
				"itemClassId","ornaClassId","verdorName","calcpriceNo","beginTime","endTime","createId","beginWeight","endWeight");
		condition.put("inivOrg", CommonUtil.getSessionOrgId(session));
		form.setCondition(condition);
		
		Pager pager = materInivManager.getMaterInivPageData(condition);
		form.setPager(pager);
		return new ModelAndView("stock/materIniv_list", "form", form);
	}

	public ModelAndView toAddMaterIniv(HttpServletRequest req,
			HttpServletResponse res){
		MaterInivForm form = new MaterInivForm();
		String inivType = CommonUtil.getParameterEmpty(req, "inivType");
		form.setInivType(inivType);
		if("1".equals(inivType))
			return new ModelAndView("stock/materInivGold_edit","form",form);
		else if("2".equals(inivType))
			return new ModelAndView("stock/materInivDiamond_edit","form",form);
		else if("3".equals(inivType))
			return new ModelAndView("stock/materInivLuodan_edit","form",form);
		else 
			return new ModelAndView("stock/materIniv_list","form",form);		
	}
	
	public ModelAndView toEditMaterIniv(HttpServletRequest req,
			HttpServletResponse res){
		MaterInivForm form = new MaterInivForm();
		String inivType = CommonUtil.getParameterEmpty(req, "inivType");
		String id = CommonUtil.getParameterEmpty(req, "id");
		MaterIniv mater = materInivManager.getMaterInivById(id);
		form.setIniv(mater);
		form.setInivType(inivType);
		
		if("1".equals(inivType))
			return new ModelAndView("stock/materInivGold_edit","form",form);
		else if("2".equals(inivType))
			return new ModelAndView("stock/materInivDiamond_edit","form",form);
		else if("3".equals(inivType))
			return new ModelAndView("stock/materInivLuodan_edit","form",form);
		else 
			return new ModelAndView("stock/materIniv_list","form",form);		
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {

		HttpSession session = req.getSession();
		PriceHeadForm form = new PriceHeadForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"wlCode","caclType","selectLabel","handoverBillId","stateId",
				"itemClassId","ornaClassId","verdorName","calcpriceNo","beginTime","endTime","createId","beginWeight","endWeight");
		condition.put("inivOrg", CommonUtil.getSessionOrgId(session));
		form.setCondition(condition);
		
		Pager pager = materInivManager.getMaterInivPageData(condition);
		form.setPager(pager);
		
		ExcelData excelData = new ExcelData();
		excelData.setTitle("饰品入库");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createdate", "updatedate"}, new Integer[]{4, 4
				});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据状态", "交接单号", "饰品编码", "供应商","基础价","饰品总重", "单据类型", "组织", "大类", "小类", "核价单号",  "材质","是否双标签", "创建时间","创建人","修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"state", "srcBillNo", "ornaCode","venderId","basicPrice","allQty", "allowInivType", "inivOrg", "itemClassId", "ornaClassId", "calcpriceNo", "qualityId", "isDblLabel","createdate","createuserid", "updatedate", "updateid"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createuserid","inivOrg","updateId","itemClassId","ornaClassId","venderId","qualityId"},
					new CacheSingletonIntf[]{UserCache.getInstance(),OrgCache.getInstance(), UserCache.getInstance(), ItemClassCache.getInstance(),OrnaClassCache.getInstance(),VendorCache.getInstance(),QualityCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"allowInivType", "state","isDblLabel"},
					new String[]{"inivtype", DictConstant.BILL_STATUS,"yesorno"});
		
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
		List<Tag> tag = materInivManager.printLabels(ids);
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
	
	
	public ModelAndView selectOldOrnaCode(HttpServletRequest req, HttpServletResponse res) {
		String ornaCode = CommonUtil.getParameterEmpty(req, "ornaCode");
		String headId = CommonUtil.getParameterEmpty(req, "headId");
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req, "selectedValues");
		SelectOrnaCodeForm form = new SelectOrnaCodeForm("true".equals(multiFlag), selectedValues);
		List<MaterIniv> list = priceHeadManager.getOldOrnaCodeByHeadId(headId, ornaCode);
		Map<String, String> dataMap = new LinkedHashMap<String, String>();
		if(CollectionUtils.isNotEmpty(list)){
			for(MaterIniv iniv : list){			
				dataMap.put(iniv.getOrnaCode(), iniv.getOrnaCode());
			}
		}
		form.setHeadId(headId);
		form.setOrnaCode(ornaCode);
		form.setDataMap(dataMap);
		return new ModelAndView("stock/win/SelectOrnaCode", "form", form);
	}
	
	private PriceHeadManager priceHeadManager;


	public void setPriceHeadManager(PriceHeadManager priceHeadManager) {
		this.priceHeadManager = priceHeadManager;
	}
	
}
