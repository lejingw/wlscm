package com.jatools.web.view.pur.cash;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.pur.cash.PayMoneyManager;
import com.jatools.vo.pur.cash.PayMoney;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.pur.HandoverForm;
import com.jatools.web.form.pur.cash.PayMoneyForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class PayMoneyController extends BaseMultiActionController {

	private Logger logger = Logger.getLogger(PayMoneyController.class);
	
	private static final String EDIT_VM = "pur/cash/payMoney_edit";
	private static final String LIST_VM = "pur/cash/payMoney_list";
	private static final String PARAMS[] = {"billno", "orgId", "verdorId", "status", "startDate", "endDate", "createId"};
	private PayMoneyManager payMoneyManager;
	public void setPayMoneyManager(PayMoneyManager payMoneyManager) {
		this.payMoneyManager = payMoneyManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		HandoverForm form = new HandoverForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = payMoneyManager.getPayMoneyData(condition);
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
		String payId = CommonUtil.getParameterNull(req, "payId");
		PayMoneyForm form = new PayMoneyForm();
		
		if(StringUtil.isNotBlank(payId)){
			PayMoney payMoney =  payMoneyManager.getPayMoney(payId);
			form.setPayMoney(payMoney);
			return new ModelAndView(EDIT_VM,"form",form);
		}else{
			return new ModelAndView(EDIT_VM,"form",form);
		}
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res) {
		String payId = CommonUtil.getParameterNull(req, "payId");
		HandoverForm form = new HandoverForm();
		
		try {
			if(StringUtil.isNotBlank(payId)){
				payMoneyManager.deletePayMoney(payId);
				// 重新查询
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
				condition.put("orgId", CommonUtil.getSessionOrgId(req));
				Pager pager = payMoneyManager.getPayMoneyData(condition);
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
	
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = payMoneyManager.getPayMoneyData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("付款单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate", "voderId"}, new Integer[]{4, 3, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "采购组织", "状态", "供应商", "付款金额", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"billNo", "orgId", "status", "voderId", "payMoney", "createDate", "createId", "updateDate", "updateId"});
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
