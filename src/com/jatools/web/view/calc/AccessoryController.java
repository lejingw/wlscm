package com.jatools.web.view.calc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.calc.AccessoryManager;
import com.jatools.vo.calc.Accessory;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.calc.AccessoryForm;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class AccessoryController extends BaseMultiActionController {
	
	private AccessoryManager accessoryManager;
	private Logger logger = Logger.getLogger(AccessoryController.class);
	
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public void setAccessoryManager(AccessoryManager accessoryManager) {
		this.accessoryManager = accessoryManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		AccessoryForm form = new AccessoryForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "id","accessoryId");
			Pager pager = accessoryManager.getAccessoryPageData(condition);
			form.setCondition(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("calc/accessory_list","form",form);//转跳页面
	}

	/**
	 * 删除
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteAccessory(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		AccessoryForm form = new AccessoryForm();
		try {
			if(StringUtil.isNotEmpty(id)){
				accessoryManager.deleteAccessory(id);
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
				Pager pager = accessoryManager.getAccessoryPageData(condition);
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
		return new ModelAndView("calc/accessory_list", "form", form);
	}
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditAccessory(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		AccessoryForm form = new AccessoryForm();
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			Accessory ven = accessoryManager.getAccessoryById(id);
			form.setA(ven);
			return new ModelAndView("calc/accessory_edit","form",form);
		}else{
			return new ModelAndView("calc/accessory_edit","form",form);
		}
	}

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		PriceHeadForm form = new PriceHeadForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "id","accessoryId");
		Pager pager = accessoryManager.getAccessoryPageData(condition);
		form.setCondition(condition);
		form.setPager(pager);

		ExcelData excelData = new ExcelData();
		excelData.setTitle("辅料系数");
		excelData.setPager(pager);//直接利用分页pager对象
		//excelData.addColumnWidth(new String[]{"createdate", "updatedate","wlCode","handoverName","no"}, new Integer[]{4, 4, 3, 3,3});//宽度默认值为2
		
		//添加列标题  
		excelData.setColumnTitle(new String[]{"辅料","系数","备注",
				"创建时间","创建人","修改时间","修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"accessoryId","coefficient",
				"createDate", "createUserId", "updateDate","updateUserId"});
		//excelData.addColumnType(new String[]{"beginWeight"}, new Class[]{String.class});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createUserId","updateUserId"},
					new CacheSingletonIntf[]{UserCache.getInstance(),UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"accessoryId"},
					new String[]{"accessories"});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
