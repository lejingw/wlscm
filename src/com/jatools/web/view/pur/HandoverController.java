package com.jatools.web.view.pur;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.PurConstant;
import com.jatools.manager.pur.HandoverCashManager;
import com.jatools.manager.pur.HandoverManager;
import com.jatools.vo.pur.HandoverCash;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.HandoverInStock;
import com.jatools.vo.pur.HandoverLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.pur.HandoverForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.stock.BaseHandoverController;

public class HandoverController extends BaseHandoverController {
	private Logger logger = Logger.getLogger(HandoverController.class);

	private static final String LIST_VM = "pur/handover_list";
	private static final String LIST_REPORT_VM = "pur/handover_report_list";
	private static final String EDIT_VM = "pur/handover_edit";
	private static final String EDIT_LINE_VM = "pur/win/handover_line";
	private static final String INSTCOK_VM = "pur/win/handover_instock";
	private static final String PURCHASE_VM = "pur/win/handover_purchase";
	private static final String LINE_REC_VM = "pur/handover_line_rec";
	private static final String CHILD_REC_VM = "pur/win/handover_child_history";
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "status", "startDate", "endDate" , "createId", "isBill", "dotype"};
	private HandoverManager handoverManager;
	private HandoverCashManager handoverCashManager;
	
	public void setHandoverManager(HandoverManager handoverManager) {
		this.handoverManager = handoverManager;
	}
	public void setHandoverCashManager(HandoverCashManager handoverCashManager) {
		this.handoverCashManager = handoverCashManager;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		HandoverForm form = new HandoverForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = handoverManager.getHandoverHeadData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(LIST_VM, "form", form);
	}
	public ModelAndView reportList(HttpServletRequest req, HttpServletResponse res) {
		HandoverForm form = new HandoverForm();
		String type = CommonUtil.getParameterNull(req, "type");
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			condition.put("type", type);
			Pager pager = null;
			if("1".equals(type))
				pager = handoverManager.getReportHandHandoverHeadData(condition);
			else
				pager = handoverManager.getReportOutHandoverHeadData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(LIST_REPORT_VM, "form", form);
	}

	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		String billno = CommonUtil.getParameterNull(req, "billno");
		HandoverForm form = new HandoverForm();
		
		if(StringUtil.isNotBlank(billid)){
			HandoverHead head =  handoverManager.getHandoverHead(billid);
			List<HandoverLine> lines = handoverManager.getHandoverLineList(billid);
			List<HandoverCash> cashList = handoverCashManager.getHandoverCashList(billid);
			beforeVeiew(head);
			beforeView(lines);
			form.setHandoverHead(head);
			form.setLines(lines);
			form.setCashLines(cashList);
			return new ModelAndView(EDIT_VM,"form",form);
		} else if(StringUtil.isNotBlank(billno)){
            HandoverHead head =  handoverManager.getHandoverHeadByBillno(billno);
            List<HandoverLine> lines = handoverManager.getHandoverLineList(head.getBillid());
            List<HandoverCash> cashList = handoverCashManager.getHandoverCashList(head.getBillid());
            beforeVeiew(head);
            beforeView(lines);
            form.setHandoverHead(head);
            form.setLines(lines);
            form.setCashLines(cashList);
            return new ModelAndView(EDIT_VM,"form",form);
        } else{
			return new ModelAndView(EDIT_VM,"form",form);
		}
	}
	
	public ModelAndView deleteList(HttpServletRequest req, HttpServletResponse res) {
		String billids = CommonUtil.getParameterNull(req, "billids");
		HandoverForm form = new HandoverForm();
		
		try {
			if(StringUtil.isNotBlank(billids)){
				String billidArray[] = billids.split(";");
				for(String billid : billidArray){
					if(StringUtil.isNotBlank(billid)){// 删除数据
						this.handoverManager.deleteHandoverHead(billid, CommonUtil.getSessionUserId(req));
					}
				}
				// 重新查询
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
				condition.put("orgId", CommonUtil.getSessionOrgId(req));
				Pager pager = handoverManager.getHandoverHeadData(condition);
				form.setPager(pager);
				form.setCondition(condition);
			} else {
				form.setSuccessfulFlag(false);
				form.setMessage("尚未选择要删除的数据。");
			}
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(LIST_VM, "form", form);
	}
	
	public ModelAndView instockList(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		HandoverForm form = new HandoverForm();
		
		if(StringUtil.isNotBlank(billid)){
			List<HandoverInStock> stockList = handoverManager.getInStockList(billid);
			for(HandoverInStock stock: stockList){
				if(stock.getNoNum()==null)stock.setNoNum(0d);
				if(stock.getNum()==null)stock.setNum(0d);
				if(stock.getStockNum()==null)stock.setStockNum(0d);
				if(stock.getStockWeight()==null)stock.setStockWeight(0d);
				//if(stock.getStockGrain()==null)stock.setStockGrain(0d);
				if(PurConstant.HANDOVER_LINE_ITEMCLASSID.equals(stock.getItemClassId())){
					List<HandoverInStock> children = handoverManager.getOtherInStockListByYL(billid);
					stock.setChildren(children);
				}
			}
			form.setInstockList(stockList);
			return new ModelAndView(INSTCOK_VM,"form",form);
		}else{
			return new ModelAndView(INSTCOK_VM,"form",form);
		}
	}
	
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = handoverManager.getHandoverHeadData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("采购交接单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate", "verdorId"}, new Integer[]{4, 3, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "来源单号", "来源类型", "组织", "业务类型", "业务时间", "供应商", "是否结算", "是否收票", "总数量", "创建时间", "创建人", "修改时间", "修改人", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "sourceNo", "sourceType", "orgId", "dotype", "dodate", "verdorId", "isCheck", "isBill", "sumNum", "createDate", "createId", "updateDate", "updateId", "status"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId", "verdorId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), VendorCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"sourceType", "status", "dotype", "isCheck", "isBill"},
					new String[]{ DictConstant.BILL_CODE, DictConstant.BILL_STATUS, DictConstant.PUR_BIZ_TYPE, DictConstant.YES_OR_NO, DictConstant.YES_OR_NO});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
	
	public ModelAndView getPurchaseList(HttpServletRequest req, HttpServletResponse res){
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "verdorId", "billno", "startDate", "endDate", "dotype");
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		HandoverForm form = new HandoverForm();
		Pager pager = this.handoverManager.getPurchaseList(condition);
		form.setPager(pager);
		return new ModelAndView(PURCHASE_VM,"form",form);
	}
	
	
	public ModelAndView toEditLine(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		HandoverForm form = new HandoverForm();
		
		if(null != billid){
			//HandoverHead head =  handoverManager.getHandoverHead(billid);
			List<HandoverLine> lines = handoverManager.getHandoverLineList(billid);
			//List<HandoverCash> cashList = handoverCashManager.getHandoverCashList(billid);
			//beforeVeiew(head);
			beforeView(lines);
			//form.setHandoverHead(head);
			form.setLines(lines);
			//form.setCashLines(cashList);
			return new ModelAndView(EDIT_LINE_VM,"form",form);
		}else{
			return new ModelAndView(EDIT_LINE_VM,"form",form);
		}
	}
	
	public ModelAndView doSearchLineRec(HttpServletRequest req, HttpServletResponse res) {
		return this.doSearchLineRec(handoverManager, LINE_REC_VM, logger, req, res);
	}
	public ModelAndView doSearchChildRec(HttpServletRequest req, HttpServletResponse res) {
		return this.doSearchChildRec(handoverManager, CHILD_REC_VM, logger, req, res);
	}
}
