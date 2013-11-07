package com.jatools.web.dwr.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.pur.HandoverManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.HandoverLine;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class HandoverDwr {

	private static Logger logger = Logger.getLogger(HandoverDwr.class);

	private HandoverManager handoverManager;
	private CommonManager commonManager;
	
	public void setHandoverManager(HandoverManager handoverManager) {
		this.handoverManager = handoverManager;
	}
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	public Map<String, String> saveOrUpdateHandover(HandoverHead handoverHead, List<HandoverLine> lines, String ids, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			if(StringUtil.isEmpty(handoverHead.getBillid())){
				handoverHead.setCreateDate(DateUtil.getCurrentDate18());
				handoverHead.setCreateId(CommonUtil.getSessionUserId(req));
				handoverHead.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_JIAOJIEDAN);
				handoverHead.setBillno(billNo);
			}
			handoverHead.setUpdateDate(DateUtil.getCurrentDate18());
			handoverHead.setUpdateId(CommonUtil.getSessionUserId(req));
			
			handoverManager.saveOrUpdateOtherHandoverHead(handoverHead, lines, ids);
			result.put("isSuccess", "true");
			result.put("billid", handoverHead.getBillid());
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", "保存出错");
		}
		return result;
	}
	
	public String deleteHandover(String billid, HttpServletRequest req) {
		try{
			if(StringUtil.isNotEmpty(billid)){
				this.handoverManager.deleteHandoverHead(billid, CommonUtil.getSessionUserId(req));
			}
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除失败";
		}
	}
	
	public Map<String, String> saveAndCheckHandover(HandoverHead handoverHead, List<HandoverLine> lines, String ids, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			if(StringUtil.isEmpty(handoverHead.getBillid())){
				handoverHead.setCreateDate(DateUtil.getCurrentDate18());
				handoverHead.setCreateId(CommonUtil.getSessionUserId(req));
				handoverHead.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_JIAOJIEDAN);
				handoverHead.setBillno(billNo);
			}
			handoverHead.setUpdateDate(DateUtil.getCurrentDate18());
			handoverHead.setUpdateId(CommonUtil.getSessionUserId(req));
			
			handoverManager.saveAndCheckOtherHandoverHead(handoverHead, lines, ids);
			result.put("isSuccess", "true");
			result.put("billid", handoverHead.getBillid());
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", "保存出错");
		}
		return result;
	}
	/**
	 * 审核单据
	 * @param billid
	 * @param req
	 * @return
	 */
	public Map<String, String> checkBill(String billid, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.handoverManager.checkBillhead(billid, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			result.put("msg", "提交审核失败");
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
			boolean res = this.handoverManager.closeOtherBillHead(billid, CommonUtil.getSessionUserId(req), CommonUtil.getSessionOrgId(req));
			if(res){
				result.put("isSuccess", "true");
			} else {
				result.put("isSuccess", "false");
				result.put("msg", "入库数量和交接单数量误差不在允许的范围内,不允许关闭单据");
			}
		} catch(Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "单据关闭失败");
			}
		}
		return result;
	}
	
	public List<SelectorOption> getAnalysisList(){
		return this.handoverManager.getAnalysisList();
	}
	
	public Map<String, String> changeHandoverLine(HandoverHead head, List<HandoverLine> lines, String deleteLineIds,  HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try{
			result = this.handoverManager.changeHandoverLine(head, lines, deleteLineIds, CommonUtil.getSessionUserId(req));
		} catch(Exception e){
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "修改行信息失败");
			}
		}
		return result;
	}
}
