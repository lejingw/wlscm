package com.jatools.web.view.calc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.calc.CoefficientWWManager;
import com.jatools.vo.calc.CoefficientWW;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.calc.CoefficientWWForm;
import com.jatools.web.form.calc.PriceHeadForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class CoefficientWWController extends BaseMultiActionController {

	private CoefficientWWManager	coefficientWWManager;
	private Logger logger = Logger.getLogger(CoefficientWWController.class);
	
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setCoefficientWWManager(CoefficientWWManager coefficientWWManager) {
		this.coefficientWWManager = coefficientWWManager;
	}


	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		CoefficientWWForm form = new CoefficientWWForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, 
					"qualityId","itemClassId","beginWeight","endWeight");
			Pager pager = coefficientWWManager.getCoefficientWWPageData(condition);
			form.setCondition(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("calc/coefficientWW_list","form",form);//转跳页面
	}
	/**
	 * 删除 总系数
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteCoefficientWW(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		CoefficientWWForm form = new CoefficientWWForm();
		try {
			if(StringUtil.isNotEmpty(id)){
				coefficientWWManager.deleteCoefficientWW(id);
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
				Pager pager = coefficientWWManager.getCoefficientWWPageData(condition);
				form.setPager(pager);
			}else{
				form.setSuccessfulFlag(false);
				form.setMessage("不能获取单据总系数id");
			}
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("calc/coefficientWW_list", "form", form);
	}
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditCoefficientWW(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		CoefficientWWForm form = new CoefficientWWForm();
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			CoefficientWW ven = coefficientWWManager.getCoefficientWWById(id);
			form.setC(ven);
			return new ModelAndView("calc/coefficientWW_edit","form",form);
		}else{
			return new ModelAndView("calc/coefficientWW_edit","form",form);
		}
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		PriceHeadForm form = new PriceHeadForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, 
				"qualityId","itemClassId","beginWeight","endWeight");
		Pager pager = coefficientWWManager.getCoefficientWWPageData(condition);
		form.setCondition(condition);
		form.setPager(pager);

		ExcelData excelData = new ExcelData();
		excelData.setTitle("委外总系数");
		excelData.setPager(pager);//直接利用分页pager对象
		//excelData.addColumnWidth(new String[]{"createdate", "updatedate","wlCode","handoverName","no"}, new Integer[]{4, 4, 3, 3,3});//宽度默认值为2
		
		//添加列标题  
		excelData.setColumnTitle(new String[]{"大类","材质","档级","起始重量","截止重量","系数",
				"创建时间","创建人","修改时间","修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "qualityId","styleStandard","startWeight","endWeight",
				"coefficient","createDate", "createUserId", "updateDate","updateUserId"});
		//excelData.addColumnType(new String[]{"beginWeight"}, new Class[]{String.class});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createUserId","itemClassId","qualityId","updateUserId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), ItemClassCache.getInstance(),QualityCache.getInstance(),UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"styleStandard"},
					new String[]{"hlevel"});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
}
