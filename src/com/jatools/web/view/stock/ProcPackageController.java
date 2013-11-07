package com.jatools.web.view.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.manager.stock.ProcPackageHeadManager;
import com.jatools.manager.stock.ProcPackageNewlineManager;
import com.jatools.manager.stock.ProcPackageOldlineManager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.stock.ProcPackageHead;
import com.jatools.vo.stock.ProcPackageNewline;
import com.jatools.vo.stock.ProcPackageOldline;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.stock.ProcPackageHeadForm;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
/**
 * 裸石拆混包单
 * @author ren.ming
 * <br>
 * Created 2011-11-20
 */
public class ProcPackageController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(ProcPackageController.class);

	private static final String LIST_VM = "stock/procPackage_list";
	private static final String EDIT_VM = "stock/procPackage_edit";
	private static final String PRINT_VM = "common/printLabelJson";
	private static final String PARAMS[] = {"billno", "orgId", "stockId", "status", "startDate", "endDate", "createId", "dotype"};
	
	private ProcPackageHeadManager procPackageHeadManager;
	private ProcPackageOldlineManager procPackageOldlineManager;
	private ProcPackageNewlineManager procPackageNewlineManager;
	private BdCommonManager bdCommonManager;
	
	public void setProcPackageHeadManager(
			ProcPackageHeadManager procPackageHeadManager) {
		this.procPackageHeadManager = procPackageHeadManager;
	}
	public void setProcPackageOldlineManager(
			ProcPackageOldlineManager procPackageOldlineManager) {
		this.procPackageOldlineManager = procPackageOldlineManager;
	}
	public void setProcPackageNewlineManager(
			ProcPackageNewlineManager procPackageNewlineManager) {
		this.procPackageNewlineManager = procPackageNewlineManager;
	}
	
	public void setBdCommonManager(BdCommonManager bdCommonManager) {
		this.bdCommonManager = bdCommonManager;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		ProcPackageHeadForm form = new ProcPackageHeadForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = procPackageHeadManager.getProcPackageHeadData(condition);
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
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		ProcPackageHeadForm form = new ProcPackageHeadForm();
		//如果带有groupId参数，则为编辑页面
		if(null != billid){
			ProcPackageHead procPackageHead = procPackageHeadManager.getProcPackageHead(billid);
			List<ProcPackageOldline> oldLines = procPackageOldlineManager.getProcPackageOldlineList(billid);
			List<ProcPackageNewline> newLines = procPackageNewlineManager.getProcPackageNewlineList(billid);
			beforeView(procPackageHead, oldLines, newLines);
			form.setProcPackageHead(procPackageHead);
			form.setNewLines(newLines);
			form.setOldLines(oldLines);
			return new ModelAndView(EDIT_VM,"form",form);
		}else{
			return new ModelAndView(EDIT_VM,"form",form);
		}
	}
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res){
		String billid = CommonUtil.getParameterNull(req, "billid");
		ProcPackageHeadForm form = new ProcPackageHeadForm();
		if(StringUtil.isNotEmpty(billid)){
			procPackageHeadManager.deleteProcPackageHead(billid);
			form.setSuccessfulFlag(true);
			form.setMessage("删除成功");
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("orgId", CommonUtil.getSessionOrgId(req));
			Pager pager = procPackageHeadManager.getProcPackageHeadData(condition);
			form.setPager(pager);
		}else{
			form.setSuccessfulFlag(false);
			form.setMessage("不能获取交接数据id");
		}
		return new ModelAndView(LIST_VM, "form", form);
	}
	
	public ModelAndView deleteList(HttpServletRequest req, HttpServletResponse res){
		String billids = CommonUtil.getParameterNull(req, "billids");
		ProcPackageHeadForm form = new ProcPackageHeadForm();
		try{
			if(StringUtil.isNotEmpty(billids)){
				String idArray[] = billids.split(";");
				for(String billid: idArray){
					if(StringUtil.isNotBlank(billid)){
						procPackageHeadManager.deleteProcPackageHead(billid);
					}
				}
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
			}else{
				form.setSuccessfulFlag(false);
				form.setMessage("未选择要删除的单据");
			}
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			Pager pager = procPackageHeadManager.getProcPackageHeadData(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		
		return new ModelAndView(LIST_VM, "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("orgId", CommonUtil.getSessionOrgId(req));
		Pager pager = procPackageHeadManager.getProcPackageHeadData(condition);
		beforeView(pager);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("拆包混包单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate"}, new Integer[]{4, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "组织", "业务类型", "仓库", "单据状态", "单据日期", "原重量合计", "原粒数合计", "新重量合计", "新粒数合计", "创建时间", "创建人", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "orgId", "dotype", "stockId", "status", "dodate", "oldWeight", "oldGrains", "newWeight", "newGrains", "createDate", "createId", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(),UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "stockId", "dotype"},
					new String[]{DictConstant.BILL_STATUS, DictConstant.INVCODE, DictConstant.PACKAGE_TYPE});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
	public ModelAndView toShowPrint(HttpServletRequest req, HttpServletResponse res){
		String billid = CommonUtil.getParameterNull(req, "billid");
		ProcPackageHeadForm form = new ProcPackageHeadForm();
		Map<String, String> condition = new HashMap<String, String>();
		try{
			JSONArray lines = new JSONArray();
			JSONObject root = new JSONObject();
			if(StringUtil.isNotBlank(billid)){
				List<Tag> printData = this.procPackageHeadManager.getPrintData(billid);
				if(CollectionUtils.isNotEmpty(printData)){
					for(Tag tag : printData){
						/*if(StringUtil.isNotBlank(tag.getIdent()) && tag.getIdent().contains("!")){
							tag.setIdent(tag.getIdent().replace("!", "$"));
						}*/
						JSON line = JSONSerializer.toJSON(tag);
						lines.add(line.toString());
					}
				}
			}
			root.put("lines", lines);
			condition.put("pt", "1");
			condition.put("params", "-1");
			form.setPrintData(root.toString());
			form.setCondition(condition);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		
		return new ModelAndView(PRINT_VM, "form", form);
	}
	
	private void beforeView(Pager pager){
		for(Object obj : pager.getPageData()){
			beforeView((ProcPackageHead)obj, null, null);
		}
	}
	private void beforeView(ProcPackageHead procPackageHead ,List<ProcPackageOldline> oldLines, List<ProcPackageNewline> newLines) {
		if(null != procPackageHead){
			if(StringUtil.isNotBlank(procPackageHead.getNewCost())){
				procPackageHead.setNewCost(Double.valueOf(procPackageHead.getNewCost())+"");
			}
			if(StringUtil.isNotBlank(procPackageHead.getNewGrains())){
				procPackageHead.setNewGrains(Double.valueOf(procPackageHead.getNewGrains())+"");
			}
			if(StringUtil.isNotBlank(procPackageHead.getNewWeight())){
				procPackageHead.setNewWeight(Double.valueOf(procPackageHead.getNewWeight())+"");
			}
			if(StringUtil.isNotBlank(procPackageHead.getOldCost())){
				procPackageHead.setOldCost(Double.valueOf(procPackageHead.getOldCost())+"");
			}
			if(StringUtil.isNotBlank(procPackageHead.getOldGrains())){
				procPackageHead.setOldGrains(Double.valueOf(procPackageHead.getOldGrains())+"");
			}
			if(StringUtil.isNotBlank(procPackageHead.getOldWeight())){
				procPackageHead.setOldWeight(Double.valueOf(procPackageHead.getOldWeight())+"");
			}
		}
		if(oldLines != null) {
			for(ProcPackageOldline oldLine : oldLines) {
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
		
		if(newLines != null) {
			for(ProcPackageNewline newLine : newLines) {
				String analysisName = this.bdCommonManager.getAnalysisNameById(newLine.getAnalysisArangeId());
				newLine.setAnalysisArangeName(analysisName);
				if(StringUtil.isNotBlank(newLine.getAllQty())){
					newLine.setAllQty(Double.valueOf(newLine.getAllQty())+"");
				}
				if(StringUtil.isNotBlank(newLine.getGrains())){
					newLine.setGrains(StringUtil.formatDouble(newLine.getGrains(), 0));
				}
			}
		}
	}
}
