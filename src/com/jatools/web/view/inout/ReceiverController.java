package com.jatools.web.view.inout;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.inout.ReceiveHeadManager;
import com.jatools.manager.inout.ReceiveLineManager;
import com.jatools.vo.inout.ReceiveHead;
import com.jatools.vo.inout.ReceiveLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.inout.ReceiveForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class ReceiverController extends BaseMultiActionController {

	private Logger logger = Logger.getLogger(ReceiverController.class);
	
	private static final String LIST_VM = "inout/receiveHead_list";
	private static final String EDIT_VM = "inout/receiveHead_edit";
	private static final String HAND_VM = "inout/receiveHandover";
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "status", "startDate", "endDate", "dotype", "createId", "handoverNo"};
	private ReceiveHeadManager receiveHeadManager;
	private ReceiveLineManager receiveLineManager;
	
	public void setReceiveHeadManager(ReceiveHeadManager receiveHeadManager) {
		this.receiveHeadManager = receiveHeadManager;
	}
	public void setReceiveLineManager(ReceiveLineManager receiveLineManager) {
		this.receiveLineManager = receiveLineManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		ReceiveForm form = new ReceiveForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = receiveHeadManager.getReceiveHeadData(condition);
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
	
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		ReceiveForm form = new ReceiveForm();
		try {
			if(StringUtil.isNotBlank(billid)){
				ReceiveHead receiveHead = this.receiveHeadManager.getReceiveHead(billid);
				List<ReceiveLine> lines = this.receiveLineManager.getReceiveLineList(billid);
				form.setReceiveHead(receiveHead);
				form.setLines(lines);
			} else {
				form.setSuccessfulFlag(Boolean.FALSE);
				form.setMessage("加载失败");
			}
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取数据出错");
		}
		return new ModelAndView(EDIT_VM, "form", form);
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		ReceiveForm form = new ReceiveForm();
		try {
			if(StringUtil.isNotBlank(billid)){
				this.receiveHeadManager.deleteReceiveHead(billid, CommonUtil.getSessionUserId(req));
				
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
				condition.put("orgId", CommonUtil.getSessionOrgId(req));
				Pager pager = receiveHeadManager.getReceiveHeadData(condition);
				form.setPager(pager);
				form.setCondition(condition);
			} else {
				form.setSuccessfulFlag(Boolean.FALSE);
				form.setMessage("加载失败");
			}
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取数据出错");
		}
		return new ModelAndView(LIST_VM, "form", form);
	}
	
	public ModelAndView doHandover(HttpServletRequest req, HttpServletResponse res) {
		ReceiveForm form = new ReceiveForm();
		try {
			String params[] = {"billno", "orgId", "verdorId", "startDate", "endDate", "srcBillNo"};
			Map<String, String> condition = CommonUtil.getConditionForPage(req, params);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = receiveHeadManager.getReceiveHandoverList(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(HAND_VM, "form", form);
	}
	
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = receiveHeadManager.getReceiveHeadData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("收票单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate", "vendorId"}, new Integer[]{4, 3, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "组织", "供应商",  "单据日期", "发票号码", "发票金额", "创建时间", "创建人", "修改时间", "修改人", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "orgId", "vendorId", "dodate", "invoiceNo", "invoiceMoney", "createDate", "createId", "updateDate", "updateId", "status"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId", "vendorId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), VendorCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"},
					new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
