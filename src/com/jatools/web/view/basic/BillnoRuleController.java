package com.jatools.web.view.basic;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.basic.BillnoRuleManager;
import com.jatools.vo.basic.BillnoRule;
import com.jatools.web.form.basic.BillnoRuleForm;
import com.jatools.web.view.BaseMultiActionController;

public class BillnoRuleController extends BaseMultiActionController {
	private Logger logger = Logger.getLogger(BillnoRuleController.class);

	private BillnoRuleManager billnoRuleManager;

	public void setBillnoRuleManager(BillnoRuleManager billnoRuleManager) {
		this.billnoRuleManager = billnoRuleManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		BillnoRuleForm form = new BillnoRuleForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
			Pager pager = billnoRuleManager.getBillnoRulePageData(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			e.printStackTrace();
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("basic/billnoRule_list", "form", form);
	}
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String billCode = CommonUtil.getParameterNull(req, "billCode");
		BillnoRuleForm form = new BillnoRuleForm();
		//如果带有billnoRuleId参数，则为编辑页面
		if(null != billCode){
			BillnoRule rule = billnoRuleManager.getBillnoRule(billCode);
			form.setRule(rule);
		}
		return new ModelAndView("basic/billnoRule_edit","form",form);
	}
}
