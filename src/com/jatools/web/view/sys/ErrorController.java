package com.jatools.web.view.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.web.view.BaseMultiActionController;

/**
 * 错误页面处理控制器
 */
public class ErrorController extends BaseMultiActionController{

    /**
     * 客户端请求没有user_action参数默认执行该方法
     * @param req
     * @param res
     * @return
     */
    public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
        //是否登陆检测
        HttpSession session = req.getSession();
        if(!CommonUtil.isLogined(session)){
            return getNoLoginViewPage();
        }else{
            return new ModelAndView("error");
        }
    }
}
