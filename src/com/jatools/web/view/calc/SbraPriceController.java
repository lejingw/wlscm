package com.jatools.web.view.calc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.calc.SbraPriceManager;
import com.jatools.vo.calc.SbraPrice;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.form.calc.SbraPriceForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class SbraPriceController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(SbraPriceController.class);
	private SbraPriceManager sbraPriceManager;
	public void setSbraPriceManager(SbraPriceManager sbraPriceManager) {
		this.sbraPriceManager = sbraPriceManager;
	}
	
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		SbraPriceForm form = new SbraPriceForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "id","itemClassId","qualityId");
			Pager pager = sbraPriceManager.getSbraPricePageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("calc/sbraPrice_list","form",form);//转跳页面
	}
	/**
	 * 删除 托架市场价
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteSbraPrice(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		SbraPriceForm form = new SbraPriceForm();
		try {
			if(StringUtil.isNotEmpty(id)){
				sbraPriceManager.deleteSbraPrice(id);
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
				Pager pager = sbraPriceManager.getSbraPricePageData(condition);
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
		return new ModelAndView("calc/sbraPrice_list", "form", form);
	}
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditSbraPrice(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		SbraPriceForm form = new SbraPriceForm();
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			SbraPrice ven = sbraPriceManager.getSbraPriceById(id);
			form.setSp(ven);
			return new ModelAndView("calc/sbraPrice_edit","form",form);
		}else{
			return new ModelAndView("calc/sbraPrice_edit","form",form);
		}
	}
	

	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		PriceHeadForm form = new PriceHeadForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "id","itemClassId","qualityId");
		Pager pager = sbraPriceManager.getSbraPricePageData(condition);
		form.setPager(pager);
		form.setCondition(condition);

		ExcelData excelData = new ExcelData();
		excelData.setTitle("托架市场价");
		excelData.setPager(pager);//直接利用分页pager对象
		//excelData.addColumnWidth(new String[]{"createdate", "updatedate","wlCode","handoverName","no"}, new Integer[]{4, 4, 3, 3,3});//宽度默认值为2
		
		//添加列标题  
		excelData.setColumnTitle(new String[]{"材质","市场价",
				"创建时间","创建人","修改时间","修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"qualityId","marketPrice",
				"createDate", "createUserId", "updateDate","updateUserId"});
		//excelData.addColumnType(new String[]{"beginWeight"}, new Class[]{String.class});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createUserId","updateUserId","qualityId"},
					new CacheSingletonIntf[]{UserCache.getInstance(),UserCache.getInstance(),QualityCache.getInstance()});
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
