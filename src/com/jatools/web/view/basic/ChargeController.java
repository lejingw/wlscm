package com.jatools.web.view.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.ChargeManager;
import com.jatools.vo.basic.Charge;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.basic.ChargeForm;
import com.jatools.web.form.basic.OrgGroupForm;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class ChargeController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(ChargeController.class);

	private ChargeManager chargeManager;

	public void setChargeManager(ChargeManager chargeManager) {
		this.chargeManager = chargeManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		ChargeForm form = new ChargeForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "weightStr", "weightEnd");
			Pager pager = chargeManager.getChargePageData(condition);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/charge_list", "form", form);
	}
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String chargeId = CommonUtil.getParameterNull(req, "chargeId");
		ChargeForm form = new ChargeForm();
		//如果带有groupId参数，则为编辑页面
		if(null != chargeId){
			Charge charge = chargeManager.getCharge(chargeId);
			form.setCharge(charge);
			return new ModelAndView("basic/charge_edit","form",form);
		}else{
			return new ModelAndView("basic/charge_edit","form",form);
		}
	}
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res){
		String chargeId = CommonUtil.getParameterNull(req, "chargeId");
		OrgGroupForm form = new OrgGroupForm();
		if(StringUtil.isNotEmpty(chargeId)){
			chargeManager.deleteCharge(chargeId);
			form.setSuccessfulFlag(true);
			form.setMessage("删除成功");
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
			Pager pager = chargeManager.getChargePageData(condition);
			form.setPager(pager);
		}else{
			form.setSuccessfulFlag(false);
			form.setMessage("不能获取交接数据id");
		}
		return new ModelAndView("basic/charge_list", "form", form);
	}
	/**
	 * 保存单据
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView saveOrUpdate(HttpServletRequest req, HttpServletResponse res) {
		Charge charge = CommonUtil.request2Object(req, Charge.class);
		String userid = CommonUtil.getSessionUserId(req);
		charge.setUpdateDate(DateUtil.getCurrentDate18());
		charge.setUpdateId(userid);
		OrgGroupForm form = new OrgGroupForm();
		if(null == charge.getChargeId()){
			charge.setCreateDate(DateUtil.getCurrentDate18());
			charge.setCreateId(userid);
			charge.setStatus(DictConstant.BILL_STATUS_SAVE);
			chargeManager.saveCharge(charge);//保存单据
			
		}else{
			chargeManager.updateCharge(charge);//修改单据
		}
		form.setSuccessfulFlag(true);
		form.setMessage("保存成功");
		
		return new ModelAndView(new RedirectView("charge.vm"), "form", form);
	}
	
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "weightStr", "weightEnd");
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = chargeManager.getChargePageData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("特殊工费标准");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate"}, new Integer[]{3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"金额始", "金额止", "工费标准", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"weightStr", "weightEnd", "chargeVal", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "updateId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{},
					new String[]{});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
