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
import com.jatools.manager.inout.SendHeadManager;
import com.jatools.manager.inout.SendLineManager;
import com.jatools.vo.inout.SendHead;
import com.jatools.vo.inout.SendLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.inout.SendForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class SendController extends BaseMultiActionController {

	private Logger logger = Logger.getLogger(SendController.class);
	
	private static final String LIST_VM = "inout/sendHead_list";
	private static final String EDIT_VM = "inout/sendHead_edit";
	private static final String OUT_VM = "inout/sendOutVendor";
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "status", "startDate", "endDate", "dotype", "createId"};
	private SendHeadManager sendHeadManager;
	private SendLineManager sendLineManager;
	
	public void setSendHeadManager(SendHeadManager sendHeadManager) {
		this.sendHeadManager = sendHeadManager;
	}

	public void setSendLineManager(SendLineManager sendLineManager) {
		this.sendLineManager = sendLineManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		SendForm form = new SendForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = sendHeadManager.getSendHeadData(condition);
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
		SendForm form = new SendForm();
		try {
			if(StringUtil.isNotBlank(billid)){
				SendHead sendHead = this.sendHeadManager.getSendHead(billid);
				List<SendLine> lines = this.sendLineManager.getSendLineList(billid);
				form.setSendHead(sendHead);
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
		SendForm form = new SendForm();
		try {
			if(StringUtil.isNotBlank(billid)){
				this.sendHeadManager.deleteSendHead(billid, CommonUtil.getSessionUserId(req));
				
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
				condition.put("orgId", CommonUtil.getSessionOrgId(req));
				Pager pager = sendHeadManager.getSendHeadData(condition);
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
	
	
	public ModelAndView doOutVendor(HttpServletRequest req, HttpServletResponse res) {
		SendForm form = new SendForm();
		try {
			String params[] = {"billno", "orgId", "verdorId", "startDate", "endDate"};
			Map<String, String> condition = CommonUtil.getConditionForPage(req, params);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = sendHeadManager.getOutVendorListData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(OUT_VM, "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = sendHeadManager.getSendHeadData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("出票单");
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
