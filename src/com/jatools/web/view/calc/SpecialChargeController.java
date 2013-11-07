package com.jatools.web.view.calc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.calc.SpecialChargeManager;
import com.jatools.vo.calc.SpecialCharge;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.calc.SpecialChargeForm;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class SpecialChargeController extends BaseMultiActionController{

	private SpecialChargeManager	specialChargeManager;
	private Logger logger = Logger.getLogger(SpecialChargeController.class);
	
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setSpecialChargeManager(SpecialChargeManager specialChargeManager) {
		this.specialChargeManager = specialChargeManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		SpecialChargeForm form = new SpecialChargeForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"");
			Pager pager = specialChargeManager.getSpecialChargePageData(condition);
			form.setCondition(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("calc/specialCharge_list","form",form);//转跳页面
	}
	/**
	 * 删除 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteSpecialCharge(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		SpecialChargeForm form = new SpecialChargeForm();
		try {
			if(StringUtil.isNotEmpty(id)){
				specialChargeManager.deleteSpecialCharge(id);
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
				Pager pager = specialChargeManager.getSpecialChargePageData(condition);
				form.setPager(pager);
			}else{
				form.setSuccessfulFlag(false);
				form.setMessage("不能获取单据总系数id");
			}
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("calc/specialCharge_list", "form", form);
	}
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditSpecialCharge(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		SpecialChargeForm form = new SpecialChargeForm();
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			SpecialCharge ven = specialChargeManager.getSpecialChargeById(id);
			form.setSc(ven);
			return new ModelAndView("calc/specialCharge_edit","form",form);
		}else{
			return new ModelAndView("calc/specialCharge_edit","form",form);
		}
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		PriceHeadForm form = new PriceHeadForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,
				"qualityId","itemClassId","beginWeight","endWeight");
		Pager pager = specialChargeManager.getSpecialChargePageData(condition);
		form.setCondition(condition);
		form.setPager(pager);

		ExcelData excelData = new ExcelData();
		excelData.setTitle("特殊工费核算规则表");
		excelData.setPager(pager);//直接利用分页pager对象
		
		//添加列标题  
		excelData.setColumnTitle(new String[]{"重量始","重量止","核算重量","工费单价","浮动值","特殊工费","备注",
				"创建时间","创建人","修改时间","修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"weightStr", "weightEnd","weightCheck","chargeUnit","chargeFloat",
				"chargeMoney","memo","createDate", "createId", "updateDate","updateId"});
		//excelData.addColumnType(new String[]{"beginWeight"}, new Class[]{String.class});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId","updateUId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{},new String[]{});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}

}
