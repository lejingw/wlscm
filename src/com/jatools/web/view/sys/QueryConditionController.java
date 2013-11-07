package com.jatools.web.view.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.sys.QueryConditionManager;
import com.jatools.vo.sys.QueryConditionHead;
import com.jatools.vo.sys.QueryConditionLine;
import com.jatools.web.form.sys.QueryconditionForm;
import com.jatools.web.view.BaseMultiActionController;

public class QueryConditionController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(ParameterController.class);
	private QueryConditionManager queryConditionManager;
	
	public void setQueryConditionManager(QueryConditionManager queryConditionManager) {
		this.queryConditionManager = queryConditionManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		QueryconditionForm form = new QueryconditionForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		form.setCondition(condition);
		Pager pager = queryConditionManager.getQueryConditionPageData(condition);
		form.setPager(pager);
		return new ModelAndView("sys/queryCondition_list", "form", form);
	}
	
	public ModelAndView toAdd(HttpServletRequest req, HttpServletResponse res){
		QueryconditionForm form = new QueryconditionForm();
//		form.setHead();
		return new ModelAndView("sys/queryCondition_edit", "form", form);
	}
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res){
		String headid = CommonUtil.getParameterEmpty(req, "headid");
		QueryconditionForm form = new QueryconditionForm();
		QueryConditionHead head = queryConditionManager.getQueryConditionHead(headid);
		List<QueryConditionLine> lineList = queryConditionManager.getQueryConditionLineByheadid(headid);
		form.setHead(head);
		form.setLineList(lineList);
		return new ModelAndView("sys/queryCondition_edit", "form", form);
	}

}
