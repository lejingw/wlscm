package com.jatools.web.view.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.VendorCoefManager;
import com.jatools.vo.basic.VendorCoef;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.basic.VendorCoefForm;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
import com.jatools.web.view.basic.VendorCoefController;

public class VendorCoefController extends BaseMultiActionController {

	private VendorCoefManager vendorCoefManager;
	private Logger logger = Logger.getLogger(VendorCoefController.class);
	
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	public void setVendorCoefManager(
			VendorCoefManager vendorCoefManager) {
		this.vendorCoefManager = vendorCoefManager;
	}


	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		VendorCoefForm form = new VendorCoefForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "vendorId","coef");
			Pager pager = vendorCoefManager.getVendorCoefPageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/vendorCoef_list","form",form);//转跳页面
	}
	/**
	 * 删除 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteVendorCoef(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		VendorCoefForm form = new VendorCoefForm();
		try {
			if(StringUtil.isNotEmpty(id)){
				vendorCoefManager.deleteVendorCoef(id);
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
				Pager pager = vendorCoefManager.getVendorCoefPageData(condition);
				form.setPager(pager);
			}else{
				form.setSuccessfulFlag(false);
				form.setMessage("不能获取单据id");
			}
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/vendorCoef_list", "form", form);
	}
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditVendorCoef(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		VendorCoefForm form = new VendorCoefForm();
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			VendorCoef ven = vendorCoefManager.getVendorCoefById(id);
			form.setSc(ven);
			return new ModelAndView("basic/vendorCoef_edit","form",form);
		}else{
			return new ModelAndView("basic/vendorCoef_edit","form",form);
		}
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		PriceHeadForm form = new PriceHeadForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "vendorId","coef");
		Pager pager = vendorCoefManager.getVendorCoefPageData(condition);
		form.setCondition(condition);
		form.setPager(pager);

		ExcelData excelData = new ExcelData();
		excelData.setTitle("供应商系数");
		excelData.setPager(pager);//直接利用分页pager对象
		//excelData.addColumnWidth(new String[]{"createdate", "updatedate","wlCode","handoverName","no"}, new Integer[]{4, 4, 3, 3,3});//宽度默认值为2
		
		//添加列标题  
		excelData.setColumnTitle(new String[]{"供应商", "系数", "创建时间","创建人","修改时间",
				"状态", "备注"});
		//添加列name
		excelData.setColumnName(new String[]{"vendorId", "coef","createDate", "createId", "updateDate",
				"status", "memo"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"vendorId","createId","vendorId"},
					new CacheSingletonIntf[]{VendorCache.getInstance(),UserCache.getInstance(),VendorCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status",},
					new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
