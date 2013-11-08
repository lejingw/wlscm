package com.jatools.web.dwr.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.manager.stock.MaterActiveManager;
import com.jatools.manager.stock.ProcInventoryDiffheadManager;
import com.jatools.manager.stock.ProcInventoryHeadManager;
import com.jatools.manager.stock.ProcInventoryLineManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.bd.Analysis;
import com.jatools.vo.stock.PackageMaterActive;
import com.jatools.vo.stock.ProcInventoryHead;
import com.jatools.vo.stock.ProcInventoryLine;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.UnitCache;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

/**
 * 库存盘点
 * @author ren.ming
 * Created 2011-12-5
 */
public class InventoryDwr {
	private static Logger logger = Logger.getLogger(InventoryDwr.class);
	
	private MaterActiveManager materActiveManager;
	private CommonManager commonManager;
	private BdCommonManager bdCommonManager;
	private ProcInventoryHeadManager procInventoryHeadManager;
	private ProcInventoryLineManager procInventoryLineManager;
	private ProcInventoryDiffheadManager procInventoryDiffheadManager;
	
	public void setProcInventoryHeadManager(
			ProcInventoryHeadManager ProcInventoryHeadManager) {
		this.procInventoryHeadManager = ProcInventoryHeadManager;
	}
	public void setBdCommonManager(BdCommonManager dbCommonManager) {
		this.bdCommonManager = dbCommonManager;
	}
	public void setMaterActiveManager(MaterActiveManager materActiveManager) {
		this.materActiveManager = materActiveManager;
	}
	
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	public void setProcInventoryLineManager(ProcInventoryLineManager procInventoryLineManager) {
		this.procInventoryLineManager = procInventoryLineManager;
	}
	public void setProcInventoryDiffheadManager(ProcInventoryDiffheadManager procInventoryDiffheadManager) {
		this.procInventoryDiffheadManager = procInventoryDiffheadManager;
	}

	/**
	 * 根据编码获取饰品现有量<br>
	 * 允许不属于本组织的饰品盘点 2012-2-1 10:06
	 * @param ornaCode 编码
	 */
	public Map<String, Object> getMaterActiveByOrnaCode(String ornaCode, String orgId, String stockId) {
		PackageMaterActive materActive = this.materActiveManager.getMAByOrnaCOde(ornaCode, null, stockId);
		return this.checkMater(materActive, orgId, stockId);
	}
	
	/**
	 * 根据条码获取饰品现有量<br>
	 * 允许不属于本组织的饰品盘点 2012-2-1 10:06
	 * @param barCode 条码
	 * @param orgId 组织id
	 */
	public Map<String, Object> getMaterActiveByBarCode(String barCode, String orgId, String stockId) {
		PackageMaterActive materActive = this.materActiveManager.getMAByBarCode(barCode, null, stockId);
		return this.checkMater(materActive, orgId, stockId);
	}
	
	private Map<String, Object> checkMater(PackageMaterActive materActive, String orgId, String stockId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != materActive){
			if(stockId.equals(materActive.getStockId())){
				result.put("isSuccess", "true");
				result.put("data", this.dealMaterActive(materActive));
			} else {
				result.put("isSuccess", "false");
				result.put("msg", "饰品不属于当前子库");
			}
		} else {
			result.put("isSuccess", "false");
			result.put("msg", "饰品不存在");
		}
		return result;
	}
	
	/**
	 * 金额和重量的小数处理以及一些id转化为名称
	 * @param packageMA
	 * @return
	 */
	private PackageMaterActive dealMaterActive(PackageMaterActive packageMA) {
		if(packageMA == null) return  packageMA;
		
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
		if(StringUtil.isNotBlank(packageMA.getNowQty())) {
			packageMA.setNowQty(Double.valueOf(packageMA.getNowQty())+"");
		}else {
			packageMA.setNowQty("0");
		}
		
//		String itemClassName = BdCommon.getItemClassDesc(packageMA.getItemClassId());
		packageMA.setItemClassName(ItemClassCache.getInstance().getItemClassDesc(packageMA.getItemClassId()));
//		String ornaClassName = BdCommon.getOrnaClassDesc(packageMA.getOrnaClassId());
		packageMA.setOrnaClassName(OrnaClassCache.getInstance().getOrnaClass(packageMA.getOrnaClassId()));
//		String styleName = this.bdCommonManager.getStyleNameById(packageMA.getStyleId());
//		packageMA.setStyleName(packageMA.getStyleName());
//		String analysisName = this.bdCommonManager.getAnalysisNameById(packageMA.getAlaysisId());
//		packageMA.setAlaysisName(packageMA.getAlaysisName());
//		String saleUnitName = BdCommon.getUnitName(packageMA.getSaleUnitId());
		packageMA.setSaleUnitName(UnitCache.getInstance().getNameById(packageMA.getSaleUnitId()));
		return packageMA;
	}
	
	
	@SuppressWarnings({ "rawtypes"})
	public Analysis getAnalysis(Map params) {
		String allQty = (String)params.get("allQty");
		String mainWeight = (String)params.get("mainWeight");
		String basicPrice = (String)params.get("basePrice");
		String itemClassId = (String)params.get("itemClassId");
		String ornaClassId = (String)params.get("ornaClassId");
		Analysis analysis = this.commonManager.getAnalysis(itemClassId, ornaClassId, allQty, basicPrice, mainWeight);
		return analysis;
	}
	
	public Map<String, String> saveOrUpdateInventory(ProcInventoryHead packageHead, List<ProcInventoryLine> oldLines, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			if(StringUtil.isEmpty(packageHead.getBillid())){
				packageHead.setCreateDate(DateUtil.getCurrentDate18());
				packageHead.setCreateId(CommonUtil.getSessionUserId(req));
				packageHead.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_CHAIHUNBAO);
				packageHead.setBillno(billNo);
			}
			packageHead.setUpdateDate(DateUtil.getCurrentDate18());
			packageHead.setUpdateId(CommonUtil.getSessionUserId(req));
			
			procInventoryHeadManager.saveOrUpdateProcInventoryHead(packageHead, oldLines);
			result.put("isSuccess", "true");
			result.put("billid", packageHead.getBillid());
			result.put("billno", packageHead.getBillno());
			
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "保存出错");
			}
			
			return result;
		}
		return result;
	}
	
	/**
	 * 取得仓库饰品数量
	 * @param stockId
	 * @param orgId
	 * @return
	 */
	public String getStockCount(String stockId, String orgId) {
		return this.materActiveManager.getStockCount(orgId, stockId);
	}
	
	public String closePackageHead(String billid, HttpServletRequest req) {
		try{
			this.procInventoryHeadManager.closeInventoryHead(billid, CommonUtil.getSessionUserId(req));
		} catch(Exception e){
			if(StringUtil.isNotBlank(e.getMessage())){
				return  e.getMessage();
			} else {
				return "关闭出错";
			}
		}
		return null;
	}
	
	/**
	 * 判断饰品是否已经盘点过
	 * @param ornaCode
	 * @param billid
	 * @return
	 */
	public boolean isExistOrnaCode(String ornaCode, String billid) {
		return procInventoryLineManager.isExistOrnaCode(ornaCode, billid);
	}
	
	public Map<String, String> changeIsmain(String billid, String orgId, String stockId, boolean checked) {
		Map<String , String > result = new HashMap<String, String>();
		try {
			boolean isSuccess = this.procInventoryHeadManager.changeIsmain(billid, orgId, stockId, checked);
			if(isSuccess) {
				result.put("isSuccess", "true");
			} else {
				result.put("isSuccess", "false");
				result.put("msg", "一个仓库只允许一个主单");
			}
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "修改主单失败");
			}
		}
		return result;
	}
	
	/**
	 * 合并盘点单据
	 * @param billids
	 * @param req
	 * @return
	 */
	public Map<String, String> mergeBills(String billids, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			boolean isSuccess = this.procInventoryHeadManager.mergeBills(billids, CommonUtil.getSessionUserId(req));
			if(isSuccess) {
				result.put("isSuccess", "true");
			} else {
				result.put("isSuccess", "false");
				result.put("msg", "合并失败。");
			}
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "合并失败。");
			}
		}
		return result;
	}
	
	public Map<String, String> createDiffBill(String billid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.procInventoryDiffheadManager.createDiffBill(billid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch (Exception e){
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", "创建差异单失败");
		}
		return result;
	}
	
	public Map<String, String> closeBill(String billid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.procInventoryHeadManager.closeInventoryHead(billid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch (Exception e){
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "关闭失败");
			}
		}
		return result;
	}
	
	public Map<String, String> openBill(String billid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.procInventoryHeadManager.openInventoryHead(billid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch (Exception e){
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "开启失败");
			}
		}
		return result;
	}
	
}
