package com.jatools.web.dwr.push;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.jatools.common.CommonUtil;
import com.jatools.manager.push.ExcludeOrnaManager;
import com.jatools.vo.push.ExcludeOrna;

public class ExcludeOrnaDwr {
	private ExcludeOrnaManager excludeOrnaManager;
	
	public void setExcludeOrnaManager(ExcludeOrnaManager excludeOrnaManager) {
		this.excludeOrnaManager = excludeOrnaManager;
	}
	public ExcludeOrna getMaterActiveInfo(String code, boolean ornaFlag){
		ExcludeOrna orna = excludeOrnaManager.getMaterActiveByCode(code, ornaFlag);
		return orna;
	}
	
	public String saveExcludeOrna(String ornaCode, HttpServletRequest req){
		String userId = CommonUtil.getSessionUserId(req);
		if(excludeOrnaManager.checkExcludeOrnaRepeat(ornaCode)>0){
			return "该饰品编码已经存在"; 
		}
		excludeOrnaManager.saveExcludeOrna(ornaCode, userId);
		return null;
	}
	public String saveExcludeOrna2(List<String> ornaCodeList, HttpServletRequest req){
		String userId = CommonUtil.getSessionUserId(req);
		excludeOrnaManager.saveExcludeOrna(ornaCodeList, userId);
		return null;
	}
	
	public String deleteExcludeOrna(List<String> billIdList){
		excludeOrnaManager.deleteExcludeOrna(billIdList);
		return null;
	}
}
