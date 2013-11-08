package com.jatools.web.dwr.stock;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.manager.stock.MaterActiveManager;
import com.jatools.manager.stock.ProcChangeHeadManager;
import com.jatools.vo.stock.PackageMaterActive;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.StringUtil;

public class BaseChangeDwr {
	private static Logger logger = Logger.getLogger(BaseChangeDwr.class);

	private MaterActiveManager materActiveManager;
	private BdCommonManager bdCommonManager;
	private ProcChangeHeadManager procChangeHeadManager;
	public void setProcChangeHeadManager(ProcChangeHeadManager ProcChangeHeadManager) {
		this.procChangeHeadManager = ProcChangeHeadManager;
	}
	public void setBdCommonManager(BdCommonManager dbCommonManager) {
		this.bdCommonManager = dbCommonManager;
	}
	public void setMaterActiveManager(MaterActiveManager materActiveManager) {
		this.materActiveManager = materActiveManager;
	}
	
	/**
	 * 根据编码获取饰品现有量<br>
	 * @param ornaCode 编码
	 */
	public Map<String, Object> getMaterActiveByOrnaCode(String ornaCode, String orgId, String stockId) {
		PackageMaterActive materActive = this.materActiveManager.getChangeMAByOrnaCode(ornaCode, orgId, stockId);
		return this.checkMater(materActive, orgId, stockId);
	}
	
	/**
	 * 根据条码获取饰品现有量<br>
	 * @param barCode 条码
	 * @param orgId 组织id
	 */
	public Map<String, Object> getMaterActiveByBarCode(String barCode, String orgId, String stockId) {
		PackageMaterActive materActive = this.materActiveManager.getChangeMAByBarCode(barCode, orgId, stockId);
		return this.checkMater(materActive, orgId, stockId);
	}
	
	private Map<String, Object> checkMater(PackageMaterActive materActive, String orgId, String stockId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != materActive){
			if(orgId.equals(materActive.getOrgId())){
				if(DictConstant.BILL_STATUS_MATER_ACTIVE_VALID.equals(materActive.getState())){
					if(stockId.equals(materActive.getStockId())){
						result.put("isSuccess", "true");
						result.put("data", this.dealMaterActive(materActive));
					} else {
						result.put("isSuccess", "false");
						result.put("msg", "饰品不属于当前子库");
					}
				} else {
					result.put("isSuccess", "false");
					result.put("msg", "饰品已在使用中");
				}
			} else {
				result.put("isSuccess", "false");
				result.put("msg", "饰品不属于当前组织");
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
		if(StringUtil.isNotBlank(packageMA.getIsConsign())) {
			packageMA.setIsConsign(StringUtil.formatDouble(packageMA.getIsConsign(), 0));
		}else {
			packageMA.setIsConsign("");
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
		return packageMA;
	}
	
	public String closeChangeHead(String billid, HttpServletRequest req) {
		this.procChangeHeadManager.closeChangeHead(billid, CommonUtil.getSessionUserId(req));
		return null;
	}
	
	public String checkChangeHead(String billid, HttpServletRequest req) {
		this.procChangeHeadManager.checkChangeHead(billid, CommonUtil.getSessionUserId(req));
		return null;
	}
	
	public String deleteChangeHead(String billid, HttpServletRequest req) {
		this.procChangeHeadManager.deleteProcChangeHead(billid);
		return null;
	}
	
	public Map<String, String> checkBill(String billid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.procChangeHeadManager.checkChangeHead(billid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	public String deleteBill(String billid, HttpServletRequest req) {
		try {
			if(StringUtil.isNotBlank(billid)){
				this.procChangeHeadManager.deleteProcChangeHead(billid);
			}
			return null;
		} catch(Exception e) {
			logger.error(e);
			return "删除失败";
		}
	}
}
