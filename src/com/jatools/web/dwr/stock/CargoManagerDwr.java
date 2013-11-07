package com.jatools.web.dwr.stock;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;

import com.jatools.common.CommonUtil;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.manager.stock.CargoManager;
import com.jatools.manager.stock.MaterActiveManager;
import com.jatools.vo.stock.PackageMaterActive;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.StringUtil;

/**
 * 形态转换
 * @author ren.ming
 * Created 2011-11-24
 */
public class CargoManagerDwr {
	//private static Logger logger = Logger.getLogger(CargoManagerDwr.class);
	
	private MaterActiveManager materActiveManager;
	private BdCommonManager bdCommonManager;
	private CargoManager cargoManager;
	
	public void setCargoManager(CargoManager cargoManager) {
		this.cargoManager = cargoManager;
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
	public Map<String, Object> getMaterByOrnaCode(String ornaCode, HttpServletRequest req) {
		PackageMaterActive materActive = this.cargoManager.getMaterByOrnaCode(ornaCode);
		return this.getMater(materActive, req);
	}
	
	/**
	 * 根据条码获取饰品现有量<br>
	 * @param barCode 条码
	 * @param orgId 财务组织id
	 */
	public Map<String, Object> getMaterByBarCode(String ornaBarCode, HttpServletRequest req) {
		PackageMaterActive materActive = this.cargoManager.getMaterByOrnaBarCode(ornaBarCode);
		return this.getMater(materActive, req);
	}
	
	
	private Map<String, Object> getMater(PackageMaterActive materActive, HttpServletRequest req){
		Map<String, Object> result = new HashMap<String, Object>();
		String orgId = CommonUtil.getSessionOrgId(req);
		if(materActive != null){
			if(orgId.equals(materActive.getOrgId())){
				materActive = this.dealMaterActive(materActive);
				result.put("isSuccess", "true");
				result.put("data", materActive);
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
			packageMA.setPosAmount("0");
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
	
	
	public String addMaterTip(String ornaCodes, HttpServletRequest req) {
		if(StringUtil.isNotBlank(ornaCodes)) {
			String ornaCodeArray[] = ornaCodes.split(";");
			if(!ArrayUtils.isEmpty(ornaCodeArray)) {
				for(String ornaCode : ornaCodeArray) {
					if(StringUtil.isNotBlank(ornaCode)) {
						this.materActiveManager.addMaterMaterial(ornaCode);
					}
				}
			}
		}
		return null;
	}
	
	public String deleteMaterTip(String ornaCodes, HttpServletRequest req) {
		if(StringUtil.isNotBlank(ornaCodes)) {
			String ornaCodeArray[] = ornaCodes.split(";");
			if(!ArrayUtils.isEmpty(ornaCodeArray)) {
				for(String ornaCode : ornaCodeArray) {
					if(StringUtil.isNotBlank(ornaCode)) {
						this.materActiveManager.deleteMaterMaterial(ornaCode);
					}
				}
			}
		}
		return null;
	}
}
