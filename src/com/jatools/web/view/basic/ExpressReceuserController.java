package com.jatools.web.view.basic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.basic.ExpressReceuserManager;
import com.jatools.vo.basic.ExpressReceuser;
import com.jatools.web.form.basic.ExpressReceuserForm;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class ExpressReceuserController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(ExpressReceuserController.class);
	private ExpressReceuserManager expressReceuserManager;

	public void setExpressReceuserManager(
			ExpressReceuserManager expressReceuserManager) {
		this.expressReceuserManager = expressReceuserManager;
	}

	/**
	 * 获取分页数据
	 */
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		ExpressReceuserForm form = new ExpressReceuserForm();
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "orgId");
		try {
			Pager pager = expressReceuserManager.getExpressReceuserPageData(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/expressReceuser_list", "form", form);
	}
	
	public ModelAndView toAdd(HttpServletRequest req, HttpServletResponse res) {
		ExpressReceuserForm form = new ExpressReceuserForm();
		return new ModelAndView("basic/expressReceuser_edit", "form", form);
	}
	
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		ExpressReceuserForm form = new ExpressReceuserForm();
		try {
			String orgId = CommonUtil.getParameterEmpty(req, "orgId");
			List<ExpressReceuser> list = expressReceuserManager.getExpressReceuserByOrgId(orgId);
			form.setOrgId(orgId);
			form.setList(list);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/expressReceuser_edit", "form", form);
	}

	/**
	 * 删除
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res) {
		ExpressReceuserForm form = new ExpressReceuserForm();
		String receIds = CommonUtil.getParameterNull(req, "receIds");
		try {
			if (StringUtil.isNotEmpty(receIds)) {
				expressReceuserManager.deleteExpressReceuser(receIds);//删除记录
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "orgId");
				Pager pager = expressReceuserManager.getExpressReceuserPageData(condition);
				form.setPager(pager);
			} else {
				form.setSuccessfulFlag(false);
				form.setMessage("不能获取参数");
			}
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}

		return new ModelAndView("basic/expressReceuser_list", "form", form);// 转跳页面
	}
}
