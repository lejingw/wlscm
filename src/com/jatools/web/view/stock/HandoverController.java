package com.jatools.web.view.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.constant.ParameterConstant;
import com.jatools.common.constant.PurConstant;
import com.jatools.manager.pur.HandoverChildManager;
import com.jatools.manager.pur.HandoverManager;
import com.jatools.manager.stock.ProcChangeHeadManager;
import com.jatools.manager.stock.ProcExitHeadManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.HandoverInStock;
import com.jatools.vo.pur.HandoverLine;
import com.jatools.vo.stock.ProcChangeHead;
import com.jatools.vo.stock.ProcExitHead;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.pur.HandoverForm;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;

public class HandoverController extends BaseHandoverController {
	private Logger logger = Logger.getLogger(HandoverController.class);
	
	private static final String LIST_VM = "stock/handover_list";
	private static final String EDIT_VM = "stock/handover_edit";
	private static final String EDIT_LINE_VM = "pur/win/other_handover_line";
	private static final String INSTCOK_VM = "pur/win/handover_instock";
	private static final String LINE_REC_VM = "stock/handover_line_rec";
	private static final String CHILD_REC_VM = "pur/win/handover_child_history";
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "status", "startDate", "endDate", "createId", "dotype"};
	
	private HandoverManager handoverManager;
	private ProcChangeHeadManager procChangeHeadManager;
	private CommonManager commonManager;
	private ProcExitHeadManager procExitHeadManager;
	private HandoverChildManager handoverChildManager;
	
	public void setHandoverChildManager(HandoverChildManager handoverChildManager) {
		this.handoverChildManager = handoverChildManager;
	}
	public void setProcExitHeadManager(ProcExitHeadManager procExitHeadManager) {
		this.procExitHeadManager = procExitHeadManager;
	}
	public void setHandoverManager(HandoverManager handoverManager) {
		this.handoverManager = handoverManager;
	}
	public void setProcChangeHeadManager(ProcChangeHeadManager procChangeHeadManager) {
		this.procChangeHeadManager = procChangeHeadManager;
	}
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		HandoverForm form = new HandoverForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = handoverManager.getOtherHandoverHeadData(condition);
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

	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		HandoverForm form = new HandoverForm();
		
		if(null != billid){
			HandoverHead head =  handoverManager.getHandoverHead(billid);
			List<HandoverLine> lines =handoverManager.getHandoverLineList(billid);
			beforeVeiew(head);
			beforeView(lines);
			form.setHandoverHead(head);
			form.setLines(lines);
			return new ModelAndView(EDIT_VM,"form",form);
		}else{
			return new ModelAndView(EDIT_VM,"form",form);
		}
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		HandoverForm form = new HandoverForm();
		try {
			if(StringUtil.isNotBlank(billid)){
				this.handoverManager.deleteHandoverHead(billid, CommonUtil.getSessionUserId(req));
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
			} else {
				form.setSuccessfulFlag(false);
				form.setMessage("删除失败");
			}
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = handoverManager.getOtherHandoverHeadData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("删除失败");
		}
		return new ModelAndView(LIST_VM, "form", form);
	}
	
	public ModelAndView miniHandover(HttpServletRequest req, HttpServletResponse res) {
		String srcBillid = CommonUtil.getParameterNull(req, "billid");
		String billCode = CommonUtil.getParameterNull(req, "billCode");
		HandoverForm form = new HandoverForm();
		form.setMini(Boolean.TRUE);
		if(null != srcBillid){
			HandoverHead head =  handoverManager.getHandoverHeadBySrcBillId(srcBillid, billCode);
			List<HandoverLine> lines = new ArrayList<HandoverLine>();
			if(null == head){
				head = createHandover(srcBillid, req);
			}
			lines =handoverManager.getHandoverLineList(head.getBillid());
			beforeVeiew(head);
			beforeView(lines);
			form.setHandoverHead(head);
			form.setLines(lines);
			return new ModelAndView(EDIT_VM,"form",form);
		}else{
			return new ModelAndView(EDIT_VM,"form",form);
		}
	}
	
	
	private HandoverHead createHandover(String srcBillId, HttpServletRequest req){
		String billType = CommonUtil.getParameterNull(req, "_b_t");
		String userId = CommonUtil.getSessionUserId(req);
		// TODO : 1、交接单头行表
		HandoverHead handoverHead = new HandoverHead();// 头表
		if(StringUtil.isNotBlank(billType) && "exit".equalsIgnoreCase(billType)){
			ProcExitHead head = this.procExitHeadManager.getProcExitHead(srcBillId);
			handoverHead.setSourceId(head.getBillid());// 来源单据id
			handoverHead.setSourceNo(head.getBillno());// 来源单据单号
			handoverHead.setSourceType(head.getBillCode());// 来源单据类型
			handoverHead.setOrgId(head.getOrgId());// 组织id
			handoverHead.setVerdorId(head.getVendorId());
		} else {
			ProcChangeHead procChangeHead = this.procChangeHeadManager.getProcChangeHead(srcBillId);
			handoverHead.setSourceId(procChangeHead.getBillid());// 来源单据id
			handoverHead.setSourceNo(procChangeHead.getBillno());// 来源单据单号
			handoverHead.setSourceType(procChangeHead.getBillCode());// 来源单据类型
			handoverHead.setOrgId(procChangeHead.getOrgId());// 组织id
			String vendorId = BdCommon.getParameterValue(ParameterConstant.CHANGE_VENDOR_ID);
			handoverHead.setVerdorId(vendorId);
		}
		
		String billno = commonManager.getBillno(GlobalConstant.BILL_CODE_JIAOJIEDAN);
		handoverHead.setBillno(billno);
		handoverHead.setStatus(DictConstant.BILL_STATUS_SAVE);
		handoverHead.setDodate(DateUtil.getCurrentDate10());
		handoverHead.setDotype(DictConstant.BILL_JJ_ZYZH);
		handoverHead.setCreateId(userId);
		handoverHead.setCreateDate(DateUtil.getCurrentDate18());
		handoverHead.setUpdateDate(DateUtil.getCurrentDate18());
		handoverHead.setUpdateId(userId);
		this.handoverManager.saveHandoverHead(handoverHead);
		if(StringUtil.isNotBlank(billType) && "exit".equalsIgnoreCase(billType)){
			ProcExitHead head = this.procExitHeadManager.getProcExitHead(srcBillId);
			// 创建 交接单行 信息和孙表信息
			this.handoverManager.insertLineByExit(head.getBillid(), handoverHead.getBillid(), userId);
			this.handoverChildManager.insertChildByExit(head.getBillid(), handoverHead.getBillid(), userId);
		}
		return handoverHead;
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = handoverManager.getOtherHandoverHeadData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("其他交接单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate", "verdorId"}, new Integer[]{4, 3, 5});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "来源单号", "来源类型", "组织", "业务类型", "业务时间", "供应商", "创建时间", "创建人", "修改时间", "修改人", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "sourceNo", "sourceType", "orgId",  "dotype", "dodate", "verdorId", "createDate", "createId", "updateDate", "updateId", "status"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId", "verdorId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), VendorCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"sourceType", "status", "dotype"},
					new String[]{ DictConstant.BILL_CODE, DictConstant.BILL_STATUS, DictConstant.PUR_BIZ_TYPE});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
	/**
	 * 入库统计
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView instockList(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		HandoverForm form = new HandoverForm();
		
		if(StringUtil.isNotBlank(billid)){
			List<HandoverInStock> stockList = handoverManager.getOtherInStockList(billid);
			for(HandoverInStock stock: stockList){
				if(stock.getNoNum()==null)stock.setNoNum(0d);
				if(stock.getNum()==null)stock.setNum(0d);
				if(stock.getStockNum()==null)stock.setStockNum(0d);
				if(stock.getStockWeight()==null)stock.setStockWeight(0d);
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
	
	public ModelAndView toEditLine(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		HandoverForm form = new HandoverForm();
		
		if(null != billid){
			List<HandoverLine> lines = handoverManager.getHandoverLineList(billid);
			beforeView(lines);
			form.setLines(lines);
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
