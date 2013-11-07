package com.jatools.web.dwr.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.tag.LabelTagApplyManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.tag.LabelReasonInfo;
import com.jatools.vo.tag.LabelTagApply;
import com.jatools.vo.tag.LabelTagApplyLine;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.StringUtil;

public class LabelTagApplyDwr extends BaseLabelTagDwr{

	private static Logger logger = Logger.getLogger(LabelTagApplyDwr.class);
	
	private LabelTagApplyManager labelTagApplyManager;
	private CommonManager commonManager;

	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}

	public void setLabelTagApplyManager(LabelTagApplyManager labelTagApplyManager) {
		this.labelTagApplyManager = labelTagApplyManager;
	}

	public String saveOrUpdateLabelApply(LabelTagApply labelTagApply, List<LabelTagApplyLine> lines, String deleteIds, HttpServletRequest req) {
		try{
			labelTagApplyManager.saveOrUpdateLabelTagApply(labelTagApply, lines, deleteIds, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	
	public String deleteLabelApply(String billid, HttpServletRequest req) {
		try{
			if(StringUtil.isNotBlank(billid)){
				this.labelTagApplyManager.deleteLabelTagApply(billid);
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
	
	public Map<String, String> saveAndCheckLabelApply(LabelTagApply labelTagApply, List<LabelTagApplyLine> lines, String deleteIds, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.labelTagApplyManager.saveAndCheckLabelTagApply(labelTagApply, lines, deleteIds, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("id", labelTagApply.getId());
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
	
	
	public Map<String, String> closeBill(LabelTagApply labelTagApply, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.labelTagApplyManager.closeLabelTagApply(labelTagApply, CommonUtil.getSessionUserId(req));
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
	
	public List<SelectorOption> getApplyReason(){
		return this.labelTagApplyManager.getApplyReason();
	}
	
	public LabelReasonInfo getApplyReasoninfo(String labelId){
		return this.labelTagApplyManager.getApplyReasoninfo(labelId);
	}
	
	
	public List<SelectorOption> getCurrentOrgList(HttpServletRequest req){
		String userId = CommonUtil.getSessionUserId(req);
		List<Org> orgList = commonManager.getCurrentUserOrgList(userId);
		List<SelectorOption> select = new ArrayList<SelectorOption>();
		for(Org org : orgList){
			select.add(new SelectorOption(org.getOrgId(), org.getOrgName()));
		}
		return select;
	}
}
