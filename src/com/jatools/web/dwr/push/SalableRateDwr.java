package com.jatools.web.dwr.push;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jatools.common.CommonUtil;
import com.jatools.manager.push.SalableRateManager;
import com.jatools.vo.push.SalableRate;

public class SalableRateDwr {
	private SalableRateManager salableRateManager;
	
	public void setSalableRateManager(SalableRateManager salableRateManager) {
		this.salableRateManager = salableRateManager;
	}

	public String saveOrUpdateSalableRate(SalableRate dn, HttpServletRequest req){
		String userId = CommonUtil.getSessionUserId(req);
		salableRateManager.saveOrUpdateSalableRate(dn, userId);
		return null;
	}
	
	public String deleteSalableRate(List<String> billIdList){
		salableRateManager.deleteSalableRate(billIdList);
		return null;
	}
}