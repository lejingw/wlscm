package com.jatools.web.dwr.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.tag.LabelTagApplyPrintManager;
import com.jatools.vo.tag.LabelTagApplyPrint;
import com.jatools.vo.tag.LabelTagApplyPrintLine;
import com.jatools.web.util.StringUtil;

public class LabelTagApplyPrintDwr extends BaseLabelTagDwr {

	private static Logger logger = Logger.getLogger(LabelTagApplyPrintDwr.class);
	
	private LabelTagApplyPrintManager labelTagApplyPrintManager;
	
	public void setLabelTagApplyPrintManager(LabelTagApplyPrintManager LabelTagApplyPrintManager) {
		this.labelTagApplyPrintManager = LabelTagApplyPrintManager;
	}

	public String saveOrUpdateLabelApply(LabelTagApplyPrint LabelTagApplyPrint, List<LabelTagApplyPrintLine> lines, String deleteIds, HttpServletRequest req) {
		try{
			labelTagApplyPrintManager.saveOrUpdateLabelTagApplyPrint(LabelTagApplyPrint, lines, deleteIds, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	
	public Map<String, String> saveAndMarkLabelApply(LabelTagApplyPrint LabelTagApplyPrint, List<LabelTagApplyPrintLine> lines, String deleteIds, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.labelTagApplyPrintManager.saveAndMarkLabelTagApplyPrint(LabelTagApplyPrint, lines, deleteIds, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("id", LabelTagApplyPrint.getId());
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "处理失败");
			}
		}
		return result;
	}
	
	public Map<String, String> receiveLabelApplyPrint(LabelTagApplyPrint LabelTagApplyPrint, List<LabelTagApplyPrintLine> lines, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.labelTagApplyPrintManager.receiveLabelApplyPrint(LabelTagApplyPrint, lines, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("id", LabelTagApplyPrint.getId());
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "处理失败");
			}
		}
		return result;
	}
	
	public Map<String, String> initLabelApplyPrint(String headid, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.labelTagApplyPrintManager.initLabelApplyPrint(headid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "处理失败");
			}
		}
		return result;
	}
	
	public Map<String, String> remarkBill(LabelTagApplyPrint LabelTagApplyPrint, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.labelTagApplyPrintManager.remarkLabelTagApplyPrint(LabelTagApplyPrint, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
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
	
	public Map<String, String> closeLabelPrint(String headid, String ornaNotInMater, String ornaNotInOrg,  HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.labelTagApplyPrintManager.closeLabelPrint(headid, ornaNotInMater, ornaNotInOrg,  CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
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

	public Map<String, String> createLabelPrint(String orgIds, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			result = this.labelTagApplyPrintManager.createLabelPrint(orgIds, CommonUtil.getSessionUserId(req));
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", "创建失败");
		}
		return result;
	}
	
	
	public Map<String, String> checkPrintOrnaCode(String headid, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			result = this.labelTagApplyPrintManager.checkPrintOrnaCode(headid);
		} catch(Exception e){
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "关闭失败");
			}
			result.put("isSuccess", "false");
		}
		return result;
	}
	
	
	public Map<String, Object> getLineByOrnaCode(String labelId, String ornaCode){
		return this.getLine(labelId, ornaCode, null);
	}
	public Map<String, Object> getLineByOrnaBarCode(String labelId, String ornaBarCode){
		return this.getLine(labelId, null, ornaBarCode);
	}
	
	private Map<String, Object> getLine(String labelId, String ornaCode, String ornaBarCode){
		Map<String, Object> result = new HashMap<String, Object>();
		LabelTagApplyPrintLine line = this.labelTagApplyPrintManager.getPrintLine(labelId, ornaCode, ornaBarCode);
		if(line != null ){
			beforeView(line);
			result.put("isSuccess", "true");
			result.put("line", line);
		} else {
			result.put("isSuccess", "false");
			result.put("msg", "饰品不在单据中 或 饰品已不在现有量库中");
		}
		return result;
	}
	
	
	public Map<String, Object> updateMaterData(String billids, HttpServletRequest req){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			this.labelTagApplyPrintManager.updateMaterData(billids, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e){
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "更新数据失败");
			}
		}
		return result;
	}
	
	public Map<String, Object> cancelPrintBill(String billids, HttpServletRequest req){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			this.labelTagApplyPrintManager.cancelPrintBill(billids, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e){
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "更新数据失败");
			}
		}
		return result;
	}
	
	public Map<String, Object> revocatData(String billids, HttpServletRequest req){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			this.labelTagApplyPrintManager.revocatMater(billids, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e){
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "更新数据失败");
			}
		}
		return result;
	}
	
	private void beforeView(LabelTagApplyPrintLine line){
		if(line != null){
			if(StringUtil.isNotBlank(line.getNum())){
				line.setNum(StringUtil.formatDouble(line.getNum(), 6));
			} else {
				line.setNum("0");
			}
			if(StringUtil.isNotBlank(line.getWeight())){
				line.setWeight(StringUtil.formatDouble(line.getWeight(), 6));
			} else {
				line.setWeight("0");
			}
			if(StringUtil.isNotBlank(line.getOldBasicPrice())){
				line.setOldBasicPrice(StringUtil.formatDouble(line.getOldBasicPrice(), 6));
			} else {
				line.setOldBasicPrice("");
			}
			if(StringUtil.isNotBlank(line.getNewBasicPrice())){
				line.setNewBasicPrice(StringUtil.formatDouble(line.getNewBasicPrice(), 6));
			} else {
				line.setNewBasicPrice("");
			}
			if(StringUtil.isNotBlank(line.getOldSpecialWorkPrice())){
				line.setOldSpecialWorkPrice(StringUtil.formatDouble(line.getOldSpecialWorkPrice(), 6));
			} else {
				line.setOldSpecialWorkPrice("");
			}
			if(StringUtil.isNotBlank(line.getNewSpecialWorkPrice())){
				line.setNewSpecialWorkPrice(StringUtil.formatDouble(line.getNewSpecialWorkPrice(), 6));
			} else {
				line.setNewSpecialWorkPrice("0");
			}
			
			if(StringUtil.isNotBlank(line.getOldAmount())){
				line.setOldAmount(StringUtil.formatDouble(line.getOldAmount(), 6));
			} else {
				line.setOldAmount("");
			}
			if(StringUtil.isNotBlank(line.getNewAmount())){
				line.setNewAmount(StringUtil.formatDouble(line.getNewAmount(), 6));
			} else {
				line.setNewAmount("");
			}
		}
	}
}
