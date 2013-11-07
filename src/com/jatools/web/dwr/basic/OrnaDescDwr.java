package com.jatools.web.dwr.basic;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.basic.OrnaDescManager;
import com.jatools.vo.basic.OrnaDesc;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class OrnaDescDwr {
	private static Logger logger = Logger.getLogger(OrnaDescDwr.class);
	
	private OrnaDescManager ornaDescManager;
	
	/**
	 * 保存或修改
	 * @param orgGroup
	 */
	public Map<String, String> saveOrUpdateOrnaDesc(OrnaDesc ornaDesc, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			if(StringUtil.isEmpty(ornaDesc.getOrnadscId())){
				ornaDesc.setCreatedate(DateUtil.getCurrentDate18());
				ornaDesc.setCreateid(CommonUtil.getSessionUserId(req));
				ornaDesc.setStatus(DictConstant.BILL_STATUS_SAVE);
			}
			ornaDesc.setUpdatedate(DateUtil.getCurrentDate18());
			ornaDesc.setUpdateid(CommonUtil.getSessionUserId(req));
			this.ornaDescManager.saveOrUpdateOrnaDesc(ornaDesc, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("ornadscId", ornaDesc.getOrnadscId());
			return result;
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
	}
	
	public String deleteOrnaDesc(String ornaDescId, HttpServletRequest req){
		try {
			if(StringUtil.isNotEmpty(ornaDescId)){
				this.ornaDescManager.deleteOrnaDesc(ornaDescId);
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

	public void setOrnaDescManager(OrnaDescManager ornaDescManager) {
		this.ornaDescManager = ornaDescManager;
	}
	
}
