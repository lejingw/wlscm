package com.jatools.web.dwr.sys;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.sys.ParameterManager;
import com.jatools.vo.sys.Parameter;

public class ParameterDwr {

	private static Logger logger = Logger.getLogger(ParameterDwr.class);

	private ParameterManager parameterManager;
	
	public void setParameterManager(ParameterManager parameterManager) {
		this.parameterManager = parameterManager;
	}
	
	public String updateParameter(Parameter parameter, HttpServletRequest req) {
		try{
			this.parameterManager.updateParameter(parameter, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "保存出错";
		}
	}
}
