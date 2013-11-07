package com.jatools.web.view.basic;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.basic.PurEmpArticleTypeManager;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.form.basic.PurEmpArticleTypeForm;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class PurEmpArticleTypeController extends BaseMultiActionController {

	private PurEmpArticleTypeManager purEmpArticleTypeManager;

	public void setPurEmpArticleTypeManager(PurEmpArticleTypeManager purEmpArticleTypeManager) {
		this.purEmpArticleTypeManager = purEmpArticleTypeManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = purEmpArticleTypeManager.getPurEmpArticleTypePageData(condition);
		PurEmpArticleTypeForm form = new PurEmpArticleTypeForm();
		form.setPager(pager);
		return new ModelAndView("basic/purEmpArticleType_list", "form", form);
	}

	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String purEmpId = CommonUtil.getParameterEmpty(req, "purEmpId");
		String empFlag = CommonUtil.getParameterEmpty(req, "empFlag");
		PurEmpArticleTypeForm form = new PurEmpArticleTypeForm();
		form.setPurEmpId(purEmpId);
		form.setEmpFlag(empFlag);
		if(StringUtil.isNotEmpty(purEmpId)){
			List<SelectorOption> orgIdList = purEmpArticleTypeManager.getOrgs(purEmpId, empFlag);
			form.setList(orgIdList);
		}
		return new ModelAndView("basic/purEmpArticleType_edit", "form", form);
	}
}
