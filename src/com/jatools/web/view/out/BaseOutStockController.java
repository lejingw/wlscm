package com.jatools.web.view.out;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.out.OutStockManager;
import com.jatools.vo.out.OutStockHead;
import com.jatools.vo.out.OutStockLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.out.OutStockForm;
import com.jatools.web.form.out.OutVendorForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.view.BaseMultiActionController;

/**
 * 所有出库的父类包括了一些公用的方法 
 *
 */
public abstract class BaseOutStockController extends BaseMultiActionController {
	public OutStockManager outStockManager;
	private String sessionOrder;

	public void setOutStockManager(OutStockManager outStockManager) {
		this.outStockManager = outStockManager;
	}
	public String getSessionKey(){
		return this.getClass().getName() + sessionOrder;
	}
	//重新封装 BaseMultiActionController 重写的方法
	public abstract ModelAndView doSubPerform(HttpServletRequest req,
			HttpServletResponse res);
	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		return doSubPerform(req, res);
	}
	
	//准备新增页面的数据
	protected OutStockForm toAddForm(HttpServletRequest req,HttpServletResponse res) {
		OutStockForm form = new OutStockForm();
		form.getHead().setOrgId(CommonUtil.getSessionOrgId(req));
		return form;
	}
	
	//得到编辑页的相关数据
	protected void toEditData(OutStockForm form, String headId) {
		OutStockHead head = outStockManager.getStockHead(headId);
		List<OutStockLine> list = outStockManager.getStockLines(headId);
		form.setHead(head);
		form.setList(list);
	}

	//加载列表的数据根据条件
	protected void getPagerData(OutStockForm form, HttpServletRequest req,String doType) {
		sessionOrder = doType;
		String [] keys={"billno","stockId","status","startDate","endDate", "showAll"};
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req,keys);
		condition.put("doType",doType);

		condition.put("userId",CommonUtil.getSessionUserId(req));//加载当前用户的数据
		Pager pager = outStockManager.getStockHeadPage(condition);
		form.setPager(pager);
		form.setCondition(condition);
	}
	
	protected ModelAndView baseExportExcel(HttpServletRequest req, HttpServletResponse res,String doType,String title,Pager pager) {
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();	
		excelData.setTitle(title);
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId","createDate", "updateDate"}, new Integer[]{4, 3, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "库存组织","仓库", "状态", "单据日期","件数合计", "重量合计", "粒数合计", "金额合计","创建时间", "创建人"});//, "修改时间", "修改人", "状态"
		//添加列name
		excelData.setColumnName(new String[]{"billno", "orgId", "stockId","status", "dodate", "sumCount", "sumWeight", "sumGrains", "sumMoney", "createDate", "createId"});//, "updateDate", "updateId", "status"
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(),UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status","stockId"},new String[]{DictConstant.BILL_STATUS, DictConstant.INVCODE,});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;
	}
	
	public ModelAndView baseExportExcelLine(HttpServletRequest req,String headId,String title, HttpServletResponse res) {
		OutVendorForm form = new OutVendorForm();
		
		Pager pager = new Pager();
		pager.setPageData((List)outStockManager.getStockLines(headId));
		form.setPager(pager);
		
		ExcelData excelData = new ExcelData();
		excelData.setTitle(title);
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{}, new Integer[]{});//宽度默认值为2
														
		//添加列标题
		excelData.setColumnTitle(new String[]{"大类", "小类", "款式", "分析范围","饰品编码","饰品名称",
				"计量单位", "现有量", "总重量", "粒数", "销售金额",  "主石重量","配石重量"});
		//添加列name
		excelData.setColumnName(new String[]{"itemClassId", "ornaClassId", "styleName","alaysisCode","ornaCode",
				"ornaDsc", "unitId", "nowQty", "allQty", "grains", "posMoney", "mainWeight", "partWeight"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"itemClassId","ornaClassId","unitId"},
					new CacheSingletonIntf[]{ItemClassCache.getInstance(),OrnaClassCache.getInstance(), UnitCache.getInstance()});
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
