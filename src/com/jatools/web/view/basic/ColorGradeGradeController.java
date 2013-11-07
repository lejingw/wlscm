package com.jatools.web.view.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.ColorGradeGradeManager;
import com.jatools.vo.basic.ColorGradeGrade;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.basic.ColorGradeGradeForm;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class ColorGradeGradeController extends BaseMultiActionController {

	private ColorGradeGradeManager colorGradeGradeManager;
	private Logger logger = Logger.getLogger(ColorGradeGradeController.class);
	
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public void setColorGradeGradeManager(ColorGradeGradeManager colorGradeGradeManager) {
		this.colorGradeGradeManager = colorGradeGradeManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		ColorGradeGradeForm form = new ColorGradeGradeForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "colorGradeId","gradeId");
			Pager pager = colorGradeGradeManager.getColorGradeGradePageData(condition);
			form.setCondition(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/colorGradeGrade_list","form",form);//转跳页面
	}

	/**
	 * 删除
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteColorGradeGrade(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		ColorGradeGradeForm form = new ColorGradeGradeForm();
		try {
			if(StringUtil.isNotEmpty(id)){
				colorGradeGradeManager.deleteColorGradeGrade(id);
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
				Pager pager = colorGradeGradeManager.getColorGradeGradePageData(condition);
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
		return new ModelAndView("basic/colorGradeGrade_list", "form", form);
	}
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditColorGradeGrade(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		ColorGradeGradeForm form = new ColorGradeGradeForm();
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			ColorGradeGrade ven = colorGradeGradeManager.getColorGradeGradeById(id);
			form.setC(ven);
			return new ModelAndView("basic/colorGradeGrade_edit","form",form);
		}else{
			return new ModelAndView("basic/colorGradeGrade_edit","form",form);
		}
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		ColorGradeGradeForm form = new ColorGradeGradeForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "colorGradeId","gradeId");
		Pager pager = colorGradeGradeManager.getColorGradeGradePageData(condition);
		form.setPager(pager);
		
		ExcelData excelData = new ExcelData();
		excelData.setTitle("色级品质");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate", "updateDate"}, new Integer[]{4, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"色级", "品质", "状态", "备注", "创建人", "创建时间","修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"colorGradeId", "gradeId", "status","memo", 
				"createId", "createDate", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId","updateId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"colorGradeId","gradeId", "status"},
					new String[]{"diacolorgrade","gradelevel", DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
