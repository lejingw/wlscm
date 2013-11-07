package com.jatools.web.dwr.stock;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.stock.ProcChangeHeadManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.stock.ProcChangeHead;
import com.jatools.vo.stock.ProcChangeLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

/**
 * 拆石
 * @author ren.ming
 * Created 2011-11-24
 */
public class DismantleDwr extends BaseChangeDwr {
	private static Logger logger = Logger.getLogger(DismantleDwr.class);
	
	private CommonManager commonManager;
	private ProcChangeHeadManager procChangeHeadManager;
	
	public void setProcChangeHeadManager(ProcChangeHeadManager ProcChangeHeadManager) {
		this.procChangeHeadManager = ProcChangeHeadManager;
	}
	
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	
	public String saveOrUpdateDismantle(ProcChangeHead procChangeHead, List<ProcChangeLine> oldLines, String ids, HttpServletRequest req) {
		try{
			if(StringUtil.isEmpty(procChangeHead.getBillid())){
				procChangeHead.setCreateDate(DateUtil.getCurrentDate18());
				procChangeHead.setCreateId(CommonUtil.getSessionUserId(req));
				procChangeHead.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_CHAISHI);
				procChangeHead.setBillno(billNo);
			}
			procChangeHead.setUpdateDate(DateUtil.getCurrentDate18());
			procChangeHead.setUpdateId(CommonUtil.getSessionUserId(req));
			
			procChangeHeadManager.saveOrUpdateProcChangeHead(procChangeHead, oldLines, ids);
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "保存失败";
		}
	}
	
	public String saveAndCheckDismantle(ProcChangeHead procChangeHead, List<ProcChangeLine> oldLines, String ids, HttpServletRequest req) {
		try{
			if(StringUtil.isEmpty(procChangeHead.getBillid())){
				procChangeHead.setCreateDate(DateUtil.getCurrentDate18());
				procChangeHead.setCreateId(CommonUtil.getSessionUserId(req));
				procChangeHead.setStatus(DictConstant.BILL_STATUS_SAVE);
				String billNo = commonManager.getBillno(GlobalConstant.BILL_CODE_CHAISHI);
				procChangeHead.setBillno(billNo);
			}
			procChangeHead.setUpdateDate(DateUtil.getCurrentDate18());
			procChangeHead.setUpdateId(CommonUtil.getSessionUserId(req));
			
			procChangeHeadManager.saveAndCheckProcChangeHead(procChangeHead, oldLines, ids);
			return null;
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(e.getMessage())){
				return e.getMessage();
			}
			return "提交审批失败";
		}
	}
}
