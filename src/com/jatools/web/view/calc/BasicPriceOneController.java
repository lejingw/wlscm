package com.jatools.web.view.calc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.calc.BasicPriceOneManager;
import com.jatools.vo.calc.BasicPriceOne;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.dwr.calc.BasicPriceOneDwr;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.form.calc.BasicPriceJadeForm;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.form.calc.BasicPriceOneForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class BasicPriceOneController extends BaseMultiActionController {
	private static Logger logger = Logger.getLogger(BasicPriceOneController.class);
	private BasicPriceOneManager basicPriceOneManager;
	private String sessionBillType = null;

	public void setBasicPriceOneManager(BasicPriceOneManager basicPriceOneManager) {
		this.basicPriceOneManager = basicPriceOneManager;
	}
	public String getSessionKey(){
		return super.getSessionKey()+this.sessionBillType;
	}
	
	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		String priceType = CommonUtil.getParameterEmpty(req, "priceType");//页面传的价格类型
		BasicPriceOneForm form = new BasicPriceOneForm();
		form.setPriceType(priceType);
		try {
			sessionBillType=priceType;
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "");
			Pager pager = basicPriceOneManager.getBasicPriceOnePageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("calc/basicpriceone_list","form",form);//转跳页面
	}
	
	/**
	 * 删除 总系数
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteBasicPriceOne(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		try {
			if(StringUtil.isNotEmpty(id)){
				basicPriceOneManager.deleteBasicPriceOne(id);
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
	public ModelAndView toEditBasicPriceOne(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		BasicPriceOneForm form = new BasicPriceOneForm();
		String priceType = CommonUtil.getParameterEmpty(req, "priceType");//页面传的价格类型
		form.setPriceType(priceType);
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			BasicPriceOne ven = basicPriceOneManager.getBasicPriceOneById(id);
			form.setSp(ven);
			return new ModelAndView("calc/basicpriceone_edit","form",form);
		}else{
			return new ModelAndView("calc/basicpriceone_edit","form",form);
		}
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		BasicPriceJadeForm form = new BasicPriceJadeForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "");
		Pager pager = basicPriceOneManager.getBasicPriceOnePageData(condition);
		form.setPager(pager);
		form.setCondition(condition);

		ExcelData excelData = new ExcelData();
		excelData.setTitle("钻石核价基础价");
		excelData.setPager(pager);//直接利用分页pager对象
		//excelData.addColumnWidth(new String[]{"createdate", "updatedate","wlCode","handoverName","no"}, new Integer[]{4, 4, 3, 3,3});//宽度默认值为2
		
		//添加列标题  
		excelData.setColumnTitle(new String[]{"大类","起始重量","截止重量","创建时间","创建人","修改时间","修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"itemclassid","startweight","endweight",
				"createdate", "createuserid", "updatedate","updateuserid"});
		excelData.addColumnType(new String[]{"startweight", "endweight"}, 
				new Class[]{Double.class, Double.class});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createuserid","itemclassid","updateuserid"},
					new CacheSingletonIntf[]{UserCache.getInstance(), ItemClassCache.getInstance(),UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{},
					new String[]{});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		return null;//返回null，不跳转至任何页面
	}
}
