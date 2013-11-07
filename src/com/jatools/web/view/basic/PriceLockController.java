package com.jatools.web.view.basic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.PriceLockManager;
import com.jatools.vo.basic.PriceLockHead;
import com.jatools.vo.basic.PriceLockLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.basic.PriceLockForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

public class PriceLockController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(PriceLockController.class);
	private String listKey = null;
	private PriceLockManager priceLockManager;

	public void setPriceLockManager(
			PriceLockManager priceLockManager) {
		this.priceLockManager = priceLockManager;
	}
	public String getSessionKey(){
		return super.getSessionKey() + listKey;
	}
	/**
	 * 获取分页数据
	 */
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		PriceLockForm form = new PriceLockForm();
		try {
			listKey = "1";
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
			Pager pager = priceLockManager.getPriceLockPageData(condition, CommonUtil.getSessionOrgId(req));
			form.setCondition(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/priceLock_list", "form", form);
	}
	/**
	 * 查询页面
	 */
	public ModelAndView toQuery(HttpServletRequest req, HttpServletResponse res) {
		PriceLockForm form = new PriceLockForm();
		try {
			listKey = "2";
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,
					"orgId", "itemClassId", "ornaClassId", "analysisId", "styleItemClassId", "styleMiddleClassId",
					"styleOrnaClassId", "styleId", "lockFlag", "posAmountStart", "posAmountEnd", "lockFlag", "barCode", "ornaCode");
			form.setCondition(condition);
			String flag = CommonUtil.getParameterEmpty(req, "flag");
			if("1".equals(flag)){
				Pager pager = priceLockManager.queryPriceLockPageData(condition);
				form.setPager(pager);
			}else{
				Pager pager = new Pager();
				form.setPager(pager);
			}
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/priceLock_query", "form", form);
	}
	
	public ModelAndView toAdd(HttpServletRequest req, HttpServletResponse res) {
		PriceLockForm form = new PriceLockForm();
		return new ModelAndView("basic/priceLock_edit", "form", form);
	}
	
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		PriceLockForm form = new PriceLockForm();
		try {
			String headid = CommonUtil.getParameterEmpty(req, "headid");
			PriceLockHead head = priceLockManager.getPriceLockHead(headid);
			List<PriceLockLine> list = priceLockManager.getPriceLockLine(headid);
			form.setHead(head);
			form.setList(list);
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("basic/priceLock_edit", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		listKey = "1";
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		Pager pager = priceLockManager.getPriceLockPageData(condition, CommonUtil.getSessionOrgId(req));
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("价格锁定单列表");
//		excelData.setDataList(dataList);//手工重新查找数据
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"createDate", "updateDate"}, new Integer[]{3, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "是否锁定", "件数合计", "创建时间", "创建人", "修改时间", "修改人", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "lockFlag", "sumCount", "createDate", "createId", "updateDate", "updateId", "status"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"createId", "updateId"}, new CacheSingletonIntf[]{UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"lockFlag", "status"}, new String[]{DictConstant.YES_OR_NO, DictConstant.PLAN_BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
}
