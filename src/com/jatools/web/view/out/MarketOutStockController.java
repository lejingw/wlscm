package com.jatools.web.view.out;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.web.form.out.OutStockForm;

public class MarketOutStockController extends BaseOutStockController {
	private Logger logger = Logger.getLogger(this.getClass());
	@Override
	public ModelAndView doSubPerform(HttpServletRequest req,
			HttpServletResponse res) {
		OutStockForm form = new OutStockForm();
		getPagerData(form,req,"3");
		return new ModelAndView("out/marketOutStock_list", "form", form);
	}
	//0丢失1报废2礼品3营销
	public ModelAndView toList(HttpServletRequest req,HttpServletResponse res) {
		OutStockForm form = new OutStockForm();
		getPagerData(form,req,"3");
		return new ModelAndView("out/marketOutStock_list", "form", form);
	}

	/**
	 * 新建单据
	 */
	public ModelAndView toAdd(HttpServletRequest req, HttpServletResponse res) {
		OutStockForm form = toAddForm(req, res);
		return new ModelAndView("out/marketOutStock_edit", "form", form);
	}

	/**
	 * 修改单据
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		OutStockForm form = new OutStockForm();
		String headId = CommonUtil.getParameterNull(req, "headid");
		if (null != headId) {
			try {
				toEditData(form, headId);
			} catch (Exception e) {
				logger.error(e);
				form.setSuccessfulFlag(false);
				form.setMessage("获取单据数据失败");
				return new ModelAndView("out/marketOutStock_list", "form", form);
			}
		}
		return new ModelAndView("out/marketOutStock_edit", "form", form);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		OutStockForm form = new OutStockForm();
		getPagerData(form,req,"3");
		baseExportExcel(req, res, "3", "营销出库单",form.getPager());
		return null;
	}

	public ModelAndView exportExcelLine(HttpServletRequest req, HttpServletResponse res) {
		OutStockForm form = new OutStockForm();
		getPagerData(form,req,"0");
		String headId = CommonUtil.getParameterNull(req, "billid");
		baseExportExcelLine(req, headId, "营销出库单",res);
		return null;
	}
}
