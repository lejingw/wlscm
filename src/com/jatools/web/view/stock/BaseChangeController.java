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
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.manager.stock.ProcChangeHeadManager;
import com.jatools.manager.stock.ProcChangeLineManager;
import com.jatools.vo.stock.ProcChangeHead;
import com.jatools.vo.stock.ProcChangeLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.stock.ProcChangeHeadForm;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
/**
 * 形态转换、原料提纯和拆石 基类控制器
 * @author ren.ming
 * Created 2011-12-7
 */
public abstract class BaseChangeController extends BaseMultiActionController{
	private Logger logger = Logger.getLogger(BaseChangeController.class);
	
	private static final String PARAMS[] = {"billno", "orgId", "stockId", "status", "startDate", "endDate", "createId"};
	
	private BdCommonManager bdCommonManager;
	private ProcChangeHeadManager procChangeHeadManager;
	private ProcChangeLineManager ProcChangeLineManager;
	
	public void setBdCommonManager(BdCommonManager bdCommonManager) {
		this.bdCommonManager = bdCommonManager;
	}
	public void setProcChangeHeadManager(ProcChangeHeadManager ProcChangeHeadManager) {
		this.procChangeHeadManager = ProcChangeHeadManager;
	}
	public void setProcChangeLineManager(ProcChangeLineManager ProcChangeLineManager) {
		this.ProcChangeLineManager = ProcChangeLineManager;
	}
	
	/**
	 * @param vmPath
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView doDasePerform(String vmPath, String dotype, HttpServletRequest req, HttpServletResponse res) {
		ProcChangeHeadForm form = new ProcChangeHeadForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			this.dealDotype(dotype, condition);
			Pager pager = procChangeHeadManager.getProcChangeHeadData(condition);
			beforeView(pager);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(vmPath, "form", form);
	}
	
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toBaseEdit(String vmPath, HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		ProcChangeHeadForm form = new ProcChangeHeadForm();
		//如果带有groupId参数，则为编辑页面
		if(null != billid){
			ProcChangeHead procChangeHead = procChangeHeadManager.getProcChangeHead(billid);
			List<ProcChangeLine> oldLines = ProcChangeLineManager.getProcChangeLineList(billid);
			beforeView(procChangeHead);
			beforeView(oldLines);
			form.setProcChangeHead(procChangeHead);
			form.setOldLines(oldLines);
			return new ModelAndView(vmPath,"form",form);
		}else{
			return new ModelAndView(vmPath,"form",form);
		}
	}
	
	/**
	 * 删除单据
	 * @param req
	 * @param res
	 * @param vmPath
	 * @return
	 */
	public ModelAndView baseDelete(String dotype, HttpServletRequest req, HttpServletResponse res, String vmPath){
		String billid = CommonUtil.getParameterNull(req, "billid");
		ProcChangeHeadForm form = new ProcChangeHeadForm();
		if(StringUtil.isNotEmpty(billid)){
			try{
				procChangeHeadManager.deleteProcChangeHead(billid);
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
			} catch(Exception e){
				form.setSuccessfulFlag(false);
				form.setMessage(e.getMessage());
			}
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			this.dealDotype(dotype, condition);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = procChangeHeadManager.getProcChangeHeadData(condition);
			beforeView(pager);
			form.setPager(pager);
		}else{
			form.setSuccessfulFlag(false);
			form.setMessage("不能获取交接数据id");
		}
		return new ModelAndView(vmPath, "form", form);
	}
	
	public ModelAndView baseExportExcel(String dotype, HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		this.dealDotype(dotype, condition);
		Pager pager = procChangeHeadManager.getProcChangeHeadData(condition);
		beforeView(pager);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		if("0".equals(dotype)){
			excelData.setTitle("形态转换单");
		} else if("1".equals(dotype)){
			excelData.setTitle("原料提纯单");
		} else {
			excelData.setTitle("拆石单");
		}
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate", "updateDate"}, new Integer[]{4, 3, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "组织", "仓库", "单据状态", "单据日期", "原件数合计", "原重量合计", "原粒数合计", "原金额合计", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "orgId", "stockId", "status", "dodate", "sumCount", "sumWeight", "sumGrains", "sumMoney", "createDate", "createId", "updateDate", "updateId"});
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
	
	private void dealDotype(String dotype, Map<String, String> condition){
		if(StringUtil.isEmpty(condition.get("dotype"))) {
			if(StringUtil.isEmpty(dotype)) {
				condition.put("dotype", "0");
			} else {
				condition.put("dotype", dotype);
			}
		}
	}
	
	protected void beforeView(Pager pager) {
		for(Object object : pager.getPageData()) {
			ProcChangeHead head = (ProcChangeHead)object;
			beforeView(head);
		}
	}
	
	protected void beforeView(ProcChangeHead head) {
		if(StringUtil.isNotBlank(head.getSumCost())) {
			head.setSumCost(StringUtil.formatDouble(Double.valueOf(head.getSumCost()), 6));
		} else {
			head.setSumCost("0");
		}
		if(StringUtil.isNotBlank(head.getSumCount())) {
			head.setSumCount(Double.valueOf(head.getSumCount())+"");
		} else {
			head.setSumCount("0");
		}
		if(StringUtil.isNotBlank(head.getSumGrains())) {
			head.setSumGrains(Double.valueOf(head.getSumGrains())+"");
		} else {
			head.setSumGrains("0");
		}
		if(StringUtil.isNotBlank(head.getSumMoney())) {
			head.setSumMoney(Double.valueOf(head.getSumMoney())+"");
		} else {
			head.setSumMoney("0");
		}
		if(StringUtil.isNotBlank(head.getSumWeight())) {
			head.setSumWeight(StringUtil.formatDouble(Double.valueOf(head.getSumWeight()), 2));
		} else {
			head.setSumWeight("0");
		}
	}
	
	protected void beforeView(List<ProcChangeLine> oldLines) {
		if(oldLines != null) {
			for(ProcChangeLine oldLine : oldLines) {
				if(StringUtil.isNotBlank(oldLine.getMainWeight())) {
					oldLine.setMainWeight(Double.valueOf(oldLine.getMainWeight())+"");
				} else {
					oldLine.setMainWeight("0");
				}
				if(StringUtil.isNotBlank(oldLine.getPartWeight())) {
					oldLine.setPartWeight(Double.valueOf(oldLine.getPartWeight())+"");
				} else {
					oldLine.setPartWeight("0");
				}
				if(StringUtil.isNotBlank(oldLine.getAllQty())) {
					oldLine.setAllQty(Double.valueOf(oldLine.getAllQty())+"");
				}else {
					oldLine.setAllQty("0");
				}
				if(StringUtil.isNotBlank(oldLine.getPosMoney())) {
					oldLine.setPosMoney(Double.valueOf(oldLine.getPosMoney())+"");
				}else {
					oldLine.setPosMoney("");
				}
				if(StringUtil.isNotBlank(oldLine.getNowQty())) {
					oldLine.setNowQty(Double.valueOf(oldLine.getNowQty())+"");
				}else {
					oldLine.setNowQty("0");
				}
				
				String itemClassName = BdCommon.getItemClassDesc(oldLine.getItemClassId());
				oldLine.setItemClassName(itemClassName);
				String ornaClassName = BdCommon.getOrnaClassDesc(oldLine.getOrnaClassId());
				oldLine.setOrnaClassName(ornaClassName);
//				String styleName = this.bdCommonManager.getStyleNameById(oldLine.getStyleId());
//				oldLine.setStyleName(styleName);
				String analysisName = this.bdCommonManager.getAnalysisNameById(oldLine.getAlaysisId());
				oldLine.setAlaysisName(analysisName);
				String unitName = BdCommon.getUnitName(oldLine.getUnitId());
				oldLine.setUnitName(unitName);
			}
		}
		
	}
}
