package com.jatools.web.dwr.inout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.inout.ReceiveHeadManager;
import com.jatools.vo.inout.ReceiveHead;
import com.jatools.vo.inout.ReceiveLine;
import com.jatools.web.util.StringUtil;

public class ReceiveDwr {

	private static Logger logger = Logger.getLogger(ReceiveDwr.class);

	private ReceiveHeadManager receiveHeadManager;
	
	public void setReceiveHeadManager(ReceiveHeadManager receiveHeadManager) {
		this.receiveHeadManager = receiveHeadManager;
	}
	
	public Map<String, String> saveOrUpdateReceive(ReceiveHead receiveHead, List<ReceiveLine> lines, String ids, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			receiveHeadManager.saveOrUpdateReceiveHead(receiveHead, lines, ids, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("billid", receiveHead.getBillid());
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "保存出错");
			}
		}
		return result;
	}
	
	public String deleteReceive(String receiveId, HttpServletRequest req) {
		try{
			if(StringUtil.isNotBlank(receiveId)){
				this.receiveHeadManager.deleteReceiveHead(receiveId, CommonUtil.getSessionUserId(req));
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
	
	public Map<String, String> saveAndCloseReceive(ReceiveHead receiveHead, List<ReceiveLine> lines, String ids, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			receiveHeadManager.saveAndCloseReceiveHead(receiveHead, lines, ids, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("billid", receiveHead.getBillid());
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "保存出错");
			}
		}
		return result;
	}
	
	/**
	 * 关闭单据
	 * @param billid
	 * @param req
	 * @return
	 */
	public Map<String, String> closeBill(String billid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.receiveHeadManager.closeReceiveBill(billid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "记账失败");
			}
		}
		return result;
	}
	
	
	public Map<String, String> reverseReceive(String billid, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.receiveHeadManager.reverseReceive(billid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "撤销失败");
			}
		}
		return result;
	}
}
