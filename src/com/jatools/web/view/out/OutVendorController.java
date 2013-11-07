package com.jatools.web.view.out;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CheckUtil;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.out.OutVendorManager;
import com.jatools.vo.out.OutVendorHead;
import com.jatools.vo.out.OutVendorLine;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.MaterNoActive;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.form.out.OutBackForm;
import com.jatools.web.form.out.OutVendorForm;
import com.jatools.web.form.sys.AuthToken;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class OutVendorController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(this.getClass());
	private OutVendorManager outVendorManager;
	private String sessionOrder;

	public void setOutVendorManager(OutVendorManager outVendorManager) {
		this.outVendorManager = outVendorManager;
	}

	public String getSessionKey(){
		return this.getClass().getName() + sessionOrder;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {return null;}
	private ModelAndView toList(HttpServletRequest req,String doType,String url){
		sessionOrder = doType;
		OutVendorForm form = new OutVendorForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"billno","status","stockId","startDate","endDate","showAll");
		condition.put("doType", doType);
		condition.put("userId",CommonUtil.getSessionUserId(req));//加载当前用户的数据
		Pager pager = outVendorManager.getVendorHeadPage(condition);
		form.setPager(pager);
		form.setShowAll(condition.get("showAll"));
		form.setCondition(condition);
		return new ModelAndView(url, "form", form);
	}
	private ModelAndView toReportList(HttpServletRequest req,String type,String url){
		sessionOrder = type;
		OutVendorForm form = new OutVendorForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"billno","status","stockId","startDate","endDate","showAll");
		condition.put("type", type);
		condition.put("userId",CommonUtil.getSessionUserId(req));//加载当前用户的数据
		Pager pager = null;
		if("1".equals(type))
			pager = outVendorManager.getReportSaleVendorHeadPage(condition);
		else if("2".equals(type))
			pager = outVendorManager.getReportOutVendorHeadPage(condition);
		else
			pager = outVendorManager.getReportSendVendorHeadPage(condition);
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView(url, "form", form);
	}
	private ModelAndView toAdd(HttpServletRequest req,String url){
		OutVendorForm form = new OutVendorForm();
		String materialType= CommonUtil.getParameterNull(req, "materialType");
		form.getHead().setOrgId(CommonUtil.getSessionOrgId(req));
		form.getHead().setMaterialType(materialType);
		form.getHead().setStatus("1");
		form.getHead().setItemClassId(outVendorManager.getItemClassByMaterial(materialType));
		return new ModelAndView(url, "form", form);
	}
	private ModelAndView toEdit(HttpServletRequest req,String url,String cancelUrl){
		OutVendorForm form = new OutVendorForm();
		String headId = CommonUtil.getParameterNull(req, "headid");
		String billno = CommonUtil.getParameterNull(req, "billno");
		if(StringUtil.isNotBlank(headId)){
			try {
				OutVendorHead head =outVendorManager.getVendorHead(headId);
				head.setItemClassId(outVendorManager.getItemClassByMaterial(head.getMaterialType()));
				List<OutVendorLine> list =outVendorManager.getVendorLines(headId);
				form.setHead(head);
				form.setList(list);
			} catch (Exception e) {
				logger.error(e);
				form.setSuccessfulFlag(false);
				form.setMessage("获取单据数据失败");
				return new ModelAndView(cancelUrl, "form", form);
			}
		} else if(StringUtil.isNotBlank(billno)) {
            try {
                OutVendorHead head =outVendorManager.getVendorHeadByBillno(billno);
                head.setItemClassId(outVendorManager.getItemClassByMaterial(head.getMaterialType()));
                List<OutVendorLine> list =outVendorManager.getVendorLines(head.getHeadId());
                form.setHead(head);
                form.setList(list);
            } catch (Exception e) {
                logger.error(e);
                form.setSuccessfulFlag(false);
                form.setMessage("获取单据数据失败");
                return new ModelAndView(cancelUrl, "form", form);
            }
        }
		return new ModelAndView(url, "form", form);
	}

	public ModelAndView exportExcelLine(HttpServletRequest req, HttpServletResponse res) {

		OutVendorForm form = new OutVendorForm();
		String headId = CommonUtil.getParameterNull(req, "headId");
		String doType=req.getParameter("dotype");
		sessionOrder = doType;
		String title=getBillTypeBydoType(Integer.parseInt(doType));
		
		Pager pager = new Pager();
		pager.setPageData((List)outVendorManager.getVendorLines(headId));
		form.setPager(pager);
		
		ExcelData excelData = new ExcelData();
		excelData.setTitle(title);
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{}, new Integer[]{});//宽度默认值为2
														
		//添加列标题
		excelData.setColumnTitle(new String[]{"大类", "小类", "款式", "分析范围","饰品编码","饰品名称",
				"计量单位", "现有量", "总重量", "粒数", "销售金额",  "主石重量","配石重量"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "styleName","alaysisCode","ornaCode",
				"ornaDsc", "unitId", "nowQty", "allQty", "grains", "posMoney", "mainWeight", "partWeight"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"itemClassId","ornaClassId","unitId"},
					new CacheSingletonIntf[]{ItemClassCache.getInstance(),OrnaClassCache.getInstance(), UnitCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{},
					new String[]{});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	public ModelAndView selectMaterialType(HttpServletRequest req, HttpServletResponse res){
		return new ModelAndView("out/selectMaterialType");
	}
	///列表 
	public ModelAndView toPubSaleList(HttpServletRequest req, HttpServletResponse res) {
		return toList(req, "0", "out/pubsale_list");
	}///列表 
	public ModelAndView toPubSaleReportList(HttpServletRequest req, HttpServletResponse res) {
		String type = CommonUtil.getParameterNull(req, "type");
		return toReportList(req, type, "out/pubsale_report_list");
	}
	public ModelAndView toPSaleList(HttpServletRequest req, HttpServletResponse res) {
		return toList(req, "1", "out/psale_list");
	}
	public ModelAndView toEntrustoutList(HttpServletRequest req, HttpServletResponse res) {
		return toList(req, "2", "out/entrustout_list");
	}
	/////新增页面
	public ModelAndView toAddPubsale(HttpServletRequest req, HttpServletResponse res) {
		return toAdd(req,"out/pubsale_edit");
	}
	public ModelAndView toAddPsale(HttpServletRequest req, HttpServletResponse res) {
		return toAdd(req,"out/psale_edit");
	}
	public ModelAndView toAddEntrustout(HttpServletRequest req, HttpServletResponse res) {
		return toAdd(req,"out/entrustout_edit");
	}
	
	//修改页面
	public ModelAndView toEditPubsale(HttpServletRequest req, HttpServletResponse res) {
		return toEdit(req, "out/pubsale_edit", "out/pubsale_list");
	}
	public ModelAndView toEditPsale(HttpServletRequest req, HttpServletResponse res) {
		return toEdit(req, "out/psale_edit", "out/psale_list");
	}
	public ModelAndView toEditEntrustout(HttpServletRequest req, HttpServletResponse res) {
		return toEdit(req, "out/entrustout_edit", "out/entrustout_list");
	}
	private ModelAndView toPrintEdit(HttpServletRequest req,String url,String cancelUrl){
		OutVendorForm form = new OutVendorForm();
		String headId = CommonUtil.getParameterNull(req, "headid");
		if(null != headId){
			try {
				OutVendorHead head =outVendorManager.getVendorHead(headId);
				head.setItemClassId(outVendorManager.getItemClassByMaterial(head.getMaterialType()));
				if("8".equals(head.getStatus())){
					form.setPrintListNo(this.outVendorManager.getPrintLineByNoMater(head.getBillid()));
					if(null!=form.getPrintListNo()){
						for (int i = 0; i < form.getPrintListNo().size(); i++) {
							MaterNoActive m = form.getPrintListNo().get(i); 
							m.setAllQty(""+Double.parseDouble(m.getAllQty()));
						}
					}
				}else{
					form.setPrintList(this.outVendorManager.getPrintLineByMater(head.getBillid()));
					if(null!=form.getPrintList()){
						for (int i = 0; i < form.getPrintList().size(); i++) {
							MaterActive m = form.getPrintList().get(i); 
							m.setAllQty(""+Double.parseDouble(m.getAllQty()));
						}
					}
				}
				form.setHead(head);
			} catch (Exception e) {
				logger.error(e);
				form.setSuccessfulFlag(false);
				form.setMessage("获取单据数据失败");
				return new ModelAndView(cancelUrl, "form", form);
			}
		}
		return new ModelAndView(url, "form", form);
	}
	public ModelAndView toPrintEntrustout(HttpServletRequest req, HttpServletResponse res) {
		return toPrintEdit(req, "out/entrustout_edit_print", "out/entrustout_edit_print");
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		String doType=req.getParameter("dotype");
		sessionOrder = doType;
		String title=getBillTypeBydoType(Integer.parseInt(doType));
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"billno","status","startDate","endDate","showAll");
		condition.put("doType", doType);
		condition.put("userId",CommonUtil.getSessionUserId(req));//加载当前用户的数据
		Pager pager = outVendorManager.getVendorHeadPage(condition);
		return OutBackController.baseExportExcel(req, res, title, pager);
	}
	
	private String getBillTypeBydoType(int dotype){
		String billType="";
		switch(dotype){
		case 0:
			billType="对公销售单";
			break;
		case 1:
			billType="代销退货单";
			break;
		case 2:
			billType="委外发料单";
			break;
		}
		return billType;
	}
}
