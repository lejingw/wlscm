package com.jatools.web.view.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.basic.UnsaleOrnaManager;
import com.jatools.vo.basic.UnsaleOrna;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.basic.UnsaleOrnaForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class UnsaleOrnaController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(UnsaleOrnaController.class);

	private UnsaleOrnaManager unsaleOrnaManager;

	public void setUnsaleOrnaManager(UnsaleOrnaManager unsaleOrnaManager) {
		this.unsaleOrnaManager = unsaleOrnaManager;
	}
	
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		UnsaleOrnaForm form = new UnsaleOrnaForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
			Pager pager = unsaleOrnaManager.getUnsaleOrnaPageData(condition);
			//condition.put("orgId", CommonUtil.getSessionOrgId(req));
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/unsaleOrna_list", "form", form);
	}
	
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String unsaleId = CommonUtil.getParameterNull(req, "unsaleId");
		UnsaleOrnaForm form = new UnsaleOrnaForm();
		
		if(StringUtil.isNotBlank(unsaleId)){
			UnsaleOrna unsaleOrna = this.unsaleOrnaManager.getUnsaleOrna(unsaleId);
			form.setUnsaleOrna(unsaleOrna);
			return new ModelAndView("basic/unsaleOrna_edit","form",form);
		}else{
			return new ModelAndView("basic/unsaleOrna_edit","form",form);
		}
	}

	
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		//condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = this.unsaleOrnaManager.getUnsaleOrnaPageData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("滞销商品规则");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate"}, new Integer[]{3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"大类", "金额始", "金额止", "流转周期", "淘汰款式周期", "非淘汰款式周期", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "moneyStr", "moneyEnd", "flowCycle", "unfashCycle", "unfashUncycle", "createDate",  "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "updateId", "itemClassId"},
					new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance(), ItemClassCache.getInstance()});
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
