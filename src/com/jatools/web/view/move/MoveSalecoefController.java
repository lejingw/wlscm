package com.jatools.web.view.move;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.move.MoveSalecoefManager;
import com.jatools.vo.move.MoveSalecoefHead;
import com.jatools.vo.move.MoveSalecoefLine;
import com.jatools.web.form.move.MoveSalecoefForm;
import com.jatools.web.view.BaseMultiActionController;

public class MoveSalecoefController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(MoveSalecoefController.class);
	private MoveSalecoefManager moveSalecoefManager;

	public void setMoveSalecoefManager(MoveSalecoefManager moveSalecoefManager) {
		this.moveSalecoefManager = moveSalecoefManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		MoveSalecoefForm form = new MoveSalecoefForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = moveSalecoefManager.getMoveSaleCoefPageData(condition);
		form.setPager(pager);
		return new ModelAndView("move/moveSalecoef_list", "form", form);
	}
	
	/**
	 * 新建单据
	 * 
	 */
	public ModelAndView toAdd(HttpServletRequest req, HttpServletResponse res) {
		MoveSalecoefForm form = new MoveSalecoefForm();
		return new ModelAndView("move/moveSalecoef_edit", "form", form);
	}
	
	/**
	 * 修改单据
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		MoveSalecoefForm form = new MoveSalecoefForm();
		String headid = CommonUtil.getParameterNull(req, "headid");
		if(null != headid){
			try {
				MoveSalecoefHead head = moveSalecoefManager.getMoveSalecoefHead(headid);
				List<MoveSalecoefLine> list = moveSalecoefManager.getMoveSalecoefLineList(headid);
				form.setHead(head);
				form.setList(list);
			} catch (Exception e) {
				logger.error(e);
				form.setSuccessfulFlag(false);
				form.setMessage("获取单据数据失败");
				return new ModelAndView("move/moveSalecoef_list", "form", form);
			}
		}
		return new ModelAndView("move/moveSalecoef_edit", "form", form);
	}
}
