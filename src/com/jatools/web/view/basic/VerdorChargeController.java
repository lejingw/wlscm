package com.jatools.web.view.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.VerdorChargeManager;
import com.jatools.vo.basic.VerdorCharge;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.basic.VerdorChargeForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class VerdorChargeController extends BaseMultiActionController{
	private VerdorChargeManager verdorChargeManager;
	private Logger logger = Logger.getLogger(VerdorChargeController.class);
	
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public void setVerdorChargeManager(VerdorChargeManager verdorChargeManager) {
		this.verdorChargeManager = verdorChargeManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		VerdorChargeForm form = new VerdorChargeForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "colorGradeId","gradeId");
			Pager pager = verdorChargeManager.getVerdorChargePageData(condition);
			form.setCondition(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/verdorCharge_list","form",form);//转跳页面
	}

	/**
	 * 删除
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteVerdorCharge(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		VerdorChargeForm form = new VerdorChargeForm();
		try {
			if(StringUtil.isNotEmpty(id)){
				verdorChargeManager.deleteVerdorCharge(id);
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
				Pager pager = verdorChargeManager.getVerdorChargePageData(condition);
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
		return new ModelAndView("basic/verdorCharge_list", "form", form);
	}
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditVerdorCharge(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		VerdorChargeForm form = new VerdorChargeForm();
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			VerdorCharge ven = verdorChargeManager.getVerdorChargeById(id);
			form.setC(ven);
			return new ModelAndView("basic/verdorCharge_edit","form",form);
		}else{
			return new ModelAndView("basic/verdorCharge_edit","form",form);
		}
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		VerdorChargeForm form = new VerdorChargeForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "colorGradeId","gradeId");
		Pager pager = verdorChargeManager.getVerdorChargePageData(condition);
		form.setPager(pager);
		
		ExcelData excelData = new ExcelData();
		excelData.setTitle("新特殊工费");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate", "updateDate","verdorId"}, new Integer[]{4, 4, 6});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"供应商", "款式", "状态", "备注", "创建人", "创建时间","修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"verdorId", "styleName", "status","memo", 
				"createId", "createDate", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"verdorId","createId","updateId"},
					new CacheSingletonIntf[]{VendorCache.getInstance(),UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{ "status"},
					new String[]{ DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
