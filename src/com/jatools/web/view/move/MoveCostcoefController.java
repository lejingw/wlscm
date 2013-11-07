package com.jatools.web.view.move;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.move.MoveCostcoefManager;
import com.jatools.vo.move.MoveCostcoef;
import com.jatools.web.form.move.MoveCostcoefForm;
import com.jatools.web.view.BaseMultiActionController;

public class MoveCostcoefController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(MoveCostcoefController.class);
	private MoveCostcoefManager moveCostcoefManager;

	public void setMoveCostcoefManager(MoveCostcoefManager moveCostcoefManager) {
		this.moveCostcoefManager = moveCostcoefManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		MoveCostcoefForm form = new MoveCostcoefForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
		Pager pager = moveCostcoefManager.getMoveCostCoefPageData(condition);
		form.setPager(pager);
		return new ModelAndView("move/moveCostcoef_list", "form", form);
	}
	
	/**
	 * 修改单据
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		MoveCostcoefForm form = new MoveCostcoefForm();
		String coefId = CommonUtil.getParameterNull(req, "coefId");
		if(null != coefId){
			try {
				MoveCostcoef coef = moveCostcoefManager.getMoveCostcoef(coefId);
				form.setCoef(coef);
			} catch (Exception e) {
				logger.error(e);
				form.setSuccessfulFlag(false);
				form.setMessage("获取单据数据失败");
				return new ModelAndView("move/moveCostcoef_list", "form", form);
			}
		}
		return new ModelAndView("move/moveCostcoef_edit", "form", form);
	}
	/**
	 * 新建单据
	 * 
	 */
	public ModelAndView toAdd(HttpServletRequest req, HttpServletResponse res) {
		MoveCostcoefForm form = new MoveCostcoefForm();
		return new ModelAndView("move/moveCostcoef_edit", "form", form);
	}
}
