package com.jatools.web.dwr.out;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.out.ConsumeManager;
import com.jatools.vo.out.Consume;
import com.jatools.vo.out.ConsumeLine;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.out.ConsumeController;

public class ConsumeDwr {
	private ConsumeManager consumeManager;
	private Logger logger = Logger.getLogger(ConsumeController.class);
	
	public void setConsumeManager(ConsumeManager consumeManager) {
		this.consumeManager = consumeManager;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public Map<String, String> saveOrUpdateConsume(Consume Consume, List<ConsumeLine> lines, String deleteIds ,HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.consumeManager.saveOrUpdateConsume(Consume, lines, deleteIds, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("billid", Consume.getBillid());
			return result;
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "保存失败");
			}
			return result;
		}
	}
	
	public Map<String, String> saveAndCheckConsume(Consume Consume, List<ConsumeLine> lines, String deleteIds ,HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.consumeManager.saveAndCheckConsume(Consume, lines, deleteIds, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("billid", Consume.getBillid());
			return result;
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "审批失败");
			}
			return result;
		}
	}
	
	public Map<String, String> deleteConsume(String billid, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.consumeManager.deleteConsume(billid);
			result.put("isSuccess", "true");
			return result;
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "删除失败");
			}
			return result;
		}
	}
	
	public Map<String, String> closeConsume(String billid, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.consumeManager.closeConsume(billid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("billid", billid);
			return result;
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "关闭失败");
			}
			return result;
		}
	}
}
