package com.jatools.web.view.pur.cash;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.out.CashMoneyAccountManager;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.pur.cash.MoneyAccountForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

public class MoneyAccountController extends BaseMultiActionController {

	private Logger logger = Logger.getLogger(MoneyAccountController.class);
	
	private static final String LIST_VM = "pur/cash/moneyAccount_list";
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "status", "startDate", "endDate", "dotype", "createId"};
	private CashMoneyAccountManager cashMoneyAccountManager;
	
	public void setCashMoneyAccountManager(CashMoneyAccountManager cashMoneyAccountManager) {
		this.cashMoneyAccountManager = cashMoneyAccountManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		MoneyAccountForm form = new MoneyAccountForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = cashMoneyAccountManager.getCashMoneyAccountData(condition);
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
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = cashMoneyAccountManager.getCashMoneyAccountData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("资金台账单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate", "voderId"}, new Integer[]{3, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"来源单据编号", "来源业务类型", "状态", "供应商", "付款金额", "余额" ,"创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"billNo", "dotype", "status", "voderId", "moneys", "lossMoney", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "voderId", "updateId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), VendorCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "dotype"},
					new String[]{DictConstant.BILL_STATUS, DictConstant.MONEY_DO_TYPE});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
