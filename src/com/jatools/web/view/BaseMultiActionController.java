package com.jatools.web.view;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;
import com.jatools.common.Global;
import com.jatools.web.form.sys.LoginForm;

/**
 * 多页面控制器基类
 * 
 */
public abstract class BaseMultiActionController extends MultiActionController {
	

	private static Logger logger = Logger.getLogger(BaseMultiActionController.class.getName());

	/**
	 * 请求没有指定user_action默认执行该方法
	 * @param req
	 * @param res
	 * @return
	 */
	public abstract ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res);

	/**
	 * 如果没有登陆转向登陆界面
	 * 
	 * @return
	 */
	public ModelAndView getNoLoginViewPage() {
		LoginForm loginForm = new LoginForm();
		loginForm.setSuccessfulFlag(false);
		loginForm.setMessage("您还没有登陆，请先登录！");
		return new ModelAndView(new RedirectView(Global.CONTEXT + "/login.vm?isLogin=false"), "loginForm", loginForm);
	}

	/**
	 * 获取远程机器IP
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			logger.debug("获取远程机器IP，request对象为null.");
			return "";
		}
		String ip = request.getHeader("x-forwarded-for");
		logger.debug("x-forwarded-for:" + ip);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			logger.debug("Proxy-Client-IP:" + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			logger.debug("WL-Proxy-Client-IP:" + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			logger.debug("RemoteAddr:" + ip);
		}
		return ip;
	}
	
	public String getSessionKey(){
		return this.getClass().getName();
	}
	
	public void write(HttpServletResponse res, String jsonStr){
		try {
//			res.setContentType("text/html");
			res.getWriter().print(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
