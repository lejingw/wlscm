package com.jatools.web.view.calc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.calc.MaterAttrUpdateManager;
import com.jatools.vo.calc.MaterAttrUpdate;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.calc.MaterAttrUpdateForm;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class MaterAttrUpdateController extends BaseMultiActionController{

	private MaterAttrUpdateManager materAttrUpdateManager;
	private Logger logger = Logger.getLogger(PriceHeadController.class);
	
	
	public void setMaterAttrUpdateManager(
			MaterAttrUpdateManager materAttrUpdateManager) {
		this.materAttrUpdateManager = materAttrUpdateManager;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		MaterAttrUpdateForm form = new MaterAttrUpdateForm();
		try {
			loadList(req, res, form);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("calc/materAttrUpdate_list","form",form);//转跳页面
	}
	public void loadList(HttpServletRequest req,
			HttpServletResponse res,MaterAttrUpdateForm form){
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"stateId");
		Pager pager = materAttrUpdateManager.getMaterAttrUpdatePageData(condition);
		form.setPager(pager);
		form.setCondition(condition);
	}
	/**
	 * 删除 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteMaterAttrUpdate(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		MaterAttrUpdateForm form = new MaterAttrUpdateForm();
		try {
			if(StringUtil.isNotEmpty(id)){
				MaterAttrUpdate m = this.materAttrUpdateManager.getMaterAttrUpdateById(id);
				if(null==m){
					form.setSuccessfulFlag(false);
					form.setMessage("单据不存在");
				}else if(!"1".equals(m.getStatus())){
					form.setSuccessfulFlag(false);
					form.setMessage("单据状态已修改");		
				}else{
					materAttrUpdateManager.deleteMaterAttrUpdate(id);
					form.setSuccessfulFlag(true);
					form.setMessage("删除成功");					
				}
			}else{
				form.setSuccessfulFlag(false);
				form.setMessage("不能获取单据id");
			}
			loadList(req, res, form);
			return new ModelAndView("calc/materAttrUpdate_list","form",form);//转跳页面
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
			return null;
		}
	}
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditMaterAttrUpdate(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		MaterAttrUpdateForm form = new MaterAttrUpdateForm();
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			MaterAttrUpdate ven = materAttrUpdateManager.getMaterAttrUpdateById(id);
			form.setU(ven);
			return new ModelAndView("calc/materAttrUpdate_edit","form",form);
		}else{
			return new ModelAndView("calc/materAttrUpdate_edit","form",form);
		}
	}
	
	/**
	 * 关闭
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView changeMaterAttrUpdateStatus(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		String status = CommonUtil.getParameterNull(req, "statusF");
		MaterAttrUpdateForm form = new MaterAttrUpdateForm();
		try {
			if(StringUtil.isNotEmpty(id)){
				MaterAttrUpdate m = this.materAttrUpdateManager.getMaterAttrUpdateById(id);
				if(null==m){
					form.setSuccessfulFlag(false);
					form.setMessage("单据不存在");
				}else if(!DictConstant.BILL_STATUS_REVIEWED.equals(m.getStatus())){
					form.setSuccessfulFlag(false);
					form.setMessage("单据状态已修改");		
				}else{
					materAttrUpdateManager.changeHeadStatus(status, m, req);
					form.setSuccessfulFlag(true);
					form.setMessage("关闭成功");					
				}
			}else{
				form.setSuccessfulFlag(false);
				form.setMessage("不能获取单据id");
			}
			loadList(req, res, form);
			return new ModelAndView("calc/materAttrUpdate_list","form",form);//转跳页面
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
			return null;
		}
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		MaterAttrUpdateForm form = new MaterAttrUpdateForm();
		loadList(req, res, form);

		ExcelData excelData = new ExcelData();
		excelData.setTitle("饰品属性修改");
		excelData.setPager(form.getPager());//直接利用分页pager对象
		
		//添加列标题  
		excelData.setColumnTitle(new String[]{"状态", "单据编号","饰品编码","组织","修改原因", "创建时间","创建人","修改时间","修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"status", "billno","ornacode", "orgid", "updatereason",
				"createdate", "createid","updatedate","updateid"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createid","updateid","orgid"},
					new CacheSingletonIntf[]{UserCache.getInstance(),UserCache.getInstance(),OrgCache.getInstance()});
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
