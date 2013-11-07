package com.jatools.web.view.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.basic.StoneMainCoefficientManager;
import com.jatools.vo.basic.StoneMainCoefficient;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.basic.StoneMainCoefficientForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class StoneMainCoefficientController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(StoneMainCoefficientController.class);

	private static final String PARAMS[] = {"status", "itemClassId", "createId", "startDate", "endDate"};
	private StoneMainCoefficientManager stoneMainCoefficientManager;

	
	public void setStoneMainCoefficientManager(
			StoneMainCoefficientManager stoneMainCoefficientManager) {
		this.stoneMainCoefficientManager = stoneMainCoefficientManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		StoneMainCoefficientForm form = new StoneMainCoefficientForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = stoneMainCoefficientManager.getStoneMainCoefficientPageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/stoneMainCoefficient_list", "form", form);
	}
	
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		StoneMainCoefficientForm form = new StoneMainCoefficientForm();
		if(StringUtil.isNotBlank(id)){
			StoneMainCoefficient coefficient = stoneMainCoefficientManager.getStoneMainCoefficient(id);
			form.setStoneMainCoefficient(coefficient);
			return new ModelAndView("basic/stoneMainCoefficient_edit","form",form);
		}else{
			return new ModelAndView("basic/stoneMainCoefficient_edit","form",form);
		}
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res){
		String id = CommonUtil.getParameterNull(req, "id");
		StoneMainCoefficientForm form = new StoneMainCoefficientForm();
		if(StringUtil.isNotEmpty(id)){
			stoneMainCoefficientManager.deleteStoneMainCoefficient(id);
			form.setSuccessfulFlag(true);
			form.setMessage("删除成功");
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = stoneMainCoefficientManager.getStoneMainCoefficientPageData(condition);
			form.setPager(pager);
		}else{
			form.setSuccessfulFlag(false);
			form.setMessage("不能获取交接数据id");
		}
		return new ModelAndView("basic/stoneMainCoefficient_list", "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = stoneMainCoefficientManager.getStoneMainCoefficientPageData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("钻石石头系数");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate", "memo","updateDate"}, new Integer[]{3, 4,3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"大类", "重量始", "重量止", "系数",  "备注", "创建时间", "创建人","修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "weightStr", "weightEnd", "coefficient", "memo", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "itemClassId","updateId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), ItemClassCache.getInstance(),UserCache.getInstance()});
		excelData.addColumnType(new String[]{"weightStr","weightEnd"}, new Class[]{Double.class,Double.class});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{}, new String[]{});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
}
