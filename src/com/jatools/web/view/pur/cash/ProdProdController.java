package com.jatools.web.view.pur.cash;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.out.CashProdAccountManager;
import com.jatools.manager.pur.cash.CashProdProdManager;
import com.jatools.vo.pur.cash.CashProdProd;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.pur.cash.CashHeadForm;
import com.jatools.web.form.pur.cash.MoneyAccountForm;
import com.jatools.web.form.pur.cash.ProdProdForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class ProdProdController extends BaseMultiActionController {

private Logger logger = Logger.getLogger(ProdProdController.class);
	
	private static final String LIST_VM = "pur/cash/prodProd_list";
	private static final String EDIT_VM = "pur/cash/prodProd_edit";
	private static final String SHOW_VM = "pur/cash/prodProdShowWin";
	
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "status", "startDate", "endDate", "dotype", "createId"};
	private CashProdProdManager cashProdProdManager;
	private CashProdAccountManager cashProdAccountManager;
	
	public void setCashProdAccountManager(CashProdAccountManager cashProdAccountManager) {
		this.cashProdAccountManager = cashProdAccountManager;
	}
	public void setCashProdProdManager(CashProdProdManager cashProdProdManager) {
		this.cashProdProdManager = cashProdProdManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		CashHeadForm form = new CashHeadForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = cashProdProdManager.getCashProdProdData(condition);
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
		ProdProdForm form = new ProdProdForm();
		try {
			if(StringUtil.isNotBlank(chaId)){
				CashProdProd prodProd = this.cashProdProdManager.getCashProdProd(chaId);
				form.setCashProdProd(prodProd);
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
		ProdProdForm form = new ProdProdForm();
		try {
			if(StringUtil.isNotBlank(chaId)){
				this.cashProdProdManager.deleteCashProdProd(chaId, CommonUtil.getSessionUserId(req));
				
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
				condition.put("orgId", CommonUtil.getSessionOrgId(req));
				Pager pager = cashProdProdManager.getCashProdProdData(condition);
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
	
	public ModelAndView showWin(HttpServletRequest req, HttpServletResponse res) {
		MoneyAccountForm form = new MoneyAccountForm();
		try {
			String params[] = {"verdorId", "startDate", "endDate", "dotype", "billno"};
			Map<String, String> condition = CommonUtil.getConditionForPage(req, params);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			condition.put("status", "2");
			condition.put("isChecked", "0");
			condition.put("isProdProd", "1");
			Pager pager = cashProdAccountManager.getCashProdAccountData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(SHOW_VM, "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = cashProdProdManager.getCashProdProdData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("料转料单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate", "voderId"}, new Integer[]{4, 3, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "采购组织", "供应商", "单据状态", "单据日期", "原类型", "原重量", "新类型", "新重量", "转换说明", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"billNo", "orgId", "voderId", "status", "billDate", "materialTypeNameOld", "weightOld", "materialTypeNameNew", "weightNew", "memo", "createDate", "createId", "updateDate", "updateId"});
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
	
}
