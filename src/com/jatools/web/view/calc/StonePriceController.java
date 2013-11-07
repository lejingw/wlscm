package com.jatools.web.view.calc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.calc.StonePriceManager;
import com.jatools.manager.calc.StonePriceManager;
import com.jatools.vo.calc.StonePrice;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.dwr.calc.StonePriceDwr;
import com.jatools.web.dwr.calc.StonePriceDwr;
import com.jatools.web.dwr.move.MoveSalecoefDwr;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.form.calc.StonePriceForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class StonePriceController extends BaseMultiActionController {
	private static Logger logger = Logger.getLogger(StonePriceController.class);
	private StonePriceManager stonePriceManager;
	private String sessionBillType = null;

	public void setStonePriceManager(StonePriceManager stonePriceManager) {
		this.stonePriceManager = stonePriceManager;
	}
	public String getSessionKey(){
		return super.getSessionKey()+this.sessionBillType;
	}
	
	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		String priceType = CommonUtil.getParameterEmpty(req, "priceType");//页面传的价格类型
		StonePriceForm form = new StonePriceForm();
		form.setPriceType(priceType);
		try {
			sessionBillType=priceType;
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "priceType","itemClassId"
						,"stoneType","shapeId","colorId","colorGradeId","cleanId","cutId","beginWeight"
						,"endWeight","createUserId","marketPrice");
//			}else{
//				condition.put("priceType", req.getParameter("priceType"));
//				condition.put("itemClassId", req.getParameter("itemClassId1"));
//				condition.put("stoneType", req.getParameter("stoneType1"));
//				condition.put("shapeId", req.getParameter("shapeId1"));
//				condition.put("colorId", req.getParameter("colorId1"));
//				condition.put("colorGradeId", req.getParameter("colorGradeId1"));
//				condition.put("cleanId", req.getParameter("cleanId1"));
//				condition.put("cutId", req.getParameter("cutId1"));
//				condition.put("beginWeight", req.getParameter("beginWeight1"));
//				condition.put("endWeight", req.getParameter("endWeight1"));
//				condition.put("createUserId", req.getParameter("createUserId1"));
//				condition.put("marketPrice", req.getParameter("marketPrice1"));
//			}
			Pager pager = stonePriceManager.getStonePricePageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		if("1".equals(priceType))
			return new ModelAndView("calc/stonePrice_list","form",form);//转跳页面
		else
			return new ModelAndView("calc/stonePrice_list_CG","form",form);//转跳页面
	}
	
	/**
	 * 删除 总系数
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteStonePrice(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		try {
			if(StringUtil.isNotEmpty(id)){
				stonePriceManager.deleteStonePrice(id);
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
	public ModelAndView toEditStonePrice(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		StonePriceForm form = new StonePriceForm();
		String priceType = CommonUtil.getParameterEmpty(req, "priceType");//页面传的价格类型
		form.setPriceType(priceType);
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			StonePrice ven = stonePriceManager.getStonePriceById(id);
			form.setSp(ven);
			return new ModelAndView("calc/stonePrice_edit","form",form);
		}else{
			return new ModelAndView("calc/stonePrice_edit","form",form);
		}
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		String priceType = CommonUtil.getParameterEmpty(req, "priceType");//页面传的价格类型
		StonePriceForm form = new StonePriceForm();
		form.setPriceType(priceType);
		sessionBillType=priceType;
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "priceType","itemClassId"
					,"stoneType","shapeId","colorId","colorGradeId","cleanId","cutId","beginWeight"
					,"endWeight","createUserId","marketPrice");
		Pager pager = stonePriceManager.getStonePricePageData(condition);
		form.setPager(pager);
		form.setCondition(condition);

		ExcelData excelData = new ExcelData();
		if("1".equals(priceType)){
			excelData.setTitle("主配石市场价");
		}else{
			excelData.setTitle("主配石采购价");
		}
		excelData.setPager(pager);//直接利用分页pager对象
		//excelData.addColumnWidth(new String[]{"createdate", "updatedate","wlCode","handoverName","no"}, new Integer[]{4, 4, 3, 3,3});//宽度默认值为2
		
		//添加列标题  
		excelData.setColumnTitle(new String[]{"大类","规格","起始重量","截止重量","形状",
				"颜色","净度","色级","切工","市场价","石头类型","价格类型",
				"创建时间","创建人","修改时间","修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "styleStandard","startWeight","endWeight",
				"shapeId","colorId","cleanId","colorGradeId","cutId","marketPrice","stoneType","priceType",
				"createDate", "createUserId", "updateDate","updateUserId"});
		excelData.addColumnType(new String[]{"startWeight", "endWeight"}, new Class[]{Double.class, Double.class});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createUserId","itemClassId","updateUserId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), ItemClassCache.getInstance(),UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"shapeId","colorId","cleanId","colorGradeId","cutId","stoneType","priceType"},
					new String[]{"diashape","diacolor","diaclean","diacolorgrade","diacut","stonetype","pricetype"});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
