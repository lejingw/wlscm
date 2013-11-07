package com.jatools.ws.review.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;

import com.jatools.dao.sys.BillReviewDao;
import com.jatools.ws.review.ViewReviewLogRemoteService;

public class ViewReviewLogRemoteServiceImpl implements ViewReviewLogRemoteService {
	private Logger logger = Logger.getLogger(EnterReviewRemoteServiceImpl.class);
	private BillReviewDao billReviewDao;
	
	public void setBillReviewDao(BillReviewDao billReviewDao) {
		this.billReviewDao = billReviewDao;
	}
	/**
	 * 本地模拟，获取审批记录
	 */
	public String getReviewLog(String billCode, String billId){
		logger.debug("------------------本地模拟，获取审批记录---------------------");
		List<Map<String, String>> list = billReviewDao.getReviewLog2(billCode, billId);
		JSONArray ja = new JSONArray();
		for(Map<String, String> map : list){
			ja.add(map);
		}
		return ja.toString();
	}
}
