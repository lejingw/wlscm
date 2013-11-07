package com.jatools.web.view.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.LabelTypeManager;
import com.jatools.vo.basic.LabelType;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.basic.LabelTypeForm;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
import com.jatools.web.view.basic.LabelTypeController;

public class LabelTypeController extends BaseMultiActionController {

	private LabelTypeManager labelTypeManager;
	private Logger logger = Logger.getLogger(LabelTypeController.class);
	
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	public void setLabelTypeManager(
			LabelTypeManager labelTypeManager) {
		this.labelTypeManager = labelTypeManager;
	}


	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		LabelTypeForm form = new LabelTypeForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
			Pager pager = labelTypeManager.getLabelTypePageData(condition);
			form.setCondition(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/labelType_list","form",form);//转跳页面
	}
	/**
	 * 删除 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteLabelType(HttpServletRequest req, HttpServletResponse res) {
		LabelTypeForm form = new LabelTypeForm();
		try {
			String id = CommonUtil.getParameterNull(req, "id");
			labelTypeManager.deleteLabelType(id);
			form.setSuccessfulFlag(true);
			form.setMessage("删除成功");
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
			Pager pager = labelTypeManager.getLabelTypePageData(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/labelType_list", "form", form);
	}
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditLabelType(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		LabelTypeForm form = new LabelTypeForm();
		if(null != id){
			LabelType ven = labelTypeManager.getLabelTypeById(id);
			form.setLabelType(ven);
			return new ModelAndView("basic/labelType_edit","form",form);
		}else{
			return new ModelAndView("basic/labelType_edit","form",form);
		}
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		Pager pager = labelTypeManager.getLabelTypePageData(condition);

		ExcelData excelData = new ExcelData();
		excelData.setTitle("标签类型");
		excelData.setPager(pager);//直接利用分页pager对象
		
		//添加列标题  
		excelData.setColumnTitle(new String[]{"标签名称", "大类","小类","计量单位", "是否镶嵌", "是否双标签段", "是否封存", "创建时间","创建人","修改时间","修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"labelTypeName", "itemClassId", "ornaClassId","unitId", "bracketFlag","dblLabelFlag", "status", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId","itemClassId","ornaClassId","unitId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), ItemClassCache.getInstance(),OrnaClassCache.getInstance(),UnitCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "bracketFlag","dblLabelFlag"}, new String[]{"yesorno", "yesorno","yesorno"});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
