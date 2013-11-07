package com.jatools.web.view.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.jatools.common.CommonUtil;
import com.jatools.manager.common.SelectStyleManager;
import com.jatools.web.form.common.SelectorForm;
import com.jatools.web.view.BaseMultiActionController;

public class SelectStyleController extends BaseMultiActionController {

	private SelectStyleManager selectStyleManager;

	public void setSelectStyleManager(SelectStyleManager selectStyleManager) {
		this.selectStyleManager = selectStyleManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req,
			HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req,
				"selectedValues");
		SelectorForm form = new SelectorForm("true".equals(multiFlag),
				selectedValues);
		return new ModelAndView("common/SelectStyle", "form", form);
	}
	
	public ModelAndView loadInivStyle(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req,"selectedValues");
		String handoverBillId = CommonUtil.getParameterEmpty(req, "handoverBillId");
		String allowInivType = CommonUtil.getParameterEmpty(req,"allowInivType");
		String itemClassId = CommonUtil.getParameterEmpty(req, "itemClassId");
		String ornaClassId = CommonUtil.getParameterEmpty(req, "ornaClassId");
		String styleItemId = CommonUtil.getParameterEmpty(req, "styleItemId");
		String styleMiddleId = CommonUtil.getParameterEmpty(req, "styleMiddleId");
		String styleOrnaId = CommonUtil.getParameterEmpty(req, "styleOrnaId");
		String vender = CommonUtil.getParameterEmpty(req, "vender");
		
		SelectorForm form = new SelectorForm("true".equals(multiFlag),
				selectedValues, handoverBillId, allowInivType,"2",itemClassId,ornaClassId,
				styleItemId,styleMiddleId,styleOrnaId,vender);
		
		return new ModelAndView("common/SelectStyle", "form", form);
	}
	
	public ModelAndView loadAllInivStyle(HttpServletRequest req, HttpServletResponse res) {
		String multiFlag = CommonUtil.getParameterEmpty(req, "multiFlag");
		String selectedValues = CommonUtil.getParameterEmpty(req,"selectedValues");
		
//		String allowInivType = CommonUtil.getParameterEmpty(req,"allowInivType");
//		String itemClassId = CommonUtil.getParameterEmpty(req, "itemClassId");
//		String ornaClassId = CommonUtil.getParameterEmpty(req, "ornaClassId");
//		String styleItemId = CommonUtil.getParameterEmpty(req, "styleItemId");
//		String styleMiddleId = CommonUtil.getParameterEmpty(req, "styleMiddleId");
//		String styleOrnaId = CommonUtil.getParameterEmpty(req, "styleOrnaId");
		
		SelectorForm form = new SelectorForm("true".equals(multiFlag),
				selectedValues);
		
		return new ModelAndView("common/SelectStyleAll", "form", form);
	}
	
}