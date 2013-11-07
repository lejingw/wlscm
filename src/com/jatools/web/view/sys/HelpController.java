package com.jatools.web.view.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.jatools.web.form.sys.HelpForm;
import com.jatools.web.view.BaseMultiActionController;

/**
 * 帮助页面控制器
 * @author pizheng
 *
 */
public class HelpController extends BaseMultiActionController {

    public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
    	
    	HelpForm helpForm = new HelpForm();
        
        return new ModelAndView("help", "helpForm", helpForm);
    }
    
    public ModelAndView doViewContent(HttpServletRequest req, HttpServletResponse res) {
    	
    	HelpForm helpForm = new HelpForm();
        
        return new ModelAndView("helpContent", "helpForm", helpForm);
    }
    
}
