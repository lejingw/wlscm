package com.jatools.web.view.pur.cash;

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
import com.jatools.manager.out.CashProdAccountManager;
import com.jatools.vo.out.CashProdAccount;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.pur.cash.MoneyAccountForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class ProdAccountController extends BaseMultiActionController {

	private Logger logger = Logger.getLogger(ProdAccountController.class);
	
	private static final String LIST_VM = "pur/cash/prodAccount_list";
	private static final String CASH_LIST_VM = "pur/cash/prodAccountCashWin";
	
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "articleId", "ornaCode", "itemClassId", "status", "startDate", "endDate", "dotype", "isChecked", "createId"};
	private CashProdAccountManager cashProdAccountManager;
	
	public void setCashProdAccountManager(CashProdAccountManager cashProdAccountManager) {
		this.cashProdAccountManager = cashProdAccountManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		MoneyAccountForm form = new MoneyAccountForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = cashProdAccountManager.getCashProdAccountData(condition);
			beforeView(pager);
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
		Pager pager = cashProdAccountManager.getCashProdAccountData(condition);
		beforeView(pager);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("货品台账单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate", "voderId"}, new Integer[]{4, 3, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"来源单据编号", "来源业务类型", "采购组织", "供应商", "饰品编码", "原料类型", "大类", "单据时间", "数量量", "已核销数量", "未核销数量" , "核销完成", "状态", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"billNo", "dotype", "orgId", "voderId", "memo", "materialTypeName", "itemClassId", "billDate", "billNums", "cashNums", "noNums", "isCheck", "status", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId", "voderId", "itemClassId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), VendorCache.getInstance(), ItemClassCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "yesorno", "dotype"},
					new String[]{DictConstant.BILL_STATUS, DictConstant.YES_OR_NO, DictConstant.PROD_DOTYPE});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
	public ModelAndView doFindCashList(HttpServletRequest req, HttpServletResponse res) {
		String prId = CommonUtil.getParameterEmpty(req, "prId");
		MoneyAccountForm form = new MoneyAccountForm();
		try {
			if(StringUtil.isNotBlank(prId)){
				List<CashProdAccount> cashList = this.cashProdAccountManager.getCashListByPrid(prId);
				form.setCashList(cashList);
			}
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取结算列表失败");
		}
		
		return new ModelAndView(CASH_LIST_VM, "form", form);
	}
	
	private void beforeView(Pager pager){
		if(null != pager && CollectionUtils.isNotEmpty(pager.getPageData())){
			for(Object obj : pager.getPageData()){
				CashProdAccount account = (CashProdAccount)obj;
				if(StringUtil.isNotBlank(account.getBillNums())){
					account.setBillNums(Double.valueOf(account.getBillNums())+"");
				} else {
					account.setBillNums("0");
				}
				if(StringUtil.isNotBlank(account.getCashNums())){
					account.setCashNums(Double.valueOf(account.getCashNums())+"");
				} else {
					account.setCashNums("0");
				}
				if(StringUtil.isNotBlank(account.getNoNums())){
					account.setNoNums(Double.valueOf(account.getNoNums())+"");
				} else {
					account.setNoNums("0");
				}
			}
		}
	}
}
