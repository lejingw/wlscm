package com.jatools.web.view.stock;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.stock.JmSaleManager;
import com.jatools.vo.stock.JmSaleHead;
import com.jatools.vo.stock.JmSaleLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.stock.JmSaleForm;
import com.jatools.web.form.stock.ProcChangeHeadForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class JmSaleController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(JmSaleController.class);
	
	private static final String PARAMS[] = {"status"};
	
	private static final String LIST_VM = "stock/jmSale_list";
	private static final String EDIT_VM = "stock/jmSale_edit";
	
	@SuppressWarnings("rawtypes")
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		ProcChangeHeadForm form = new ProcChangeHeadForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = jmSaleManager.getJmSaleData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(LIST_VM, "form", form);
	}
	
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		JmSaleForm form = new JmSaleForm();
		//如果带有groupId参数，则为编辑页面
		if(StringUtil.isNotBlank(billid)){
			JmSaleHead head = jmSaleManager.getJmSaleHead(billid);
			List<JmSaleLine> lines = jmSaleManager.getLines(billid);
			form.setJmSaleHead(head);
			form.setLines(lines);
			return new ModelAndView(EDIT_VM,"form",form);
		}else{
			return new ModelAndView(EDIT_VM,"form",form);
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = jmSaleManager.getJmSaleData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("加盟特价单");
		
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate", "updateDate"}, new Integer[]{4, 3, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "组织", "仓库", "单据状态", "单据日期", "件数合计", "重量合计", "粒数合计", "金额合计", "特价金额合计", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "orgId", "stockId", "status", "dodate", "sumCount", "sumWeight", "sumGrains", "sumMoney", "sumJmmoney", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "stockId"},
					new String[]{DictConstant.BILL_STATUS, DictConstant.INVCODE});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
	private JmSaleManager jmSaleManager;
	
	public void setJmSaleManager(JmSaleManager JmSaleManager) {
		this.jmSaleManager = JmSaleManager;
	}
}
