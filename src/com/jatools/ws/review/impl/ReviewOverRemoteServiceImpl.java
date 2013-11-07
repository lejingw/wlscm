package com.jatools.ws.review.impl;

import java.util.List;

import com.jatools.common.Global;
import com.jatools.common.intf.ReviewActionIntf;
import com.jatools.dao.sys.BillReviewDao;
import com.jatools.vo.util.ReviewLog;
import com.jatools.web.util.StringUtil;
import com.jatools.ws.review.ReviewOverRemoteService;

public class ReviewOverRemoteServiceImpl implements ReviewOverRemoteService {
	private BillReviewDao billReviewDao;
	
	public void setBillReviewDao(BillReviewDao billReviewDao) {
		this.billReviewDao = billReviewDao;
	}
	
	/**
	 * 本地审批结束
	 * @param billCode 单据编码
	 * @param billId 单据id
	 * @param successFlag true审批通过 false审批不通过
	 */
	public void reviewAction(String billCode, String billId, boolean successFlag, String userid){
        try {
            List<ReviewLog> logList = billReviewDao.getReviewLogByBillCodeAndId(billCode, billId);
            if(logList.size()<1){
                throw new RuntimeException("库存系统不能找到对应的审批记录");
            }
            for(ReviewLog reviewLog : logList){
                billReviewDao.reviewSuccess(reviewLog.getLogid(), successFlag, userid);

                Object obj = Global.springContext.getBean(reviewLog.getBeanName());
                if(null == obj){
                    throw new RuntimeException("不能根据beanName["+reviewLog.getBeanName()+"]获取审批单据对应的dao");
                }
                if(obj instanceof ReviewActionIntf){
                    ReviewActionIntf reviewIntf = (ReviewActionIntf)obj;
                    if(successFlag){
                        reviewIntf.reviewSuccess(reviewLog.getBillId(), userid);
                    }else{
                        reviewIntf.reviewFail(reviewLog.getBillId(), userid);
                    }
                }else{
                    throw new RuntimeException("审批单据dao必须实现ReviewInterface接口");
                }
            }
        } catch(Exception e) {
            throw new RuntimeException("审批失败，系统异常", e);
        }
	}

	/**
	 * webservice审批结束
	 * @param reviewResult 审批结果1成功、2继续、3失败
	 */
	public String reviewCallBack(String modelCode, Long billId, Long reviewResult, Long reviewUserId, String reviewDate){
		if(2 == reviewResult.intValue()){
			return "SUCCESS";
		}
		String billCode = billReviewDao.getBillCodeByModelCode(modelCode);
    	if(StringUtil.isEmpty(billCode)){
    		throw new RuntimeException("库存系统，不能根据模块编码获取单据编码");
    	}
		reviewAction(billCode, ""+billId, 1 == reviewResult.intValue(), ""+reviewUserId);
		return "SUCCESS";
	}
}
