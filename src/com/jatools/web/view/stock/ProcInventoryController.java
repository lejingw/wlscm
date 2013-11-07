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
import com.jatools.manager.stock.MaterActiveManager;
import com.jatools.manager.stock.ProcInventoryDiffheadManager;
import com.jatools.manager.stock.ProcInventoryDifflineManager;
import com.jatools.manager.stock.ProcInventoryHeadManager;
import com.jatools.manager.stock.ProcInventoryLineManager;
import com.jatools.vo.stock.PackageMaterActive;
import com.jatools.vo.stock.ProcInventoryDiffhead;
import com.jatools.vo.stock.ProcInventoryDiffline;
import com.jatools.vo.stock.ProcInventoryHead;
import com.jatools.vo.stock.ProcInventoryLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.stock.ProcInventoryDiffHeadForm;
import com.jatools.web.form.stock.ProcInventoryHeadForm;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;
/**
 * 盘点单
 * @author ren.ming
 * <br>
 * Created 2011-12-5
 */
public class ProcInventoryController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(ProcInventoryController.class);

	private static final String PARAMS[] = {"billno", "userId", "stockId", "status", "startDate", "endDate", "createId"};
	
	private ProcInventoryHeadManager procInventoryHeadManager;
	private ProcInventoryLineManager procInventoryLineManager;
	private BdCommonManager bdCommonManager;
	private MaterActiveManager materActiveManager;
	private ProcInventoryDiffheadManager procInventoryDiffheadManager;
	private ProcInventoryDifflineManager procInventoryDifflineManager;
	
	
	public void setProcInventoryHeadManager(
			ProcInventoryHeadManager ProcInventoryHeadManager) {
		this.procInventoryHeadManager = ProcInventoryHeadManager;
	}
	public void setProcInventoryLineManager(
			ProcInventoryLineManager ProcInventoryLineManager) {
		this.procInventoryLineManager = ProcInventoryLineManager;
	}
	
	public void setBdCommonManager(BdCommonManager bdCommonManager) {
		this.bdCommonManager = bdCommonManager;
	}
	public void setMaterActiveManager(MaterActiveManager materActiveManager) {
		this.materActiveManager = materActiveManager;
	}
	public void setProcInventoryDiffheadManager(ProcInventoryDiffheadManager procInventoryDiffheadManager) {
		this.procInventoryDiffheadManager = procInventoryDiffheadManager;
	}
	public void setProcInventoryDifflineManager(ProcInventoryDifflineManager procInventoryDifflineManager) {
		this.procInventoryDifflineManager = procInventoryDifflineManager;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		ProcInventoryHeadForm form = new ProcInventoryHeadForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("userId", CommonUtil.getSessionUserId(req));
			Pager pager = procInventoryHeadManager.getProcInventoryHeadData(condition);
			beforeDateList(pager.getPageData());
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("stock/procInventory_list", "form", form);
	}
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		ProcInventoryHeadForm form = new ProcInventoryHeadForm();
		//如果带有groupId参数，则为编辑页面
		if(null != billid){
			ProcInventoryHead procInventoryHead = procInventoryHeadManager.getProcInventoryHead(billid);
			List<ProcInventoryLine> oldLines = procInventoryLineManager.getProcInventoryLineList(billid);
			beforeDate(procInventoryHead);
			form.setProcInventoryHead(procInventoryHead);
			form.setLines(oldLines);
			return new ModelAndView("stock/procInventory_edit","form",form);
		}else{
			return new ModelAndView("stock/procInventory_edit","form",form);
		}
	}
	
	public ModelAndView toShow(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		ProcInventoryHeadForm form = new ProcInventoryHeadForm();
		//如果带有groupId参数，则为编辑页面
		if(null != billid){
			ProcInventoryHead procInventoryHead = procInventoryHeadManager.getProcInventoryHead(billid);
			beforeDate(procInventoryHead);
			Map<String, String> condition = CommonUtil.getConditionForPage(req, "billid", "isCode", "itemClassId", "ornaClassId", "ornaCode");
			Pager pager = procInventoryLineManager.getProcInventoryLineData(condition);
			
			beforeView(pager.getPageData());
			form.setProcInventoryHead(procInventoryHead);
			form.setPager(pager);
			form.setCondition(condition);
			return new ModelAndView("stock/procInventory_show","form",form);
		}else{
			return new ModelAndView("stock/procInventory_show","form",form);
		}
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res){
		String billid = CommonUtil.getParameterNull(req, "billid");
		ProcInventoryHeadForm form = new ProcInventoryHeadForm();
		if(StringUtil.isNotEmpty(billid)){
			procInventoryHeadManager.deleteProcInventoryHead(billid);
			form.setSuccessfulFlag(true);
			form.setMessage("删除成功");
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("userId", CommonUtil.getSessionUserId(req));
			Pager pager = procInventoryHeadManager.getProcInventoryHeadData(condition);
			form.setPager(pager);
		}else{
			form.setSuccessfulFlag(false);
			form.setMessage("不能获取交接数据id");
		}
		return new ModelAndView("stock/procInventory_list", "form", form);
	}
	
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("userId", CommonUtil.getSessionUserId(req));
		Pager pager = procInventoryHeadManager.getProcInventoryHeadData(condition);
		beforeDateList(pager.getPageData());
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("盘点单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate"}, new Integer[]{4, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "组织", "单据日期", "盘点人员", "盘点件数", "库存总件数", "子库", "是否主单", "状态", "创建时间", "修改时间", "修改人"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "orgId", "dodate", "createId", "sumCount", "isstock", "stockId", "ismain", "status", "createDate", "updateDate", "updateId"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId", "updateId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "stockId", "ismain"},
					new String[]{DictConstant.BILL_STATUS, DictConstant.INVCODE, DictConstant.YES_OR_NO});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
	
	
	/**
	 * 查询未盘点饰品
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView doNoStockList(HttpServletRequest req, HttpServletResponse res) {
		ProcInventoryHeadForm form = new ProcInventoryHeadForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPage(req, "itemClassId", "orgId", "ornaClassId", "groups", "stockId");
			condition.put("noStock", "1");
			//condition.put("state", "900");
			if(StringUtil.isEmpty(condition.get("orgId"))) {
				condition.put("orgId", CommonUtil.getSessionOrgId(req));
			}
			if(StringUtil.isNotBlank(condition.get("orgId"))) {
				Pager pager = materActiveManager.getMaterActivePagerData(condition);
				dealMaterActive(pager);
				form.setPager(pager);
			}
			form.setCondition(condition);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("stock/procInventory_nostock_list", "form", form);
	}
	
	/**
	 * 金额和重量的小数处理以及一些id转化为名称
	 * @param packageMA
	 * @return
	 */
	private void dealMaterActive(Pager pager) {
		for(Object object : pager.getPageData()) {
			PackageMaterActive packageMA = (PackageMaterActive)object;
			
			if(StringUtil.isNotBlank(packageMA.getMainWeight())) {
				packageMA.setMainWeight(Double.valueOf(packageMA.getMainWeight())+"");
			} else {
				packageMA.setMainWeight("0");
			}
			if(StringUtil.isNotBlank(packageMA.getPartWeight())) {
				packageMA.setPartWeight(Double.valueOf(packageMA.getPartWeight())+"");
			} else {
				packageMA.setPartWeight("0");
			}
			if(StringUtil.isNotBlank(packageMA.getAllQty())) {
				packageMA.setAllQty(Double.valueOf(packageMA.getAllQty())+"");
			}else {
				packageMA.setAllQty("0");
			}
			if(StringUtil.isNotBlank(packageMA.getPosAmount())) {
				packageMA.setPosAmount(Double.valueOf(packageMA.getPosAmount())+"");
			}else {
				packageMA.setPosAmount("");
			}
			if(StringUtil.isNotBlank(packageMA.getStoneNowNum())) {
				packageMA.setStoneNowNum(Double.valueOf(packageMA.getStoneNowNum())+"");
			}else {
				packageMA.setStoneNowNum("0");
			}
			
			if(StringUtil.isNotBlank(packageMA.getNowQty())) {
				packageMA.setNowQty(Double.valueOf(packageMA.getNowQty())+"");
			}else {
				packageMA.setNowQty("0");
			}
			
			String itemClassName = BdCommon.getItemClassDesc(packageMA.getItemClassId());
			packageMA.setItemClassName(itemClassName);
			String ornaClassName = BdCommon.getOrnaClassDesc(packageMA.getOrnaClassId());
			packageMA.setOrnaClassName(ornaClassName);
			String styleName = this.bdCommonManager.getStyleNameById(packageMA.getStyleId());
			packageMA.setStyleName(styleName);
			String analysisName = this.bdCommonManager.getAnalysisNameById(packageMA.getAlaysisId());
			packageMA.setAlaysisName(analysisName);
			String saleUnitName = BdCommon.getUnitName(packageMA.getSaleUnitId());
			packageMA.setSaleUnitName(saleUnitName);
		}
	}
	/**
	 * 查看差异单
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView showDiffBill(HttpServletRequest req, HttpServletResponse res) {
		ProcInventoryHeadForm form = new ProcInventoryHeadForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPage(req, "sourceNo");
			Pager pager = procInventoryDiffheadManager.getProcInventoryDiffheadData(condition);
			//beforeDateList(pager.getPageData());
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取差异单列表数据出错");
		}
		return new ModelAndView("stock/procInventorydiff_list", "form", form);
	}
	
	public ModelAndView exportDiffExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "sourceNo");
		Pager pager = procInventoryDiffheadManager.getProcInventoryDiffheadData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("盘点单【"+condition.get("sourceNo") + "】差异单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createDate"}, new Integer[]{4, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"差异编号", "组织", "创建时间", "创建人", "件数合计", "子库", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "orgId", "createDate", "createId", "sumCount", "stockId", "status"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createId"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status", "stockId"},
					new String[]{DictConstant.BILL_STATUS, DictConstant.INVCODE});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}
	
	/**
	 * 查看差异单详情
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toShowDiff(HttpServletRequest req, HttpServletResponse res) {
		String billid = CommonUtil.getParameterNull(req, "billid");
		ProcInventoryDiffHeadForm form = new ProcInventoryDiffHeadForm();
		
		if(null != billid){
			ProcInventoryDiffhead head = procInventoryDiffheadManager.getProcInventoryDiffhead(billid);
			if(StringUtil.isNotBlank(head.getDodate())) {
				head.setDodate(head.getDodate().split(" ")[0]);
			}
			if(StringUtil.isNotBlank(head.getCreateDate())) {
				head.setCreateDate(head.getCreateDate());
			}
			String params[] = {"billid", "itemClassId", "ornaClassId", "groups", "ornaCode", "state", "diffSignState", "bill_type", "bill_no"};
			Map<String, String> condition = CommonUtil.getConditionForPage(req, params);
			Pager pager = procInventoryDifflineManager.getProcInventoryDifflineData(condition);
			
			beforeView2(pager.getPageData());
			form.setProcInventoryDiffhead(head);
			form.setPager(pager);
			form.setCondition(condition);
			return new ModelAndView("stock/procInventorydiff_show","form",form);
		}else{
			return new ModelAndView("stock/procInventorydiff_show","form",form);
		}
	}
	
	private void beforeDateList(List<Object>  heads) {
		for(Object head : heads) {
			beforeDate((ProcInventoryHead)head);
		}
	}
	
	private void beforeDate(ProcInventoryHead head) {
		if(StringUtil.isNotBlank(head.getDodate())) {
			head.setDodate(head.getDodate().split(" ")[0]);
		}
		if(StringUtil.isNotBlank(head.getCreateDate())) {
			head.setCreateDate(head.getCreateDate());
		}
	}
	
	private void beforeView(List<Object> lines) {
		if(lines != null) {
			for(Object line : lines) {
				ProcInventoryLine oldLine = (ProcInventoryLine)line;
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
				String styleName = this.bdCommonManager.getStyleNameById(oldLine.getStyleId());
				oldLine.setStyleName(styleName);
				String analysisName = this.bdCommonManager.getAnalysisNameById(oldLine.getAlaysisId());
				oldLine.setAlaysisName(analysisName);
				String unitName = BdCommon.getUnitName(oldLine.getUnitId());
				oldLine.setUnitName(unitName);
			}
		}
	}
	
	private void beforeView2(List<Object> lines) {
		if(lines != null) {
			for(Object line : lines) {
				ProcInventoryDiffline oldLine = (ProcInventoryDiffline)line;
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
				String styleName = this.bdCommonManager.getStyleNameById(oldLine.getStyleId());
				oldLine.setStyleName(styleName);
				String analysisName = this.bdCommonManager.getAnalysisNameById(oldLine.getAlaysisId());
				oldLine.setAlaysisName(analysisName);
				String unitName = BdCommon.getUnitName(oldLine.getUnitId());
				oldLine.setUnitName(unitName);
			}
		}
	}
}
