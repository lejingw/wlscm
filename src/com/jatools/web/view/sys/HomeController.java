package com.jatools.web.view.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.web.form.sys.HomeForm;
import com.jatools.web.view.BaseMultiActionController;

public class HomeController extends BaseMultiActionController {
	private static Logger logger = Logger.getLogger(HomeController.class.getName());

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		
		if (!CommonUtil.isLogined(req)) {
			return getNoLoginViewPage();
		}
		HttpSession session = req.getSession();
		HomeForm homeForm = new HomeForm();
		homeForm.setUserid(CommonUtil.getSessionUserId(session));
		homeForm.setUsername(CommonUtil.getSessionUserName(session));
		return new ModelAndView("home","homeForm",homeForm);
	}

}
