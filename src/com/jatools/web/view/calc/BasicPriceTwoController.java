package com.jatools.web.view.calc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.calc.BasicPriceTwoManager;
import com.jatools.vo.calc.BasicPriceTwo;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.dwr.calc.BasicPriceTwoDwr;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.form.calc.BasicPriceJadeForm;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.form.calc.BasicPriceTwoForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class BasicPriceTwoController extends BaseMultiActionController {
	private static Logger logger = Logger.getLogger(BasicPriceTwoController.class);
	private BasicPriceTwoManager basicPriceTwoManager;
	private String sessionBillType = null;

	public void setBasicPriceTwoManager(BasicPriceTwoManager basicPriceTwoManager) {
		this.basicPriceTwoManager = basicPriceTwoManager;
	}
	public String getSessionKey(){
		return super.getSessionKey()+this.sessionBillType;
	}
	
	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		String priceType = CommonUtil.getParameterEmpty(req, "priceType");//页面传的价格类型
		BasicPriceTwoForm form = new BasicPriceTwoForm();
		form.setPriceType(priceType);
		try {
			sessionBillType=priceType;
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "");
			Pager pager = basicPriceTwoManager.getBasicPriceTwoPageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("calc/basicpricetwo_list","form",form);//转跳页面
	}
	
	/**
	 * 删除 总系数
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteBasicPriceTwo(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		try {
			if(StringUtil.isNotEmpty(id)){
				basicPriceTwoManager.deleteBasicPriceTwo(id);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return this.doPerform(req, res);
	}
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditBasicPriceTwo(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		BasicPriceTwoForm form = new BasicPriceTwoForm();
		String priceType = CommonUtil.getParameterEmpty(req, "priceType");//页面传的价格类型
		form.setPriceType(priceType);
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			BasicPriceTwo ven = basicPriceTwoManager.getBasicPriceTwoById(id);
			form.setSp(ven);
			return new ModelAndView("calc/basicpricetwo_edit","form",form);
		}else{
			return new ModelAndView("calc/basicpricetwo_edit","form",form);
		}
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		BasicPriceJadeForm form = new BasicPriceJadeForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "");
		Pager pager = basicPriceTwoManager.getBasicPriceTwoPageData(condition);
		form.setPager(pager);
		form.setCondition(condition);

		ExcelData excelData = new ExcelData();
		excelData.setTitle("钻石核价基础价");
		excelData.setPager(pager);//直接利用分页pager对象
		//excelData.addColumnWidth(new String[]{"createdate", "updatedate","wlCode","handoverName","no"}, new Integer[]{4, 4, 3, 3,3});//宽度默认值为2
		
		//添加列标题  
		excelData.setColumnTitle(new String[]{"价格","创建时间","创建人","修改时间","修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"basicprice","createdate", "createuserid", "updatedate","updateuserid"});
		excelData.addColumnType(new String[]{"basicprice"}, 
				new Class[]{Double.class});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createuserid","updateuserid"},
					new CacheSingletonIntf[]{UserCache.getInstance(),UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{},
					new String[]{});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		return null;//返回null，不跳转至任何页面
	}
}
