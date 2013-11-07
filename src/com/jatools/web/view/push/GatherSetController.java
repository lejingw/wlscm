package com.jatools.web.view.push;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.push.GatherOrderManager;
import com.jatools.manager.push.GatherSetManager;
import com.jatools.vo.push.GatherSetHead;
import com.jatools.vo.push.GatherSetLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.push.GatherSetForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

public class GatherSetController extends BaseMultiActionController {
	private GatherSetManager gatherSetManager;
//	private GatherOrderManager gatherOrderManager;

	public void setGatherSetManager(GatherSetManager gatherSetManager) {
		this.gatherSetManager = gatherSetManager;
	}

//	public void setGatherOrderManager(GatherOrderManager gatherOrderManager) {
//		this.gatherOrderManager = gatherOrderManager;
//	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 下单设置列表
	 */
	public ModelAndView setList(HttpServletRequest req, HttpServletResponse res) {
		GatherSetForm form = new GatherSetForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		Pager pager = gatherSetManager.getGatherSetPageData(condition);
		form.setPager(pager);
		form.setCondition(condition);
		return new ModelAndView("push/gatherSet_setList", "form", form);
	}

	/**
	 * 下单头设置
	 */
	public ModelAndView headEdit(HttpServletRequest req, HttpServletResponse res) {
		String billId = CommonUtil.getParameterEmpty(req, "billId");
		GatherSetHead head = gatherSetManager.getGatherSetHead(billId);
		GatherSetForm form = new GatherSetForm();
		form.setHead(head);
		return new ModelAndView("push/gatherSet_headEdit", "form", form);
	}

	/**
	 * 下单行设置
	 */
	public ModelAndView bodyEdit(HttpServletRequest req, HttpServletResponse res) {
		String headid = CommonUtil.getParameterEmpty(req, "billId");
		GatherSetHead head = gatherSetManager.getGatherSetHead(headid);
		List<GatherSetLine> lineList = gatherSetManager.getGatherSetLineList(headid);
		GatherSetForm form = new GatherSetForm();
		form.setHead(head);
		form.setLineList(lineList);
		return new ModelAndView("push/gatherSet_bodyEdit", "form", form);
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "showAllFlag");
		Pager pager = gatherSetManager.getGatherSetPageData(condition);
		
		ExcelData excelData = new ExcelData();//excel数据对象
		excelData.setTitle("下单设置");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.setColumnTitle(new String[]{"状态", "总单编号", "区域", "周期类型", "商品类别", "购物开始日期", "购物结束日期", "销售开始日期", "销售结束日期", "最后到货日期", "组织", "创建人", "创建时间", "修改人", "修改时间"});
		excelData.setColumnName(new String[]{"status", "billNo", "regionName", "cycleTypeName","articleTypeNames", "purDateStart", "purDateEnd", "saleDateStart", "saleDateEnd", "purArriveDateEnd", "orgId", "createId", "createDate", "updateId", "updateDate"});
//		excelData.addColumnWidth(new String[]{"startDate", "endDate", "orgId", "itemClassId", "ornaClassId", "analysisName","styleItemClassId", "turnoverNum", "createId", "createDate", "updateId", "updateDate", "status"}, new Integer[]{4, 4, 3, 3, 4});//宽度默认值为2
		//设置对应的缓存数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"}, new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);
		util.export(res);
		return null;
	}
}
