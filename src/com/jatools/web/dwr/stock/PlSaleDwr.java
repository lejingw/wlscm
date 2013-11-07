package com.jatools.web.dwr.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.manager.stock.MaterActiveManager;
import com.jatools.manager.stock.PlSaleManager;
import com.jatools.vo.bd.Style;
import com.jatools.vo.stock.PackageMaterActive;
import com.jatools.vo.stock.PlSaleHead;
import com.jatools.vo.stock.PlSaleLine;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.StringUtil;


public class PlSaleDwr {
	private static Logger logger = Logger.getLogger(PlSaleDwr.class);
	
	private PlSaleManager plSaleManager;
	
	public void setPlSaleManager(PlSaleManager PlSaleManager) {
		this.plSaleManager = PlSaleManager;
	}

	public String saveOrUpdatePlSale(PlSaleHead PlSaleHead, List<PlSaleLine> lines, String deleteIds, HttpServletRequest req) {
		try{
			plSaleManager.saveOrUpdatePlSaleHead(PlSaleHead, lines, deleteIds, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存失败";
		}
	}
	
	public Map<String, String> saveAndCheckPlSale(PlSaleHead PlSaleHead, List<PlSaleLine> lines, String deleteIds, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			plSaleManager.saveAndCheckPlSaleHead(PlSaleHead, lines, deleteIds, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("billid", PlSaleHead.getBillid());
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	public String deletePlSale(String billid, HttpServletRequest req) {
		try {
			if(StringUtil.isNotBlank(billid)){
				this.plSaleManager.deletePlSaleHead(billid);
			}
			return null;
		} catch(Exception e) {
			logger.error(e);
			return "删除失败";
		}
	}
	
	public String closePlSale(String billid, HttpServletRequest req) {
        try {
            this.plSaleManager.closePlSale(billid, CommonUtil.getSessionUserId(req));
        } catch (Exception e) {
            if(StringUtil.isNotBlank(e.getMessage())) {
                return e.getMessage();
            }
            return "关闭失败";
        }

		return null;
	}

    public String destroyPlSale(String billid, HttpServletRequest req) {
        try {
		    this.plSaleManager.destroyPlSale(billid, CommonUtil.getSessionUserId(req));
        } catch (Exception e) {
            if(StringUtil.isNotBlank(e.getMessage())) {
                return e.getMessage();
            }
            return "撤销失败";
        }
		return null;
	}
	
	
	/**
	 * 根据编码获取饰品现有量<br>
	 * @param ornaCode 编码
	 */
	public Map<String, Object> getMaterActiveByOrnaCode(String ornaCode, String orgId, String stockId, String vendorId) {
		PackageMaterActive materActive = this.materActiveManager.getChangeMAByOrnaCode(ornaCode, orgId, stockId);
		if(materActive == null){
			materActive = this.materActiveManager.getMaterNoactiveByOrnaCode(ornaCode);
            if(materActive != null){
                materActive.setOutFlag("1");
            }
		} else {
            materActive.setOutFlag("0");
        }
		return this.checkMater(materActive, orgId, stockId, vendorId);
	}
	
	/**
	 * 根据条码获取饰品现有量<br>
	 * @param barCode 条码
	 * @param orgId 财务组织id
	 */
	public Map<String, Object> getMaterActiveByBarCode(String barCode, String orgId, String stockId, String vendorId) {
		PackageMaterActive materActive = this.materActiveManager.getChangeMAByBarCode(barCode, orgId, stockId);
		if(materActive == null){
			materActive = this.materActiveManager.getMaterNoactiveByBarCode(barCode);
            if(materActive != null){
                materActive.setOutFlag("1");
            }
		} else {
            materActive.setOutFlag("0");
        }
		return this.checkMater(materActive, orgId, stockId, vendorId);
	}
	
	private Map<String, Object> checkMater(PackageMaterActive materActive, String orgId, String stockId, String vendorId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != materActive){
			/*if(orgId.equals(materActive.getOrgId())){*/
				if(stockId.equals(materActive.getStockId())){
					if(vendorId.equals(materActive.getSupplierId())){
						if("1".equals(materActive.getIsConsign())){
							result.put("isSuccess", "true");
							result.put("data", this.dealMaterActive(materActive));
						} else {
							result.put("isSuccess", "false");
							result.put("msg", "饰品必须是代销饰品");
						}
					} else {
						result.put("isSuccess", "false");
						result.put("msg", "饰品不属于所选供应商");
					}
				} else {
					result.put("isSuccess", "false");
					result.put("msg", "饰品不属于当前子库");
				}
			/*} else {
				result.put("isSuccess", "false");
				result.put("msg", "饰品不属于当前组织");
			}*/
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
		Style style= this.bdCommonManager.getStyleById(packageMA.getStyleId());
		if(style != null){
			packageMA.setStyleitemclass(style.getStyleitemclass());
			packageMA.setStylemiddleclass(style.getStylemiddleclass());
			packageMA.setStyleornaclass(style.getStyleornaclass());
		}
		return packageMA;
	}

    public String isExistsOrnaCode(String billid, String ornaCode){
        boolean isExists = this.plSaleManager.isExistsOrnaCode(billid, ornaCode);
        if(isExists) {
            return "饰品编码[" + ornaCode +"]已经存在";
        }
        isExists = this.plSaleManager.isExistsInOtherBill(billid, ornaCode);
        if(isExists) {
            return "饰品编码[" + ornaCode +"]已经在其他单据中存在";
        }
        return null;
    }


	private MaterActiveManager materActiveManager;
	private BdCommonManager bdCommonManager;
	
	public void setBdCommonManager(BdCommonManager dbCommonManager) {
		this.bdCommonManager = dbCommonManager;
	}
	public void setMaterActiveManager(MaterActiveManager materActiveManager) {
		this.materActiveManager = materActiveManager;
	}
}
