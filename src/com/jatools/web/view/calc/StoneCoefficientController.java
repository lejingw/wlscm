package com.jatools.web.view.calc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.calc.StoneCoefficientManager;
import com.jatools.vo.calc.StoneCoefficient;
import com.jatools.web.form.calc.StoneCoefficientForm;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class StoneCoefficientController extends BaseMultiActionController {

	private Logger logger = Logger.getLogger(StoneCoefficientController.class);
	private StoneCoefficientManager stoneCoefficientManager;
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	public void setStoneCoefficientManager(
			StoneCoefficientManager stoneCoefficientManager) {
		this.stoneCoefficientManager = stoneCoefficientManager;
	}

	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		StoneCoefficientForm form = new StoneCoefficientForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "id","itemClassId");
			Pager pager = stoneCoefficientManager.getStoneCoefficientPageData(condition);
			form.setPager(pager);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("calc/stoneCoefficient_list","form",form);//转跳页面
	}
	/**
	 * 删除 总系数
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView deleteStoneCoefficient(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		StoneCoefficientForm form = new StoneCoefficientForm();
		try {
			if(StringUtil.isNotEmpty(id)){
				stoneCoefficientManager.deleteStoneCoefficient(id);
				form.setSuccessfulFlag(true);
				form.setMessage("删除成功");
				Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req);
				Pager pager = stoneCoefficientManager.getStoneCoefficientPageData(condition);
				form.setPager(pager);
			}else{
				form.setSuccessfulFlag(false);
				form.setMessage("不能获取单据总系数id");
			}
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView("calc/stoneCoefficient_list", "form", form);
	}
	/**
	 * 转跳编辑页面
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEditStoneCoefficient(HttpServletRequest req,
			HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "id");
		StoneCoefficientForm form = new StoneCoefficientForm();
		//如果带有vendorId参数，则为编辑页面
		if(null != id){
			StoneCoefficient ven = stoneCoefficientManager.getStoneCoefficientById(id);
			form.setSc(ven);
			return new ModelAndView("calc/stoneCoefficient_edit","form",form);
		}else{
			return new ModelAndView("calc/stoneCoefficient_edit","form",form);
		}
	}
}
