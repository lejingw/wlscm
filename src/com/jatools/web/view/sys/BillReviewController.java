package com.jatools.web.view.sys;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.sys.BillReviewManager;
import com.jatools.vo.util.ReviewLog;
import com.jatools.web.form.BaseForm;
import com.jatools.web.view.BaseMultiActionController;

public class BillReviewController extends BaseMultiActionController {
	private static Logger logger = Logger.getLogger(BillReviewController.class);
	private BillReviewManager billReviewManager;
	
	public void setBillReviewManager(BillReviewManager billReviewManager) {
		this.billReviewManager = billReviewManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		BaseForm form = new BaseForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
			Pager pager = billReviewManager.getBillReviewPageData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("sys/billReview_list", "form", form);
	}
	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toReview(HttpServletRequest req, HttpServletResponse res) {
		try {
			String logid = CommonUtil.getParameterEmpty(req, "logid");
			ReviewLog reviewLog = billReviewManager.getReviewLog(logid);
			if(null != reviewLog.getPageUrl()){
				if(reviewLog.getPageUrl().indexOf('?')>0){
					reviewLog.setReviewUrl(reviewLog.getPageUrl() + "&reviewflag=1");
				}else{
					reviewLog.setReviewUrl(reviewLog.getPageUrl() + "?reviewflag=1");
				}
			}else{
				reviewLog.setReviewUrl("about:blank");
			}
			return new ModelAndView("sys/billReview_view", "log", reviewLog);
		} catch (RuntimeException e) {
			logger.error(e);
			throw e;
		}
	}
	/**
	 * 审批同意
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView reviewSuccess(HttpServletRequest req, HttpServletResponse res) {
		try {
			String logid = CommonUtil.getParameterEmpty(req, "logid");
			 billReviewManager.reviewSuccess(logid, true, CommonUtil.getSessionUserId(req));
			return new ModelAndView(new RedirectView("billReview.vm"));
		} catch (RuntimeException e) {
			logger.error(e);
			throw e;
		}
	}
	/**
	 * 审批不同意
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView reviewFail(HttpServletRequest req, HttpServletResponse res) {
		try {
			String logid = CommonUtil.getParameterEmpty(req, "logid");
			 billReviewManager.reviewSuccess(logid, false, CommonUtil.getSessionUserId(req));
			return new ModelAndView(new RedirectView("billReview.vm"));
		} catch (RuntimeException e) {
			logger.error(e);
			throw e;
		}
	}
}
