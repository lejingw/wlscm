package com.jatools.web.dwr.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.tag.LabelTagImportManager;
import com.jatools.vo.tag.LabelTagImportHead;
import com.jatools.vo.tag.LabelTagImportLine;
import com.jatools.web.util.StringUtil;

public class LabelTagImportDwr {

	private static Logger logger = Logger.getLogger(LabelTagImportDwr.class);
	
	private LabelTagImportManager labelTagImportManager;
	/*private CommonManager commonManager;

	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}*/

	public void setLabelTagImportManager(LabelTagImportManager labelTagImportManager) {
		this.labelTagImportManager = labelTagImportManager;
	}

	public String saveOrUpdateLabelImport(LabelTagImportHead labelTagImportHead, List<LabelTagImportLine> lines, String deleteIds, HttpServletRequest req) {
		try{
			labelTagImportManager.saveOrUpdateLabelTagImportHead(labelTagImportHead, lines, deleteIds, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	
	
	public String deleteLabelImport(String headid, HttpServletRequest req) {
		try{
			if(StringUtil.isNotBlank(headid)){
				this.labelTagImportManager.deleteLabelTagImportHead(headid);
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "删除失败";
		}
	}
	
	public Map<String, String> saveAndCheckLabelImport(LabelTagImportHead labelTagImportHead, List<LabelTagImportLine> lines, String deleteIds, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.labelTagImportManager.saveAndCheckLabelTagImportHead(labelTagImportHead, lines, deleteIds, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("headid", labelTagImportHead.getHeadid());
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "提交审核失败");
			}
		}
		return result;
	}
	
	
	public Map<String, String> closeLabelImport(String headid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.labelTagImportManager.closeLabelTagImport(headid, CommonUtil.getSessionUserId(req));
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
	
}
