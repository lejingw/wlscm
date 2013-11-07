package com.jatools.web.dwr.basic;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.CreateOldbarCodeManager;

public class CreateOldbarCodeDwr {
	private static Logger logger = Logger.getLogger(CreateOldbarCodeDwr.class);
	private CreateOldbarCodeManager createOldbarCodeManager;

	public void setCreateOldbarCodeManager(CreateOldbarCodeManager createOldbarCodeManager) {
		this.createOldbarCodeManager = createOldbarCodeManager;
	}

	public Long generateOldbarCode(String codeType, String year, String month, Long numCount, HttpSession session){
		Long startIndex = createOldbarCodeManager.generateOldbarCode(codeType, year, month, numCount, CommonUtil.getSessionUserId(session));
		return startIndex;
	}
}
