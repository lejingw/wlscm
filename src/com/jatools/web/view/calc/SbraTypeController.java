package com.jatools.web.view.calc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.calc.SbraTypeManager;
import com.jatools.vo.calc.SbraTypeHead;
import com.jatools.vo.calc.SbraTypeLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.form.calc.SbraTypeForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;
import com.jatools.web.view.calc.SbraTypeController;

public class SbraTypeController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(SbraTypeController.class);
	private SbraTypeManager sbraTypeManager;
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public void setSbraTypeManager(SbraTypeManager sbraTypeManager) {
		this.sbraTypeManager = sbraTypeManager;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		SbraTypeForm form = new SbraTypeForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"sbraType");
		Pager pager = sbraTypeManager.getSbraTypePageData(condition);
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("calc/sbraType_list", "form", form);
	}
	
	/**
	 * 新建单据
	 * 
	 */
	public ModelAndView toAdd(HttpServletRequest req, HttpServletResponse res) {
		SbraTypeForm form = new SbraTypeForm();
		return new ModelAndView("calc/sbraType_edit", "form", form);
	}
	
	/**
	 * 修改单据
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		SbraTypeForm form = new SbraTypeForm();
		String id = CommonUtil.getParameterNull(req, "id");
		if(null != id){
			try {
				SbraTypeHead head = sbraTypeManager.getSbraTypeHead(id);
				List<SbraTypeLine> list = sbraTypeManager.getSbraTypeLineList(id);
				form.setHead(head);
				form.setLine(list);
			} catch (Exception e) {
				logger.error(e);
				form.setSuccessfulFlag(false);
				form.setMessage("获取单据数据失败");
				return new ModelAndView("calc/sbraType_list", "form", form);
			}
		}
		return new ModelAndView("calc/sbraType_edit", "form", form);
	}
	

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		PriceHeadForm form = new PriceHeadForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,"sbraType");
		Pager pager = sbraTypeManager.getSbraTypePageData(condition);
		form.setPager(pager);
		form.setCondition(condition);

		ExcelData excelData = new ExcelData();
		excelData.setTitle("配件类型");
		excelData.setPager(pager);//直接利用分页pager对象
		//excelData.addColumnWidth(new String[]{"createdate", "updatedate","wlCode","handoverName","no"}, new Integer[]{4, 4, 3, 3,3});//宽度默认值为2

		//添加列标题  
		excelData.setColumnTitle(new String[]{"配件类型", "其它系数","是否封存","是否工费", "创建时间","创建人","修改时间","修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"sbraType", "coefficient", "archiveFlag","isFee", "createDate", "createUserId", "updateDate","updateUserId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createUserId","updateUserId"},
					new CacheSingletonIntf[]{UserCache.getInstance(),UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"archiveFlag", "isFee"},
					new String[]{"yesorno", "yesorno"});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
