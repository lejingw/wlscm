package com.jatools.web.view.pur.cash;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.pur.cash.CashHeadManager;
import com.jatools.manager.pur.cash.CashLineManager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.pur.cash.CashHead;
import com.jatools.vo.pur.cash.CashLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.pur.cash.CashHeadForm;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class CashHeadController extends BaseMultiActionController {

	private Logger logger = Logger.getLogger(CashHeadController.class);
	
	private static final String LIST_VM = "pur/cash/cashHead_list";
	private static final String EDIT_VM = "pur/cash/cashHead_edit";
	private static final String MINI_VM = "pur/cash/cashMinWin";
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "status", "startDate", "endDate", "dotype", "createId"};
	
	private CashHeadManager cashHeadManager;
	private CashLineManager cashLineManager;
	

	public void setCashHeadManager(CashHeadManager cashHeadManager) {
		this.cashHeadManager = cashHeadManager;
	}
	public void setCashLineManager(CashLineManager cashLineManager) {
		this.cashLineManager = cashLineManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		CashHeadForm form = new CashHeadForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = cashHeadManager.getCashHeadData(condition);
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
		String cashId = CommonUtil.getParameterNull(req, "cashId");
		CashHeadForm form = new CashHeadForm();
		try {
			if(StringUtil.isNotBlank(cashId)){
				CashHead cashHead = this.cashHeadManager.getCashHead(cashId);
				List<CashLine> lines = this.cashLineManager.getCashLineList(cashId);
				form.setCashHead(cashHead);
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
		String cashId = CommonUtil.getParameterNull(req, "cashId");
		CashHeadForm form = new CashHeadForm();
		try {
			if(StringUtil.isNotBlank(cashId)){
				this.cashHeadManager.deleteCashHead(cashId, CommonUtil.getSessionUserId(req));
				
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
				condition.put("orgId", CommonUtil.getSessionOrgId(req));
				Pager pager = cashHeadManager.getCashHeadData(condition);
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
	
	public ModelAndView selectCash(HttpServletRequest req, HttpServletResponse res) {
		String params[] = {"is_src", "vendorId", "cashId", "data", "billType", "billno", "startDate", "endDate", "articleTypeId"};
		Map<String, String> condition = CommonUtil.getConditionForPage(req, params);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		CashHeadForm form = new CashHeadForm();
		try{
			Pager pager = this.cashHeadManager.getCashPagerList(condition);
			beforeView(pager);
			form.setPager(pager);
			form.setCondition(condition);
		}catch(Exception e){
			logger.error(e);
			form.setSuccessfulFlag(Boolean.FALSE);
			form.setMessage("获取单据数据失败");
		}
		return new ModelAndView(MINI_VM, "form", form);
	}
	
	public ModelAndView doNewCash(HttpServletRequest req, HttpServletResponse res){
		String prId = CommonUtil.getParameterEmpty(req, "prId");
		CashHeadForm form = new CashHeadForm();
		if(StringUtil.isNotBlank(prId)){
			CashProdAccount prodAccount = this.cashHeadManager.getProdAccountById(prId);
			if(prodAccount !=null){
				String userId = CommonUtil.getSessionUserId(req);
				String orgId = CommonUtil.getSessionOrgId(req);
				CashHead head = new CashHead();
				head.setVoderId(prodAccount.getVoderId());
				head.setDotype(prodAccount.getDotype());
				head.setStatus(DictConstant.BILL_STATUS_SAVE);
				head.setBillDate(DateUtil.getCurrentDate10());
				head.setCreateDate(DateUtil.getCurrentDate10());
				head.setOrgId(orgId);
				head.setCreateId(userId);
				
				CashLine line = new CashLine();
				line.setBillNo(prodAccount.getBillNo());
				line.setBillType(prodAccount.getDotype());
				line.setItemClassId(prodAccount.getItemClassId());
				if(null != prodAccount.getNoNums()){
					line.setLessNums(Double.valueOf(prodAccount.getNoNums()));
				}else {
					line.setLessNums(0d);
				}
				line.setPrId(prodAccount.getPrId());
				List<CashLine> lines = new ArrayList<CashLine>();
				lines.add(line);
				form.setCashHead(head);
				form.setLines(lines);
			}
		}
		return new ModelAndView(EDIT_VM, "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = cashHeadManager.getCashHeadData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("结算单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate", "voderId"}, new Integer[]{4, 3, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "采购组织", "状态", "供应商", "单据日期", "结算重量", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"billNo", "orgId", "status", "voderId", "billDate", "cashNums", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId", "voderId", "updateId"},
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
	
	private void beforeView(Pager pager){
		if(CollectionUtils.isNotEmpty(pager.getPageData())){
			for(Object obj : pager.getPageData()){
				CashProdAccount prod = (CashProdAccount)obj;
				if(StringUtil.isNotBlank(prod.getBillNums())){
					prod.setBillNums(StringUtil.formatDouble(prod.getBillNums(), 6));
				}
				if(StringUtil.isNotBlank(prod.getCashNums())){
					prod.setCashNums(StringUtil.formatDouble(prod.getCashNums(), 6));
				}
				if(StringUtil.isNotBlank(prod.getNoNums())){
					prod.setNoNums(StringUtil.formatDouble(prod.getNoNums(), 6));
				}
			}
		}
	}
}
