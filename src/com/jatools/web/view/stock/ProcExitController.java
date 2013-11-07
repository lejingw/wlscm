package com.jatools.web.view.stock;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.stock.ProcExitHeadManager;
import com.jatools.manager.stock.ProcExitLineManager;
import com.jatools.vo.stock.ProcExitHead;
import com.jatools.vo.stock.ProcExitLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.form.stock.ProcExitHeadForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
/**
 * 退料单
 * @author ren.ming
 * <br>
 * Created 2011-11-28
 */
public class ProcExitController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(ProcExitController.class);

	private static final String LIST_VM = "stock/procExit_list";
	private static final String LIST_REPORT_VM = "stock/procExit_report_list";
	private static final String EDIT_VM = "stock/procExit_edit";
	private static final String PARAMS[] = {"billno", "orgId", "stockId", "status", "startDate", "endDate", "createId"};
	
	private ProcExitHeadManager procExitHeadManager;
	private ProcExitLineManager procExitLineManager;
	
	public void setProcExitHeadManager(ProcExitHeadManager procExitHeadManager) {
		this.procExitHeadManager = procExitHeadManager;
	}
	public void setProcExitLineManager(ProcExitLineManager procExitLineManager) {
		this.procExitLineManager = procExitLineManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		ProcExitHeadForm form = new ProcExitHeadForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = procExitHeadManager.getProcExitHeadData(condition);
			beforeView(pager);
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
	public ModelAndView doReportPerform(HttpServletRequest req, HttpServletResponse res) {
		ProcExitHeadForm form = new ProcExitHeadForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			Pager pager = procExitHeadManager.getReportProcExitHeadData(condition);
			beforeView(pager);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(LIST_REPORT_VM, "form", form);
	}
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		String billno = CommonUtil.getParameterNull(req, "billno");
		ProcExitHeadForm form = new ProcExitHeadForm();
		
		if(StringUtil.isNotBlank(billid)){
			ProcExitHead ProcExitHead = procExitHeadManager.getProcExitHead(billid);
			List<ProcExitLine> exitLines = procExitLineManager.getProcExitLineList(billid);
			beforeView(ProcExitHead, exitLines);
			form.setProcExitHead(ProcExitHead);
			form.setExitLines(exitLines);
			return new ModelAndView(EDIT_VM,"form",form);
		} else if(StringUtil.isNotBlank(billno)){
            ProcExitHead ProcExitHead = procExitHeadManager.getProcExitHeadByBillno(billno);
            List<ProcExitLine> exitLines = procExitLineManager.getProcExitLineList(ProcExitHead.getBillid());
            beforeView(ProcExitHead, exitLines);
            form.setProcExitHead(ProcExitHead);
            form.setExitLines(exitLines);
            return new ModelAndView(EDIT_VM,"form",form);
        }else{
			return new ModelAndView(EDIT_VM,"form",form);
		}
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = procExitHeadManager.getProcExitHeadData(condition);
		beforeView(pager);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("退料单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate", "vendorId"}, new Integer[]{4, 3, 4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "组织", "供应商",  "仓库", "单据状态", "单据日期", "件数合计", "重量合计", "粒数合计", "是否结算", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "orgId", "vendorId", "stockId", "status", "dodate", "sumCount", "sumWeight", "sumGrains", "isCheck", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId", "updateId", "vendorId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), UserCache.getInstance(), VendorCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "stockId", "isCheck"},
					new String[]{DictConstant.BILL_STATUS, DictConstant.INVCODE, DictConstant.YES_OR_NO});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
	private void beforeView(Pager pager){
		for(Object obj : pager.getPageData()){
			beforeView((ProcExitHead)obj, null);
		}
	}
			
	private void beforeView(ProcExitHead head , List<ProcExitLine> exitLines) {
		if(null != head){
			if(StringUtil.isNotBlank(head.getSumCost())){
				head.setSumCost(Double.valueOf(head.getSumCost())+"");
			}
			if(StringUtil.isNotBlank(head.getSumCount())){
				head.setSumCount(Double.valueOf(head.getSumCount())+"");
			}
			if(StringUtil.isNotBlank(head.getSumGrains())){
				head.setSumGrains(Double.valueOf(head.getSumGrains())+"");
			}
			if(StringUtil.isNotBlank(head.getSumMoney())){
				head.setSumMoney(Double.valueOf(head.getSumMoney())+"");
			}
			if(StringUtil.isNotBlank(head.getSumWeight())){
				head.setSumWeight(Double.valueOf(head.getSumWeight())+"");
			}
			
		}
		if(CollectionUtils.isNotEmpty(exitLines)) {
			for(ProcExitLine line : exitLines){
				if(StringUtil.isNotBlank(line.getExitNums())){
					line.setExitNums(Double.valueOf(line.getExitNums())+"");
				}
				if(StringUtil.isNotBlank(line.getBillNums())){
					line.setBillNums(Double.valueOf(line.getBillNums())+"");
				}
				if(StringUtil.isNotBlank(line.getOldNums())){
					line.setOldNums(Double.valueOf(line.getOldNums())+"");
				}
			}
		}
	}
}
