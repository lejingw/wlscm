package com.jatools.web.view.move;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.move.MoveNetManager;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.form.move.MoveNetForm;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class MoveNetController extends BaseMultiActionController {

	private MoveNetManager moveNetManager;

	public void setMoveNetManager(MoveNetManager moveNetManager) {
		this.moveNetManager = moveNetManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		MoveNetForm form = new MoveNetForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = moveNetManager.getMoveNetPageData(condition);
		form.setPager(pager);
		return new ModelAndView("move/moveNet_list", "form", form);
	}

	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String orgid = CommonUtil.getParameterEmpty(req, "orgid");
		String outflag = CommonUtil.getParameterEmpty(req, "outflag");
		MoveNetForm form = new MoveNetForm();
		form.setOrgid(orgid);
		form.setOutflag(outflag);
		if(StringUtil.isNotEmpty(orgid)){
			List<SelectorOption> orgIdList = moveNetManager.getOrgs(orgid, outflag);
			form.setList(orgIdList);
		}
		return new ModelAndView("move/moveNet_edit", "form", form);
	}
}
