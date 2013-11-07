package com.jatools.web.dwr.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.vo.stock.MaterActive;
import com.jatools.web.util.StringUtil;

public class BaseLabelTagDwr {

	private MaterActiveDao materActiveDao;
	
	public void setMaterActiveDao(MaterActiveDao materActiveDao) {
		this.materActiveDao = materActiveDao;
	}
	
	/**
	 * 取饰品 根据 饰品编码
	 * @param ornaCode
	 * @param req
	 * @return
	 */
	public Map<String, Object> getMaterByOrnaCode(String ornaCode, String orgId, HttpServletRequest req){
		MaterActive mater = this.materActiveDao.getMaterActiveForLabelByOrnaCode(ornaCode);
		return this.checkMater(mater, orgId);
	}
	
	/**
	 * 取饰品  根据 条码
	 * @param ornaBarCode
	 * @param req
	 * @return
	 */
	public Map<String, Object> getMaterByOrnaBarCode(String ornaBarCode, String orgId, HttpServletRequest req){
		MaterActive mater = this.materActiveDao.getMaterActiveForLabelByornaBarcode(ornaBarCode);
		return this.checkMater(mater, orgId);
	}
	
	private Map<String, Object> checkMater(MaterActive mater, String orgId){
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != mater){
			if(orgId.equals(mater.getOrgId())){
				if("900".equals(mater.getState())){
					if("1".equals(mater.getStockId())){
						beforeView(mater);
						result.put("isSuccess", "true");
						result.put("mater", mater);
					} else {
						result.put("isSuccess", "false");
						result.put("msg", "饰品不在饰品库中");
					}
				}else {
					result.put("isSuccess", "false");
					result.put("msg", "饰品被使用中");
				}
			} else {
				result.put("isSuccess", "false");
				result.put("msg", "饰品不属于当前组织");
			}
		} else {
			result.put("isSuccess", "false");
			result.put("msg", "饰品不存在 或 饰品已经创建了尚未关闭的申请单（打印单）");
		}
		return result;
	}
	
	private void beforeView(MaterActive mater){
		if(mater != null){
			if(StringUtil.isNotBlank(mater.getNowQty())){
				mater.setNowQty(StringUtil.formatDouble(mater.getNowQty(), 6));
			} else {
				mater.setNowQty("0");
			}
			if(StringUtil.isNotBlank(mater.getAllQty())){
				mater.setAllQty(StringUtil.formatDouble(mater.getAllQty(), 6));
			} else {
				mater.setAllQty("0");
			}
			if(StringUtil.isNotBlank(mater.getBasicPrice())){
				mater.setBasicPrice(StringUtil.formatDouble(mater.getBasicPrice(), 6));
			} else {
				mater.setBasicPrice("");
			}
			if(StringUtil.isNotBlank(mater.getSpecialWorkPrice())){
				mater.setSpecialWorkPrice(StringUtil.formatDouble(mater.getSpecialWorkPrice(), 6));
			} else {
				mater.setSpecialWorkPrice("");
			}
			if(StringUtil.isNotBlank(mater.getPosAmount())){
				mater.setPosAmount(StringUtil.formatDouble(mater.getPosAmount(), 6));
			} else {
				mater.setPosAmount("");
			}
		}
	}
}
