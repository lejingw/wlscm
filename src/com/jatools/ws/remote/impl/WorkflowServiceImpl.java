package com.jatools.ws.remote.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jatools.common.Global;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.intf.ReviewBill;
import com.jatools.dao.sys.BillReviewDao;
import com.jatools.vo.util.ReviewLog;
import com.jatools.web.cache.DictCache;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.client.EnterReviewRemoteClient;
import com.jatools.ws.client.ReviewOverRemoteClient;
import com.jatools.ws.client.ViewReviewLogRemoteClient;
import com.jatools.ws.remote.EnterReviewWebService;
import com.jatools.ws.remote.NoticeToRefreshWebService;
import com.jatools.ws.remote.ViewReviewLogWebService;
import com.jatools.ws.remote.WorkflowService;
import com.jatools.ws.review.vo.ReviewInfo;

public class WorkflowServiceImpl implements WorkflowService {
	private final String WLSCM_AGENT = "scm";
	private String callbackWebServiceUrl;
	private BillReviewDao billReviewDao;

	private EnterReviewRemoteClient enterReviewRemoteClient;
	private ViewReviewLogRemoteClient viewReviewLogRemoteClient;
	private ReviewOverRemoteClient reviewOverRemoteClient;
//	private RefreshJmCacheRemoteClient refreshJmCacheRemoteClient;
	
	private EnterReviewWebService enterReviewWebService;
	private ViewReviewLogWebService viewReviewLogWebService;
	private NoticeToRefreshWebService noticeToRefreshWebService;
	
	public void setBillReviewDao(BillReviewDao billReviewDao) {
		this.billReviewDao = billReviewDao;
	}

	public void setEnterReviewRemoteClient(EnterReviewRemoteClient enterReviewRemoteClient) {
		this.enterReviewRemoteClient = enterReviewRemoteClient;
	}

	public void setViewReviewLogRemoteClient(ViewReviewLogRemoteClient viewReviewLogRemoteClient) {
		this.viewReviewLogRemoteClient = viewReviewLogRemoteClient;
	}

	public void setReviewOverRemoteClient(ReviewOverRemoteClient reviewOverRemoteClient) {
		this.reviewOverRemoteClient = reviewOverRemoteClient;
	}
	
	public void setCallbackWebServiceUrl(String callbackWebServiceUrl) {
		this.callbackWebServiceUrl = callbackWebServiceUrl;
	}

	public void setEnterReviewWebService(EnterReviewWebService enterReviewWebService) {
		this.enterReviewWebService = enterReviewWebService;
	}

	public void setViewReviewLogWebService(
			ViewReviewLogWebService viewReviewLogWebService) {
		this.viewReviewLogWebService = viewReviewLogWebService;
	}

//	public void setRefreshJmCacheRemoteClient(
//			RefreshJmCacheRemoteClient refreshJmCacheRemoteClient) {
//		this.refreshJmCacheRemoteClient = refreshJmCacheRemoteClient;
//	}

	public void setNoticeToRefreshWebService(
			NoticeToRefreshWebService noticeToRefreshWebService) {
		this.noticeToRefreshWebService = noticeToRefreshWebService;
	}

	/**
	 * 进入审批
	 * @param reviewBill
	 * @param userid
	 */
	public void enterReview(ReviewBill reviewBill, String userid){
		//保存审批单据信息
		ReviewLog reviewLog = saveLocalReviewLog(reviewBill, userid);
		if(Global.ENABLE_REMOTE_REVIEW_FLAG){
			ReviewInfo reviewBean = new ReviewInfo();
	    	reviewBean.setReviewBillId(new Long(reviewBill.getBillid()));
	    	reviewBean.setReviewBillNo(reviewBill.getBillno());
	    	String modelCode = billReviewDao.getModelCodeByBillCode(reviewBill.getBillCode());//模块编码
	    	if(StringUtil.isEmpty(modelCode)){
	    		throw new RuntimeException("不能根据单据编码获取模块编码");
	    	}
	    	reviewBean.setModuleCode(modelCode);
	    	reviewBean.setCallBackUrl(callbackWebServiceUrl);
	    	reviewBean.setHtmlUrl(reviewBill.getPageUrl());
	    	reviewBean.setUserId(new Long(userid));
	    	reviewBean.setFinanceOrgId(new Long(reviewBill.getOrgId()));
	    	String result = enterReviewWebService.doEnterReview(reviewBean);
			if (!"SUCCESS".equals(result)) {
				throw new RuntimeException("进入审批失败["+result+"]");
			}
		}else{
			try {
				enterReviewRemoteClient.enterReviewRemote(WLSCM_AGENT, reviewBill.getBillCode(), reviewLog.getBillName(),
						reviewBill.getBillid(), reviewBill.getBillno(), reviewBill.getPageUrl());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("进入审批失败，请检查web service url");
			}
		}
	}
	/**
	 * 本地保存审批记录
	 * @param reviewBill
	 * @param userid
	 * @return
	 */
	private ReviewLog saveLocalReviewLog(ReviewBill reviewBill, String userid) {
        try {
            String billName = DictCache.getInstance().getValue(DictConstant.BILL_CODE, reviewBill.getBillCode());
            ReviewLog reviewLog = new ReviewLog();
            reviewLog.setBillCode(reviewBill.getBillCode());
            reviewLog.setBillName(billName);
            reviewLog.setBillId(reviewBill.getBillid());
            reviewLog.setBillNo(reviewBill.getBillno());
            reviewLog.setPageUrl(reviewBill.getPageUrl());
            reviewLog.setFinishFlag("0");
            reviewLog.setBeanName(reviewBill.getBeanName());
            reviewLog.setCreateDate(DateUtil.getCurrentDate18());
            reviewLog.setCreateId(userid);
            reviewLog.setUpdateDate(DateUtil.getCurrentDate18());
            reviewLog.setUpdateId(userid);
            reviewLog.setRemoteFlag(Global.ENABLE_REMOTE_REVIEW_FLAG?DictConstant.YES_OR_NO_YES:DictConstant.YES_OR_NO_NO);
            billReviewDao.saveReviewLog(reviewLog);
            return reviewLog;
        } catch (Exception e) {
            throw new RuntimeException("保存审批日志失败", e);
        }
	}
	
	/**
	 * 审批结束
	 * @param billCode
	 * @param billId
	 * @param successFlag true审批通过 false审批不通过
	 */
	public void reviewAction(String billCode, String billId, boolean successFlag, String userid){
		reviewOverRemoteClient.reviewAction(billCode, billId, successFlag, userid);
	}
	
	/**
	 * 获取审批记录
	 * @param billCode
	 * @param billId
	 */
	public List<Map<String, String>> getReviewLog(String billCode, String billId){
		if(Global.ENABLE_REMOTE_REVIEW_FLAG){
	    	String modelCode = billReviewDao.getModelCodeByBillCode(billCode);//模块编码
	    	if(StringUtil.isEmpty(modelCode)){
	    		throw new RuntimeException("不能根据单据编码获取模块编码");
	    	}
			String result = viewReviewLogWebService.getReviewLogPageData(new Long(billId), modelCode);
			JSONArray ja = JSONArray.fromObject(result);
			return convertToList(ja);
		}else{
			String result = viewReviewLogRemoteClient.getReviewLog(billCode, billId);
			JSONArray ja = JSONArray.fromObject(result);
			return convertToList(ja);
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Map<String, String>> convertToList(JSONArray ja){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Iterator<JSONObject> iter = ja.iterator();
		while(iter.hasNext()){
			JSONObject obj = iter.next();
			Map<String, String> map = new HashMap<String, String>();
			map.put("sort", obj.getString("sort"));//关口
//			map.put("state", obj.getString("state"));
			map.put("stateName", obj.getString("stateName"));//状态
			map.put("reviewType", obj.getString("reviewType"));//审批类型
			map.put("reviewEmp", obj.getString("reviewEmp"));//审批人
			map.put("reveiwDate", obj.getString("reveiwDate"));//审批时间
			map.put("advice", obj.getString("advice"));//审批意见
			map.put("insertFlag", obj.getString("insertFlag"));//是否新增
			list.add(map);
		}
		return list;
	}
	/**
	 * 刷新加盟系统缓存
	public void refreshJmCache(String msgKey){
		try {
			refreshJmCacheRemoteClient.refreshCache(msgKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */
	/**
	 * 通知加盟系统缓存刷新
	 */
	public void refreshJmCache(String msgKey){
		String appKey = "wlscm";
		String lineName = "1";
		noticeToRefreshWebService.noticeToRefreshCahce(msgKey, appKey, lineName);
	}
}
