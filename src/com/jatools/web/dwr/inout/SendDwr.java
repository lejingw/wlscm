package com.jatools.web.dwr.inout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.inout.SendHeadManager;
import com.jatools.vo.inout.SendHead;
import com.jatools.vo.inout.SendLine;
import com.jatools.web.util.StringUtil;

public class SendDwr {

	private static Logger logger = Logger.getLogger(SendDwr.class);

	private SendHeadManager sendHeadManager;
	
	public void setSendHeadManager(SendHeadManager sendHeadManager) {
		this.sendHeadManager = sendHeadManager;
	}
	
	public Map<String, String> saveOrUpdateSend(SendHead sendHead, List<SendLine> lines, String ids, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			sendHeadManager.saveorUpdateSendHead(sendHead, lines, ids, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("billid", sendHead.getBillid());
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
	
	public String deleteSend(String sendId, HttpServletRequest req) {
		try{
			if(StringUtil.isNotBlank(sendId)){
				this.sendHeadManager.deleteSendHead(sendId, CommonUtil.getSessionUserId(req));
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
	public Map<String, String> saveAndCloseSend(SendHead sendHead, List<SendLine> lines, String ids, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			sendHeadManager.saveAndCloseSendHead(sendHead, lines, ids, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("billid", sendHead.getBillid());
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
			this.sendHeadManager.closeSendHead(billid, CommonUtil.getSessionUserId(req));
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
	
}
