package com.jatools.web.view.calc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.calc.FashionCoefficientManager;
import com.jatools.vo.calc.FashionCoefficient;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.calc.FashionCoefficientForm;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class FashionCoefficientController extends BaseMultiActionController {

	private FashionCoefficientManager fashionCoefficientManager;
	private Logger logger = Logger.getLogger(FashionCoefficientController.class);
	
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	public void setFashionCoefficientManager(
			FashionCoefficientManager fashionCoefficientManager) {
		this.fashionCoefficientManager = fashionCoefficientManager;
	}


	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		FashionCoefficientForm form = new FashionCoefficientForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, 
					"itemClassId","beginWeight","endWeight");
			Pager pager = fashionCoefficientManager.getFashionCoefficientPageData(condition);
			form.setCondition(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("calc/fashionCoefficient_list","form",form);//转跳页面
	}
	/**
	 * 删除 总系数
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteFashionCoefficient(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		FashionCoefficientForm form = new FashionCoefficientForm();
		try {
			if(StringUtil.isNotEmpty(id)){
				fashionCoefficientManager.deleteFashionCoefficient(id);
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
				Pager pager = fashionCoefficientManager.getFashionCoefficientPageData(condition);
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
		return new ModelAndView("calc/fashionCoefficient_list", "form", form);
	}
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditFashionCoefficient(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		FashionCoefficientForm form = new FashionCoefficientForm();
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			FashionCoefficient ven = fashionCoefficientManager.getFashionCoefficientById(id);
			form.setSc(ven);
			return new ModelAndView("calc/fashionCoefficient_edit","form",form);
		}else{
			return new ModelAndView("calc/fashionCoefficient_edit","form",form);
		}
	}
	

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		PriceHeadForm form = new PriceHeadForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, 
				"itemClassId","beginWeight","endWeight");
		Pager pager = fashionCoefficientManager.getFashionCoefficientPageData(condition);
		form.setCondition(condition);
		form.setPager(pager);

		ExcelData excelData = new ExcelData();
		excelData.setTitle("黄金系数");
		excelData.setPager(pager);//直接利用分页pager对象
		//excelData.addColumnWidth(new String[]{"createdate", "updatedate","wlCode","handoverName","no"}, new Integer[]{4, 4, 3, 3,3});//宽度默认值为2
		
		//添加列标题  
		excelData.setColumnTitle(new String[]{"大类","系数","起始总成本","截止总成本","状态","备注",
				"创建时间","创建人","修改时间","修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "coefficient","priceStr","priceEnd","status",
				"memo","createDate", "createId", "updateDate","updateId"});
		//excelData.addColumnType(new String[]{"beginWeight"}, new Class[]{String.class});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId","itemClassId","updateId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), ItemClassCache.getInstance(),UserCache.getInstance()});
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
