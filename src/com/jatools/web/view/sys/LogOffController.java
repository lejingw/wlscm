package com.jatools.web.view.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Global;
import com.jatools.web.view.BaseMultiActionController;

public class LogOffController extends BaseMultiActionController {
	
	private static Logger logger = Logger.getLogger(LogOffController.class);

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		if(null != session){
			logger.debug("成功登出系统");
			CommonUtil.logoutClearSession(session);
			session.invalidate();
		}
		return new ModelAndView(new RedirectView(Global.CONTEXT + "/login.vm?isLogin=false"));
	}

}
