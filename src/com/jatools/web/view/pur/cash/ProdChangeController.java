package com.jatools.web.view.pur.cash;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.pur.cash.CashProdChangeManager;
import com.jatools.vo.pur.cash.CashProdChange;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.pur.cash.CashHeadForm;
import com.jatools.web.form.pur.cash.ProdChangeForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class ProdChangeController extends BaseMultiActionController {

	private Logger logger = Logger.getLogger(ProdChangeController.class);
	
	private static final String LIST_VM = "pur/cash/prodChange_list";
	private static final String EDIT_VM = "pur/cash/prodChange_edit";
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "status", "startDate", "endDate", "dotype", "createId", "billType"};
	private CashProdChangeManager cashProdChangeManager;
	
	public void setCashProdChangeManager(CashProdChangeManager cashProdChangeManager) {
		this.cashProdChangeManager = cashProdChangeManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		CashHeadForm form = new CashHeadForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = cashProdChangeManager.getCashProdChangeData(condition);
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
		String chaId = CommonUtil.getParameterNull(req, "chaId");
		String billno = CommonUtil.getParameterNull(req, "billno");
		ProdChangeForm form = new ProdChangeForm();
		try {
			if(StringUtil.isNotBlank(chaId)){
				CashProdChange prodchange = this.cashProdChangeManager.getCashProdChange(chaId);
				form.setCashProdChange(prodchange);
			} else if(StringUtil.isNotBlank(billno)){
                CashProdChange prodchange = this.cashProdChangeManager.getCashProdChangeByBillno(billno);
                form.setCashProdChange(prodchange);
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
		String chaId = CommonUtil.getParameterNull(req, "chaId");
		ProdChangeForm form = new ProdChangeForm();
		try {
			if(StringUtil.isNotBlank(chaId)){
				this.cashProdChangeManager.deleteCashProdChange(chaId);
				
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
				condition.put("orgId", CommonUtil.getSessionOrgId(req));
				Pager pager = cashProdChangeManager.getCashProdChangeData(condition);
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
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = cashProdChangeManager.getCashProdChangeData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("货品台账单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate", "voderId"}, new Integer[]{4, 3, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "采购组织", "供应商", "单据状态", "单据日期", "大类", "重量", "业务类型", "单据类型", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"billNo", "orgId", "voderId", "status", "billDate", "itemClassId", "chaNums", "dotype", "billType", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId", "voderId", "itemClassId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), VendorCache.getInstance(), ItemClassCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "dotype", "billType"},
					new String[]{DictConstant.BILL_STATUS, DictConstant.CHANGE_DO_TYPE, DictConstant.PROD_DOTYPE});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
