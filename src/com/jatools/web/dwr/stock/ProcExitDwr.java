package com.jatools.web.dwr.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.stock.ProcExitHeadManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.stock.ProcExitHead;
import com.jatools.vo.stock.ProcExitLine;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

/**
 * 退料
 * @author ren.ming
 * Created 2011-11-29
 */
public class ProcExitDwr {
	private static Logger logger = Logger.getLogger(ProcExitDwr.class);
	
	private CommonManager commonManager;
	private ProcExitHeadManager procExitHeadManager;
	
	public void setProcExitHeadManager(ProcExitHeadManager ProcExitHeadManager) {
		this.procExitHeadManager = ProcExitHeadManager;
	}
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	
	/**
	 * 根据供应商id取供应商名称
	 * @param vendorId
	 * @return
	 */
	public String getVendorName(String vendorId) {
		return VendorCache.getInstance().getVendorName(vendorId);
	}
	
	public String saveOrUpdatePackage(ProcExitHead exitHead, List<ProcExitLine> exitLines, String ids, HttpServletRequest req) {
		try{
			if(StringUtil.isEmpty(exitHead.getBillid())){
				exitHead.setCreateDate(DateUtil.getCurrentDate18());
				exitHead.setCreateId(CommonUtil.getSessionUserId(req));
				exitHead.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_TUILIAO);
				exitHead.setBillno(billNo);
			}
			procExitHeadManager.saveOrUpdateProcExitHead(exitHead, exitLines, ids, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存出错";
		}
	}
	
	public String deleteExit(String exitid, HttpServletRequest req) {
		try{
			if(StringUtil.isNotBlank(exitid)){
				this.procExitHeadManager.deleteProcExitHead(exitid, CommonUtil.getSessionUserId(req));
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
	
	public Map<String, String> saveAndCheckExitHead(ProcExitHead exitHead, List<ProcExitLine> exitLines, String ids, HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			if(StringUtil.isEmpty(exitHead.getBillid())){
				exitHead.setCreateDate(DateUtil.getCurrentDate18());
				exitHead.setCreateId(CommonUtil.getSessionUserId(req));
				exitHead.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_TUILIAO);
				exitHead.setBillno(billNo);
			}
			exitHead.setUpdateDate(DateUtil.getCurrentDate18());
			exitHead.setUpdateId(CommonUtil.getSessionUserId(req));
			procExitHeadManager.saveAndCheckProcExitHead(exitHead, exitLines, ids, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
			result.put("billid", exitHead.getBillid());
			return result;
		} catch (Exception e) {
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "提交审核失败");
			}
			return result;
		}
	}
	
	
	public String closeExitHead(String billid, HttpServletRequest req) {
		try{
			this.procExitHeadManager.closeExitHead(billid, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "关闭失败";
		}
	}
	
	public String checkExitHead(String billid, HttpServletRequest req) {
		try{
			this.procExitHeadManager.checkBillHead(billid, CommonUtil.getSessionUserId(req));
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "审批失败";
		}
	}
	
	public Map<String, String> cashCharge(String billid, String charge, HttpServletRequest req){
		Map<String, String> result = new HashMap<String, String>();
		try {
			this.procExitHeadManager.cashCharge(billid, charge, CommonUtil.getSessionUserId(req));
			result.put("isSuccess", "true");
		} catch(Exception e){
			logger.error(e);
			result.put("isSuccess", "false");
			if(StringUtil.isNotBlank(e.getMessage())){
				result.put("msg", e.getMessage());
			} else {
				result.put("msg", "修改工费失败");
			}
		}
		return result;
	}
}
