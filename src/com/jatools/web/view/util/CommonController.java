package com.jatools.web.view.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.BillnoRuleManager;
import com.jatools.manager.bd.BdCommonManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.basic.BillnoRule;
import com.jatools.vo.bd.Style;
import com.jatools.web.form.util.CommonForm;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class CommonController extends BaseMultiActionController{
	private CommonManager commonManager;
	private BillnoRuleManager billnoRuleManager;
	private BdCommonManager bdCommonManager;
	
	public void setBdCommonManager(BdCommonManager bdCommonManager) {
		this.bdCommonManager = bdCommonManager;
	}

	public void setBillnoRuleManager(BillnoRuleManager billnoRuleManager) {
		this.billnoRuleManager = billnoRuleManager;
	}

	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}

	public ModelAndView viewReviewLog(HttpServletRequest req, HttpServletResponse res) {
		String billCode = CommonUtil.getParameterEmpty(req, "billCode");
		String billId = CommonUtil.getParameterEmpty(req, "billId");
		CommonForm form = new CommonForm();
		BillnoRule rule = billnoRuleManager.getBillnoRule(billCode);
		form.setMessage(rule.getBillName() + "审批流程");
		if(StringUtil.isEmpty(billCode)){
			form.setMessage(form.getMessage()+"不能获取单据编号[billCode]参数");
		}
		if(StringUtil.isEmpty(billId)){
			form.setMessage(form.getMessage()+"不能获取单据ID[billId]参数");
		}
		List<Map<String, String>> reviewLogList = commonManager.getReviewLog(billCode, billId);
		form.setReviewLogList(reviewLogList);
		return new ModelAndView("util/viewReviewLog", "form", form);
	}
	
	public ModelAndView viewStylePic(HttpServletRequest req, HttpServletResponse res) {
		String styleId = CommonUtil.getParameterEmpty(req, "styleId");
		CommonForm form = new CommonForm();
		if(StringUtil.isEmpty(styleId)){
			form.setMessage("不能获取款式id参数");
		}else{
//			String basePath = "http://192.168.0.208:9088";
			String basePath = CommonUtil.getPicBasePath();
			Style style = bdCommonManager.getStyleById(styleId);
			form.setStyleId(styleId);
			form.setStyleBigPicPath(basePath + style.getIsbiggraph());
			form.setStyleSmallPicPath(basePath + style.getIssmallgraph());
            if(StringUtil.isNotBlank(style.getObliquegraph())){
                form.setStyleObliquePicPath(basePath + style.getObliquegraph());
            }
            if(StringUtil.isNotBlank(style.getTopviewgraph())){
                form.setStyleTopviewPicPath(basePath + style.getTopviewgraph());
            }
		}
		return new ModelAndView("util/viewStylePic", "form", form);
	}
}